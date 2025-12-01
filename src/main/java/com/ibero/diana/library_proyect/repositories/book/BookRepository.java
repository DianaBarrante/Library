package com.ibero.diana.library_proyect.repositories.book;

import com.ibero.diana.library_proyect.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    Page<Book> findByAvailable(boolean available, Pageable pageable);

    Page<Book> findByAuthor_Id(int authorId, Pageable pageable);

    Page<Book> findByGenre_Id(int genreId, Pageable pageable);

    Page<Book> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
