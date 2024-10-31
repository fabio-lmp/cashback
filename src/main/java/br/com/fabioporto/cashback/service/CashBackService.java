package br.com.fabioporto.cashback.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.fabioporto.cashback.entity.CashBack;
import br.com.fabioporto.cashback.entity.Cliente;
import br.com.fabioporto.cashback.repository.CashBackRepository;

@Service
public class CashBackService {
    
    private CashBackRepository cashBackRepository;
    private LancamentoService lancamentoService;

    public CashBackService(CashBackRepository cashBackRepository, LancamentoService lancamentoService) {
        this.cashBackRepository = cashBackRepository;
        this.lancamentoService = lancamentoService;
    }

    public List<CashBack> list() {
        return cashBackRepository.findAll();
    }

    public CashBack findById(Long id) {
        Optional<CashBack> opt = cashBackRepository.findById(id);

        return opt.isPresent()?opt.get():null;
    }

    public CashBack create(CashBack cashBack) {
        CashBack newCashBack = cashBackRepository.save(cashBack);

        lancamentoService.credit(newCashBack);

        return newCashBack;
    }

    public CashBack update(CashBack cashBack) {
        return cashBackRepository.save(cashBack);
    }

    public List<CashBack> delete(Long id) {
        cashBackRepository.deleteById(id);

        return list();
    }

    public CashBack expire(CashBack cashBack) {
        cashBack.setDataExpiracao(new Date());

        lancamentoService.expire(cashBack);

        return cashBackRepository.save(cashBack);
    }

    public BigDecimal getSaldo(Cliente cliente) {
        Map<String,BigDecimal> saldo = cashBackRepository.getSaldoQueryParam(cliente.getId());
        return saldo.size()!=0?saldo.get("soma"):new BigDecimal(0D);
    }

    public List<CashBack> listDisponiveis(Long id) {
        return cashBackRepository.listDisponiveisQueryParam(id);
    }

    public CashBack resgatar(CashBack cashBack) {
        cashBack.setDataUtilizacao(new Date());

        lancamentoService.rescue(cashBack);

        return cashBackRepository.save(cashBack);
    }

    public List<CashBack> resgatarLista(List<CashBack> list) {
        List<CashBack> resgatados = new ArrayList<>();
        for (CashBack item : list) {
            resgatados.add(resgatar(item));
        }
        return resgatados;
    }

}
