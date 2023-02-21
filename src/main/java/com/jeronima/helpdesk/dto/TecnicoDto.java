package com.jeronima.helpdesk.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeronima.helpdesk.domain.Perfil;
import com.jeronima.helpdesk.domain.Tecnico;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TecnicoDto  {

    @NotNull(message = "Obrigatório informar o NOME.")
    private String nome;
    @NotNull(message = "Obrigatório informar o CPF.")
    private String cpf;
    @NotNull(message = "Obrigatório informar o EMAIL.")
    private String email;
    @NotNull(message = "Obrigatório informar a SENHA.")
    private String senha;
    private Set<Integer> perfis = new HashSet<>();
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao;

    public TecnicoDto(){}

    public TecnicoDto(Tecnico tecnico) {
        this.nome = tecnico.getNome();
        this.cpf = tecnico.getCpf();
        this.email = tecnico.getEmail();
        this.senha = tecnico.getSenha();
        this.dataCriacao = tecnico.getDataCriacao();
        this.perfis = tecnico.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil.getCodigo());
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }


}
