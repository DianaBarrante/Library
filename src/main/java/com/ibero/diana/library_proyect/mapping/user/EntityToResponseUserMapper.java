package com.ibero.diana.library_proyect.mapping.user;

import com.ibero.diana.library_proyect.dtos.user.UserResponseDto;
import com.ibero.diana.library_proyect.entities.User;
import com.ibero.diana.library_proyect.mapping.IMapper;
import org.springframework.stereotype.Service;

@Service("EntityToResponseUserMapper")
public class EntityToResponseUserMapper implements IMapper<User, UserResponseDto> {


    @Override
    public UserResponseDto map(User input) {
        if (input == null) return null;
        UserResponseDto output = new UserResponseDto();
        output.setId(input.getId());
        output.setFullName(input.getFullName());
        output.setEmail(input.getEmail());
        output.setHomeAddress(input.getHomeAddress());
        return output;
    }
}
