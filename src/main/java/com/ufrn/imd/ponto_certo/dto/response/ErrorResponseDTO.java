package com.ufrn.imd.ponto_certo.dto.response;

import java.time.ZonedDateTime;

public record ErrorResponseDTO(
        ZonedDateTime timestamp,
        Integer status,
        String error,
        String message,
        String path) {
}
