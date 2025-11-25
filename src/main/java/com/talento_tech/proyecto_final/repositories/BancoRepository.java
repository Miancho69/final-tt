package com.talento_tech.proyecto_final.repositories;

import com.talento_tech.proyecto_final.entities.Banco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BancoRepository extends JpaRepository<Banco, Long> {
    List<Banco> findByBancoContaining (String txt);
}
