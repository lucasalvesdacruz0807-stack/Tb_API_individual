package org.serratec.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class VeiculoAtualizar {

    @NotBlank
    private String marca;

    @NotBlank
    private String modelo;

    @NotNull
    @Min(1900)
    private Integer ano;

    @NotBlank
    @Min(1)
    private float valor;

    @NotBlank
    @Min(0)
    private float maximoDesconto;

    @NotNull
    private Boolean vendido;

    private Float valorVenda;

}
