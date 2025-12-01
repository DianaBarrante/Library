package com.ibero.diana.library_proyect.dtos.loan;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class LoanReturnRequestDto {
    @NotEmpty
    private List<LoanIdDto> loans;

    @Data
    public static class LoanIdDto {
        private Integer id;
    }
}