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

import com.ufrn.imd.ponto_certo.dto.request.EmpresaCreateRequestDTO;
import com.ufrn.imd.ponto_certo.dto.request.EmpresaUpdateRequestDTO;
import com.ufrn.imd.ponto_certo.dto.response.ApiResponseDTO;
import com.ufrn.imd.ponto_certo.dto.response.EmpresaResponseDTO;
import com.ufrn.imd.ponto_certo.service.EmpresaService;
import com.ufrn.imd.ponto_certo.service.JwtService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/empresa")
@AllArgsConstructor
public class EmpresaController {
    
    private EmpresaService service;
    private JwtService jwtService;

    @GetMapping
    public ResponseEntity<ApiResponseDTO<EmpresaResponseDTO>> find() {
        Long userIdToken = jwtService.extractUserIdFromRequest();
        EmpresaResponseDTO empresa = service.findByUserId(userIdToken);
        ApiResponseDTO<EmpresaResponseDTO> response = new ApiResponseDTO<>(
            true,
            "Empresa encontrada com sucesso.",
            empresa,
            null
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<EmpresaResponseDTO>> findById(@PathVariable Long id) {
        EmpresaResponseDTO empresa = service.findById(id);
        ApiResponseDTO<EmpresaResponseDTO> response = new ApiResponseDTO<>(
            true,
            "Empresa encontrada com sucesso.",
            empresa,
            null
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponseDTO<EmpresaResponseDTO>> create(@RequestBody EmpresaCreateRequestDTO data) {
        Long userIdToken = jwtService.extractUserIdFromRequest();
        data = data.withOwnerId(userIdToken);
        EmpresaResponseDTO empresa = service.create(data);
        ApiResponseDTO<EmpresaResponseDTO> response = new ApiResponseDTO<>(
            true,
            "Empresa criada com sucesso.",
            empresa,
            null
        );

        return ResponseEntity.ok(response);
    }

    @PatchMapping
    public ResponseEntity<ApiResponseDTO<EmpresaResponseDTO>> update(@RequestBody EmpresaUpdateRequestDTO data) {
        Long userIdToken = jwtService.extractUserIdFromRequest();
        EmpresaResponseDTO empresaFromUser = service.findByUserId(userIdToken);
        EmpresaResponseDTO empresa = service.update(data, empresaFromUser.id());
        ApiResponseDTO<EmpresaResponseDTO> response = new ApiResponseDTO<>(
            true,
            "Empresa atualizada com sucesso.",
            empresa,
            null
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping()
    public ResponseEntity<ApiResponseDTO<?>> delete() {
        Long userIdToken = jwtService.extractUserIdFromRequest();
        EmpresaResponseDTO empresa = service.findByUserId(userIdToken);
        service.deleteById(empresa.id());
        ApiResponseDTO<?> response = new ApiResponseDTO<>(
            true,
            "Empresa removida com sucesso.",
            null,
            null
        );

        return ResponseEntity.ok(response);
    }
}
