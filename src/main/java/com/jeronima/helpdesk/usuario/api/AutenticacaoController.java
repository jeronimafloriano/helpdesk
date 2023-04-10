package com.jeronima.helpdesk.usuario.api;


import com.jeronima.helpdesk.usuario.api.dto.AutenticacaoDto;
import com.jeronima.helpdesk.usuario.api.dto.RegistroAutenticacaoDto;
import com.jeronima.helpdesk.usuario.api.dto.RespostaAutenticacaoDto;
import com.jeronima.helpdesk.usuario.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {

    private final AuthenticationService service;

    public AutenticacaoController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/registrar")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<RespostaAutenticacaoDto> registrar(@RequestBody RegistroAutenticacaoDto requisicao){
        return ResponseEntity.ok(service.registrar(requisicao));
    }

    @PostMapping("/autenticar")
    @CrossOrigin(origins = {"http://localhost:8080", "http://localhost:4200"})
    public ResponseEntity<RespostaAutenticacaoDto> autenticar(@RequestBody AutenticacaoDto requisicao){
        return ResponseEntity.ok(
                service.autenticar(requisicao));
    }

}
