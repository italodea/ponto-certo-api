package com.ufrn.imd.ponto_certo.service;

import com.ufrn.imd.ponto_certo.controller.UserController;
import com.ufrn.imd.ponto_certo.exception.BusinessException;
import com.ufrn.imd.ponto_certo.exception.UnauthorizedException;
import com.ufrn.imd.ponto_certo.model.Empresa;
import com.ufrn.imd.ponto_certo.model.User;
import com.ufrn.imd.ponto_certo.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ufrn.imd.ponto_certo.dto.request.FuncionarioCreateRequestDTO;
import com.ufrn.imd.ponto_certo.dto.request.FuncionarioUpdateRequestDTO;
import com.ufrn.imd.ponto_certo.dto.response.FuncionarioResponseDTO;
import com.ufrn.imd.ponto_certo.exception.ResourceNotFoundException;
import com.ufrn.imd.ponto_certo.mapper.FuncionarioMapper;
import com.ufrn.imd.ponto_certo.model.Funcionario;
import com.ufrn.imd.ponto_certo.repository.FuncionarioRepository;
import com.ufrn.imd.ponto_certo.util.AttributeUtils;

import lombok.AllArgsConstructor;

import java.util.Optional;

@Service
@AllArgsConstructor
public class FuncionarioService {

    private final UserRepository userRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final FuncionarioMapper funcionarioMapper;

    public Funcionario findByIdIfExists(Long id) {
        return funcionarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Funcionario de ID " + id + " não encontrado."));
    }

    public Funcionario findByUserIdIfExists(Long userId) {
        return funcionarioRepository.findByUserId(userId).orElseThrow(() -> new ResourceNotFoundException(
                "Funcionario de ID " + userId + " não encontrado."));
    }

    public FuncionarioResponseDTO findByUserId(Long userId) {
        return funcionarioMapper.toDTO(this.findByUserIdIfExists(userId));
    }

    public FuncionarioResponseDTO findById(Long id) {
        return funcionarioMapper.toDTO(this.findByIdIfExists(id));
    }

    public FuncionarioResponseDTO create(FuncionarioCreateRequestDTO data) {
        if (funcionarioRepository.findByUserId(data.userId()).isPresent()) {
            throw new UnauthorizedException("Já possui um funcionário cadastrado.");
        }

        Funcionario funcionario = funcionarioMapper.toEntity(data);
        return funcionarioMapper.toDTO(this.save(funcionario));
    }

    public FuncionarioResponseDTO update(FuncionarioUpdateRequestDTO body, Long id) {
        Funcionario funcionario = this.findByIdIfExists(id);

        BeanUtils.copyProperties(body, funcionario, AttributeUtils.getNullOrBlankPropertyNames(body));

        return funcionarioMapper.toDTO(funcionarioRepository.save(funcionario));
    }

    public Funcionario save(Funcionario funcionario) {

        System.out.println("A1" + funcionario.getUserId());
        //Todo: Pegar Usuário corretamente para validar
        validateBeforeSave(funcionario.getUser());
        return funcionarioRepository.save(funcionario);
    }

    public void deleteById(Long id){
        Funcionario funcionario = this.findByIdIfExists(id);
        funcionario.setActive(false);

        funcionarioRepository.save(funcionario);
    }

    public void validateBeforeSave(User user) {
        validadeCPF(user.getCpf(), user.getId());
    }

    private void validadeCPF(String cpf, Long user_id) {
        Optional<User> user = userRepository.findByCpf(cpf);

        if (user.isPresent() && (user_id == null || !user.get().getId().equals(user_id))) {
            throw new BusinessException(
                    "CPF inválido: " + cpf + ". Já existe um funcionário com este CPF.",
                    HttpStatus.CONFLICT);
        }
    }
}
