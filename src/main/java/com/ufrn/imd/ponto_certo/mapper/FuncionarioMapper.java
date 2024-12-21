package com.ufrn.imd.ponto_certo.mapper;

import org.mapstruct.Mapper;

import com.ufrn.imd.ponto_certo.dto.request.FuncionarioCreateDTO;
import com.ufrn.imd.ponto_certo.dto.request.FuncionarioUpdateDTO;
import com.ufrn.imd.ponto_certo.dto.response.FuncionarioResponseDTO;
import com.ufrn.imd.ponto_certo.model.Funcionario;

@Mapper(componentModel = "spring")
public interface FuncionarioMapper {
    Funcionario toEntity(FuncionarioCreateDTO createFuncionarioRequestDTO);
    Funcionario toEntity(FuncionarioUpdateDTO updateFuncionarioRequestDTO);
    FuncionarioResponseDTO toDTO(Funcionario funcionario);
}
