package com.ufrn.imd.ponto_certo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record LocalizationLogCreateDTO(
    @NotBlank(message = "Latitude é obrigatório")
    @Pattern(regexp = "[-+]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?)")
    String latitude,
    @NotBlank(message = "Longitude é obrigatório")
    @Pattern(regexp = "[-+]?(180(\\.0+)?|((1[0-7]\\d)|([1-9]?\\d))(\\.\\d+)?)")
    String longitude,
    Long workLogId,
    Long funcionarioId
) {
    public LocalizationLogCreateDTO(String latitude, String longitude, Long funcionarioRegistrosId) {
        this(latitude, longitude, funcionarioRegistrosId, null);
    }

    public LocalizationLogCreateDTO withFuncionarioId(Long funcionarioId) {
        return new LocalizationLogCreateDTO(this.latitude, this.longitude, this.workLogId, funcionarioId);
    }
}
