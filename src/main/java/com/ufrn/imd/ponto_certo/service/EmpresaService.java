package com.ufrn.imd.ponto_certo.service;

import java.text.AttributedCharacterIterator.Attribute;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ufrn.imd.ponto_certo.dto.request.EmpresaCreateRequestDTO;
import com.ufrn.imd.ponto_certo.dto.request.EmpresaUpdateRequestDTO;
import com.ufrn.imd.ponto_certo.dto.response.EmpresaResponseDTO;
import com.ufrn.imd.ponto_certo.exception.ResourceNotFoundException;
import com.ufrn.imd.ponto_certo.mapper.EmpresaMapper;
import com.ufrn.imd.ponto_certo.model.Empresa;
import com.ufrn.imd.ponto_certo.repository.EmpresaRepository;
import com.ufrn.imd.ponto_certo.service.interfaces.IEmpresaService;
import com.ufrn.imd.ponto_certo.util.AttributeUtils;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmpresaService implements IEmpresaService {
    private EmpresaRepository empresaRepository;
    private EmpresaMapper empresaMapper;

    @Override
    public Empresa findByIdIfExists(Long id) {
        return empresaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Empresa de ID " + id + " não encontrado."));
    }

    @Override
    public EmpresaResponseDTO findById(Long id) {
        return empresaMapper.toDTO(this.findByIdIfExists(id));
    }

    @Override
    public EmpresaResponseDTO create(EmpresaCreateRequestDTO data) {
        Empresa empresa = empresaMapper.toEntity(data);

        return empresaMapper.toDTO(empresaRepository.save(empresa));
    }

    @Override
    public EmpresaResponseDTO update(EmpresaUpdateRequestDTO data, Long id) {
        Empresa empresa = this.findByIdIfExists(id);

        BeanUtils.copyProperties(data, empresa, AttributeUtils.getNullOrBlankPropertyNames(data));
        return empresaMapper.toDTO(empresaRepository.save(empresa));
    }

    @Override
    public void deleteById(Long id) {

        Empresa empresa = this.findByIdIfExists(id);
        empresa.setActive(false);
        
        empresaRepository.save(empresa);
    }
}
