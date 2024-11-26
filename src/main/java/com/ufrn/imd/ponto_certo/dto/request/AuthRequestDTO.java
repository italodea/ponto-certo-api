package com.ufrn.imd.ponto_certo.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AuthRequestDTO(
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}
