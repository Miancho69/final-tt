package com.talento_tech.proyecto_final.services;

import com.talento_tech.proyecto_final.entities.Banco;

import java.util.List;
import java.util.Optional;

public interface BancoService {
    List<Banco> retrieve (String nombreBanco);
    Optional<Banco> retieveId(Long id);
    Banco save (Banco banco);
    Banco update (Banco bancoDb, Banco banco);
    void deleteId (Long id);
}
