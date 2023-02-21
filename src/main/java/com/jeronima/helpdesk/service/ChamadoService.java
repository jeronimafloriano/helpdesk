package com.jeronima.helpdesk.service;

import com.jeronima.helpdesk.domain.*;
import com.jeronima.helpdesk.dto.ChamadoDto;
import com.jeronima.helpdesk.exceptions.ObjectNotFoundException;
import com.jeronima.helpdesk.repository.ChamadoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChamadoService {

    private final ChamadoRepository repository;

    public ChamadoService(ChamadoRepository repository) {
        this.repository = repository;
    }

    public Chamado findById(Integer id){
        return repository.findById(id).orElseThrow( () ->
                new ObjectNotFoundException("Chamado não encontrado com o ID: " + id));
    }

    public List<ChamadoDto> findAll() {
        var chamados = repository.findAll();
        return chamados.stream().map(chamado -> new ChamadoDto(chamado)).collect(Collectors.toList());
    }

    public Chamado create(ChamadoDto dto, Tecnico tecnico, Cliente cliente) {
        var chamado =  new Chamado(Prioridade.toEnum(dto.getPrioridade()),
                            Status.toEnum(dto.getStatus()),
                            dto.getTitulo(), tecnico, cliente);

        verificaStatusEAtualizaFechamento(dto);

        return repository.save(chamado);
    }

    public Chamado update(Integer id, ChamadoDto dto, Tecnico tecnico, Cliente cliente) {
        var chamado = findById(id);
        chamado.setPrioridade(Prioridade.toEnum(dto.getPrioridade()));
        chamado.setStatus(Status.toEnum(dto.getStatus()));
        chamado.setTitulo(dto.getTitulo());
        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);

        verificaStatusEAtualizaFechamento(dto);

        return repository.save(chamado);
    }

    private void verificaStatusEAtualizaFechamento(ChamadoDto dto){
        if (dto.getStatus().equals(2)){
            dto.setDataFechamento(LocalDate.now());
        }
    }
}
