package org.serratec.service;

import org.serratec.entity.ClienteEntity;
import org.serratec.entity.VeiculoEntity;
import org.serratec.exception.ConflitoException;
import org.serratec.exception.DadosInvalidosException;
import org.serratec.exception.NaoEncontradoException;
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


    public void inserir(VeiculoCriar veiculoCriar) {

        if (Boolean.TRUE.equals(veiculoCriar.getVendido()) && veiculoCriar.getValorVenda() == null) {
            throw new DadosInvalidosException("valorVenda é obrigatório se vendido for true");
        }
        Optional<VeiculoEntity> placaExistente = veiculoRepository.findByPlaca(veiculoCriar.getPlaca());

        if (placaExistente.isPresent()) {
            throw new ConflitoException("Placa já cadastrada");
        }

        ClienteEntity cliente = clienteRepository.findById(veiculoCriar.getClienteId())
                .orElseThrow(() -> new NaoEncontradoException("Cliente não encontrado"));

        VeiculoEntity veiculo = new VeiculoEntity(cliente, veiculoCriar);

        veiculoRepository.save(veiculo);
    }


    public List<VeiculoBuscar> buscar(String placa, String marca, String modelo) {

        List<VeiculoEntity> veiculos;


        if (placa != null && !placa.isBlank()) {

            Optional<VeiculoEntity> veiculoOpt = veiculoRepository.findByPlaca(placa);

            veiculos = veiculoOpt
                    .map(List::of)
                    .orElseThrow(() -> new NaoEncontradoException("Placa não encontrada"));

        }
        else if (marca != null && !marca.isBlank()) {

            veiculos = veiculoRepository.findByMarcaContainingIgnoreCase(marca);

            if (veiculos.isEmpty()) {
                throw new NaoEncontradoException("Marca não encontrada");
            }

        }
        else if (modelo != null && !modelo.isBlank()) {

            veiculos = veiculoRepository.findByModeloContainingIgnoreCase(modelo);

            if (veiculos.isEmpty()) {
                throw new NaoEncontradoException("Modelo não encontrado");
            }

        }
        else {

            veiculos = veiculoRepository.findAll();

            if (veiculos.isEmpty()) {
                throw new NaoEncontradoException("Nenhum veículo encontrado");
            }
        }

        return veiculos.stream()
                .map(VeiculoBuscar::new)
                .toList();
    }

    public void atualizar(UUID id, VeiculoAtualizar veiculoAtualizar) {

        VeiculoEntity veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Veículo não encontrado"));

        veiculo.setMarca(veiculoAtualizar.getMarca());
        veiculo.setModelo(veiculoAtualizar.getModelo());
        veiculo.setAno(veiculoAtualizar.getAno());
        veiculo.setValor(veiculoAtualizar.getValor());
        veiculo.setMaximoDesconto(veiculoAtualizar.getMaximoDesconto());
        veiculo.setVendido(veiculoAtualizar.getVendido());
        veiculo.setValorVenda(veiculoAtualizar.getValorVenda());

        if (Boolean.TRUE.equals(veiculoAtualizar.getVendido()) && veiculoAtualizar.getValorVenda() == null) {
            throw new DadosInvalidosException("valorVenda é obrigatório se vendido for true");
        }

        veiculoRepository.save(veiculo);
    }

    public void deletar(UUID id) {

        Optional<VeiculoEntity> veiculoOpt = veiculoRepository.findById(id);

        if (veiculoOpt.isEmpty()) {
            throw new NaoEncontradoException("Veículo não encontrado");
        }

        veiculoRepository.deleteById(id);
    }
}
