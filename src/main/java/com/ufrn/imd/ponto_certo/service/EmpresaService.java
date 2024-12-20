package com.ufrn.imd.ponto_certo.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ufrn.imd.ponto_certo.dto.request.EmpresaCreateDTO;
import com.ufrn.imd.ponto_certo.dto.request.EmpresaUpdateDTO;
import com.ufrn.imd.ponto_certo.dto.response.EmpresaResponseDTO;
import com.ufrn.imd.ponto_certo.exception.BusinessException;
import com.ufrn.imd.ponto_certo.exception.ResourceNotFoundException;
import com.ufrn.imd.ponto_certo.exception.UnauthorizedException;
import com.ufrn.imd.ponto_certo.mapper.EmpresaMapper;
import com.ufrn.imd.ponto_certo.model.Empresa;
import com.ufrn.imd.ponto_certo.repository.EmpresaRepository;
import com.ufrn.imd.ponto_certo.util.AttributeUtils;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmpresaService {
    private final EmpresaRepository empresaRepository;
    private final EmpresaMapper empresaMapper;

    public Empresa findByIdIfExists(Long id) {
        return empresaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Empresa de ID " + id + " não encontrado."));
    }

    public Empresa findByUserIdIfExists(Long userIdToken) {
        return empresaRepository.findByUserId(userIdToken).orElseThrow(() -> new ResourceNotFoundException(
                "Empresa não encontrada para este usuário."));
    }

    public Empresa findCompanyByInviteCode(String invite_code){
        return empresaRepository.findByInviteCode(invite_code).orElseThrow(() -> new ResourceNotFoundException(
                "Este código não pertence a uma empresa."));
    }

    public EmpresaResponseDTO findById(Long id) {
        return empresaMapper.toDTO(this.findByIdIfExists(id));
    }

    public EmpresaResponseDTO findByInviteCode(String invite_code) {
        return empresaMapper.toDTO(this.findCompanyByInviteCode(invite_code));
    }

    public EmpresaResponseDTO findByUserId(Long userIdToken) {
        return empresaMapper.toDTO(findByUserIdIfExists(userIdToken));
    }

    public EmpresaResponseDTO create(EmpresaCreateDTO data) {
        if (empresaRepository.findByUserId(data.ownerId()) != null) {
            throw new UnauthorizedException("Já possui uma empresa cadastrada.");
        }

        Empresa empresa = empresaMapper.toEntity(data);

        return empresaMapper.toDTO(this.save(empresa));
    }

    public EmpresaResponseDTO update(EmpresaUpdateDTO data, Long id) {
        Empresa empresa = this.findByIdIfExists(id);

        BeanUtils.copyProperties(data, empresa, AttributeUtils.getNullOrBlankPropertyNames(data));
        return empresaMapper.toDTO(this.save(empresa));
    }

    public Empresa save(Empresa empresa) {
        validateBeforeSave(empresa);
        return empresaRepository.save(empresa);
    }

    public void deleteById(Long id) {

        Empresa empresa = this.findByIdIfExists(id);
        empresa.setActive(false);

        empresaRepository.save(empresa);
    }

    public void validateBeforeSave(Empresa empresa) {
        validadeCNPJ(empresa.getCnpj(), empresa.getId());
    }

    private void validadeCNPJ(String cnpj, Long id) {
        Optional<Empresa> empresa = empresaRepository.findByCnpj(cnpj);

        if (empresa.isPresent() && (id == null || !empresa.get().getId().equals(id))) {
            throw new BusinessException(
                    "CNPJ inválido: " + cnpj + ". Uma empresa cadastrada já utiliza este CNPJ.",
                    HttpStatus.CONFLICT);
        }
    }
}
