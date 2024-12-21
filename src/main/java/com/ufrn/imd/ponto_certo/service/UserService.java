package com.ufrn.imd.ponto_certo.service;

import com.ufrn.imd.ponto_certo.dto.request.UserCreateDTO;
import com.ufrn.imd.ponto_certo.dto.request.UserUpdateDTO;
import com.ufrn.imd.ponto_certo.dto.response.UserResponseDTO;
import com.ufrn.imd.ponto_certo.exception.ResourceNotFoundException;
import com.ufrn.imd.ponto_certo.mapper.UserMapper;
import com.ufrn.imd.ponto_certo.model.User;
import com.ufrn.imd.ponto_certo.repository.UserRepository;
import com.ufrn.imd.ponto_certo.exception.BusinessException;
import com.ufrn.imd.ponto_certo.util.AttributeUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserValidationService IUserValidationService;

    @Transactional
    public void delete(Long userId) {
        IUserValidationService.validateUser(userId);
        User user = findById(userId);

        user.setActive(false);
        userRepository.save(user);
    }

    public UserResponseDTO update(UserUpdateDTO dto, Long userId) {
        User user = findById(userId);
        validateBeforeUpdate(user);

        BeanUtils.copyProperties(dto, user, AttributeUtils.getNullOrBlankPropertyNames(dto));
        return userMapper.toDto(userRepository.save(user));
    }

    public UserResponseDTO save(UserCreateDTO dto) {
        User entity = userMapper.toEntity(dto);
        validateBeforeSave(entity);

        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return userMapper.toDto(userRepository.save(entity));
    }

    public User findById(Long userId) {
        return userRepository.findByIdAndActiveTrue(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Usuário de ID " + userId + " não encontrado."
                ));
    }

    private void validateBeforeUpdate(User entity) {
        IUserValidationService.validateUser(entity.getId());
        validateBeforeSave(entity);
    }

    private void validateBeforeSave(User entity) {
        validateEmail(entity.getEmail(), entity.getId());
        validatePhoneNumber(entity.getPhoneNumber(), entity.getId());
        validateCpf(entity.getCpf(), entity.getId());
    }

    private void validateEmail(String email, Long id) {
        Optional<User> user = userRepository.findByEmailIgnoreCaseAndActiveTrue(email);
        if (user.isPresent() && (id == null || !user.get().getId().equals(id))) {
            throw new BusinessException(
                    "E-mail inválido: " + email + ". Um usuário cadastrado já utiliza este e-mail.",
                    HttpStatus.CONFLICT
            );
        }
    }

    private void validatePhoneNumber(String phone, Long id) {
        Optional<User> user = userRepository.findByPhoneNumberAndActiveTrue(phone);
        if (user.isPresent() && (id == null || !user.get().getId().equals(id))) {
            throw new BusinessException(
                    "Número de telefone inválido: " + phone + ". Um usuário cadastrado já utiliza este número de telefone.",
                    HttpStatus.CONFLICT
            );
        }
    }

    private void validateCpf(String cpf, Long id) {
        Optional<User> user = userRepository.findByCpfAndActiveTrue(cpf);
        if (user.isPresent() && (id == null || !user.get().getId().equals(id))) {
            throw new BusinessException(
                    "CPF inválido: " + cpf + ". Um usuário cadastrado já utiliza este CPF.",
                    HttpStatus.CONFLICT
            );
        }
    }

}
