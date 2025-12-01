package com.ibero.diana.library_proyect.controllers;

import com.ibero.diana.library_proyect.common.dtos.ApiResponse;
import com.ibero.diana.library_proyect.dtos.loan.LoanDto;
import com.ibero.diana.library_proyect.enums.loan.LoanState;
import com.ibero.diana.library_proyect.services.loan.ILoanReadService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loan")
public class LoanReadController {

    private final ILoanReadService readLoanService;

    public LoanReadController(ILoanReadService readLoanService) {
        this.readLoanService = readLoanService;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<Page<LoanDto>>> getAllLoans(Pageable pageable) {
        Page<LoanDto> loans = readLoanService.getAllLoans(pageable);
        ApiResponse<Page<LoanDto>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "All loans retrieved successfully",
                loans
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<LoanDto>> getLoanById(@PathVariable int id) {
        LoanDto loan = readLoanService.getLoanById(id);
        ApiResponse<LoanDto> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Loan retrieved successfully",
                loan
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<Page<LoanDto>>> getLoansByUser(
            @PathVariable int userId, Pageable pageable) {
        Page<LoanDto> loans = readLoanService.getLoansByUser(userId, pageable);
        ApiResponse<Page<LoanDto>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Loans for user retrieved successfully",
                loans
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<ApiResponse<Page<LoanDto>>> getLoansByBook(
            @PathVariable int bookId, Pageable pageable) {
        Page<LoanDto> loans = readLoanService.getLoansByBook(bookId, pageable);
        ApiResponse<Page<LoanDto>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Loans for book retrieved successfully",
                loans
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/state")
    public ResponseEntity<ApiResponse<Page<LoanDto>>> getLoansByState(
            @RequestParam String state, Pageable pageable) {

        LoanState stateEnum;
        try {
            stateEnum = LoanState.valueOf(state.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Estado inválido: " + state);
        }

        Page<LoanDto> loans = readLoanService.getLoansByState(stateEnum, pageable);
        ApiResponse<Page<LoanDto>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Loans with state '" + state + "' retrieved successfully",
                loans
        );
        return ResponseEntity.ok(response);
    }

}
