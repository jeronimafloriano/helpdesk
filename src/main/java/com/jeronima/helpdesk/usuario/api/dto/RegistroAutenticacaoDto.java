package com.jeronima.helpdesk.usuario.api.dto;



public class RegistroAutenticacaoDto {

    public RegistroAutenticacaoDto(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.senha = password;
    }

    public RegistroAutenticacaoDto(){}

    private String firstName;
    private String lastName;
    private String email;
    private String senha;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return senha;
    }

    public void setPassword(String password) {
        this.senha = password;
    }
}
