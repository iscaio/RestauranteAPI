package com.api.restaurante.feature.prato;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

public class PratoDTOs {
    
    @Data
    @Builder
    public static class Request{
        @NotBlank(message = "O nome do prato é obrigatorio")
        private String nomePrato;
        private String categoria;
        @Positive(message = "O preçõ deve ser positivo")
        private BigDecimal preco;

    }

    @Data
    @Builder
    public static class Response {
        private long id;
        private String nomePrato;
        private String categoria;
        private BigDecimal preco;
        
    }
}
