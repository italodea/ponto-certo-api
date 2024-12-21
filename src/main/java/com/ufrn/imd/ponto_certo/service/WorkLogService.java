package com.ufrn.imd.ponto_certo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ufrn.imd.ponto_certo.dto.request.WorkLogCreate;
import com.ufrn.imd.ponto_certo.dto.request.WorkLogUpdate;
import com.ufrn.imd.ponto_certo.dto.response.WorkLogResponseDTO;
import com.ufrn.imd.ponto_certo.exception.ResourceNotFoundException;
import com.ufrn.imd.ponto_certo.exception.UnauthorizedException;
import com.ufrn.imd.ponto_certo.mapper.WorkLogMapper;
import com.ufrn.imd.ponto_certo.model.User;
import com.ufrn.imd.ponto_certo.model.WorkLog;
import com.ufrn.imd.ponto_certo.model.enums.EnumTipoAcesso;
import com.ufrn.imd.ponto_certo.model.enums.EnumTipoHoraRegistrada;
import com.ufrn.imd.ponto_certo.repository.WorkLogRepository;
import com.ufrn.imd.ponto_certo.util.AttributeUtils;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class WorkLogService {
    private final WorkLogRepository workLogRepository;
    private final WorkLogMapper workLogMapper;
    private final UserService userService;

    public WorkLogResponseDTO findById(Long id) {
        return workLogMapper.toDTO(this.findByIdIfExists(id));
    }

    public List<WorkLogResponseDTO> findAllFromFuncionario(Long funcionarioId) {
        List<WorkLog> workLogs = workLogRepository.findAllByFuncionarioId(funcionarioId);
        
        List<WorkLogResponseDTO> workLogsDTO = workLogs.stream()
            .map(workLogMapper::toDTO)
            .toList();
        
        return workLogsDTO;
    }

    public WorkLog findLast(Long funcionarioId) {
        List<WorkLog> w = workLogRepository.findFirstByFuncionarioIdOrderByDataDesc(funcionarioId);
        return w.size() > 0 ? w.get(0) : null;
    }

    public WorkLogResponseDTO create(WorkLogCreate data){
        LocalDateTime now = LocalDateTime.now();

        WorkLog lastLog = this.findLast(data.funcionarioId());
        if(lastLog != null && lastLog.getTipoAcesso() != null) {
            this.validateLastLog(data, lastLog);
        }

        WorkLog workLog = workLogMapper.toEntity(data);
        workLog.setTipoAcesso(EnumTipoHoraRegistrada.valueOf(data.tipoAcesso()));
        workLog.setFuncionarioId(data.funcionarioId());
        workLog.setDataRegistro(now);
        workLog.setTipoAcesso(EnumTipoHoraRegistrada.valueOf(data.tipoAcesso()));
        workLog.setHoraRegistro(now.getHour() + ":" + now.getMinute());
        return workLogMapper.toDTO(this.save(workLog));
    }

    public WorkLogResponseDTO update(WorkLogUpdate data, Long id, Long userIdToken) {
        User user = userService.findById(userIdToken);

        if(user.getTypeAccess() == EnumTipoAcesso.FUNCIONARIO) {
            throw new UnauthorizedException("Funcionário não tem permissão para editar pontos.");
        }
        
        WorkLog workLog = this.findByIdIfExists(id);
        BeanUtils.copyProperties(data, AttributeUtils.getNullOrBlankPropertyNames(workLog));
        return workLogMapper.toDTO(this.save(workLog));
    }

    public WorkLog findByIdIfExists(Long id) {
        return workLogRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
            "Funcionario de ID " + id + " não encontrado."));
    }

    public List<WorkLog> findAllFromFuncionariosIfExists(Long funcionarioId) {
        return workLogRepository.findAllByFuncionarioId(funcionarioId);
    }

    
    public WorkLog save(WorkLog workLog) {
        return workLogRepository.save(workLog);
    }

    public void validateLastLog(WorkLogCreate data, WorkLog lastLog) {
        if(data.tipoAcesso() == "ENTRADA" && lastLog.getTipoAcesso().getDescription() == EnumTipoHoraRegistrada.ENTRADA.getDescription()) {
            throw new UnauthorizedException("Já existe um registro de entrada para este funcionário.");
        }
        if(data.tipoAcesso() == "SAIDA"){
            if(lastLog.getTipoAcesso() == EnumTipoHoraRegistrada.SAIDA) {
                throw new UnauthorizedException("Já existe um registro de saída para este funcionário.");
            }
            if(lastLog.getTipoAcesso() == EnumTipoHoraRegistrada.INTERVALO_INICIO) {
                throw new UnauthorizedException("Este funcionário ainda não registrou o fim do intervalo.");
            }
        }
        if(data.tipoAcesso() == "INTERVALO_INICIO" && lastLog.getTipoAcesso() != EnumTipoHoraRegistrada.ENTRADA) {
            throw new UnauthorizedException("Este funcionário ainda não registrou a entrada.");
        }
        if(data.tipoAcesso() == "INTERVALO_FIM" && lastLog.getTipoAcesso() != EnumTipoHoraRegistrada.INTERVALO_INICIO) {
            throw new UnauthorizedException("Este funcionário ainda não registrou o início do intervalo.");
        }
    }
}
