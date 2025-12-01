package com.ibero.diana.library_proyect.mapping.book;


import com.ibero.diana.library_proyect.dtos.author.ResponseAuthorDto;
import com.ibero.diana.library_proyect.dtos.book.ResponseBookDto;
import com.ibero.diana.library_proyect.dtos.genre.ResponseGenreDto;
import com.ibero.diana.library_proyect.entities.Author;
import com.ibero.diana.library_proyect.entities.Book;
import com.ibero.diana.library_proyect.entities.Genre;
import com.ibero.diana.library_proyect.mapping.IMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("EntityToResponseBookMapper")
public class EntityToResponseBookMapper implements IMapper<Book, ResponseBookDto> {

    private final IMapper<Author, ResponseAuthorDto> authorMapper;
    private final IMapper<Genre, ResponseGenreDto> genreMapper;
    public EntityToResponseBookMapper(@Qualifier("EntityToResponseAuthorMapper") IMapper<Author, ResponseAuthorDto> authorMapper,
                                      @Qualifier("EntityToResponseGenreMapper") IMapper<Genre, ResponseGenreDto> genreMapper) {
        this.authorMapper = authorMapper;
        this.genreMapper = genreMapper;
    }

    public ResponseBookDto map (Book book) {
        if (book == null) return null;
        ResponseBookDto responseBookDto = new ResponseBookDto();
        responseBookDto.setId(book.getId());
        responseBookDto.setName(book.getName());
        responseBookDto.setAuthor(authorMapper.map(book.getAuthor()));
        responseBookDto.setGenre(genreMapper.map(book.getGenre()));
        responseBookDto.setPublishDate(book.getPublishDate());
        responseBookDto.setPrice(book.getPrice());
        return responseBookDto;
    }
}
