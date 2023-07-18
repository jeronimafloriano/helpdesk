package com.jeronima.helpdesk.usuario.service;

import com.jeronima.helpdesk.security.JwtService;
import com.jeronima.helpdesk.usuario.api.dto.AutenticacaoDto;
import com.jeronima.helpdesk.usuario.api.dto.RegistroAutenticacaoDto;
import com.jeronima.helpdesk.usuario.api.dto.RespostaAutenticacaoDto;
import com.jeronima.helpdesk.usuario.model.Role;
import com.jeronima.helpdesk.usuario.model.Usuario;
import com.jeronima.helpdesk.usuario.repository.UsuarioRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class AuthenticationService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder,
                                 JwtService jwtService, AuthenticationManager authenticationManager) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public RespostaAutenticacaoDto registrar(RegistroAutenticacaoDto requisicao){
        Usuario usuario = Usuario.builder()
                .id(UUID.randomUUID().toString())
                .firstName(requisicao.getFirstName())
                .lastName(requisicao.getLastName())
                .email(requisicao.getEmail())
                .password(passwordEncoder.encode(requisicao.getPassword()))
                .role(Role.ADMIN)
                .build();

        usuarioRepository.saveAndFlush(usuario);
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", usuario.getRole());

        String token = jwtService.generateToken(extraClaims, usuario);
        return new RespostaAutenticacaoDto(token);
    }

    public RespostaAutenticacaoDto autenticar(@RequestBody AutenticacaoDto requisicao){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requisicao.getEmail(), requisicao.getSenha())
        );

        Usuario usuario = usuarioRepository.findByEmail(
                requisicao.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));

        String token = jwtService.generateToken(usuario);

        return new RespostaAutenticacaoDto(token);

    }
}
