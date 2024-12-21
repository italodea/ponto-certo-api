package com.ufrn.imd.ponto_certo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.ufrn.imd.ponto_certo.model.enums.EnumTipoHoraRegistrada;
import com.ufrn.imd.ponto_certo.model.enums.EnumTipoValidacao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "work_log")
@Data
public class WorkLog extends BaseEntity{
    @Column(nullable = false, name = "funcionario_id")
    private Long funcionarioId;

    @OneToOne(targetEntity = Funcionario.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionario_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Funcionario funcionario;

    @Column(name = "validacao")
    @Enumerated(EnumType.STRING)
    private EnumTipoValidacao validacao;

    @Column(nullable = false, name = "tipo_acesso")
    @Enumerated(EnumType.STRING)
    private EnumTipoHoraRegistrada tipoAcesso;
    
    @Column(nullable = false, name = "hora_registro")
    private String horaRegistro;

    @Column(nullable = false, name = "data_registro")
    private LocalDateTime dataRegistro;

    @Column(nullable = true, name = "observacoes")
    private String observacoes;
}
