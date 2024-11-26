package com.ufrn.imd.ponto_certo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ufrn.imd.ponto_certo.model.enums.EnumRegime;
import com.ufrn.imd.ponto_certo.model.enums.EnumTrabalho;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity()
@Table(name="funcionarios")
@Data
public class Funcionario extends BaseEntity{

    @Column(nullable = false, name = "empresa_id")
    private Long empresaId;

    @JsonIgnore
    @JoinColumn(name = "empresa_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = Empresa.class, fetch = FetchType.EAGER)
    private Empresa empresa;

    @Column(nullable = false, name = "tipo_regime")
    @Enumerated(EnumType.STRING)
    private EnumRegime tipoRegime;

    @Column(nullable = false, name = "tipo_trabalho")
    @Enumerated(EnumType.STRING)
    private EnumTrabalho tipoTrabalho;

    @Column(nullable = false, name = "user_id")
    private Long userId;    

    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    private User user;

}
