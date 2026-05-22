package org.serratec.model;

import lombok.Data;
import org.serratec.entity.VeiculoEntity;

import java.util.UUID;

@Data
public class VeiculoBuscar {

    private UUID id;
    private UUID clienteId;
    private String marca;
    private String modelo;
    private Integer ano;
    private float valor;
    private String placa;
    private float maximoDesconto;
    private Boolean vendido;
    private float valorVenda;

    public VeiculoBuscar(VeiculoEntity veiculo) {
        this.id = veiculo.getId();
        this.clienteId = veiculo.getCliente().getId();
        this.marca = veiculo.getMarca();
        this.modelo = veiculo.getModelo();
        this.ano = veiculo.getAno();
        this.valor = veiculo.getValor();
        this.placa = veiculo.getPlaca();
        this.maximoDesconto = veiculo.getMaximoDesconto();
        this.vendido = veiculo.isVendido();
        this.valorVenda = veiculo.getValorVenda();
    }
}
