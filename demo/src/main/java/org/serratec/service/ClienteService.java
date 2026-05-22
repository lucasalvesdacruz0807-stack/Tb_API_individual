package org.serratec.service;

import org.serratec.entity.ClienteEntity;
import org.serratec.model.ClienteBuscar;
import org.serratec.model.ClienteCriar;
import org.serratec.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    public void inserir (ClienteCriar clienteCriar) {
        ClienteEntity cliente = new ClienteEntity(clienteCriar);
        this.clienteRepository.save(cliente);
    }


    public List<ClienteBuscar> buscar (String nome, String cpf) {
        List <ClienteEntity> clientes;


        if (cpf != null && !cpf.isBlank()) {
            clientes = clienteRepository.findByCpf(cpf);
        }

         else if (nome != null && !nome.isBlank()) {

            clientes = clienteRepository.findByNomeContainingIgnoreCase(nome);


        } else {

            clientes = clienteRepository.findAll();
        }

         return clientes
                 .stream()
                 .map(ClienteBuscar::new)
                 .toList();
    }

    public void deletar(UUID id) {

        Optional<ClienteEntity> clienteOpt = clienteRepository.findById(id);

        if (clienteOpt.isEmpty()) {
            // Exception será feita depois
        }

        clienteRepository.deleteById(id);
    }

}
