package com.ufrn.imd.ponto_certo.service.interfaces;

import com.ufrn.imd.ponto_certo.dto.request.AuthRequestDTO;
import com.ufrn.imd.ponto_certo.dto.response.AuthResponseDTO;

public interface IAuthenticationService {
    AuthResponseDTO authenticate(AuthRequestDTO request);
}
