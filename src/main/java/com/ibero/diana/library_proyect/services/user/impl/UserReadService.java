package com.ibero.diana.library_proyect.services.user.impl;

import com.ibero.diana.library_proyect.dtos.user.UserResponseDto;
import com.ibero.diana.library_proyect.entities.User;
import com.ibero.diana.library_proyect.mapping.IMapper;
import com.ibero.diana.library_proyect.repositories.user.UserRepository;
import com.ibero.diana.library_proyect.services.user.IUserReadService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserReadService implements IUserReadService {

    private final IMapper<User, UserResponseDto> UserMapper;
    private final UserRepository userRepository;

    public UserReadService(@Qualifier("EntityToResponseUserMapper") IMapper<User, UserResponseDto>  UserMapper, UserRepository userRepository) {
        this.UserMapper = UserMapper;
        this.userRepository = userRepository;
    }


    @Override
    public Page<UserResponseDto> userGetAllByState(boolean state,Pageable pageable) {
        return this.userRepository.getUsersByStateIs(state,pageable).map(this.UserMapper::map);
    }
}
