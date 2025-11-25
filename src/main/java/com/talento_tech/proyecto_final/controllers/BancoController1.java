package com.talento_tech.proyecto_final.controllers;

import com.talento_tech.proyecto_final.services.BancoService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/bancos1")
public class BancoController1 {

    @Autowired
    private BancoService1 service;

    @GetMapping
    public ResponseEntity<?> retrieve (@RequestParam(required = false, defaultValue = "") String nombreBanco) {
        return service.retrieve(nombreBanco);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retieveId (@PathVariable Long id) {
        return service.retieveId(id);
    }
}
