package com.ibero.diana.library_proyect.mapping.genre;

import com.ibero.diana.library_proyect.dtos.genre.ResponseGenreDto;
import com.ibero.diana.library_proyect.entities.Book;
import com.ibero.diana.library_proyect.entities.Genre;
import com.ibero.diana.library_proyect.mapping.IMapper;
import org.springframework.stereotype.Service;

@Service("EntityToResponseGenreMapper")
public class EntityToResponseGenreMapper implements IMapper<Genre, ResponseGenreDto> {


    @Override
    public ResponseGenreDto map(Genre input) {
        if (input == null) return null;
        ResponseGenreDto responseGenreDto = new ResponseGenreDto();
        if (input.getId() != null) responseGenreDto.setId(input.getId());
        responseGenreDto.setGenreName(input.getName());
        return responseGenreDto;
    }
}
