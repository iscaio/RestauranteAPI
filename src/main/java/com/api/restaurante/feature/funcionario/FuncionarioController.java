package com.api.restaurante.feature.funcionario;

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
@RequestMapping("/api/funcionarios")
@RequiredArgsConstructor

public class FuncionarioController {
    
    private final FuncionarioService funcionarioService;

    @GetMapping
    public ResponseEntity<List<FuncionarioDTOs.Response>> listarTodos(){
        return ResponseEntity.ok(funcionarioService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDTOs.Response> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(funcionarioService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<FuncionarioDTOs.Response> criar(@Valid @RequestBody FuncionarioDTOs.Request request){
        FuncionarioDTOs.Response novoFuncionario = funcionarioService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoFuncionario);
    }

}
