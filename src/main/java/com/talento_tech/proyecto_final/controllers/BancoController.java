package com.talento_tech.proyecto_final.controllers;

import com.talento_tech.proyecto_final.entities.Banco;
import com.talento_tech.proyecto_final.services.BancoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/bancos")
public class BancoController {

    @Autowired
    private BancoService service;

    @GetMapping
    public ResponseEntity<?> retrieve (@RequestParam(required = false, defaultValue = "") String nombreBanco) {
        return service.retrieve(nombreBanco);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retieveId (@PathVariable Long id) {
        return service.retrieveId(id);
    }

    @PostMapping
    public ResponseEntity<?> addNew (@RequestBody Banco banco) {
        return service.save(banco);
    }

    @PutMapping
    public ResponseEntity<?> update (@RequestBody Banco banco) {
        return service.update(banco);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteId (@PathVariable Long id) {
        return service.deleteId(id);
    }
}
