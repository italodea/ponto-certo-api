package com.ufrn.imd.ponto_certo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ufrn.imd.ponto_certo.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    @Query("SELECT e FROM Empresa e WHERE e.owner.id = ?1")
    Optional<Empresa> findByUserId(Long userIdToken);

    Optional<Empresa> findByCnpj(String cnpj);
}
