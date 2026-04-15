package com.api.restaurante.feature.prato;

import jakarta.persistence.*;
import lombok.*;
 
import java.math.BigDecimal;
 
@Entity
@Table(name = "pratos")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Prato {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prato")
    private Long id;
 
    @Column(nullable = false)
    private String nomePrato;
 
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;
 
    private String categoria;
}