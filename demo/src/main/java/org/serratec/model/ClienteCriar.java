package org.serratec.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteCriar {

    @NotBlank
    private String nome;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String telefone;

    @NotBlank
    private String cpf;

}
