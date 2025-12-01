package com.ibero.diana.library_proyect.services.author;

import com.ibero.diana.library_proyect.dtos.author.ResponseAuthorDto;

import java.util.List;

public interface IAuthorReadService {

    List<ResponseAuthorDto> getAuthors();
}
