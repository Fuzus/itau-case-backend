package br.com.itau.casebackendjr.controller;

import br.com.itau.casebackendjr.domain.dto.PersonalDataDTO;
import br.com.itau.casebackendjr.domain.dto.PersonalDataUpdateDTO;
import br.com.itau.casebackendjr.domain.ports.interfaces.PersonalServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("cadastro")
public class PersonalDataController {

    private PersonalServicePort servicePort;

    public PersonalDataController(PersonalServicePort service){
        this.servicePort = service;
    }

    @GetMapping
    public ResponseEntity<List<PersonalDataDTO>> getAll(){
        return ResponseEntity.ok(servicePort.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonalDataDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(servicePort.getPerson(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PersonalDataDTO> create(@RequestBody PersonalDataDTO dto, UriComponentsBuilder uriBuilder){
        var responseBody = servicePort.create(dto);
        var uri = uriBuilder.path("cadastro/{id}").buildAndExpand(responseBody.id()).toUri();
        return ResponseEntity.created(uri).body(responseBody);
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<PersonalDataDTO> update(@PathVariable Long id, @RequestBody PersonalDataUpdateDTO dto){
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
