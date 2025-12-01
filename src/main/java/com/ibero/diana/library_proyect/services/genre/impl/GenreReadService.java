package com.ibero.diana.library_proyect.services.genre.impl;

import com.ibero.diana.library_proyect.dtos.genre.ResponseGenreDto;
import com.ibero.diana.library_proyect.entities.Genre;
import com.ibero.diana.library_proyect.mapping.IMapper;
import com.ibero.diana.library_proyect.repositories.genre.GenreRepository;
import com.ibero.diana.library_proyect.services.genre.IGenreReadService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreReadService implements IGenreReadService {

    private final GenreRepository genreRepository;
    private final IMapper<Genre, ResponseGenreDto>  genreMapper;

    public GenreReadService(GenreRepository genreRepository,@Qualifier("EntityToResponseGenreMapper") IMapper<Genre, ResponseGenreDto> genreMapper) {
        this.genreRepository = genreRepository;
        this.genreMapper = genreMapper;
    }


    @Override
    public List<ResponseGenreDto> getGenres() {
        return this.genreRepository.findAll().stream().map(genreMapper::map).toList();
    }
}
