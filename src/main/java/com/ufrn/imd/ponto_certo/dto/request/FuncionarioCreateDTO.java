package com.ufrn.imd.ponto_certo.dto.request;

import jakarta.validation.constraints.NotBlank;

public record FuncionarioCreateDTO(
    @NotBlank
    Long empresaId,
    String tipoRegime,
    String tipoTrabalho,
    Long userId
) {
    public FuncionarioCreateDTO(Long empresaId, String tipoRegime, String tipoTrabalho) {
        this(empresaId, tipoRegime, tipoTrabalho, null);
    }

    public FuncionarioCreateDTO withUserId(Long userId) {
        return new FuncionarioCreateDTO(this.empresaId, this.tipoRegime, this.tipoTrabalho, userId);
    }
}
