package com.ibero.diana.library_proyect.dtos.genre;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGenreDto {
    private Integer id;
    private String genreName;
}
