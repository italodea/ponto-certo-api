package com.ufrn.imd.ponto_certo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record WorkLogResponseDTO(
    Long id,
    String data,
    String horaInicio,
    String horaFim,
    String validacao,
    FuncionarioResponseDTO funcionario
) {
    
}
