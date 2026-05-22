package org.serratec.repository;

import org.serratec.entity.ClienteEntity;
import org.serratec.model.ClienteBuscar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository <ClienteEntity, UUID> {

    List <ClienteEntity> findByCpf(String cpf);

    List <ClienteEntity> findByNomeContainingIgnoreCase(String nome);

}
