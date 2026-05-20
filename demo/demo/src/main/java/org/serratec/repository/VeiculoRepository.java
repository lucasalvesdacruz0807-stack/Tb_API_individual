package org.serratec.repository;

import org.serratec.entity.VeiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VeiculoRepository extends JpaRepository<VeiculoEntity, UUID> {
    
}
