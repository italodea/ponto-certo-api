package com.ufrn.imd.ponto_certo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ufrn.imd.ponto_certo.model.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    
    @Query("SELECT f FROM Funcionario f WHERE f.user.id = ?1")
    Optional<Funcionario> getByUserId(Long userId);

    
}
