package com.talento_tech.proyecto_final.services;

import com.talento_tech.proyecto_final.entities.Banco;
import com.talento_tech.proyecto_final.exceptions.EmptyNameException;
import com.talento_tech.proyecto_final.exceptions.InvalidBCRACodeException;
import com.talento_tech.proyecto_final.exceptions.InvalidCuitNumberException;
import com.talento_tech.proyecto_final.exceptions.InvalidInformationException;
import com.talento_tech.proyecto_final.exceptions.InvalidInputException;
import com.talento_tech.proyecto_final.exceptions.EmptyListException;
import com.talento_tech.proyecto_final.repositories.BancoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BancoServiceImpl implements BancoService {

    @Autowired
    private BancoRepository repository;


    @Override
    @Transactional(readOnly = true)
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

        throw new EmptyListException("No se encuentran datos para mostrar");

//        return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                .body("No se encuentran datos para mostrar");
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> retrieveId(Long id) {
        if (id > 0) {
            Optional<Banco> optBanco = repository.findById(id);

            if (optBanco.isPresent()) {
                return ResponseEntity.ok(optBanco.get());
            }

            throw new EmptyListException("No se encuentran datos para mostrar");
        }

        throw new InvalidInputException("Debe proporcionar un id válido para poder buscarlo en la base de datos");

//        return ResponseEntity
//                .status(HttpStatus.BAD_REQUEST)
//                .body("Debe proporcionar un id válido para poder buscarlo en la base de datos");
    }

    @Override
    @Transactional
    public ResponseEntity<?> save(Banco banco) {
        if (isValid(banco)) {
            banco.setBanco(banco.getBanco().toUpperCase());

            Banco newBanco = repository.save(banco);

            return ResponseEntity.status(HttpStatus.CREATED).body(newBanco);
        } else {
            throw new InvalidInformationException("La información proporcionada no es válida para realizar la grabación.");
        }

//        return ResponseEntity
//                .status(HttpStatus.NOT_ACCEPTABLE)
//                .body("La información proporcionada no es válida para realizar la grabación.");
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(Banco banco) {
        if (banco.getBancoId() > 0) {
            Optional<Banco> optBanco = repository.findById(banco.getBancoId());

            if (optBanco.isPresent()) {
                if (isValid(banco)) {
                    Banco bancoDb = optBanco.get();

                    bancoDb.setBanco(banco.getBanco().toUpperCase());
                    bancoDb.setCodigoBCRA(banco.getCodigoBCRA());
                    bancoDb.setCuit(banco.getCuit());
                    bancoDb.setOrdenListado(banco.getOrdenListado());

                    Banco bancoUpdated = repository.save(bancoDb);

                    return ResponseEntity.status(HttpStatus.CREATED).body(bancoUpdated);
                } else {
                    throw new InvalidInformationException("La información proporcionada no pasa las validaciones para realizar la grabacion.");

//                    return ResponseEntity
//                            .status(HttpStatus.NOT_ACCEPTABLE)
//                            .body("La información proporcionada no pasa las validaciones para realizar la grabacion.");
                }
            } else {
                throw new EmptyListException("No se encuentran datos para mostrar");
            }

//            return ResponseEntity.notFound().build();
        } else {
            throw new InvalidInputException("Debe proporcionar un id válido para poder buscarlo en la base de datos");
        }

//        return ResponseEntity
//                .status(HttpStatus.NOT_ACCEPTABLE)
//                .body("La información proporcionada no permite actualizar los datos modificados.");
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteId(Long id) {
        Optional<Banco> optBanco = repository.findById(id);

        if (optBanco.isPresent()) {
            Banco bancoDeleted = optBanco.get();

            repository.deleteById(id);

            return ResponseEntity.status(HttpStatus.FOUND).body(bancoDeleted);
        }

        throw new EmptyListException("No se encuentran datos para mostrar");

//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)
//                .body("No encontramos el id proporcionado para eliminarlo");
    }

    private Boolean isValid(Banco banco) {
        Boolean ret=true;

        // Verifica que el nombre del banco no esté vacío o que sean espacios en blanco
        if (banco.getBanco().isEmpty() || banco.getBanco().isBlank()) {
            throw new EmptyNameException("El nombre no puede estar vacio");
        }

        // Toda entidad bancaria debe tener un código asignado por el BCRA.
        if (ret) {
            if (banco.getCodigoBCRA() <= 0) {
                throw new InvalidBCRACodeException("El código del BCRA debe ser válido");
            }
        }

        if (ret) {
            // TODO: Generar una funcion de validacion del numero de cuit.
            ret = validaCuit (banco.getCuit());

            if (!ret) {
                throw new InvalidCuitNumberException("El número de CUIT es inválido");
            }
        }

        return ret;
    }

    private Boolean validaCuit (String cuit) {
        // TODO: Generar aqui la fincion de validacion de numero de cuit.
        // TODO: Esto puede ir a una clase de validaciones/librerias generales.

        if (cuit.trim().length() != 11) {
            return false; // No tiene 11 dígitos
        }


        return true;
    }
}
