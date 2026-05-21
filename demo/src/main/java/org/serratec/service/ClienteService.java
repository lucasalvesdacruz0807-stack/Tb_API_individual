package org.serratec.service;

import org.serratec.entity.ClienteEntity;
import org.serratec.model.ClienteCriar;
import org.serratec.repository.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void inserir(ClienteCriar clienteCriar) {
        ClienteEntity cliente = new ClienteEntity(clienteCriar);
        this.clienteRepository.save(cliente);
    }
}
