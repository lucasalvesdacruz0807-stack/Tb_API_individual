package org.serratec.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.serratec.model.VeiculoCriar;

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
    private float maximoDesconto;

    @Column (nullable = false)
    private boolean vendido;

    private float valorVenda;

    public VeiculoEntity(ClienteEntity cliente, VeiculoCriar veiculoCriar) {
        this.cliente = cliente;
        this.marca = veiculoCriar.getMarca();
        this.modelo = veiculoCriar.getModelo();
        this.ano = veiculoCriar.getAno();
        this.valor = veiculoCriar.getValor();
        this.placa = veiculoCriar.getPlaca();
        this.maximoDesconto = veiculoCriar.getMaximoDesconto();
        this.vendido = Boolean.TRUE.equals(veiculoCriar.getVendido());
        this.valorVenda = veiculoCriar.getValorVenda();
    }
}
