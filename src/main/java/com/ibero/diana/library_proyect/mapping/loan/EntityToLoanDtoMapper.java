package com.ibero.diana.library_proyect.mapping.loan;

import com.ibero.diana.library_proyect.dtos.book.ResponseBookDto;
import com.ibero.diana.library_proyect.dtos.loan.LoanDto;
import com.ibero.diana.library_proyect.entities.Book;
import com.ibero.diana.library_proyect.entities.Loan;
import com.ibero.diana.library_proyect.mapping.IMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("EntityToLoanDtoMapper")
public class EntityToLoanDtoMapper implements IMapper<Loan, LoanDto> {

    private final IMapper<Book, ResponseBookDto> bookMapper;

    public EntityToLoanDtoMapper(@Qualifier("EntityToResponseBookMapper")IMapper<Book, ResponseBookDto> bookMapper) {
        this.bookMapper = bookMapper;
    }


    @Override
    public LoanDto map(Loan input) {
        if (input == null) return null;
        LoanDto loanDto = new LoanDto();
        loanDto.setBook(bookMapper.map(input.getBook()));
        loanDto.setDateStart(input.getDateStart());
        loanDto.setDateEnd(input.getDateEnd());
        return loanDto;
    }
}
