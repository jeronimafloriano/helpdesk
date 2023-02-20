package com.jeronima.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Chamado implements Serializable {


    private static final long serialVersionUID = -8726403375749094647L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFechamento;
    private Prioridade prioridade;
    private Status status;
    private String titulo;
    private String observacoes;
    @ManyToOne()
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;
    @ManyToOne()
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Chamado(){
        this.dataAbertura = LocalDate.now();
    }

    public Chamado(Prioridade prioridade, Status status, String titulo, Tecnico tecnico, Cliente cliente) {
        this.prioridade = prioridade;
        this.status = status;
        this.titulo = titulo;
        this.tecnico = tecnico;
        this.cliente = cliente;
        this.dataAbertura = LocalDate.now();
    }

    public Integer getId() {
        return id;
    }


    public LocalDate getDataAbertura() {
        return dataAbertura;
    }


    public LocalDate getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDate dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public Cliente getCliente() {
        return cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chamado chamado = (Chamado) o;
        return id.equals(chamado.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Chamado{" +
                "id=" + id +
                ", dataAbertura=" + dataAbertura +
                ", dataFechamento=" + dataFechamento +
                ", prioridade=" + prioridade +
                ", status=" + status +
                ", titulo='" + titulo + '\'' +
                ", observacoes='" + observacoes + '\'' +
                ", tecnico=" + tecnico +
                ", cliente=" + cliente +
                '}';
    }
}
