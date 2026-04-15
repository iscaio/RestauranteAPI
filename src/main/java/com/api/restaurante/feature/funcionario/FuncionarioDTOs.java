package com.api.restaurante.feature.funcionario;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

public class FuncionarioDTOs {
    
    @Data
    @Builder
    public static class Request{
        private String nome;
        private String cargo;
        private BigDecimal salario;
    }

    @Data
    @Builder
    public static class Response{
        private Long id;
        private String nome;
        private String cargo;
        private BigDecimal salario;
    }
}
