package com.talento_tech.proyecto_final.services;

import com.talento_tech.proyecto_final.entities.Banco;
import com.talento_tech.proyecto_final.exceptions.IngresoInvalidoException;
import com.talento_tech.proyecto_final.repositories.BancoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BancoServiceImpl1 implements BancoService1 {

    @Autowired
    private BancoRepository repository;


    @Override
    public ResponseEntity<?> retrieve(String nombreBanco) {
        List<Banco> bancos = new ArrayList<>();

        if (!nombreBanco.isBlank()) {
            bancos = repository.findByBancoContaining(nombreBanco);
        } else {
            bancos = repository.findAll();
        }

        if (bancos.size() > 0) {
            return ResponseEntity.ok(bancos);
        }

        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> retieveId(Long id) {
        if (id > 0) {
            Optional<Banco> optBanco = repository.findById(id);

            if (optBanco.isPresent()) {
                return ResponseEntity.ok(optBanco.get());
            }

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Debe proporcionar un id válido para poder buscarlo en la base de datos");
    }

    @Override
    public ResponseEntity<?> save(Banco banco) {
        return null;
    }

    @Override
    public ResponseEntity<?> update(Banco banco) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteId(Long id) {
        return null;
    }
}
