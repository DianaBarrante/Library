package com.ibero.diana.library_proyect.controllers;

import com.ibero.diana.library_proyect.common.dtos.ApiResponse;
import com.ibero.diana.library_proyect.dtos.loan.LoanCreateRequestDto;
import com.ibero.diana.library_proyect.dtos.loan.LoanDto;
import com.ibero.diana.library_proyect.dtos.loan.LoanReturnRequestDto;
import com.ibero.diana.library_proyect.services.loan.ILoanWriteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {

    private final ILoanWriteService loanWriteService;

    public LoanController(ILoanWriteService loanWriteService) {
        this.loanWriteService = loanWriteService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<List<LoanDto>>> createLoan(
            @Valid @RequestBody LoanCreateRequestDto loanCreateRequestDto) {

        List<LoanDto> loans = loanWriteService.createLoan(loanCreateRequestDto);
        ApiResponse<List<LoanDto>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Loans created successfully",
                loans
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/return")
    public ResponseEntity<ApiResponse<List<LoanDto>>> returnLoan(
            @Valid @RequestBody LoanReturnRequestDto loanReturnRequestDto) {

        List<LoanDto> loans = loanWriteService.returnLoan(loanReturnRequestDto);
        ApiResponse<List<LoanDto>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Loans returned successfully",
                loans
        );
        return ResponseEntity.ok(response);
    }

}
