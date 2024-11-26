package com.ufrn.imd.ponto_certo.service.interfaces;

import com.ufrn.imd.ponto_certo.dto.request.EmpresaCreateRequestDTO;
import com.ufrn.imd.ponto_certo.dto.request.EmpresaUpdateRequestDTO;
import com.ufrn.imd.ponto_certo.dto.response.EmpresaResponseDTO;
import com.ufrn.imd.ponto_certo.model.Empresa;

public interface IEmpresaService {

    public EmpresaResponseDTO create(EmpresaCreateRequestDTO empresa);

    public Empresa findByIdIfExists(Long id);

    public EmpresaResponseDTO findById(Long id);
    
    public void deleteById(Long id);

    public EmpresaResponseDTO update(EmpresaUpdateRequestDTO empresa, Long id);
    
}
