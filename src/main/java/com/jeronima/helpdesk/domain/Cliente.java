package com.jeronima.helpdesk.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cliente extends Pessoa {

    private static final long serialVersionUID = 6291235869367526706L;
    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Chamado> chamados = new ArrayList<>();

    public Cliente() {}

    public Cliente(String nome, String cpf, String email, String senha) {
        super(nome, cpf, email, senha, Perfil.CLIENTE);
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

}
