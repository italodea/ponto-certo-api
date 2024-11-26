package com.ufrn.imd.ponto_certo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufrn.imd.ponto_certo.dto.request.EmpresaCreateRequestDTO;
import com.ufrn.imd.ponto_certo.dto.request.EmpresaUpdateRequestDTO;
import com.ufrn.imd.ponto_certo.dto.response.ApiResponseDTO;
import com.ufrn.imd.ponto_certo.dto.response.EmpresaResponseDTO;
import com.ufrn.imd.ponto_certo.service.interfaces.IEmpresaService;
import com.ufrn.imd.ponto_certo.service.interfaces.IJwtService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/empresa")
@AllArgsConstructor
public class EmpresaController {
    
    private IEmpresaService service;
    private final IJwtService jwtService;

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
        EmpresaResponseDTO empresa = service.create(data);
        ApiResponseDTO<EmpresaResponseDTO> response = new ApiResponseDTO<>(
            true,
            "Empresa criada com sucesso.",
            empresa,
            null
        );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<EmpresaResponseDTO>> update(@PathVariable Long id, @RequestBody EmpresaUpdateRequestDTO data) {
        EmpresaResponseDTO empresa = service.update(data, id);
        ApiResponseDTO<EmpresaResponseDTO> response = new ApiResponseDTO<>(
            true,
            "Empresa atualizada com sucesso.",
            empresa,
            null
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<?>> delete(@PathVariable Long id) {
        service.deleteById(id);
        ApiResponseDTO<?> response = new ApiResponseDTO<>(
            true,
            "Empresa removida com sucesso.",
            null,
            null
        );

        return ResponseEntity.ok(response);
    }
}
