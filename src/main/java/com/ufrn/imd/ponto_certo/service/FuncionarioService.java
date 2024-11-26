package com.ufrn.imd.ponto_certo.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ufrn.imd.ponto_certo.dto.request.FuncionarioCreateRequestDTO;
import com.ufrn.imd.ponto_certo.dto.request.FuncionarioUpdateRequestDTO;
import com.ufrn.imd.ponto_certo.dto.response.FuncionarioResponseDTO;
import com.ufrn.imd.ponto_certo.dto.response.UserResponseDTO;
import com.ufrn.imd.ponto_certo.exception.ResourceNotFoundException;
import com.ufrn.imd.ponto_certo.mapper.FuncionarioMapper;
import com.ufrn.imd.ponto_certo.model.Funcionario;
import com.ufrn.imd.ponto_certo.repository.FuncionarioRepository;
import com.ufrn.imd.ponto_certo.util.AttributeUtils;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FuncionarioService {
    
    private final FuncionarioRepository funcionarioRepository;
    private final FuncionarioMapper funcionarioMapper;

    public Funcionario findByIdIfExists(Long id) {
        return funcionarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Funcionario de ID " + id + " não encontrado."));
    }

    public Funcionario findByUserIdIfExists(Long userId) {
        return funcionarioRepository.getByUserId(userId).orElseThrow(() -> new ResourceNotFoundException(
                "Funcionario de ID " + userId + " não encontrado."));
    }

    public FuncionarioResponseDTO findByUserId(Long userId) {
        return funcionarioMapper.toDTO(this.findByUserIdIfExists(userId));
    }

    public FuncionarioResponseDTO findById(Long id) {
        return funcionarioMapper.toDTO(this.findByIdIfExists(id));
    }

    public FuncionarioResponseDTO create(FuncionarioCreateRequestDTO body) {
        Funcionario funcionario = funcionarioMapper.toEntity(body);
        return funcionarioMapper.toDTO(funcionarioRepository.save(funcionario));
    }

    public FuncionarioResponseDTO update(FuncionarioUpdateRequestDTO body, Long id) {
        Funcionario funcionario = this.findByIdIfExists(id);

        BeanUtils.copyProperties(body, funcionario, AttributeUtils.getNullOrBlankPropertyNames(body));

        return funcionarioMapper.toDTO(funcionarioRepository.save(funcionario));
    }

    public void deleteById(Long id){
        Funcionario funcionario = this.findByIdIfExists(id);
        funcionario.setActive(false);

        funcionarioRepository.save(funcionario);
    }
}
