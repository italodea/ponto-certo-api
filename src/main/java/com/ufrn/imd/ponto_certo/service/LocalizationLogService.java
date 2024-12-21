package com.ufrn.imd.ponto_certo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ufrn.imd.ponto_certo.dto.response.LocalizationLogResponseDTO;
import com.ufrn.imd.ponto_certo.exception.ResourceNotFoundException;
import com.ufrn.imd.ponto_certo.mapper.LocalizationLogMapper;
import com.ufrn.imd.ponto_certo.model.LocalizationLog;
import com.ufrn.imd.ponto_certo.repository.LocalizationLogRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LocalizationLogService {
    private final LocalizationLogRepository repository;
    private final LocalizationLogMapper mapper;

    public LocalizationLogResponseDTO findById(Long id) {
        return mapper.toDto(this.findByIdIfExists(id));
    }

    public List<LocalizationLogResponseDTO> findAllFromFuncionarios(Long funcionarioId) {
        List<LocalizationLog> logs = this.findAllFromFuncionariosIfExists(funcionarioId);
        List<LocalizationLogResponseDTO> logsDTO = logs.stream().map(log -> mapper.toDto(log)).toList();
        return logsDTO;
    }

    public LocalizationLogResponseDTO create(LocalizationLog localizationLog) {
        return mapper.toDto(this.save(localizationLog));
    }

    public LocalizationLog findByIdIfExists(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Log de localização de ID " + id + " não encontrado."));
    }

    public List<LocalizationLog> findAllFromFuncionariosIfExists(Long funcionarioId) {
        return repository.findAllByFuncionarioId(funcionarioId);
    }

    public LocalizationLog save(LocalizationLog localizationLog) {
        return repository.save(localizationLog);
    }
}
