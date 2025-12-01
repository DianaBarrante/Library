package com.ibero.diana.library_proyect.services.user;

import com.ibero.diana.library_proyect.dtos.user.UserRequestDto;
import com.ibero.diana.library_proyect.dtos.user.UserResponseDto;

public interface IUserWriteService {

    UserResponseDto userCreate(UserRequestDto userRequestDto);
    UserResponseDto userUpdate(Integer userId,UserRequestDto userRequestDto);
    UserResponseDto userToggleStatus(Integer userId);
}
