package com.ibero.diana.library_proyect.services.author.impl;

import com.ibero.diana.library_proyect.dtos.author.ResponseAuthorDto;
import com.ibero.diana.library_proyect.entities.Author;
import com.ibero.diana.library_proyect.mapping.IMapper;
import com.ibero.diana.library_proyect.repositories.author.AuthorRepository;
import com.ibero.diana.library_proyect.services.author.IAuthorReadService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AuthorReadService implements IAuthorReadService {

    private final IMapper<Author, ResponseAuthorDto> authorMapper;
    private final AuthorRepository authorRepository;

    public AuthorReadService(AuthorRepository authorRepository,@Qualifier("EntityToResponseAuthorMapper") IMapper<Author, ResponseAuthorDto> authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }


    @Override
    public List<ResponseAuthorDto> getAuthors() {
        return  authorRepository.findAll().stream().map(authorMapper::map).toList();
    }
}
