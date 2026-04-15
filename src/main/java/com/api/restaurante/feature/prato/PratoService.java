package com.api.restaurante.feature.prato;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor

public class PratoService {

    private final PratoRepository pratoRepository;
    
    @Transactional(readOnly = true)
    public List<PratoDTOs.Response> buscarTodos() {
        return pratoRepository.findAll().stream().map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public PratoDTOs.Response buscarPorId(Long id) {
        Prato prato = pratoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prato ID " + id + " não encontrado"));
        return mapToResponse(prato);
    }

    @Transactional
    public PratoDTOs.Response criar(PratoDTOs.Request request) {
        Prato prato = mapToEntity(request);
        prato = pratoRepository.save(prato);
        return mapToResponse(prato);
    }

    private PratoDTOs.Response mapToResponse(Prato prato) {
        return PratoDTOs.Response.builder()
                .id(prato.getId())
                .nomePrato(prato.getNomePrato())
                .categoria(prato.getCategoria())
                .preco(prato.getPreco())
                .build();
    }

    private Prato mapToEntity(PratoDTOs.Request request) {
        return Prato.builder()
                .nomePrato(request.getNomePrato())
                .categoria(request.getCategoria())
                .preco(request.getPreco())
                .build();
    }

}
