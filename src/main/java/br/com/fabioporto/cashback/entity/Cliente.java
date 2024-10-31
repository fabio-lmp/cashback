package br.com.fabioporto.cashback.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "clientes")
public class Cliente {

    private Long id;
    private String nome;
    private String whatsapp;
    private Date dataNascimento;
    private boolean profissional;
    private boolean recebeWhatsapp;
    private Date ultimaCompra;
    private Date ultimoEnvioWhatsapp;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(length = 12)
    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    @Temporal(TemporalType.DATE)
    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public boolean isProfissional() {
        return profissional;
    }

    public void setProfissional(boolean profissional) {
        this.profissional = profissional;
    }

    public boolean isRecebeWhatsapp() {
        return recebeWhatsapp;
    }

    public void setRecebeWhatsapp(boolean recebeWhatsapp) {
        this.recebeWhatsapp = recebeWhatsapp;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getUltimaCompra() {
        return ultimaCompra;
    }

    public void setUltimaCompra(Date ultimaCompra) {
        this.ultimaCompra = ultimaCompra;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getUltimoEnvioWhatsapp() {
        return ultimoEnvioWhatsapp;
    }

    public void setUltimoEnvioWhatsapp(Date ultimoEnvioWhatsapp) {
        this.ultimoEnvioWhatsapp = ultimoEnvioWhatsapp;
    }

}
