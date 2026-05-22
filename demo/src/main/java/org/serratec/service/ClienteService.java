package org.serratec.service;

import org.serratec.entity.ClienteEntity;
import org.serratec.exception.ConflitoException;
import org.serratec.exception.NaoEncontradoException;
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
        List<ClienteEntity> clienteExistente = clienteRepository.findByCpf(clienteCriar.getCpf());

        if (!clienteExistente.isEmpty()) {
            throw new ConflitoException("CPF já cadastrado");
        }
        ClienteEntity cliente = new ClienteEntity(clienteCriar);
        this.clienteRepository.save(cliente);
    }


    public List<ClienteBuscar> buscar (String nome, String cpf) {
        List <ClienteEntity> clientes;

        if (cpf != null && !cpf.isBlank()) {
            clientes = clienteRepository.findByCpf(cpf);

            if (clientes.isEmpty()) {
                throw new NaoEncontradoException("CPF não encontrado");
            }
        }
         else if (nome != null && !nome.isBlank()) {

            clientes = clienteRepository.findByNomeContainingIgnoreCase(nome);

            if (clientes.isEmpty()) {
                throw new NaoEncontradoException("Nome não encontrado");
            }

        } else {

            clientes = clienteRepository.findAll();

            if (clientes.isEmpty()) {
                throw new NaoEncontradoException("Nenhum cliente encontrado");
            }
        }

         return clientes
                 .stream()
                 .map(ClienteBuscar::new)
                 .toList();
    }

    public void deletar(UUID id) {

        Optional<ClienteEntity> clienteOpt = clienteRepository.findById(id);

        if (clienteOpt.isEmpty()) {
            throw new NaoEncontradoException("Cliente não encontrado");
        }

        clienteRepository.deleteById(id);
    }

}
