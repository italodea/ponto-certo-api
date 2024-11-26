package com.ufrn.imd.ponto_certo.service;

import com.ufrn.imd.ponto_certo.dto.request.AuthRequestDTO;
import com.ufrn.imd.ponto_certo.dto.response.AuthResponseDTO;
import com.ufrn.imd.ponto_certo.model.User;
import com.ufrn.imd.ponto_certo.model.UserDetailsImpl;
import com.ufrn.imd.ponto_certo.repository.UserRepository;
import com.ufrn.imd.ponto_certo.exception.ResourceNotFoundException;
import com.ufrn.imd.ponto_certo.service.interfaces.IAuthenticationService;
import com.ufrn.imd.ponto_certo.service.interfaces.IJwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements IAuthenticationService {

    private final UserRepository userRepository;
    private final IJwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationService(UserRepository userRepository,
                                 IJwtService jwtService,
                                 AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthResponseDTO authenticate(AuthRequestDTO request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(), request.password()
                )
        );

        User user = userRepository.findByEmailIgnoreCaseAndActiveTrue(request.email())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));

        String token = jwtService.generateToken(new UserDetailsImpl(user));

        return new AuthResponseDTO(token);
    }
}
