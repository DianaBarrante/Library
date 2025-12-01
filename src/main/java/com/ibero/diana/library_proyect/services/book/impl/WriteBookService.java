package com.ibero.diana.library_proyect.services.book.impl;

import com.ibero.diana.library_proyect.dtos.author.ResponseAuthorDto;
import com.ibero.diana.library_proyect.dtos.book.RequestCreateBookDto;
import com.ibero.diana.library_proyect.dtos.book.ResponseBookDto;
import com.ibero.diana.library_proyect.entities.Book;
import com.ibero.diana.library_proyect.mapping.IMapper;
import com.ibero.diana.library_proyect.repositories.book.BookRepository;
import com.ibero.diana.library_proyect.services.book.IWriteBookService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class WriteBookService implements IWriteBookService {

    private final IMapper<Book, ResponseBookDto> bookMapper;
    private final BookRepository bookRepository;

    public WriteBookService(@Qualifier("EntityToResponseBookMapper")IMapper <Book, ResponseBookDto> bookMapper,BookRepository bookRepository) {
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public ResponseBookDto createBook(RequestCreateBookDto book) {
        Book newBook = new Book();
        newBook.setName(book.getName());
        newBook.setAuthor(book.getAuthor());
        newBook.setGenre(book.getGenre());
        newBook.setPublishDate(book.getPublishDate());
        newBook.setPrice(book.getPrice());
        return this.bookMapper.map(this.bookRepository.save(newBook));
    }
}
