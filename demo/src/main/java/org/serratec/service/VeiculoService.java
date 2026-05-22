package org.serratec.service;

import org.serratec.entity.ClienteEntity;
import org.serratec.entity.VeiculoEntity;
import org.serratec.model.VeiculoAtualizar;
import org.serratec.model.VeiculoBuscar;
import org.serratec.model.VeiculoCriar;
import org.serratec.repository.ClienteRepository;
import org.serratec.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VeiculoService {

    private VeiculoRepository veiculoRepository;
    private ClienteRepository clienteRepository;

    public VeiculoService(VeiculoRepository veiculoRepository,  ClienteRepository clienteRepository) {
        this.veiculoRepository = veiculoRepository;
        this.clienteRepository = clienteRepository;
    }

    //inserir
    public void inserir(VeiculoCriar veiculoCriar) {

        if (Boolean.TRUE.equals(veiculoCriar.getVendido()) && veiculoCriar.getValorVenda() == null) {
            throw new RuntimeException("valorVenda é obrigatório quando vendido for true");
        }

        ClienteEntity cliente = clienteRepository.findById(veiculoCriar.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        VeiculoEntity veiculo = new VeiculoEntity(cliente, veiculoCriar);

        veiculoRepository.save(veiculo);
    }

    // buscar
    public List<VeiculoBuscar> buscar(String placa, String marca, String modelo) {

        List<VeiculoEntity> veiculos;


        if (placa != null && !placa.isBlank()) {

            Optional<VeiculoEntity> veiculoOpt = veiculoRepository.findByPlaca(placa);

            veiculos = veiculoOpt
                    .map(List::of)
                    .orElse(List.of());

        }
        else if (marca != null && !marca.isBlank()) {

            veiculos = veiculoRepository.findByMarcaContainingIgnoreCase(marca);

        }
        else if (modelo != null && !modelo.isBlank()) {

            veiculos = veiculoRepository.findByModeloContainingIgnoreCase(modelo);

        }
        else {

            veiculos = veiculoRepository.findAll();
        }

        return veiculos.stream()
                .map(VeiculoBuscar::new)
                .toList();
    }

    public void atualizar(UUID id, VeiculoAtualizar veiculoAtualizar) {

        VeiculoEntity veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        veiculo.setMarca(veiculoAtualizar.getMarca());
        veiculo.setModelo(veiculoAtualizar.getModelo());
        veiculo.setAno(veiculoAtualizar.getAno());
        veiculo.setValor(veiculoAtualizar.getValor());
        veiculo.setMaximoDesconto(veiculoAtualizar.getMaximoDesconto());
        veiculo.setVendido(veiculoAtualizar.getVendido());
        veiculo.setValorVenda(veiculoAtualizar.getValorVenda());

        if (Boolean.TRUE.equals(veiculoAtualizar.getVendido()) && veiculoAtualizar.getValorVenda() == null) {
            throw new RuntimeException("Valor de venda é obrigatório quando o veículo está vendido");
        }

        veiculoRepository.save(veiculo);
    }

    public void deletar(UUID id) {

        Optional<VeiculoEntity> veiculoOpt = veiculoRepository.findById(id);

        if (veiculoOpt.isEmpty()) {
            // Exception futura
        }

        veiculoRepository.deleteById(id);
    }
}
