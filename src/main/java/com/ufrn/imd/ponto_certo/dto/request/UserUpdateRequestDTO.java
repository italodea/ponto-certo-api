package com.ufrn.imd.ponto_certo.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record UserUpdateRequestDTO(
        String firstName,
        String lastName,
        @Email
        String email,
        String password,
        @Pattern(regexp = "^\\([1-9]{2}\\) (?:[2-8]|9[0-9])[0-9]{3}\\-[0-9]{4}$", message = "O número de telefone deve estar no formato (XX) XXXXX-XXXX.")
        String phoneNumber,
        @Pattern(regexp = "^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}$", message = "O CPF deve estar no formato XXX.XXX.XXX-XX.")
        String cpf
) {
}
