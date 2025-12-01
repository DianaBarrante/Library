package com.ibero.diana.library_proyect.services.loan.impl;

import com.ibero.diana.library_proyect.dtos.loan.LoanCreateRequestDto;
import com.ibero.diana.library_proyect.dtos.loan.LoanDto;
import com.ibero.diana.library_proyect.dtos.loan.LoanReturnRequestDto;
import com.ibero.diana.library_proyect.entities.Book;
import com.ibero.diana.library_proyect.entities.Loan;
import com.ibero.diana.library_proyect.enums.loan.LoanState;
import com.ibero.diana.library_proyect.mapping.IMapper;
import com.ibero.diana.library_proyect.repositories.loan.LoanRepository;
import com.ibero.diana.library_proyect.services.loan.ILoanWriteService;
import com.ibero.diana.library_proyect.utils.book.BookService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanWriteService implements ILoanWriteService {

    private final LoanRepository loanRepository;
    private final IMapper<Loan, LoanDto> loanMapper;
    private final IMapper<LoanCreateRequestDto, List<Loan>> LoanCreateRequestDtoToEntityMapper;
    private final BookService bookService;

    public LoanWriteService(@Qualifier("EntityToLoanDtoMapper") IMapper<Loan, LoanDto> loanMapper,
                            LoanRepository loanRepository,
                            @Qualifier("LoanCreateRequestDtoToEntityMapper") IMapper<LoanCreateRequestDto, List<Loan>> LoanCreateRequestDtoToEntityMapper,
                            BookService bookService) {
        this.loanMapper = loanMapper;
        this.loanRepository = loanRepository;
        this.LoanCreateRequestDtoToEntityMapper = LoanCreateRequestDtoToEntityMapper;
        this.bookService = bookService;
    }

    @Transactional
    @Override
    public List<LoanDto> createLoan(LoanCreateRequestDto loanCreateRequestDto) {
        List<Loan> loans = this.LoanCreateRequestDtoToEntityMapper.map(loanCreateRequestDto);

        List<Integer> bookIds = loans.stream()
                .map(l -> l.getBook().getId())
                .toList();

        List<Book> books = bookService.validateAndGetAvailableBooks(bookIds);

        List<Loan> savedLoans = loanRepository.saveAll(loans);

        bookService.markAsUnavailable(books);

        return savedLoans.stream()
                .map(this.loanMapper::map)
                .toList();
    }

    @Transactional
    @Override
    public List<LoanDto> returnLoan(LoanReturnRequestDto loanReturnRequestDto) {
        List<Integer> loanIds = extractLoanIds(loanReturnRequestDto);
        List<Loan> updatedLoans = updateLoanStates(loanIds);
        List<Book> books = updatedLoans.stream()
                .map(Loan::getBook)
                .toList();
        bookService.markAsAvailable(books);

        return updatedLoans.stream()
                .map(loanMapper::map)
                .toList();
    }


    private List<Integer> extractLoanIds(LoanReturnRequestDto request) {
        return request.getLoans().stream()
                .map(LoanReturnRequestDto.LoanIdDto::getId)
                .toList();
    }

    private List<Loan> updateLoanStates(List<Integer> loanIds) {
        List<Loan> loans = loanRepository.findAllById(loanIds);

        if (loans.size() != loanIds.size()) {
            throw new IllegalArgumentException("Uno o más préstamos no existen en la base de datos.");
        }

        loans.forEach(loan -> {
            if (loan.getState() == LoanState.RETURNED) {
                throw new IllegalStateException("El préstamo con id " + loan.getId() + " ya fue devuelto.");
            }
            loan.setState(LoanState.RETURNED);
        });

        return loanRepository.saveAll(loans);
    }


}

