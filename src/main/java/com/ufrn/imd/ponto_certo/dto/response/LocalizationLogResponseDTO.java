package com.ufrn.imd.ponto_certo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record LocalizationLogResponseDTO(
    String latitude,
    String longitude,
    WorkLogResponseDTO workLog
) {
    
}
