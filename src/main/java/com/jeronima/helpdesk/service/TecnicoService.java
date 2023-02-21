package com.jeronima.helpdesk.service;

import com.jeronima.helpdesk.domain.Tecnico;
import com.jeronima.helpdesk.repository.TecnicoRepository;
import com.jeronima.helpdesk.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TecnicoService {
    private final TecnicoRepository repository;

    public TecnicoService(TecnicoRepository repository) {
        this.repository = repository;
    }

    public Tecnico findById(Integer id){
        return repository.findById(id).orElseThrow( () ->
                new ObjectNotFoundException("Técnico não encontrado com o ID: " + id));
    }
}
