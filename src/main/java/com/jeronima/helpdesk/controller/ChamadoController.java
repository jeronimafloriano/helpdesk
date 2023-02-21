package com.jeronima.helpdesk.controller;

import com.jeronima.helpdesk.domain.Chamado;
import com.jeronima.helpdesk.dto.ChamadoDto;
import com.jeronima.helpdesk.service.ChamadoService;
import com.jeronima.helpdesk.service.ClienteService;
import com.jeronima.helpdesk.service.TecnicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/chamados")
public class ChamadoController {

    private final ChamadoService service;
    private final TecnicoService tecnicoService;
    private final ClienteService clienteService;

    public ChamadoController(ChamadoService service, TecnicoService tecnicoService, ClienteService clienteService) {
        this.service = service;
        this.tecnicoService = tecnicoService;
        this.clienteService = clienteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChamadoDto> findById(@PathVariable Integer id){
        Chamado chamado = service.findById(id);
        return ResponseEntity.ok().body(new ChamadoDto(chamado));
    }

    @GetMapping
    public ResponseEntity<List<ChamadoDto>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<ChamadoDto> create(@Valid @RequestBody ChamadoDto dto){
        var tecnico = tecnicoService.findById(dto.getTecnico());
        var cliente = clienteService.findById(dto.getCliente());
        var chamado= service.create(dto, tecnico, cliente);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(chamado.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChamadoDto> update(@PathVariable Integer id, @Valid @RequestBody ChamadoDto dto){
        var tecnico = tecnicoService.findById(dto.getTecnico());
        var cliente = clienteService.findById(dto.getCliente());
        var chamado = service.update(id, dto, tecnico, cliente);

        return ResponseEntity.ok().body(new ChamadoDto(chamado));
    }
}
