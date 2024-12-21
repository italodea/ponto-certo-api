package com.ufrn.imd.ponto_certo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ufrn.imd.ponto_certo.model.LocalizationLog;

@Repository
public interface LocalizationLogRepository extends JpaRepository<LocalizationLog, Long> {

    @Query("SELECT l FROM LocalizationLog l WHERE l.funcionario.id = ?1")
    List<LocalizationLog> findAllByFuncionarioId(Long funcionarioId);
    
}
