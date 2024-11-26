package com.ufrn.imd.ponto_certo.dto.request;

import com.ufrn.imd.ponto_certo.model.enums.EnumRegime;
import com.ufrn.imd.ponto_certo.model.enums.EnumTrabalho;

public record FuncionarioUpdateRequestDTO(
    EnumRegime tipoRegime,
    EnumTrabalho tipoTrabalho
) {}
