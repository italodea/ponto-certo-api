package com.ufrn.imd.ponto_certo.mapper;

import com.ufrn.imd.ponto_certo.dto.request.UserCreateRequestDTO;
import com.ufrn.imd.ponto_certo.dto.request.UserUpdateRequestDTO;
import com.ufrn.imd.ponto_certo.dto.response.UserResponseDTO;
import com.ufrn.imd.ponto_certo.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserUpdateRequestDTO userUpdateRequestDTO);
    User toEntity(UserCreateRequestDTO userCreateRequestDTO);
    UserResponseDTO toDto(User user);
}
