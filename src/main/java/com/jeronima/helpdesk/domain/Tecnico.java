package com.jeronima.helpdesk.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jeronima.helpdesk.dto.TecnicoDto;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tecnico extends Pessoa {

    private static final long serialVersionUID = 6291235869367526706L;

    @JsonIgnore
    @OneToMany(mappedBy = "tecnico")
    private List<Chamado> chamados = new ArrayList<>();

    public Tecnico() {}

    public Tecnico(String nome, String cpf, String email, String senha) {
        super(nome, cpf, email, senha, Perfil.TECNICO);
    }

    public Tecnico(TecnicoDto dto) {
        super(dto.getNome(), dto.getCpf(), dto.getEmail(), dto.getSenha(), Perfil.TECNICO);
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

}
