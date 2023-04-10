package com.jeronima.helpdesk.controller;

import com.jeronima.helpdesk.domain.Tecnico;
import com.jeronima.helpdesk.dto.TecnicoDto;
import com.jeronima.helpdesk.service.TecnicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tecnicos")
public class TecnicoController {

    private final TecnicoService service;

    public TecnicoController(TecnicoService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TecnicoDto> findById(@PathVariable Integer id){
        Tecnico tecnico = service.findById(id);
        return ResponseEntity.ok().body(new TecnicoDto(tecnico));
    }

    @GetMapping
    @CrossOrigin(origins = {"http://localhost:4200"})
    public ResponseEntity<List<TecnicoDto>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<TecnicoDto> create(@Valid @RequestBody TecnicoDto dto){
        var tecnico = service.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(tecnico.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TecnicoDto> update(@PathVariable Integer id, @Valid @RequestBody TecnicoDto dto){
        var tenico = service.update(id, dto);
        return ResponseEntity.ok().body(new TecnicoDto(tenico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TecnicoDto> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
