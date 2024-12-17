package com.ufrn.imd.ponto_certo.mapper;

import org.mapstruct.Mapper;

import com.ufrn.imd.ponto_certo.dto.request.EmpresaCreateRequestDTO;
import com.ufrn.imd.ponto_certo.dto.request.EmpresaUpdateRequestDTO;
import com.ufrn.imd.ponto_certo.dto.response.EmpresaResponseDTO;
import com.ufrn.imd.ponto_certo.model.Empresa;

@Mapper(componentModel = "spring")
public interface EmpresaMapper {
    Empresa toEntity(EmpresaCreateRequestDTO createEmpresaRequestDTO);
    Empresa toEntity(EmpresaUpdateRequestDTO updateEmpresaRequestDTO);
    EmpresaResponseDTO toDTO(Empresa empresa);
}
