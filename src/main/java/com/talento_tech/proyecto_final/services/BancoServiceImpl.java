package com.talento_tech.proyecto_final.services;

import com.talento_tech.proyecto_final.entities.Banco;
import com.talento_tech.proyecto_final.repositories.BancoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BancoServiceImpl implements BancoService {

    @Autowired
    BancoRepository repository;


    @Override
    public List<Banco> retrieve(String nombreBanco) {
        if (!nombreBanco.isBlank()) {
            return repository.findByBancoContaining(nombreBanco);
        }

        return repository.findAll();
    }

    @Override
    public Optional<Banco> retieveId(Long id) {
        return repository.findById(id);
    }


    @Override
    public Banco save(Banco banco) {
        banco.setBanco(banco.getBanco().toUpperCase());

        return repository.save(banco);
    }

    @Override
    public Banco update(Banco bancoDb, Banco banco) {
        bancoDb.setBanco(banco.getBanco());
        bancoDb.setCodigoBCRA(banco.getCodigoBCRA());
        bancoDb.setCuit(banco.getCuit());
        bancoDb.setOrdenListado(banco.getOrdenListado());

        return repository.save(bancoDb);
    }


    @Override
    public void deleteId(Long id) {
        repository.deleteById(id);
    }

    private Boolean isValid(Banco banco) {
        Boolean ret=true;

        // Verifica que el nombre del banco no esté vacío o que sean espacios en blanco
        if (banco.getBanco().isEmpty() || banco.getBanco().isBlank()) {
            ret = false;
        }

        // Toda entidad bancaria debe tener un código asignado por el BCRA.
        if (banco.getCodigoBCRA() == 0) {
            ret = false;
        }

        // TODO: Generar una funcion de validacion del numero de cuit.
        ret = validaCuit (banco.getCuit());


        return ret;
    }

    private Boolean validaCuit (String cuit) {
        // TODO: Generar aqui la fincion de validacion de numero de cuit.
        return true;
    }
}
