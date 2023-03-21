package com.jeronima.helpdesk.usuario.api.dto;



public class AutenticacaoDto {

    private String email;
    private String password;

    public AutenticacaoDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AutenticacaoDto(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




}
