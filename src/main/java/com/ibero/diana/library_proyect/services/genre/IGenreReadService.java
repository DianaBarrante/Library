package com.ibero.diana.library_proyect.services.genre;

import com.ibero.diana.library_proyect.dtos.genre.ResponseGenreDto;

import java.util.List;

public interface IGenreReadService {

    List<ResponseGenreDto> getGenres();
}
