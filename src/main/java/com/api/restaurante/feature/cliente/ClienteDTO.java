package com.api.restaurante.feature.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

public class ClienteDTO {

    @Data
    public static class Request {
        @NotBlank(message = "CPF é obrigatório")
        @Size(min = 11, max = 14, message = "CPF inválido")
        private String cpf;

        @NotBlank(message = "Nome é obrigatório")
        private String nome;

        @Email(message = "E-mail inválido")
        private String email;

        private String telefone;
    }

    @Data
    public static class Response {
        private Long id;
        private String cpf;
        private String nome;
        private String email;
        private String telefone;
    }
}
