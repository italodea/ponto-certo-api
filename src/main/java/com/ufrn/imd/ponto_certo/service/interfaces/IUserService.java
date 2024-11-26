package com.ufrn.imd.ponto_certo.service.interfaces;

import com.ufrn.imd.ponto_certo.dto.request.UserCreateRequestDTO;
import com.ufrn.imd.ponto_certo.dto.request.UserUpdateRequestDTO;
import com.ufrn.imd.ponto_certo.dto.response.UserResponseDTO;
import com.ufrn.imd.ponto_certo.model.User;

public interface IUserService {
    void delete(Long userId);

    UserResponseDTO update(UserUpdateRequestDTO dto, Long userId);

    UserResponseDTO save(UserCreateRequestDTO dto);

    User findById(Long userId);
}
