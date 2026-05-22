package org.serratec.repository;

import org.serratec.entity.VeiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VeiculoRepository extends JpaRepository<VeiculoEntity, UUID> {

    Optional<VeiculoEntity> findByPlaca(String placa);

    List<VeiculoEntity> findByMarcaContainingIgnoreCase(String marca);

    List<VeiculoEntity> findByModeloContainingIgnoreCase(String modelo);
    
}
