package br.com.itau.casebackendjr.controller;

import br.com.itau.casebackendjr.CaseBackendJrApplication;
import br.com.itau.casebackendjr.domain.dto.RegisterDTO;
import br.com.itau.casebackendjr.domain.dto.RegisterUpdateDTO;
import br.com.itau.casebackendjr.domain.ports.interfaces.RegisterServicePort;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("cadastro")
public class RegisterController {

    private static Logger logger = LoggerFactory.getLogger(CaseBackendJrApplication.class);

    private RegisterServicePort servicePort;

    public RegisterController(RegisterServicePort service){
        this.servicePort = service;
    }

    @GetMapping
    public ResponseEntity<List<RegisterDTO>> getAll(){
        logger.info("Realizando request GET para '/cadastro'");
        return ResponseEntity.ok(servicePort.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegisterDTO> getById(@PathVariable Long id){
        logger.info(String.format("Realizando request GET para '/cadastro/%d'", id));
        return ResponseEntity.ok(servicePort.getPerson(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<RegisterDTO> create(@RequestBody @Valid RegisterDTO dto, UriComponentsBuilder uriBuilder){
        logger.info("Realizando request POST para '/cadastro'");
        var responseBody = servicePort.create(dto);
        var uri = uriBuilder.path("cadastro/{id}").buildAndExpand(responseBody.id()).toUri();
        return ResponseEntity.created(uri).body(responseBody);
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<RegisterDTO> update(@PathVariable Long id, @RequestBody RegisterUpdateDTO dto){
        logger.info(String.format("Realizando request PATCH para '/cadastro/%d'", id));
        var responseBody = servicePort.update(id, dto);
        return ResponseEntity.ok(responseBody);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        logger.info(String.format("Realizando request DELETE para '/cadastro/%d'", id));
        servicePort.delete(id);
        return ResponseEntity.noContent().build();
    }

}
