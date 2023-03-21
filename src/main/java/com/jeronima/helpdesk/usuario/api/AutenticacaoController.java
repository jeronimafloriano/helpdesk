package com.jeronima.helpdesk.usuario.api;


import com.jeronima.helpdesk.usuario.api.dto.AutenticacaoDto;
import com.jeronima.helpdesk.usuario.api.dto.RegistroAutenticacaoDto;
import com.jeronima.helpdesk.usuario.api.dto.RespostaAutenticacaoDto;
import com.jeronima.helpdesk.usuario.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {

    private final AuthenticationService service;

    public AutenticacaoController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/registrar")
    public ResponseEntity<RespostaAutenticacaoDto> registrar(@RequestBody RegistroAutenticacaoDto requisicao){
        return ResponseEntity.ok(service.registrar(requisicao));
    }

    @PostMapping("/autenticar")
    public ResponseEntity<RespostaAutenticacaoDto> autenticar(@RequestBody AutenticacaoDto requisicao){
        return ResponseEntity.ok(service.autenticar(requisicao));
    }

}
