package com.hamidur.springBootRESTfulWebservices.services;

import com.hamidur.springBootRESTfulWebservices.dto.DadosLogin;
import com.hamidur.springBootRESTfulWebservices.exception.ExpiredTokenException;
import com.hamidur.springBootRESTfulWebservices.exception.InvalidLoginException;
import com.hamidur.springBootRESTfulWebservices.exception.InvalidTokenException;
import com.hamidur.springBootRESTfulWebservices.models.Customer;
import com.hamidur.springBootRESTfulWebservices.repos.CustomerRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserAuthenticationService {

    private final CustomerRepository userRepository;
    private final TokenService tokenService;

    @Autowired
    public UserAuthenticationService(CustomerRepository userRepository, TokenService tokenService){
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }


    public Customer authenticate(DadosLogin dados, String token){
        Customer user = userRepository.findByEmailIgnoreCase(dados.getEmail());
        if(dados.getSenha().equals(user.getPassword()) && !token.isEmpty() && validate(token)) {
            return user;
        }
        else {
            throw new InvalidLoginException();
        }
    }

    private boolean validate(String token) {
        try {
            String tokenTratado = token.replace("Bearer ", "");
            Claims claims = tokenService.decodeToken(tokenTratado);

            System.out.println(claims.getIssuer());
            System.out.println(claims.getIssuedAt());
            //Verifica se o token está expirado
            if (claims.getExpiration().before(new Date(System.currentTimeMillis()))) throw new ExpiredTokenException();
            System.out.println(claims.getExpiration());
            return true;
        } catch (ExpiredTokenException et){
            et.printStackTrace();
            throw et;
        } catch (Exception e) {
            e.printStackTrace();
            throw new InvalidTokenException();
        }

    }


}
