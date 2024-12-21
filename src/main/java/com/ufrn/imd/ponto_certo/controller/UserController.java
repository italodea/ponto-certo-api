package com.ufrn.imd.ponto_certo.controller;

import com.ufrn.imd.ponto_certo.dto.request.UserCreateDTO;
import com.ufrn.imd.ponto_certo.dto.request.UserUpdateDTO;
import com.ufrn.imd.ponto_certo.dto.response.ApiResponseDTO;
import com.ufrn.imd.ponto_certo.dto.response.UserResponseDTO;
import com.ufrn.imd.ponto_certo.service.UserService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<?>> delete(@PathVariable Long id) {
        userService.delete(id);
        ApiResponseDTO<?> response = new ApiResponseDTO<>(
                true,
                "Usuário removido com sucesso.",
                null,
                null);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<UserResponseDTO>> update(
            @PathVariable Long id,
            @RequestBody @Valid UserUpdateDTO dto) {

        ApiResponseDTO<UserResponseDTO> response = new ApiResponseDTO<>(
                true,
                "Usuário atualizado com sucesso.",
                userService.update(dto, id),
                null);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponseDTO<UserResponseDTO>> save(
            @Valid @RequestBody UserCreateDTO dto) {

        ApiResponseDTO<UserResponseDTO> response = new ApiResponseDTO<>(
                true,
                "Usuário criado com sucesso.",
                userService.save(dto),
                null);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
