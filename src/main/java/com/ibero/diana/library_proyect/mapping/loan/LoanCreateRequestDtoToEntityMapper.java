package com.ibero.diana.library_proyect.mapping.loan;

import com.ibero.diana.library_proyect.dtos.loan.LoanCreateRequestDto;
import com.ibero.diana.library_proyect.entities.Book;
import com.ibero.diana.library_proyect.entities.Loan;
import com.ibero.diana.library_proyect.mapping.IMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("LoanCreateRequestDtoToEntityMapper")
public class LoanCreateRequestDtoToEntityMapper implements IMapper<LoanCreateRequestDto, List<Loan>> {



    @Override
    public List<Loan> map(LoanCreateRequestDto input) {
        if (input == null) return null;

        return input.getLoans().stream().map(loanDto -> {
            Loan loan = new Loan();
            loan.setUser(input.getUser());

            Book book = new Book();
            book.setId(loanDto.getBook().getId());
            loan.setBook(book);

            loan.setDateStart(loanDto.getDateStart());
            loan.setDateEnd(loanDto.getDateEnd());
            return loan;
        }).toList();



    }
}
