package com.ibero.diana.library_proyect.services.loan;

import com.ibero.diana.library_proyect.dtos.loan.LoanDto;
import com.ibero.diana.library_proyect.enums.loan.LoanState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ILoanReadService {
    Page<LoanDto> getAllLoans(Pageable pageable);

    LoanDto getLoanById(int id);

    Page<LoanDto> getLoansByUser(int userId, Pageable pageable);

    Page<LoanDto> getLoansByBook(int bookId, Pageable pageable);

    Page<LoanDto> getLoansByState(LoanState state, Pageable pageable);
}
