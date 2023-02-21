package com.jeronima.helpdesk.service;

import com.jeronima.helpdesk.domain.Tecnico;
import com.jeronima.helpdesk.dto.TecnicoDto;
import com.jeronima.helpdesk.exceptions.DataIntegrityViolationException;
import com.jeronima.helpdesk.repository.PessoaRepository;
import com.jeronima.helpdesk.repository.TecnicoRepository;
import com.jeronima.helpdesk.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

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

        var tecnico =  new Tecnico(dto);
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


    public Tecnico update(Integer id, TecnicoDto dto) {
        var tecnico = findById(id);
        tecnico.setNome(dto.getNome());
        tecnico.setCpf(dto.getCpf());
        tecnico.setEmail(dto.getEmail());
        tecnico.setSenha(dto.getSenha());

        return repository.save(tecnico);
    }

    public void delete(Integer id) {
        var tecnico = findById(id);
        var chamados = tecnico.getChamados();

        if(chamados.size() > 0 ){
            var abertos = chamados.stream().filter(chamado -> chamado.getDataFechamento() == null).count() > 0;
            if(abertos)
                throw new DataIntegrityViolationException("Técnico possui chamados em aberto que " +
                        "ainda não foram encerrados, e por isso não poderá ser deletado.");
        }
        repository.deleteById(tecnico.getId());
    }
}
