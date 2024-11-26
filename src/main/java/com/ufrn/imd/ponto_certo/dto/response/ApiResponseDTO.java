package com.ufrn.imd.ponto_certo.dto.response;

public record ApiResponseDTO<DTO>(
        boolean success,
        String message,
        DTO data,
        DTO error
) { }