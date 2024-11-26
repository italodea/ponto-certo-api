package com.ufrn.imd.ponto_certo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ufrn.imd.ponto_certo.model.enums.EnumRegime;
import com.ufrn.imd.ponto_certo.model.enums.EnumTrabalho;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record FuncionarioResponseDTO(
    Long id,
    String nome,
    String cpf,
    String email,
    String phone,
    EnumRegime tipoRegime,
    EnumTrabalho tipoTrabalho,
    EmpresaResponseDTO empresa,
    UserResponseDTO user
) {
}
