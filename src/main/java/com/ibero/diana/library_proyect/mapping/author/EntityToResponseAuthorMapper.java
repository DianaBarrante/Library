package com.ibero.diana.library_proyect.mapping.author;

import com.ibero.diana.library_proyect.dtos.author.ResponseAuthorDto;
import com.ibero.diana.library_proyect.entities.Author;
import com.ibero.diana.library_proyect.mapping.IMapper;
import org.springframework.stereotype.Service;

@Service("EntityToResponseAuthorMapper")
public class EntityToResponseAuthorMapper implements IMapper<Author, ResponseAuthorDto> {


    @Override
    public ResponseAuthorDto map(Author input) {
        if (input == null) return null;
        ResponseAuthorDto responseAuthorDto = new ResponseAuthorDto();
        if (input.getId() != null) responseAuthorDto.setId(input.getId());
        responseAuthorDto.setName(input.getName());
        return responseAuthorDto;
    }
}
