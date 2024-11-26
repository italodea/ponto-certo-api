package com.ufrn.imd.ponto_certo.model;

import com.ufrn.imd.ponto_certo.model.enums.EnumTipoAcesso;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User extends BaseEntity {

    @Column(nullable = false, name="first_name")
    private String firstName;

    @Column(nullable = true, name="last_name")
    private String lastName;

    @Column(nullable = false, unique = true, name="email")
    private String email;

    @Column(nullable = false, name="password")
    private String password;

    @Column(nullable = true, name="phone_number")
    private String phoneNumber;

    @Column(nullable = false, name="cpf", unique = true)
    private String cpf;

    @Column(nullable = false, name="type_access")
    @Enumerated(EnumType.STRING)
    private EnumTipoAcesso typeAccess = EnumTipoAcesso.ADMIN;
}
