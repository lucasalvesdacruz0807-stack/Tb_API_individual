package org.serratec.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "veiculos")
public class VeiculoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @ManyToOne
    @JoinColumn (nullable = false)
    private ClienteEntity cliente;

    @Column (nullable = false)
    private String modelo;

    @Column (nullable = false)
    private String marca;

    @Column (nullable = false)
    private Integer ano;

    @Column (nullable = false)
    private float valor;

    @Column (nullable = false, unique = true)
    private String placa;

    @Column (nullable = false)
    private float maxDesconto;

    @Column (nullable = false)
    private boolean vendido;

    private float valorVenda;
}
