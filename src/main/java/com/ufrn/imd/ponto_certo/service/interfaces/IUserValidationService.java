package com.ufrn.imd.ponto_certo.service.interfaces;

public interface IUserValidationService {
    void validateUser(Long userId, String errorMessage);
    void validateUser(Long userId);
}
