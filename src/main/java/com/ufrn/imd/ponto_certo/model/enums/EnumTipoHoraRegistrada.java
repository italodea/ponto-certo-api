package com.ufrn.imd.ponto_certo.model.enums;

public enum EnumTipoHoraRegistrada {
    ENTRADA("Entrada"),
    INTERVALO_INICIO("Intervalo Início"),
    INTERVALO_FIM("Intervalo Fim"),
    SAIDA("Saída");

    private final String description;

    EnumTipoHoraRegistrada(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
