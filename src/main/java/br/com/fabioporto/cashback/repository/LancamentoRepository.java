package br.com.fabioporto.cashback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fabioporto.cashback.entity.Cliente;
import br.com.fabioporto.cashback.entity.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
    
    List<Lancamento> findByCliente(Cliente cliente);

}
