package com.api.restaurante.feature.pedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.Builder;

public class PedidoDTOs {

    @Data
    public static class Request {
    
        private Long funcionarioId;

        @NotBlank(message = "O CPF do cliente é obrigatorio!")
        private String clienteCpf;

        @NotEmpty(message = "O pedido deve ter pelo menos um item")
        private List<ItemRequest> itens;
        
    }

    @Data
    public static class ItemRequest {
        
        @NotNull(message = "O ID do prato é obrigatorio")
        private Long pratoId;

        @NotNull(message = "A quantidade é obrigatoria")
        @Positive(message = "A quantidade deve ser positiva")
        private Integer quantidade;
        
    }

    @Data
    @Builder
    public static class Response {
    
        private Long id;
        private LocalDateTime data;
        private String status;
        private BigDecimal valorTotal;
        private String clienteNome;
        private String funcionarioNome;
        private List<ItemResponse> itens;
    }

    @Data
    @Builder
    public static class ItemResponse {

        private Long pratoId;
        private String pratoNome;
        private Integer quantidade;
        private BigDecimal precoUnitario;
        private BigDecimal subtotal;

    }

}
