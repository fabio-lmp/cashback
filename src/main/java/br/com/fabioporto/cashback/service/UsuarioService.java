package br.com.fabioporto.cashback.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.fabioporto.cashback.entity.Cliente;
import br.com.fabioporto.cashback.entity.Usuario;
import br.com.fabioporto.cashback.exception.UserExistsExcetion;
import br.com.fabioporto.cashback.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> create(Usuario usuario) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(usuario.getPassword());
        
        if (findByLogin(usuario.getLogin()) != null) {
            throw new UserExistsExcetion();    
        }
        
        usuario.setPassword(encryptedPassword);
        usuarioRepository.save(usuario);

        return list();
    }

    public List<Usuario> list() {
        return usuarioRepository.findAll();
    }

    public Usuario findByLogin(String login) {
        return (Usuario) usuarioRepository.findByLogin(login);
    }

    public Usuario findByCliente(Cliente cliente) {
        Usuario usuario = usuarioRepository.findByCliente(cliente);

        return usuario!=null?usuario:null;
    }

    public List<Usuario> delete(Long id) {
        usuarioRepository.deleteById(id);
        return list();
    }
}
