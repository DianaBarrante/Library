package com.ibero.diana.library_proyect.services.user;

import com.ibero.diana.library_proyect.dtos.user.UserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserReadService {
    Page<UserResponseDto> userGetAllByState(boolean state,Pageable pageable);
}
