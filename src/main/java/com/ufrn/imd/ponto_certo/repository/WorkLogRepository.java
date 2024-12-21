package com.ufrn.imd.ponto_certo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ufrn.imd.ponto_certo.model.WorkLog;

@Repository
public interface WorkLogRepository extends JpaRepository<WorkLog, Long>{

    @Query("SELECT w FROM WorkLog w WHERE w.funcionario.id = ?1")
    List<WorkLog> findAllByFuncionarioId(Long funcionarioId);

    @Query("SELECT w FROM WorkLog w WHERE w.funcionario.id = ?1 ORDER BY w.dataRegistro DESC")
    List<WorkLog> findFirstByFuncionarioIdOrderByDataDesc(Long funcionarioId);
    
}
