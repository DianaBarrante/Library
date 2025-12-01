package com.ibero.diana.library_proyect.services.book.impl;

import com.ibero.diana.library_proyect.dtos.book.ResponseBookDto;
import com.ibero.diana.library_proyect.entities.Book;
import com.ibero.diana.library_proyect.mapping.IMapper;
import com.ibero.diana.library_proyect.repositories.book.BookRepository;
import com.ibero.diana.library_proyect.services.book.IReadBookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.TreeSet;

@Service
public class ReadBookService implements IReadBookService {


    private final BookRepository bookRepository;
    private final IMapper<Book, ResponseBookDto> bookMapper;

    public ReadBookService(BookRepository bookRepository,
                           IMapper<Book, ResponseBookDto> bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }


    @Override
    public Page<ResponseBookDto> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable).map(bookMapper::map);
    }

    @Override
    public ResponseBookDto getBookById(int id) {
        return bookRepository.findById(id)
                .map(bookMapper::map)
                .orElseThrow(() -> new IllegalArgumentException("Libro no encontrado con id: " + id));
    }

    @Override
    public Page<ResponseBookDto> getBooksByAvailability(boolean available, Pageable pageable) {
        return bookRepository.findByAvailable(available, pageable).map(bookMapper::map);
    }

    @Override
    public Page<ResponseBookDto> getBooksByAuthor(int authorId, Pageable pageable) {
        return bookRepository.findByAuthor_Id(authorId, pageable).map(bookMapper::map);
    }

    @Override
    public Page<ResponseBookDto> getBooksByGenre(int genreId, Pageable pageable) {
        return bookRepository.findByGenre_Id(genreId, pageable).map(bookMapper::map);
    }

    @Override
    public Page<ResponseBookDto> getBooksByPriceRange(int minPrice, int maxPrice, Pageable pageable) {
        List<Book> allBooks = bookRepository.findAll();

        TreeSet<Book> treeByPrice = new TreeSet<>(
                (a, b) -> {
                    int compare = Integer.compare(a.getPrice(), b.getPrice());
                    return (compare == 0) ? Integer.compare(a.getId(), b.getId()) : compare;
                });

        treeByPrice.addAll(allBooks);

        List<ResponseBookDto> filtered = treeByPrice.stream()
                .filter(book -> book.getPrice() >= minPrice && book.getPrice() <= maxPrice)
                .map(bookMapper::map)
                .toList();
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), filtered.size());
        List<ResponseBookDto> paginated = filtered.subList(start, end);
        return new PageImpl<>(paginated, pageable, filtered.size());
    }

    @Override
    public Page<ResponseBookDto> getBooksByPublishDateRange(Date startDate, Date endDate, Pageable pageable) {
        List<Book> allBooks = bookRepository.findAll();
        TreeSet<Book> treeByDate = new TreeSet<>(
                (a, b) -> {
                    int compare = a.getPublishDate().compareTo(b.getPublishDate());
                    return (compare == 0) ? Integer.compare(a.getId(), b.getId()) : compare;
                });

        treeByDate.addAll(allBooks);
        List<ResponseBookDto> filtered = treeByDate.stream()
                .filter(book -> !book.getPublishDate().before(startDate) && !book.getPublishDate().after(endDate))
                .map(bookMapper::map)
                .toList();
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), filtered.size());
        List<ResponseBookDto> paginated = filtered.subList(start, end);
        return new PageImpl<>(paginated, pageable, filtered.size());
    }

    @Override
    public Page<ResponseBookDto> searchBooksByName(String name, Pageable pageable) {
        return bookRepository.findByNameContainingIgnoreCase(name, pageable).map(bookMapper::map);
    }
}
