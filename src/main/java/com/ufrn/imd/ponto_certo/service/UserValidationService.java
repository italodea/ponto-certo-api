package com.ufrn.imd.ponto_certo.service;

import com.ufrn.imd.ponto_certo.exception.ForbiddenOperationException;

import com.ufrn.imd.ponto_certo.service.interfaces.IJwtService;
import com.ufrn.imd.ponto_certo.service.interfaces.IUserValidationService;
import org.springframework.stereotype.Service;

@Service
public class UserValidationService implements IUserValidationService {

    private final IJwtService jwtService;

    public UserValidationService(IJwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void validateUser(Long userId, String errorMessage) {
        Long userIdToken = jwtService.extractUserIdFromRequest();

        if (!userId.equals(userIdToken)) {
            throw (errorMessage != null && !errorMessage.trim().isEmpty())
                    ? new ForbiddenOperationException(errorMessage)
                    : new ForbiddenOperationException();
        }

    }

    @Override
    public void validateUser(Long userId) {
        validateUser(userId, null);
    }
}
