package com.ibero.diana.library_proyect.controllers;

import com.ibero.diana.library_proyect.common.dtos.ApiResponse;
import com.ibero.diana.library_proyect.dtos.user.UserRequestDto;
import com.ibero.diana.library_proyect.dtos.user.UserResponseDto;
import com.ibero.diana.library_proyect.services.user.IUserReadService;
import com.ibero.diana.library_proyect.services.user.IUserWriteService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserWriteService  userWriteService;
    private final IUserReadService userReadService;

    public UserController(IUserWriteService userWriteService, IUserReadService userReadService) {
        this.userWriteService = userWriteService;
        this.userReadService = userReadService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<UserResponseDto>> create(
            @Valid @RequestBody UserRequestDto userRequestDto) {
        UserResponseDto user = userWriteService.userCreate(userRequestDto);
        ApiResponse<UserResponseDto> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "User created successfully",
                user
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<ApiResponse<UserResponseDto>> update(
            @Valid @RequestBody UserRequestDto userRequestDto,
            @PathVariable Integer userId) {
        UserResponseDto user = userWriteService.userUpdate(userId, userRequestDto);
        ApiResponse<UserResponseDto> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "User updated successfully",
                user
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/toggleStatus/{userId}")
    public ResponseEntity<ApiResponse<UserResponseDto>> toggleStatus(@PathVariable Integer userId) {
        UserResponseDto user = userWriteService.userToggleStatus(userId);
        ApiResponse<UserResponseDto> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "User status toggled successfully",
                user
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<Page<UserResponseDto>>> getAllUsers(
            @RequestParam boolean state,
            @RequestParam int page,
            @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserResponseDto> users = userReadService.userGetAllByState(state, pageable);
        ApiResponse<Page<UserResponseDto>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Users retrieved successfully",
                users
        );
        return ResponseEntity.ok(response);
    }

}
