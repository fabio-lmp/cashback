package br.com.fabioporto.cashback.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.fabioporto.cashback.entity.CashBack;
import br.com.fabioporto.cashback.entity.Cliente;
import br.com.fabioporto.cashback.entity.Lancamento;
import br.com.fabioporto.cashback.entity.LancamentoTipo;
import br.com.fabioporto.cashback.repository.LancamentoRepository;

@Service
public class LancamentoService {
    
    private LancamentoRepository lancamentoRepository;

    public LancamentoService(LancamentoRepository lancamentoRepository) {
        this.lancamentoRepository = lancamentoRepository;
    }

    public List<Lancamento> listByCliente(Cliente cliente) {
        return lancamentoRepository.findByCliente(cliente);
    }

    public Lancamento create(Lancamento lancamento) {
        return lancamentoRepository.save(lancamento);
    }

    public Lancamento credit(CashBack cashBack) {

        Lancamento lancamento = new Lancamento();
        lancamento.setCliente(cashBack.getCliente());
        lancamento.setCashBack(cashBack);
        lancamento.setTipo(LancamentoTipo.CREDITADO);
        lancamento.setDescricao("CRÉDITO CASHBACK COMPRA REALIZADA");
        lancamento.setValor(cashBack.getCashBack());

        return create(lancamento);       
 
    }

    public Lancamento expire(CashBack cashBack) {

        Lancamento lancamento = new Lancamento();
        lancamento.setCliente(cashBack.getCliente());
        lancamento.setCashBack(cashBack);
        lancamento.setTipo(LancamentoTipo.EXPIRADO);
        lancamento.setDescricao("DEBITO CASHBACK EXPIRADO");
        lancamento.setValor(cashBack.getCashBack().multiply(new BigDecimal(-1)));

        return create(lancamento);       
    }

    public Lancamento rescue(CashBack cashBack) {

        Lancamento lancamento = new Lancamento();
        lancamento.setCliente(cashBack.getCliente());
        lancamento.setCashBack(cashBack);
        lancamento.setTipo(LancamentoTipo.RESGATADO);
        lancamento.setDescricao("DEBITO CASHBACK RESGATADO");
        lancamento.setValor(cashBack.getCashBack().multiply(new BigDecimal(-1)));

        return create(lancamento);       
    }

}
