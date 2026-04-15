package com.api.restaurante.feature.funcionario;

import jakarta.persistence.*;
import lombok.*;
 
import java.math.BigDecimal;
 
@Entity
@Table(name = "funcionarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Funcionario {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Column(nullable = false)
    private String nome;
 
    @Column(nullable = false)
    private String cargo;
 
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal salario;
}