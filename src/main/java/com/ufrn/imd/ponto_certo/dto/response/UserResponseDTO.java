package com.ufrn.imd.ponto_certo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserResponseDTO(
        Long id,
        String email,
        String firstName,
        String lastName,
        String phoneNumber
) {
}
