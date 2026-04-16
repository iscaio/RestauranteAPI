package com.api.restaurante.feature.pedido;

import java.math.BigDecimal;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
 
    @Column(name= "pedido_id")
    @NotNull(message = "O ID do prato é obritório")
    private Long pratoId;
 
    @Positive(message = "A quantidade deve ser positiva")
    private Integer quantidade;
 
    private BigDecimal precoUnitarioNoPedido;
    private BigDecimal subtotal;

    @PrePersist
    @PreUpdate
    public void calcularSubtotal(){
        if (precoUnitarioNoPedido != null && quantidade != null){
            this.subtotal = precoUnitarioNoPedido.multiply(BigDecimal.valueOf(quantidade));
        }
    }
}