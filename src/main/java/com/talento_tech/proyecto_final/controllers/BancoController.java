package com.talento_tech.proyecto_final.controllers;

import com.talento_tech.proyecto_final.entities.Banco;
import com.talento_tech.proyecto_final.services.BancoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bancos")
public class BancoController {

    @Autowired
    private BancoService service;

    @GetMapping
    public ResponseEntity<?> retrieve (@RequestParam(required = false, defaultValue = "") String nombreBanco) {
        List<Banco> bancos = service.retrieve(nombreBanco);

        return ResponseEntity.ok(bancos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retieveId (@PathVariable Long id) {
        Optional<Banco> optBanco = service.retieveId(id);

        if (optBanco.isPresent()) {
            return ResponseEntity.ok(optBanco.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> addNew (@RequestBody Banco banco) {
        Banco newBanco = service.save(banco);

        return ResponseEntity.status(HttpStatus.CREATED).body(newBanco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update (@RequestBody Banco banco, @PathVariable Long id) {
        Optional<Banco> optBanco = service.retieveId(id);

        if (optBanco.isPresent()) {
            Banco bancoDb = optBanco.get();
            Banco bancoUpdated = service.update(bancoDb, banco);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(bancoUpdated);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteId (@PathVariable Long id) {
        Optional<Banco> optBanco = service.retieveId(id);

        if (optBanco.isPresent()) {
            service.deleteId(id);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
