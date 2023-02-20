package com.jeronima.helpdesk.service;

import com.jeronima.helpdesk.domain.Tecnico;
import com.jeronima.helpdesk.repository.TecnicoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Service
public class TecnicoService {
    private final TecnicoRepository repository;

    public TecnicoService(TecnicoRepository repository) {
        this.repository = repository;
    }

    public Tecnico findById(Integer id){
        return repository.findById(id).orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Técnico não encontrado."));
    }
}
