package com.ufrn.imd.ponto_certo.dto.request;

import com.ufrn.imd.ponto_certo.model.enums.EnumRegime;
import com.ufrn.imd.ponto_certo.model.enums.EnumTrabalho;

import jakarta.validation.constraints.NotBlank;

public record FuncionarioCreateRequestDTO(
    @NotBlank
    Long empresaId,
    EnumRegime tipoRegime,
    EnumTrabalho tipoTrabalho,
    Long userId
) {
    public FuncionarioCreateRequestDTO(Long empresaId, EnumRegime tipoRegime, EnumTrabalho tipoTrabalho) {
        this(empresaId, tipoRegime, tipoTrabalho, null);
    }

    public FuncionarioCreateRequestDTO withUserId(Long userId) {
        return new FuncionarioCreateRequestDTO(this.empresaId, this.tipoRegime, this.tipoTrabalho, userId);
    }
}
