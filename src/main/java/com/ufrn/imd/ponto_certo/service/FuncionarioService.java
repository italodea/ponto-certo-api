package com.ufrn.imd.ponto_certo.service;

import com.ufrn.imd.ponto_certo.exception.UnauthorizedException;
import com.ufrn.imd.ponto_certo.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ufrn.imd.ponto_certo.dto.request.FuncionarioCreateDTO;
import com.ufrn.imd.ponto_certo.dto.request.FuncionarioUpdateDTO;
import com.ufrn.imd.ponto_certo.dto.response.FuncionarioResponseDTO;
import com.ufrn.imd.ponto_certo.exception.ResourceNotFoundException;
import com.ufrn.imd.ponto_certo.mapper.FuncionarioMapper;
import com.ufrn.imd.ponto_certo.model.Funcionario;
import com.ufrn.imd.ponto_certo.model.User;
import com.ufrn.imd.ponto_certo.model.enums.EnumTipoAcesso;
import com.ufrn.imd.ponto_certo.repository.FuncionarioRepository;
import com.ufrn.imd.ponto_certo.util.AttributeUtils;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final FuncionarioMapper funcionarioMapper;
    private final UserService userService;

    public Funcionario findByIdIfExists(Long id) {
        return funcionarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Funcionario de ID " + id + " não encontrado."));
    }

    public Funcionario findByUserIdIfExists(Long userId) {
        return funcionarioRepository.findByUserId(userId).orElseThrow(() -> new ResourceNotFoundException(
                "Este usuário não é um funcionário cadastrado."));
    }

    public FuncionarioResponseDTO findByUserId(Long userId) {
        return funcionarioMapper.toDTO(this.findByUserIdIfExists(userId));
    }

    public FuncionarioResponseDTO findById(Long id, Long userId) {
        User user = userService.findById(userId);
        Funcionario funcionario = this.findByIdIfExists(id);
        if(funcionario.getUserId() != user.getId() && user.getTypeAccess() == EnumTipoAcesso.FUNCIONARIO){
            throw new UnauthorizedException("Você não tem permissão para acessar este recurso.");
        }
        return funcionarioMapper.toDTO(funcionario);
    }

    public FuncionarioResponseDTO create(FuncionarioCreateDTO data) {
        if (funcionarioRepository.findByUserId(data.userId()).isPresent()) {
            throw new UnauthorizedException("Já possui um funcionário cadastrado com este usuário.");
        }

        Funcionario funcionario = funcionarioMapper.toEntity(data);
        return funcionarioMapper.toDTO(this.save(funcionario));
    }

    public FuncionarioResponseDTO update(FuncionarioUpdateDTO body, Long id) {
        Funcionario funcionario = this.findByIdIfExists(id);

        BeanUtils.copyProperties(body, funcionario, AttributeUtils.getNullOrBlankPropertyNames(body));

        return funcionarioMapper.toDTO(funcionarioRepository.save(funcionario));
    }

    public Funcionario save(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public void deleteById(Long id, Long userId) {
        Funcionario funcionario = this.findByIdIfExists(id);
        User user = userService.findById(userId);

        if(funcionario.getUserId() != user.getId() && user.getTypeAccess() == EnumTipoAcesso.FUNCIONARIO){
            throw new UnauthorizedException("Você não tem permissão para acessar este recurso.");
        }

        funcionario.setActive(false);

        funcionarioRepository.save(funcionario);
    }
}
