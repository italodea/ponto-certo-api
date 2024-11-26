package com.ufrn.imd.ponto_certo.model.enums;

public enum EnumRegime {
    CLT("CLT"),
    PJ("PJ"),
    ESTAGIO("Estágio"),
    TEMPORARIO("Temporário"),
    TERCEIRIZADO("Terceirizado"),
    AUTONOMO("Autônomo"),
    MEI("MEI"),
    OUTRO("Outro");

    private final String description;

    EnumRegime(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
