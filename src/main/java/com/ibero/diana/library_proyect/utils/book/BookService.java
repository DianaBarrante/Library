package com.ibero.diana.library_proyect.utils.book;

import com.ibero.diana.library_proyect.entities.Book;
import com.ibero.diana.library_proyect.repositories.book.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Valida que todos los libros existan y estén disponibles.
     *
     * @param bookIds lista de IDs de libros
     * @return lista de libros encontrados
     * @throws IllegalArgumentException si algún libro no existe
     * @throws IllegalArgumentException si algún libro no está disponible
     */
    public List<Book> validateAndGetAvailableBooks(List<Integer> bookIds) {
        List<Book> books = bookRepository.findAllById(bookIds);

        if (books.size() != bookIds.size()) {
            throw new IllegalArgumentException("Uno o más libros no existen en la base de datos.");
        }

        books.forEach(book -> {
            if (!book.getAvailable()) {
                throw new IllegalArgumentException("El libro con id " + book.getId() + " no está disponible.");
            }
        });

        return books;
    }

    /**
     * Marca los libros como no disponibles y los persiste en la base de datos.
     *
     * @param books lista de libros a actualizar
     */
    public void markAsUnavailable(List<Book> books) {
        books.forEach(book -> book.setAvailable(false));
        bookRepository.saveAll(books);
    }

    /**
     * Marca los libros como disponibles nuevamente (si devuelven el préstamo).
     *
     * @param books lista de libros a actualizar
     */
    public void markAsAvailable(List<Book> books) {
        books.forEach(book -> book.setAvailable(true));
        bookRepository.saveAll(books);
    }
}
