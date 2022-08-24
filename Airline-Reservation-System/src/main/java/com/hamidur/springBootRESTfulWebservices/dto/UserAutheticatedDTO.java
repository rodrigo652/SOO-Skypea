package com.hamidur.springBootRESTfulWebservices.dto;

import com.hamidur.springBootRESTfulWebservices.models.Customer;

public class UserAutheticatedDTO {
    private String tipo;
    private String email;
    private String nome;
    private String token;

    public UserAutheticatedDTO(String email, String nome, String token, String tipo) {

        this.email = email;
        this.nome = nome;
        this.token = token;
        this.tipo = tipo;
    }

    public UserAutheticatedDTO(){}

    public static UserAutheticatedDTO toDTO(Customer user, String tipo) {
        return new UserAutheticatedDTO(user.getEmail(), user.getFirstName(), user.getToken(), tipo);
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getToken() {
        return token;
    }

    public String getTipo() {
        return tipo;
    }
}
