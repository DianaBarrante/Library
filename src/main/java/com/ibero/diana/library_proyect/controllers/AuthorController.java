package com.ibero.diana.library_proyect.controllers;

import com.ibero.diana.library_proyect.common.dtos.ApiResponse;
import com.ibero.diana.library_proyect.dtos.author.ResponseAuthorDto;
import com.ibero.diana.library_proyect.services.author.IAuthorReadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final IAuthorReadService authorReadService;
    public AuthorController(IAuthorReadService authorReadService) {
        this.authorReadService = authorReadService;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<ResponseAuthorDto>>> getAllGenres() {
        List<ResponseAuthorDto> authors = authorReadService.getAuthors();
        ApiResponse<List<ResponseAuthorDto>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Authors fetched successfully",
                authors
        );
        return ResponseEntity.ok(response);
    }

}
