package com.ufrn.imd.ponto_certo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "localization_log")
@Entity
@Data
public class LocalizationLog extends BaseEntity {

    @Column(nullable = false, name = "latitude")
    private String latitude;

    @Column(nullable = false, name = "longitude")
    private String longitude;

    @OneToOne(targetEntity = WorkLog.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "work_log_id", referencedColumnName = "id", insertable = false, updatable = false)
    private WorkLog workLog;

    @Column(nullable = false, name = "work_log_id")
    private Long workLogId;

    @Column(nullable = false, name = "funcionario_id")
    private Long funcionarioId;

    @OneToOne(targetEntity = Funcionario.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionario_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Funcionario funcionario;
}
