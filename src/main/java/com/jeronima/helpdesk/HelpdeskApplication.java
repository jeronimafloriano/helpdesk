package com.jeronima.helpdesk;

import com.jeronima.helpdesk.domain.*;
import com.jeronima.helpdesk.repository.ChamadoRepository;
import com.jeronima.helpdesk.repository.ClienteRepository;
import com.jeronima.helpdesk.repository.TecnicoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {

	private final ClienteRepository clienteRepository;
	private final TecnicoRepository tecnicoRepository;
	private final ChamadoRepository chamadoRepository;

	public HelpdeskApplication(ClienteRepository clienteRepository, TecnicoRepository tecnicoRepository, ChamadoRepository chamadoRepository) {
		this.clienteRepository = clienteRepository;
		this.tecnicoRepository = tecnicoRepository;
		this.chamadoRepository = chamadoRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Tecnico tecnico = new Tecnico("Marcos", "85266933145", "marcos@email.com", "123");
		Cliente cliente = new Cliente("Maria", "52477899635", "maria@email.com", "321");
		Chamado chamado = new Chamado(Prioridade.ALTA, Status.ANDAMENTO, "Ajuste Impressora", tecnico, cliente);

		tecnicoRepository.save(tecnico);
		clienteRepository.save(cliente);
		chamadoRepository.save(chamado);
	}
}
