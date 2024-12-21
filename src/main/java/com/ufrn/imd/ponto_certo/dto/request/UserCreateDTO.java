package com.ufrn.imd.ponto_certo.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserCreateDTO(
        @NotBlank(message = "firstName é obrigatório.")
        String firstName,
        String lastName,
        @NotBlank(message = "email é obrigatório.")
        @Email
        String email,
        @NotBlank(message = "password é obrigatório.")
                @Pattern(message = "A senha deve conter pelo menos 8 caracteres.", regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")
        String password,
        @Pattern(regexp = "^\\([1-9]{2}\\) (?:[2-8]|9[0-9])[0-9]{3}\\-[0-9]{4}$", message = "O número de telefone deve estar no formato (XX) XXXXX-XXXX.")
        String phoneNumber,
        @NotBlank(message = "cpf é obrigatório.")
        @Pattern(regexp = "^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}$", message = "O CPF deve estar no formato XXX.XXX.XXX-XX.")
        String cpf,
        @NotBlank(message = "typeAccess é obrigatório.")
        String typeAccess
) {
}
