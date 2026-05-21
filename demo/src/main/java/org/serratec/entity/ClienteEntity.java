package org.serratec.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.serratec.model.ClienteCriar;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clientes")
public class ClienteEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    @Column (nullable = false)
    private String nome;

    @Column (nullable = false)
    private String email;

    @Column (nullable = false)
    private String telefone;

    @Column (nullable = false, unique = true)
    private String cpf;

    public ClienteEntity(ClienteCriar cliente) {
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
    }

}
