package com.talento_tech.proyecto_final.services;

import com.talento_tech.proyecto_final.entities.Banco;
import org.springframework.http.ResponseEntity;

public interface BancoService1 {
    ResponseEntity<?> retrieve (String nombreBanco);
    ResponseEntity<?> retieveId(Long id);
    ResponseEntity<?> save (Banco banco);
    ResponseEntity<?> update (Banco banco);
    ResponseEntity<?> deleteId (Long id);
}
