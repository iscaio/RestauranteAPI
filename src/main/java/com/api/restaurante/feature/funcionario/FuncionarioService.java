package com.api.restaurante.feature.funcionario;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FuncionarioService {
    
    private final FuncionarioRepository funcionarioRepository;

    @Transactional(readOnly = true)
    public List<FuncionarioDTOs.Response> buscarTodos(){
        return funcionarioRepository.findAll().stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public FuncionarioDTOs.Response buscarPorId(Long id){
        Funcionario funcionario = funcionarioRepository.findById(id)
            .orElseThrow(()-> new EntityNotFoundException("Funcionario ID" + id + "não encontrado"));
        
        return mapToResponse(funcionario);
    }

    @Transactional
    public FuncionarioDTOs.Response criar(FuncionarioDTOs.Request request) {
        Funcionario funcionario = mapToEntity(request);
        funcionario = funcionarioRepository.save(funcionario);
        return mapToResponse(funcionario);
    }

    //map
    private FuncionarioDTOs.Response mapToResponse(Funcionario funcionario){
        return FuncionarioDTOs.Response.builder()
            .id(funcionario.getId())
            .nome(funcionario.getNome())
            .cargo(funcionario.getCargo())
            .salario(funcionario.getSalario())
            .build();

    }

    private Funcionario mapToEntity(FuncionarioDTOs.Request request){
        return Funcionario.builder()
            .nome(request.getNome())
            .cargo(request.getCargo())
            .salario(request.getSalario())
            .build();
    }
}
