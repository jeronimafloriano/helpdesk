package com.jeronima.helpdesk.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeronima.helpdesk.domain.Chamado;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

public class ChamadoDto implements Serializable {

    private static final long serialVersionUID = -4201518505867312713L;
    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFechamento;
    @NotNull(message = "Obrigatório informar a PRIORIDADE.")
    private Integer prioridade;
    @NotNull(message = "Obrigatório informar o STATUS.")
    private Integer status;
    @NotNull(message = "Obrigatório informar o TÍTULO.")
    private String titulo;
    private String observacoes;
    @NotNull(message = "Obrigatório informar o TÉCNICO.")
    private Integer tecnico;
    private String nomeTecnico;
    @NotNull(message = "Obrigatório informar o CLIENTE.")
    private Integer cliente;
    private String nomeCliente;

    public ChamadoDto(){}

    public ChamadoDto(Chamado chamado) {
        this.id = chamado.getId();
        this.dataAbertura = chamado.getDataAbertura();
        this.dataFechamento = chamado.getDataFechamento();
        this.prioridade = chamado.getPrioridade().getCodigo();
        this.status = chamado.getStatus().getCodigo();
        this.titulo = chamado.getTitulo();
        this.observacoes = chamado.getObservacoes();
        this.tecnico = chamado.getTecnico().getId();
        this.nomeTecnico = chamado.getTecnico().getNome();
        this.cliente = chamado.getCliente().getId();;
        this.nomeCliente = chamado.getCliente().getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDate getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDate dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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

    public Integer getTecnico() {
        return tecnico;
    }

    public void setTecnico(Integer tecnico) {
        this.tecnico = tecnico;
    }

    public String getNomeTecnico() {
        return nomeTecnico;
    }

    public void setNomeTecnico(String nomeTecnico) {
        this.nomeTecnico = nomeTecnico;
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
}
