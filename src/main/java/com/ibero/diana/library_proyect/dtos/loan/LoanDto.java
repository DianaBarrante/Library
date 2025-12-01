package com.ibero.diana.library_proyect.dtos.loan;

import com.ibero.diana.library_proyect.dtos.book.ResponseBookDto;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDto {

    @NotNull(message = "Book is required")
    private ResponseBookDto book;

    @NotNull(message = "Start date is required")
    @FutureOrPresent(message = "Start date cannot be in the past")
    private Date dateStart;

    @NotNull(message = "End date is required")
    @FutureOrPresent(message = "End date cannot be in the past")
    private Date dateEnd;
}
