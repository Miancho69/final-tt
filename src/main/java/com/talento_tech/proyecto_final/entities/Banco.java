package com.talento_tech.proyecto_final.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "bancos")
public class Banco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "banco_id")
    private Long bancoId;

    @Column(name = "codigo_bcra")
    private Integer codigoBCRA;
    private String banco;
    private String cuit;

    @Column(name = "orden_listado")
    private Integer ordenListado;

    public Long getBancoId() {
        return bancoId;
    }

    public void setBancoId(Long bancoId) {
        this.bancoId = bancoId;
    }

    public Integer getCodigoBCRA() {
        return codigoBCRA;
    }

    public void setCodigoBCRA(Integer codigoBCRA) {
        this.codigoBCRA = codigoBCRA;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public Integer getOrdenListado() {
        return ordenListado;
    }

    public void setOrdenListado(Integer ordenListado) {
        this.ordenListado = ordenListado;
    }

    @Override
    public String toString() {
        return banco ;
    }
}
