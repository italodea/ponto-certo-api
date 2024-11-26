package com.ufrn.imd.ponto_certo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufrn.imd.ponto_certo.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    
}
