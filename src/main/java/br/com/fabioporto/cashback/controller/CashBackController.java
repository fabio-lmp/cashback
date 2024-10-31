package br.com.fabioporto.cashback.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fabioporto.cashback.entity.CashBack;
import br.com.fabioporto.cashback.entity.Cliente;
import br.com.fabioporto.cashback.entity.dto.CashBackDTO;
import br.com.fabioporto.cashback.entity.dto.CashBackListIdDTO;
import br.com.fabioporto.cashback.service.CashBackService;
import br.com.fabioporto.cashback.service.ClienteService;

@RestController
@RequestMapping("/cashback")
public class CashBackController {

    private CashBackService cashBackService;
    private ClienteService clienteService;

    public CashBackController(CashBackService cashBackService, ClienteService clienteService) {
        this.cashBackService = cashBackService;
        this.clienteService = clienteService;
    }


    @GetMapping("/saldo/{id}")
    public BigDecimal getSaldo(@PathVariable("id") Long id) {
        return cashBackService.getSaldo(clienteService.findById(id)); 
    }

    @PostMapping
    public CashBack creditar(@RequestBody CashBackDTO dto) {
        Cliente cliente = clienteService.findById(dto.idCliente());

        CashBack cashBack = new CashBack();
        cashBack.setCliente(cliente);
        cashBack.setValorComprado(new BigDecimal(dto.valorComprado()) );

        Calendar cal = Calendar.getInstance(); 
        cal.setTime(new Date() ); 
        cal.add(Calendar.DATE, 30);  
        cashBack.setDataValidade(cal.getTime());

        cashBack.setCashBack(new BigDecimal(dto.valorComprado() * 0.05));

        return cashBackService.create(cashBack);
    }

    @PutMapping("/expirar/{id}")
    public CashBack expirar(@PathVariable("id") Long id) {

        CashBack cashBack = cashBackService.findById(id);


        return cashBackService.expire(cashBack);
    }   

    @GetMapping("/{id}")
    public List<CashBack> listDisponiveis(@PathVariable("id") Long id) {
        return cashBackService.listDisponiveis(id);
    }   
 
    @PostMapping("/resgatar")
    public List<CashBack> resgatar(@RequestBody CashBackListIdDTO dtoList) {

        List<CashBack> lista = new ArrayList<>();
        for (Long idDTO : dtoList.lista()) {
            CashBack cashBack = cashBackService.findById(idDTO);

            lista.add(cashBack);
        }

        return cashBackService.resgatarLista(lista);
    }
    
}