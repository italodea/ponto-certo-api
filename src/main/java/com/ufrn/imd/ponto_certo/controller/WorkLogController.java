package com.ufrn.imd.ponto_certo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufrn.imd.ponto_certo.dto.request.WorkLogCreate;
import com.ufrn.imd.ponto_certo.dto.request.WorkLogUpdate;
import com.ufrn.imd.ponto_certo.dto.response.ApiResponseDTO;
import com.ufrn.imd.ponto_certo.dto.response.WorkLogResponseDTO;
import com.ufrn.imd.ponto_certo.model.Funcionario;
import com.ufrn.imd.ponto_certo.service.FuncionarioService;
import com.ufrn.imd.ponto_certo.service.JwtService;
import com.ufrn.imd.ponto_certo.service.WorkLogService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/worklog")
public class WorkLogController {
    private final WorkLogService service;
    private final FuncionarioService funcionarioService;
    private final JwtService jwtService;

    @GetMapping()
    public ResponseEntity<ApiResponseDTO<List<WorkLogResponseDTO>>> findAll() {
        Long userIdToken = jwtService.extractUserIdFromRequest();
        Funcionario funcionario = funcionarioService.findByUserIdIfExists(userIdToken);
        List<WorkLogResponseDTO> workLog = service.findAllFromFuncionario(funcionario.getId());

        ApiResponseDTO<List<WorkLogResponseDTO>> response = new ApiResponseDTO<>(
                true,
                "WorkLog encontrado com sucesso.",
                workLog,
                null);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<WorkLogResponseDTO>> findById(Long id) {
        WorkLogResponseDTO workLog = service.findById(id);
        ApiResponseDTO<WorkLogResponseDTO> response = new ApiResponseDTO<>(
                true,
                "WorkLog encontrado com sucesso.",
                workLog,
                null);

        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<ApiResponseDTO<WorkLogResponseDTO>> create(@RequestBody WorkLogCreate data) {
        Long userIdToken = jwtService.extractUserIdFromRequest();
        Funcionario funcionario = funcionarioService.findByUserIdIfExists(userIdToken);
        data.withFuncionarioId(funcionario.getId());
        WorkLogResponseDTO workLog = service.create(data);
        ApiResponseDTO<WorkLogResponseDTO> response = new ApiResponseDTO<>(
                true,
                "WorkLog criado com sucesso.",
                workLog,
                null);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<WorkLogResponseDTO>> update(WorkLogUpdate body, Long id) {
        Long userIdToken = jwtService.extractUserIdFromRequest();
        WorkLogResponseDTO workLog = service.update(body, id, userIdToken);
        ApiResponseDTO<WorkLogResponseDTO> response = new ApiResponseDTO<>(
                true,
                "WorkLog atualizado com sucesso.",
                workLog,
                null);

        return ResponseEntity.ok(response);
    }
}
