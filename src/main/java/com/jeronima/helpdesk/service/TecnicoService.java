package com.jeronima.helpdesk.service;

import com.jeronima.helpdesk.domain.Tecnico;
import com.jeronima.helpdesk.dto.TecnicoDto;
import com.jeronima.helpdesk.exceptions.DataIntegrityViolationException;
import com.jeronima.helpdesk.repository.PessoaRepository;
import com.jeronima.helpdesk.repository.TecnicoRepository;
import com.jeronima.helpdesk.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TecnicoService {
    private final TecnicoRepository repository;
    private final PessoaRepository pessoaRepository;

    public TecnicoService(TecnicoRepository repository, PessoaRepository pessoaRepository) {
        this.repository = repository;
        this.pessoaRepository = pessoaRepository;
    }

    public Tecnico findById(Integer id){
        return repository.findById(id).orElseThrow( () ->
                new ObjectNotFoundException("Técnico não encontrado com o ID: " + id));
    }

    public List<TecnicoDto> findAll() {
        var tecnicos = repository.findAll();
        return tecnicos.stream().map(tec -> new TecnicoDto(tec)).collect(Collectors.toList());
    }

    public Tecnico create(TecnicoDto dto) {
        validaCpf(dto.getCpf());
        validaEmail(dto.getEmail());

        var tecnico =  new Tecnico(dto.getNome(), dto.getCpf(), dto.getEmail(), dto.getSenha());
        return repository.save(tecnico);
    }

    private void validaCpf(String cpf) {
       pessoaRepository.findByCpf(cpf).ifPresent(x -> {
           throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
       });

    }

    private void validaEmail(String email) {
        pessoaRepository.findByEmail(email).ifPresent(x -> {
            throw new DataIntegrityViolationException("Email já cadastrado no sistema");
        });
    }

}
