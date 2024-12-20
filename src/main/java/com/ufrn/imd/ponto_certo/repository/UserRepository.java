package com.ufrn.imd.ponto_certo.repository;

import com.ufrn.imd.ponto_certo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailIgnoreCaseAndActiveTrue(String email);
    Optional<User> findByPhoneNumberAndActiveTrue(String phoneNumber);
    Optional<User> findByIdAndActiveTrue(Long id);
    Optional<User> findByCpfAndActiveTrue(String email);
    Optional<User> findByCpf(String cpf);
}
