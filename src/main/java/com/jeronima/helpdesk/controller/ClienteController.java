package com.jeronima.helpdesk.controller;

import com.jeronima.helpdesk.domain.Cliente;
import com.jeronima.helpdesk.dto.ClienteDto;
import com.jeronima.helpdesk.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> findById(@PathVariable Integer id){
        Cliente cliente = service.findById(id);
        return ResponseEntity.ok().body(new ClienteDto(cliente));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDto>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<ClienteDto> create(@Valid @RequestBody ClienteDto dto){
        var cliente = service.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> update(@PathVariable Integer id, @Valid @RequestBody ClienteDto dto){
        var cliente = service.update(id, dto);
        return ResponseEntity.ok().body(new ClienteDto(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClienteDto> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
