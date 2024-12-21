package com.ufrn.imd.ponto_certo.mapper;

import org.mapstruct.Mapper;

import com.ufrn.imd.ponto_certo.dto.request.LocalizationLogCreateDTO;
import com.ufrn.imd.ponto_certo.dto.response.LocalizationLogResponseDTO;
import com.ufrn.imd.ponto_certo.model.LocalizationLog;

@Mapper(componentModel = "spring")
public interface LocalizationLogMapper {
    LocalizationLog toEntity(LocalizationLogCreateDTO localizationLog);
    LocalizationLogResponseDTO toDto(LocalizationLog localizationLog);
}
