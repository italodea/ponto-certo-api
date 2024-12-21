package com.ufrn.imd.ponto_certo.mapper;

import org.mapstruct.Mapper;

import com.ufrn.imd.ponto_certo.dto.request.EmpresaCreateDTO;
import com.ufrn.imd.ponto_certo.dto.request.EmpresaUpdateDTO;
import com.ufrn.imd.ponto_certo.dto.response.EmpresaResponseDTO;
import com.ufrn.imd.ponto_certo.model.Empresa;

@Mapper(componentModel = "spring")
public interface EmpresaMapper {
    Empresa toEntity(EmpresaCreateDTO createEmpresaRequestDTO);
    Empresa toEntity(EmpresaUpdateDTO updateEmpresaRequestDTO);
    EmpresaResponseDTO toDTO(Empresa empresa);
}
