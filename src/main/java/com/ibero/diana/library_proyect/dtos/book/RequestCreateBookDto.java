package com.ibero.diana.library_proyect.dtos.book;

import com.ibero.diana.library_proyect.entities.Author;
import com.ibero.diana.library_proyect.entities.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestCreateBookDto {
    @NotNull
    @NotBlank
    @Size(min = 1, max = 100)
    private String name;
    @NotNull
    private Author author;
    private Genre genre;
    @NotNull
    private Date publishDate;
    @NotNull
    private int price;
}
