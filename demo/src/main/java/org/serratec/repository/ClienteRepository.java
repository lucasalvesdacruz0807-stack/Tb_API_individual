package org.serratec.repository;

import org.serratec.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository <ClienteEntity, UUID> {


}
