package com.ibero.diana.library_proyect.dtos.loan;

import com.ibero.diana.library_proyect.entities.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanCreateRequestDto {

    @NotNull(message = "User is required")
    private User user;

    @NotEmpty(message = "At least one loan is required")
    @Valid
    private List<LoanDto> loans;
}
