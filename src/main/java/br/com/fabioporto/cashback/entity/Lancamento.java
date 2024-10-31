package br.com.fabioporto.cashback.entity;

import java.util.Date;
import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "lancamentos")
public class Lancamento {

    private Long id;
    private Date dataHora = new Date();
    private Cliente cliente;
    private CashBack cashBack;
    private LancamentoTipo tipo;
    private String descricao;
    private BigDecimal valor;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Temporal(TemporalType.TIME)
    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @ManyToOne
    @JoinColumn(name = "id_cash_back")
    public CashBack getCashBack() {
        return cashBack;
    }

    public void setCashBack(CashBack cashBack) {
        this.cashBack = cashBack;
    }

    @Enumerated(EnumType.STRING)
    public LancamentoTipo getTipo() {
        return tipo;
    }

    public void setTipo(LancamentoTipo tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

}
