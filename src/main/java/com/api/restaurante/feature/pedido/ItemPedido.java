package com.api.restaurante.feature.pedido;

import com.api.restaurante.feature.prato.Prato;
import jakarta.persistence.*;
import lombok.*;
 
@Entity
@Table(name = "itens_pedido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ItemPedido {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pedidoid", nullable = false)
    private Pedido pedido;
 
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "pratoId", nullable = false)
    private Prato prato;
 
    @Column(nullable = false)
    private Integer quantidade;
}