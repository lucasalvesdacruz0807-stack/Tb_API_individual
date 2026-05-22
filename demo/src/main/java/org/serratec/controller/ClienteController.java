package org.serratec.controller;

import jakarta.validation.Valid;
import org.serratec.model.ClienteBuscar;
import org.serratec.model.ClienteCriar;
import org.serratec.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping ("/cliente")
public class ClienteController {

    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Void> inserir(@Valid @RequestBody ClienteCriar clienteCriar) {
        this.clienteService.inserir(clienteCriar);
        return ResponseEntity.status(CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<ClienteBuscar>> buscar(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String cpf
    ) {

        return ResponseEntity.ok(clienteService.buscar(nome, cpf));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {

        clienteService.deletar(id);

        return ResponseEntity.noContent().build();
    }


}
