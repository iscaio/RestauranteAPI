package com.api.restaurante.feature.pedido;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long id;

    @NotNull(message = "A data é obrigatória")
    private LocalDateTime data;

    private String status;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @Column(name = "cliente_cpf")
    @NotNull(message = "O CPF do cliente é obrigatorio")
    private String clienteCpf;

    @Column(name = "funcionario_id")
    private Long funcionarioId;

    @PrePersist
    protected void onCreate(){
        this.data = LocalDateTime.now();
        if(this.status == null){
            this.status = "PENDENTE";
        }
    }
    
}