package com.ufrn.imd.ponto_certo.dto.request;

import com.ufrn.imd.ponto_certo.model.enums.EnumRegime;
import com.ufrn.imd.ponto_certo.model.enums.EnumTrabalho;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record FuncionarioCreateRequestDTO(
    Long empresaId,
    EnumRegime tipoRegime,
    EnumTrabalho tipoTrabalho,
    @NotBlank
    String nome,
    @NotBlank
    @Pattern(regexp = "^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}$", message = "CPF inválido")
    String cpf
) {}
