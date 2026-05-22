package org.serratec.controller;

import jakarta.validation.Valid;
import org.serratec.model.VeiculoAtualizar;
import org.serratec.model.VeiculoBuscar;
import org.serratec.model.VeiculoCriar;
import org.serratec.service.VeiculoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping ("/veiculo")
public class VeiculoController {

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {

        this.veiculoService = veiculoService;
    }

}
