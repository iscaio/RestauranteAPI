package com.api.restaurante.feature.pedido;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {
    private final PedidoService pedidoService;

    @GetMapping("/{id}")
public ResponseEntity<PedidoDTOs.Response> buscarPorId(@PathVariable Long id) {
    return ResponseEntity.ok(pedidoService.buscarPorId(id));
}

    @PostMapping
    public ResponseEntity<PedidoDTOs.Response> criar(@Valid @RequestBody PedidoDTOs.Request request){
        PedidoDTOs.Response novoPedido = pedidoService.criarPedido(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPedido);
    }
}
