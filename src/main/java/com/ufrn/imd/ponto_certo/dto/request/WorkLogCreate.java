package com.ufrn.imd.ponto_certo.dto.request;

import jakarta.validation.constraints.NotBlank;

public record WorkLogCreate(
    @NotBlank(message = "Nome é obrigatório")
    String tipoAcesso,
    Long funcionarioId
) {
    public WorkLogCreate(String tipoAcesso) {
        this(tipoAcesso, null);
    }

    public WorkLogCreate withFuncionarioId(Long funcionarioId) {
        return new WorkLogCreate(this.tipoAcesso, funcionarioId);
    }
}

