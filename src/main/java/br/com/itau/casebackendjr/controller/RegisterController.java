package br.com.itau.casebackendjr.controller;

import br.com.itau.casebackendjr.domain.dto.RegisterDTO;
import br.com.itau.casebackendjr.domain.dto.RegisterUpdateDTO;
import br.com.itau.casebackendjr.domain.ports.interfaces.RegisterServicePort;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("cadastro")
public class RegisterController {

    private RegisterServicePort servicePort;

    public RegisterController(RegisterServicePort service){
        this.servicePort = service;
    }

    @GetMapping
    public ResponseEntity<List<RegisterDTO>> getAll(){
        return ResponseEntity.ok(servicePort.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegisterDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(servicePort.getPerson(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<RegisterDTO> create(@RequestBody @Valid RegisterDTO dto, UriComponentsBuilder uriBuilder){
        var responseBody = servicePort.create(dto);
        var uri = uriBuilder.path("cadastro/{id}").buildAndExpand(responseBody.id()).toUri();
        return ResponseEntity.created(uri).body(responseBody);
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<RegisterDTO> update(@PathVariable Long id, @RequestBody RegisterUpdateDTO dto){
        var responseBody = servicePort.update(id, dto);
        return ResponseEntity.ok(responseBody);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        servicePort.delete(id);
        return ResponseEntity.noContent().build();
    }

}