package com.hamidur.springBootRESTfulWebservices.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.hamidur.springBootRESTfulWebservices.models.Customer;

@JsonAutoDetect(fieldVisibility= JsonAutoDetect.Visibility.ANY)
public class UserRegistrationDTO extends Customer {

    private Long id;

    private String nome;
    private String email;
    private String senha;

    public UserRegistrationDTO() {

    }

    public UserRegistrationDTO(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Customer toUser() {
        return new Customer(getNome(), getEmail(), getSenha());
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
