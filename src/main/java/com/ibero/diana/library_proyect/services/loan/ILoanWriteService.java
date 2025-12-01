package com.ibero.diana.library_proyect.services.loan;


import com.ibero.diana.library_proyect.dtos.loan.LoanCreateRequestDto;
import com.ibero.diana.library_proyect.dtos.loan.LoanDto;
import com.ibero.diana.library_proyect.dtos.loan.LoanReturnRequestDto;

import java.util.List;

public interface ILoanWriteService {

    List<LoanDto> createLoan(LoanCreateRequestDto loanCreateRequestDto);
    List<LoanDto> returnLoan(LoanReturnRequestDto loanReturnRequestDto);

}
