package com.ibero.diana.library_proyect.services.user.impl;

import com.ibero.diana.library_proyect.dtos.user.UserRequestDto;
import com.ibero.diana.library_proyect.dtos.user.UserResponseDto;
import com.ibero.diana.library_proyect.entities.User;
import com.ibero.diana.library_proyect.mapping.IMapper;
import com.ibero.diana.library_proyect.repositories.user.UserRepository;
import com.ibero.diana.library_proyect.services.user.IUserWriteService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserWriteService  implements IUserWriteService {

    private final IMapper<User, UserResponseDto> UserMapper;
    private final UserRepository userRepository;

    public UserWriteService(@Qualifier("EntityToResponseUserMapper") IMapper<User, UserResponseDto>  UserMapper,UserRepository userRepository) {
        this.UserMapper = UserMapper;
        this.userRepository = userRepository;
    }


    @Transactional
    @Override
    public UserResponseDto userCreate(UserRequestDto userRequestDto) {
        User user = new User();
        user.setFullName(userRequestDto.getFullName());
        user.setEmail(userRequestDto.getEmail());
        user.setPhoneNumber(String.valueOf(userRequestDto.getPhoneNumber()));
        user.setHomeAddress(userRequestDto.getHomeAddress());
        return this.UserMapper.map(this.userRepository.save(user));
    }

    @Transactional
    @Override
    public UserResponseDto userUpdate(Integer userId,UserRequestDto userRequestDto) {
        User userDb = this.userRepository.findById(userId).orElse(null);
        if(userDb == null){
            return null;
        }
        userDb.setFullName(userRequestDto.getFullName());
        userDb.setEmail(userRequestDto.getEmail());
        userDb.setPhoneNumber(userRequestDto.getPhoneNumber());
        userDb.setHomeAddress(userRequestDto.getHomeAddress());
        return this.UserMapper.map(this.userRepository.save(userDb));
    }

    @Override
    public UserResponseDto userToggleStatus(Integer userId) {
        User userDb = this.userRepository.findById(userId).orElse(null);
        if(userDb == null){
            return null;
        }
        userDb.setState(!userDb.isState());
        return this.UserMapper.map(this.userRepository.save(userDb));
    }
}
