package com.ufrn.imd.ponto_certo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EmpresaUpdateRequestDTO(
    @Pattern(regexp = "^.{1,}$", message = "O nome da empresa deve conter pelo menos 1 caractere.")
    String nome,
    @Pattern(regexp = "^\\([1-9]{2}\\) (?:[2-8]|9[0-9])[0-9]{3}\\-[0-9]{4}$", message = "O número de telefone deve estar no formato (XX) XXXXX-XXXX.")
    String phone
) {}
