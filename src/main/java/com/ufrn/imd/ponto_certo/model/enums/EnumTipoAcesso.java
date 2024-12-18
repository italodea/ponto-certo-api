package com.ufrn.imd.ponto_certo.model.enums;

public enum EnumTipoAcesso {
    ADMIN("Admin"),
    GESTOR("Gestor"),
    FUNCIONARIO("Funcionário");

    private final String description;

    EnumTipoAcesso(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
