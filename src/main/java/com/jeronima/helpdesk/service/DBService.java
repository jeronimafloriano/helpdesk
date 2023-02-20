package com.jeronima.helpdesk.service;

import com.jeronima.helpdesk.domain.*;
import com.jeronima.helpdesk.repository.ChamadoRepository;
import com.jeronima.helpdesk.repository.ClienteRepository;
import com.jeronima.helpdesk.repository.TecnicoRepository;
import org.springframework.stereotype.Service;

@Service
public class DBService {

    private final ClienteRepository clienteRepository;
    private final TecnicoRepository tecnicoRepository;
    private final ChamadoRepository chamadoRepository;

    public DBService(ClienteRepository clienteRepository, TecnicoRepository tecnicoRepository, ChamadoRepository chamadoRepository) {
        this.clienteRepository = clienteRepository;
        this.tecnicoRepository = tecnicoRepository;
        this.chamadoRepository = chamadoRepository;
    }

    public void instanciaDB(){
        Tecnico tecnico = new Tecnico("Marcos", "85266933145", "marcos@email.com", "123");
        Cliente cliente = new Cliente("Maria", "52477899635", "maria@email.com", "321");
        Chamado chamado = new Chamado(Prioridade.ALTA, Status.ANDAMENTO, "Ajuste Impressora", tecnico, cliente);

        tecnicoRepository.save(tecnico);
        clienteRepository.save(cliente);
        chamadoRepository.save(chamado);
    }
}
