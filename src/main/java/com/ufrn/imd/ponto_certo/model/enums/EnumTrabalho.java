package com.ufrn.imd.ponto_certo.model.enums;

public enum EnumTrabalho {
    REMOTO("Remoto"),
    PRESENCIAL("Presencial"),
    HIBRIDO("Híbrido");

    private final String description;

    EnumTrabalho(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
