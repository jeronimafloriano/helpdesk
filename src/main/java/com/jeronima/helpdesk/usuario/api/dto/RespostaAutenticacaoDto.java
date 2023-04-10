package com.jeronima.helpdesk.usuario.api.dto;

import com.fasterxml.jackson.annotation.JsonValue;

public class RespostaAutenticacaoDto {
    @JsonValue
    private String token;

    public RespostaAutenticacaoDto(String token) {
        this.token = token;
    }

    public RespostaAutenticacaoDto() {
    }
}
