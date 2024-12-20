package com.ufrn.imd.ponto_certo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufrn.imd.ponto_certo.dto.request.FuncionarioCreateDTO;
import com.ufrn.imd.ponto_certo.dto.request.FuncionarioUpdateDTO;
import com.ufrn.imd.ponto_certo.dto.response.ApiResponseDTO;
import com.ufrn.imd.ponto_certo.dto.response.FuncionarioResponseDTO;
import com.ufrn.imd.ponto_certo.service.FuncionarioService;
import com.ufrn.imd.ponto_certo.service.JwtService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/funcionario")
public class FuncionarioController {
    private final FuncionarioService service;
    private final JwtService jwtService;

    @GetMapping()
    public ResponseEntity<ApiResponseDTO<FuncionarioResponseDTO>> find() {
        Long userIdToken = jwtService.extractUserIdFromRequest();
        FuncionarioResponseDTO funcionario = service.findByUserId(userIdToken);
        ApiResponseDTO<FuncionarioResponseDTO> response = new ApiResponseDTO<>(
                true,
                "Funcionario encontrado com sucesso.",
                funcionario,
                null);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<FuncionarioResponseDTO>> findById(@PathVariable Long id) {
        Long userIdToken = jwtService.extractUserIdFromRequest();
        FuncionarioResponseDTO funcionario = service.findById(id, userIdToken);
        ApiResponseDTO<FuncionarioResponseDTO> response = new ApiResponseDTO<>(
                true,
                "Funcionario encontrado com sucesso.",
                funcionario,
                null);

        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<ApiResponseDTO<FuncionarioResponseDTO>> create(
            @RequestBody FuncionarioCreateDTO data) {
        Long userIdToken = jwtService.extractUserIdFromRequest();
        data = data.withUserId(userIdToken);
        FuncionarioResponseDTO funcionario = service.create(data);
        ApiResponseDTO<FuncionarioResponseDTO> response = new ApiResponseDTO<>(
                true,
                "Funcionario criado com sucesso.",
                funcionario,
                null);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<FuncionarioResponseDTO>> update(
            @RequestBody FuncionarioUpdateDTO body,
            @PathVariable Long id) {
        FuncionarioResponseDTO funcionario = service.update(body, id);
        ApiResponseDTO<FuncionarioResponseDTO> response = new ApiResponseDTO<>(
                true,
                "Funcionario atualizado com sucesso.",
                funcionario,
                null);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Void>> deleteById(@PathVariable Long id) {
        Long userIdToken = jwtService.extractUserIdFromRequest();
        service.deleteById(id, userIdToken);
        ApiResponseDTO<Void> response = new ApiResponseDTO<>(
                true,
                "Funcionario deletado com sucesso.",
                null,
                null);

        return ResponseEntity.ok(response);
    }
}
