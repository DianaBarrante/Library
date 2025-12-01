package com.ibero.diana.library_proyect.services.book;

import com.ibero.diana.library_proyect.dtos.book.RequestCreateBookDto;
import com.ibero.diana.library_proyect.dtos.book.ResponseBookDto;

public interface IWriteBookService {

    public ResponseBookDto createBook(RequestCreateBookDto book);
}
