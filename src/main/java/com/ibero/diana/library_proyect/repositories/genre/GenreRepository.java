package com.ibero.diana.library_proyect.repositories.genre;

import com.ibero.diana.library_proyect.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository  extends JpaRepository<Genre, Integer> {
}
