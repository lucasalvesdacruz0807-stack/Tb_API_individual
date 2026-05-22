package org.serratec.controller;

import jakarta.validation.Valid;
import org.serratec.exception.MensagemResposta;
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

    @PostMapping
    public ResponseEntity<MensagemResposta> inserir(@RequestBody @Valid VeiculoCriar veiculoCriar) {
        veiculoService.inserir(veiculoCriar);

        return ResponseEntity.status(HttpStatus.CREATED).body(new MensagemResposta
                ("Veículo cadastrado com sucesso"));
    }


    @GetMapping
    public ResponseEntity<List<VeiculoBuscar>> buscar(
            @RequestParam(required = false) String placa,
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) String modelo
    ) {

        return ResponseEntity.ok(veiculoService.buscar(placa, marca, modelo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensagemResposta> atualizar
            (@PathVariable UUID id, @RequestBody @Valid VeiculoAtualizar veiculoAtualizar) {

        veiculoService.atualizar(id, veiculoAtualizar);

        return ResponseEntity.ok(new MensagemResposta("Veículo atualizado com sucesso"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensagemResposta> deletar(@PathVariable UUID id) {

        veiculoService.deletar(id);

        return ResponseEntity.ok(new MensagemResposta("Veículo removido com sucesso"));
    }
}
