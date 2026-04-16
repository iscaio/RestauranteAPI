package com.api.restaurante.feature.cliente;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Transactional(readOnly = true)
    public List<ClienteDTOs.Response> buscarTodos() {
        return clienteRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ClienteDTOs.Response buscarPorCpf(String cpf) {
        Cliente cliente = clienteRepository.findById(cpf)
                .orElseThrow(() -> new EntityNotFoundException("Cliente CPF " + cpf + " não encontrado"));
        return mapToResponse(cliente);
    }

    @Transactional
    public ClienteDTOs.Response criar(ClienteDTOs.Request request) {
        if (clienteRepository.existsByCpf(request.getCpf())) {
            throw new IllegalArgumentException("Cliente CPF " + request.getCpf() + " já cadastrado");
        }
        Cliente cliente = mapToEntity(request);
        cliente = clienteRepository.save(cliente);
        return mapToResponse(cliente);
    }

    // Mapeamentos
    private ClienteDTOs.Response mapToResponse(Cliente cliente) {
        return ClienteDTOs.Response.builder()
                .cpf(cliente.getCpf())
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .telefone(cliente.getTelefone())
                .build();
    }

    private Cliente mapToEntity(ClienteDTOs.Request request) {
        return Cliente.builder()
                .cpf(request.getCpf())
                .nome(request.getNome())
                .email(request.getEmail())
                .telefone(request.getTelefone())
                .build();
    }
}

//aaaaaaaaaaaaaaaaa