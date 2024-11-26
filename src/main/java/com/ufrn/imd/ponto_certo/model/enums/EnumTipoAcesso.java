package com.ufrn.imd.ponto_certo.model.enums;

public enum EnumTipoAcesso {
    ADMIN("Admin"),
    FUNCIONARIO("Funcionário"),
    GESTOR("Gestor");

    private final String description;

    EnumTipoAcesso(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
