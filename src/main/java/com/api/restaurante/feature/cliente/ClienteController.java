package com.api.restaurante.feature.cliente;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDTOs.Response>> listarTodos() {
        return ResponseEntity.ok(clienteService.buscarTodos());
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ClienteDTOs.Response> buscarPorCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(clienteService.buscarPorCpf(cpf));
    }

    @PostMapping
    public ResponseEntity<ClienteDTOs.Response> criar(@Valid @RequestBody ClienteDTOs.Request request) {
        ClienteDTOs.Response novoCliente = clienteService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
    }
}