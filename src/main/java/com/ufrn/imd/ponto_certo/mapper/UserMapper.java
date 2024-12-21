package com.ufrn.imd.ponto_certo.mapper;

import com.ufrn.imd.ponto_certo.dto.request.UserCreateDTO;
import com.ufrn.imd.ponto_certo.dto.request.UserUpdateDTO;
import com.ufrn.imd.ponto_certo.dto.response.UserResponseDTO;
import com.ufrn.imd.ponto_certo.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserUpdateDTO userUpdateRequestDTO);
    User toEntity(UserCreateDTO userCreateRequestDTO);
    UserResponseDTO toDto(User user);
}
