package com.hamidur.springBootRESTfulWebservices.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.hamidur.springBootRESTfulWebservices.models.Customer;

@JsonAutoDetect(fieldVisibility= JsonAutoDetect.Visibility.ANY)
public class UserResponserDTO {

    private Long id;

    private String nome;
    private String email;
    private String senha;

    public UserResponserDTO(String nome, String email, String senha){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
    public UserResponserDTO(){}

    public static UserResponserDTO toDTO(Customer user) {
        return new UserResponserDTO(user.getFirstName(), user.getEmail(), user.getPassword());
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
