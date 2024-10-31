package br.com.fabioporto.cashback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fabioporto.cashback.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByWhatsapp(String whatsapp);

}
