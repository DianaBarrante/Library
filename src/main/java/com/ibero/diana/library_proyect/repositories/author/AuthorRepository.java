package com.ibero.diana.library_proyect.repositories.author;

import com.ibero.diana.library_proyect.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
