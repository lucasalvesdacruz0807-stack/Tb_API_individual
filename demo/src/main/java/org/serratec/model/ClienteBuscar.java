package org.serratec.model;

import lombok.Data;
import org.serratec.entity.ClienteEntity;

import java.util.UUID;

@Data
public class ClienteBuscar {

        private UUID id;
        private String nome;
        private String telefone;
        private String cpf;
        private String email;

        public ClienteBuscar(ClienteEntity cliente) {
                this.id = cliente.getId();
                this.nome = cliente.getNome();
                this.telefone = cliente.getTelefone();
                this.cpf = cliente.getCpf();
                this.email = cliente.getEmail();
        }
}
