package br.com.fabioporto.cashback.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fabioporto.cashback.entity.Cliente;
import br.com.fabioporto.cashback.entity.UserRole;
import br.com.fabioporto.cashback.entity.Usuario;
import br.com.fabioporto.cashback.service.ClienteService;
import br.com.fabioporto.cashback.service.UsuarioService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private ClienteService clienteService;
    private UsuarioService usuarioService;

    public ClienteController(ClienteService clienteService, UsuarioService usuarioService) {
        this.clienteService = clienteService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    List<Cliente> list() {
        return clienteService.list();
    } 

    @PostMapping
    Cliente create(@RequestBody Cliente cliente) {
    
        Cliente newCliente = clienteService.create(cliente);
        String senha = new SimpleDateFormat("ddMMyyyy").format(cliente.getDataNascimento());
        System.out.println(senha);
        Usuario usuario = new Usuario(cliente.getWhatsapp(), senha , newCliente, UserRole.USER);

        usuarioService.create(usuario);

        return newCliente;
    }


    @PutMapping
    Cliente update(@RequestBody Cliente cliente) {
        return clienteService.update(cliente);
    }

    @DeleteMapping("{id}")
    List<Cliente> delete(@PathVariable("id") Long id) {
        Cliente cliente = clienteService.findById(id);
        
        Usuario usuario = usuarioService.findByCliente(cliente);
        if (usuario != null) {
            usuarioService.delete(usuario.getId());
        }

        return clienteService.delete(id);
    }

}
