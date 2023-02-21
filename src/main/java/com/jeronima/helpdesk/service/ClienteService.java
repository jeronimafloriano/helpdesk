package com.jeronima.helpdesk.service;

import com.jeronima.helpdesk.domain.Cliente;
import com.jeronima.helpdesk.dto.ClienteDto;
import com.jeronima.helpdesk.exceptions.DataIntegrityViolationException;
import com.jeronima.helpdesk.exceptions.ObjectNotFoundException;
import com.jeronima.helpdesk.repository.ClienteRepository;
import com.jeronima.helpdesk.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    private final ClienteRepository repository;
    private final PessoaRepository pessoaRepository;

    public ClienteService(ClienteRepository repository, PessoaRepository pessoaRepository) {
        this.repository = repository;
        this.pessoaRepository = pessoaRepository;
    }

    public Cliente findById(Integer id){
        return repository.findById(id).orElseThrow( () ->
                new ObjectNotFoundException("Cliente não encontrado com o ID: " + id));
    }

    public List<ClienteDto> findAll() {
        var clientes = repository.findAll();
        return clientes.stream().map(tec -> new ClienteDto(tec)).collect(Collectors.toList());
    }

    public Cliente create(ClienteDto dto) {
        validaCpf(dto.getCpf());
        validaEmail(dto.getEmail());

        var cliente =  new Cliente(dto);
        return repository.save(cliente);
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


    public Cliente update(Integer id, ClienteDto dto) {
        var cliente = findById(id);
        cliente.setNome(dto.getNome());
        cliente.setCpf(dto.getCpf());
        cliente.setEmail(dto.getEmail());
        cliente.setSenha(dto.getSenha());

        return repository.save(cliente);
    }

    public void delete(Integer id) {
        var cliente = findById(id);
        var chamados = cliente.getChamados();

        if(chamados.size() > 0 ){
            var abertos = chamados.stream().filter(chamado -> chamado.getDataFechamento() == null).count() > 0;
            if(abertos)
                throw new DataIntegrityViolationException("Técnico possui chamados em aberto que " +
                        "ainda não foram encerrados, e por isso não poderá ser deletado.");
        }
        repository.deleteById(cliente.getId());
    }
}
