package com.ufrn.imd.ponto_certo.mapper;

import org.mapstruct.Mapper;

import com.ufrn.imd.ponto_certo.dto.request.FuncionarioCreateRequestDTO;
import com.ufrn.imd.ponto_certo.dto.request.FuncionarioUpdateRequestDTO;
import com.ufrn.imd.ponto_certo.dto.response.FuncionarioResponseDTO;
import com.ufrn.imd.ponto_certo.model.Funcionario;

@Mapper(componentModel = "spring")
public interface FuncionarioMapper {
    Funcionario toEntity(FuncionarioCreateRequestDTO createFuncionarioRequestDTO);
    Funcionario toEntity(FuncionarioUpdateRequestDTO updateFuncionarioRequestDTO);
    FuncionarioResponseDTO toDTO(Funcionario funcionario);
}
