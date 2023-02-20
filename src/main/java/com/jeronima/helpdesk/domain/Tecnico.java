package com.jeronima.helpdesk.domain;


import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tecnico extends Pessoa {

    private static final long serialVersionUID = 6291235869367526706L;
    @OneToMany(mappedBy = "tecnico")
    private List<Chamado> chamados = new ArrayList<>();

    public Tecnico() {}

    public Tecnico(String nome, String cpf, String email, String senha) {
        super(nome, cpf, email, senha, Perfil.TECNICO);
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

}
