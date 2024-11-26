package com.ufrn.imd.ponto_certo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;
import java.util.Random;

@Entity()
@Table(name="empresas")
@Data
public class Empresa extends BaseEntity{

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cnpj;

    @Column(nullable = true)
    private String phone;

    @Column(nullable = false, unique = true, name = "invite_code")
    private String inviteCode = generateInviteCode();

    @Column(nullable = false)
    private User owner;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Funcionario> funcionarios;

    public String generateInviteCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }
}
