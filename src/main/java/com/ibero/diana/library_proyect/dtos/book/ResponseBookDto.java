package com.ibero.diana.library_proyect.dtos.book;

import com.ibero.diana.library_proyect.dtos.author.ResponseAuthorDto;
import com.ibero.diana.library_proyect.dtos.genre.ResponseGenreDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBookDto {
    private int id;
    private String name;
    private ResponseAuthorDto author;
    private ResponseGenreDto genre;
    private Date publishDate;
    private int price;
}
