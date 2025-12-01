package com.ibero.diana.library_proyect.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private Integer id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String homeAddress;

}
