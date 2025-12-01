package com.ibero.diana.library_proyect.controllers;

import com.ibero.diana.library_proyect.common.dtos.ApiResponse;
import com.ibero.diana.library_proyect.dtos.book.RequestCreateBookDto;
import com.ibero.diana.library_proyect.dtos.book.ResponseBookDto;
import com.ibero.diana.library_proyect.services.book.IReadBookService;
import com.ibero.diana.library_proyect.services.book.IWriteBookService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/book")
public class BookController {

    private final IWriteBookService writeBookService;
    private final IReadBookService readBookService;

    public BookController(IWriteBookService writeBookService, IReadBookService readBookService) {
        this.writeBookService = writeBookService;
        this.readBookService = readBookService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ResponseBookDto>> createBook(
            @Valid @RequestBody RequestCreateBookDto book) {
        ResponseBookDto createdBook = writeBookService.createBook(book);
        ApiResponse<ResponseBookDto> response = new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Book created successfully",
                createdBook
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    // ----------------- GET -----------------

    @GetMapping
    public ResponseEntity<ApiResponse<Page<ResponseBookDto>>> getAllBooks(Pageable pageable) {
        Page<ResponseBookDto> books = readBookService.getAllBooks(pageable);

        ApiResponse<Page<ResponseBookDto>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Books retrieved successfully",
                books
        );

        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ResponseBookDto>> getBookById(@PathVariable int id) {
        ResponseBookDto book = readBookService.getBookById(id);
        ApiResponse<ResponseBookDto> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Book retrieved successfully",
                book
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/availability/{available}")
    public ResponseEntity<ApiResponse<Page<ResponseBookDto>>> getBooksByAvailability(
            @PathVariable boolean available,
            Pageable pageable) {

        Page<ResponseBookDto> books = readBookService.getBooksByAvailability(available, pageable);
        ApiResponse<Page<ResponseBookDto>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Books retrieved successfully by availability",
                books
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<ApiResponse<Page<ResponseBookDto>>> getBooksByAuthor(
            @PathVariable int authorId,
            Pageable pageable) {

        Page<ResponseBookDto> books = readBookService.getBooksByAuthor(authorId, pageable);
        ApiResponse<Page<ResponseBookDto>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Books retrieved successfully by author",
                books
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/genre/{genreId}")
    public ResponseEntity<ApiResponse<Page<ResponseBookDto>>> getBooksByGenre(
            @PathVariable int genreId,
            Pageable pageable) {

        Page<ResponseBookDto> books = readBookService.getBooksByGenre(genreId, pageable);
        ApiResponse<Page<ResponseBookDto>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Books retrieved successfully by genre",
                books
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/price")
    public ResponseEntity<ApiResponse<Page<ResponseBookDto>>> getBooksByPriceRange(
            @RequestParam int min,
            @RequestParam int max,
            Pageable pageable) {

        Page<ResponseBookDto> books = readBookService.getBooksByPriceRange(min, max, pageable);
        ApiResponse<Page<ResponseBookDto>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Books retrieved successfully by price range",
                books
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/publish-date")
    public ResponseEntity<ApiResponse<Page<ResponseBookDto>>> getBooksByPublishDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date end,
            Pageable pageable) {

        Page<ResponseBookDto> books = readBookService.getBooksByPublishDateRange(start, end, pageable);
        ApiResponse<Page<ResponseBookDto>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Books retrieved successfully by publish date range",
                books
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<ResponseBookDto>>> searchBooksByName(
            @RequestParam String name,
            Pageable pageable) {

        Page<ResponseBookDto> books = readBookService.searchBooksByName(name, pageable);
        ApiResponse<Page<ResponseBookDto>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Books retrieved successfully by search",
                books
        );
        return ResponseEntity.ok(response);
    }



}
