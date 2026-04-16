package com.api.restaurante.feature.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

public class ClienteDTOs {

    @Data
    @Builder
    public static class Request {
        @NotBlank(message = "CPF é obrigatório")
        @Size(min = 11, max = 14, message = "CPF inválido")
        private String cpf;

        @NotBlank(message = "Nome é obrigatório")
        private String nome;

        @Email(message = "E-mail inválido")
        private String email;

        @Size(max = 20)
        private String telefone;
    }

    @Data
    @Builder
    public static class Response {
        private String cpf;
        private String nome;
        private String email;
        private String telefone;
    }
}

//aaaaaaaaaaaaaaaaa