package com.ufrn.imd.ponto_certo.model.enums;

public enum EnumTipoValidacao {
    BIOMETRIA("Biometria"),
    QR_CODE("QR Code"),
    LOCAL("Localização");

    private final String description;

    EnumTipoValidacao(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}