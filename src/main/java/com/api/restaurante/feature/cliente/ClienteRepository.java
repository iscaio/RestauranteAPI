package com.api.restaurante.feature.cliente;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {
    boolean existsByCpf(String cpf);
}


//aaaaaaaaaaaaaaaaa