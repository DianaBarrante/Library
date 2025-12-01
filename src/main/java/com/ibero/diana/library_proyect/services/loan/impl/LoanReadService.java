package com.ibero.diana.library_proyect.services.loan.impl;

import com.ibero.diana.library_proyect.dtos.loan.LoanDto;
import com.ibero.diana.library_proyect.entities.Loan;
import com.ibero.diana.library_proyect.enums.loan.LoanState;
import com.ibero.diana.library_proyect.mapping.IMapper;
import com.ibero.diana.library_proyect.repositories.loan.LoanRepository;
import com.ibero.diana.library_proyect.services.loan.ILoanReadService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.TreeSet;


@Service
public class LoanReadService implements ILoanReadService {

    private final LoanRepository loanRepository;
    private final IMapper<Loan, LoanDto> loanMapper;

    public LoanReadService(LoanRepository loanRepository,
                           IMapper<Loan, LoanDto> loanMapper) {
        this.loanRepository = loanRepository;
        this.loanMapper = loanMapper;
    }

    @Override
    public Page<LoanDto> getAllLoans(Pageable pageable) {
        return loanRepository.findAll(pageable).map(this.loanMapper::map);
    }

    @Override
    public LoanDto getLoanById(int id) {
        return loanRepository.findById(id)
                .map(loanMapper::map)
                .orElseThrow(() -> new IllegalArgumentException("Préstamo no encontrado con id: " + id));
    }

    @Override
    public Page<LoanDto> getLoansByUser(int userId, Pageable pageable) {
        List<Loan> allLoans = loanRepository.findAll();

        TreeSet<Loan> loansByUser = new TreeSet<>(
                (a, b) -> {
                    int compare = Integer.compare(a.getUser().getId(), b.getUser().getId());
                    return (compare == 0) ? Integer.compare(a.getId(), b.getId()) : compare;
                });
        loansByUser.addAll(allLoans);
        List<LoanDto> filtered = loansByUser.stream()
                .filter(loan -> loan.getUser().getId() == userId)
                .map(loanMapper::map)
                .toList();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), filtered.size());
        List<LoanDto> paginated = filtered.subList(start, end);
        return new PageImpl<>(paginated, pageable, filtered.size());
    }

    @Override
    public Page<LoanDto> getLoansByBook(int bookId, Pageable pageable) {
        List<Loan> allLoans = loanRepository.findAll();
        TreeSet<Loan> loansByBook = new TreeSet<>(
                (a, b) -> {
                    int compare = Integer.compare(a.getBook().getId(), b.getBook().getId());
                    return (compare == 0) ? Integer.compare(a.getId(), b.getId()) : compare;
                });

        loansByBook.addAll(allLoans);
        List<LoanDto> filtered = loansByBook.stream()
                .filter(loan -> loan.getBook().getId() == bookId)
                .map(loanMapper::map)
                .toList();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), filtered.size());
        List<LoanDto> paginated = filtered.subList(start, end);

        return new PageImpl<>(paginated, pageable, filtered.size());
    }

    @Override
    public Page<LoanDto> getLoansByState(LoanState state, Pageable pageable) {
        List<Loan> allLoans = loanRepository.findAll();

        TreeSet<Loan> loansByState = new TreeSet<>(
                (a, b) -> {
                    int compare = a.getState().compareTo(b.getState());
                    return (compare == 0) ? a.getDateStart().compareTo(b.getDateStart()) : compare;
                });

        loansByState.addAll(allLoans);

        List<LoanDto> filtered = loansByState.stream()
                .filter(loan -> loan.getState() == state)
                .map(loanMapper::map)
                .toList();

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), filtered.size());
        List<LoanDto> paginated = filtered.subList(start, end);

        return new PageImpl<>(paginated, pageable, filtered.size());
    }
}
