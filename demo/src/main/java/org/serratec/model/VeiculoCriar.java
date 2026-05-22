package org.serratec.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class VeiculoCriar {

    @NotNull
    private UUID clienteId;

    @NotBlank
    private String marca;

    @NotBlank
    private String modelo;

    @NotNull
    @Min(1900)
    private Integer ano;

    @NotNull
    @Min(1)
    private Float valor;

    @NotBlank
    private String placa;

    @NotNull
    @Min(0)
    private float maximoDesconto;

    @NotNull
    private Boolean vendido;

    private Float valorVenda;
}
