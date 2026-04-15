package com.api.restaurante.feature.funcionario;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface FuncionarioRepository  extends JpaRepository<Funcionario, Long>{
    
}
