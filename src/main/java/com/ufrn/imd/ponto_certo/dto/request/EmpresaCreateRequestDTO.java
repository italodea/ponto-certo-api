package com.ufrn.imd.ponto_certo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EmpresaCreateRequestDTO(
    @NotBlank(message = "Nome é obrigatório")
    String nome,
    @NotBlank(message = "CNPJ é obrigatório")
    @Pattern(regexp = "^[0-9]{2}\\.[0-9]{3}\\.[0-9]{3}\\/[0-9]{4}\\-[0-9]{2}$", message = "CNPJ inválido")
    String cnpj,
    @Pattern(regexp = "^\\([1-9]{2}\\) (?:[2-8]|9[0-9])[0-9]{3}\\-[0-9]{4}$", message = "O número de telefone deve estar no formato (XX) XXXXX-XXXX.")
    String phone,
    Long userId
) {}
