package com.ufrn.imd.ponto_certo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufrn.imd.ponto_certo.dto.response.ApiResponseDTO;
import com.ufrn.imd.ponto_certo.dto.response.LocalizationLogResponseDTO;
import com.ufrn.imd.ponto_certo.model.Funcionario;
import com.ufrn.imd.ponto_certo.model.LocalizationLog;
import com.ufrn.imd.ponto_certo.service.FuncionarioService;
import com.ufrn.imd.ponto_certo.service.JwtService;
import com.ufrn.imd.ponto_certo.service.LocalizationLogService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/localizationlog")
public class LocalizationLogController {
    private final LocalizationLogService service;
    private final FuncionarioService funcionarioService;
    private final JwtService jwtService;

    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<LocalizationLogResponseDTO>>> findAllFromFuncionario() {
        Long userIdToken = jwtService.extractUserIdFromRequest();
        Funcionario funcionario = funcionarioService.findByUserIdIfExists(userIdToken);

        ApiResponseDTO<List<LocalizationLogResponseDTO>> response = new ApiResponseDTO<>(
                true,
                "Logs de localização encontrados com sucesso.",
                service.findAllFromFuncionarios(funcionario.getId()),
                null);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<LocalizationLogResponseDTO>> findById(Long id) {
        LocalizationLogResponseDTO localizationLog = service.findById(id);
        ApiResponseDTO<LocalizationLogResponseDTO> response = new ApiResponseDTO<>(
                true,
                "Log de localização encontrado com sucesso.",
                localizationLog,
                null);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponseDTO<LocalizationLogResponseDTO>> create(LocalizationLog localizationLog) {
        LocalizationLogResponseDTO localizationLogDTO = service.create(localizationLog);
        ApiResponseDTO<LocalizationLogResponseDTO> response = new ApiResponseDTO<>(
                true,
                "Log de localização criado com sucesso.",
                localizationLogDTO,
                null);

        return ResponseEntity.ok(response);
    }
}