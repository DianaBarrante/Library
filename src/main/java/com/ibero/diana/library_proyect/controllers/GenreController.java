package com.ibero.diana.library_proyect.controllers;

import com.ibero.diana.library_proyect.common.dtos.ApiResponse;
import com.ibero.diana.library_proyect.dtos.genre.ResponseGenreDto;
import com.ibero.diana.library_proyect.services.genre.IGenreReadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genre")
public class GenreController {

    private final IGenreReadService  genreReadService;

    public GenreController(IGenreReadService genreReadService) {
        this.genreReadService = genreReadService;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<ResponseGenreDto>>> getAllGenres() {
        List<ResponseGenreDto> genres = genreReadService.getGenres();
        ApiResponse<List<ResponseGenreDto>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Genres retrieved successfully",
                genres
        );
        return ResponseEntity.ok(response);
    }

}
