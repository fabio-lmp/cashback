package br.com.fabioporto.cashback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.fabioporto.cashback.entity.Cliente;
import br.com.fabioporto.cashback.entity.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
 
    UserDetails findByLogin(String login);
    Usuario findByCliente(Cliente cliente);

}
