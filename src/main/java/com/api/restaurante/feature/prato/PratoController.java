package com.api.restaurante.feature.prato;

import java.util.List;
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
@RequestMapping("/api/pratos")
@RequiredArgsConstructor
public class PratoController {

    private final PratoService pratoService;
    
    @GetMapping
    public ResponseEntity<List<PratoDTOs.Response>> listarTodos(){
        return ResponseEntity.ok(pratoService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PratoDTOs.Response> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(pratoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<PratoDTOs.Response> criar(@Valid @RequestBody PratoDTOs.Request request){
        PratoDTOs.Response novoPrato = pratoService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPrato);
    }
}
