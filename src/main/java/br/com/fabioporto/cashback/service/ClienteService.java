package br.com.fabioporto.cashback.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.fabioporto.cashback.entity.Cliente;
import br.com.fabioporto.cashback.repository.ClienteRepository;

@Service
public class ClienteService {
    
    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> list() {
        return clienteRepository.findAll();
    }

    public Cliente create(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente update(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> delete(Long id) {
        clienteRepository.deleteById(id);
        return list();
    }

    public Cliente findByWhatsapp(String whatsapp) {
        return clienteRepository.findByWhatsapp(whatsapp);
    }

    public Cliente findById(Long id) {
        Optional<Cliente> optCliente = clienteRepository.findById(id);

        return optCliente.isPresent()?optCliente.get():null;
    }
}
