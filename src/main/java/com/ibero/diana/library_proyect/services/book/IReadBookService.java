package com.ibero.diana.library_proyect.services.book;

import com.ibero.diana.library_proyect.dtos.book.ResponseBookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface IReadBookService {
    Page<ResponseBookDto> getAllBooks(Pageable pageable);
    ResponseBookDto getBookById(int id);
    Page<ResponseBookDto> getBooksByAvailability(boolean available, Pageable pageable);
    Page<ResponseBookDto> getBooksByAuthor(int authorId, Pageable pageable);
    Page<ResponseBookDto> getBooksByGenre(int genreId, Pageable pageable);
    Page<ResponseBookDto> getBooksByPriceRange(int minPrice, int maxPrice, Pageable pageable);
    Page<ResponseBookDto> getBooksByPublishDateRange(Date startDate, Date endDate, Pageable pageable);
    Page<ResponseBookDto> searchBooksByName(String name, Pageable pageable);
}
