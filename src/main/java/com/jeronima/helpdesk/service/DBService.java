package com.jeronima.helpdesk.service;

import com.jeronima.helpdesk.domain.*;
import com.jeronima.helpdesk.repository.ChamadoRepository;
import com.jeronima.helpdesk.repository.ClienteRepository;
import com.jeronima.helpdesk.repository.TecnicoRepository;
import com.jeronima.helpdesk.usuario.model.Role;
import com.jeronima.helpdesk.usuario.model.Usuario;
import com.jeronima.helpdesk.usuario.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DBService {

    private final ClienteRepository clienteRepository;
    private final TecnicoRepository tecnicoRepository;
    private final ChamadoRepository chamadoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder;

    public DBService(ClienteRepository clienteRepository, TecnicoRepository tecnicoRepository,
                     ChamadoRepository chamadoRepository, UsuarioRepository usuarioRepository
                     , PasswordEncoder encoder) {

        this.clienteRepository = clienteRepository;
        this.tecnicoRepository = tecnicoRepository;
        this.chamadoRepository = chamadoRepository;
        this.usuarioRepository = usuarioRepository;
        this.encoder = encoder;
    }

    public void instanciaDB(){
        Tecnico tecnico = new Tecnico("Marcos", "85266933145",
                "marcos@email.com", encoder.encode("123")); //encriptando a senha
        Tecnico tecnico2 = new Tecnico("Paulo", "15266933777",
                "paulo@email.com", encoder.encode("123"));
        Tecnico tecnico3 = new Tecnico("Luana", "25266933888",
                "luana@email.com", encoder.encode("999"));
        Tecnico tecnico4 = new Tecnico("Samara", "35266933101",
                "samara@email.com", encoder.encode("123"));
        Tecnico tecnico5 = new Tecnico("Lorrane", "45266933102",
                "lorrane@email.com", encoder.encode("123"));
        Tecnico tecnico6 = new Tecnico("Luiz", "55266933103",
                "luiz@email.com", encoder.encode("123"));
        Cliente cliente = new Cliente("Maria", "52477899635",
                "maria@email.com", encoder.encode("321")); //encriptando a senha
        Chamado chamado = new Chamado(Prioridade.ALTA, Status.ANDAMENTO, "Ajuste Impressora",
                tecnico, cliente);
        Usuario usuario = new Usuario(UUID.randomUUID().toString(), "João", "Silva", "joaosilva@email.com",
                encoder.encode("147563"), Role.ADMIN);

        tecnicoRepository.save(tecnico);
        tecnicoRepository.save(tecnico2);
        tecnicoRepository.save(tecnico3);
        tecnicoRepository.save(tecnico4);
        tecnicoRepository.save(tecnico5);
        tecnicoRepository.save(tecnico6);
        clienteRepository.save(cliente);
        chamadoRepository.save(chamado);
        usuarioRepository.save(usuario);
    }

}
