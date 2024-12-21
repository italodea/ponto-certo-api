package com.ufrn.imd.ponto_certo.mapper;

import org.mapstruct.Mapper;

import com.ufrn.imd.ponto_certo.dto.request.WorkLogCreate;
import com.ufrn.imd.ponto_certo.dto.request.WorkLogUpdate;
import com.ufrn.imd.ponto_certo.dto.response.WorkLogResponseDTO;
import com.ufrn.imd.ponto_certo.model.WorkLog;

@Mapper(componentModel = "spring")
public interface WorkLogMapper {
    WorkLog toEntity(WorkLogCreate createWorkLogRequestDTO);
    WorkLog toEntity(WorkLogUpdate updateWorkLogRequestDTO);
    WorkLogResponseDTO toDTO(WorkLog workLog);

}
