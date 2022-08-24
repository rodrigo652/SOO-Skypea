package com.hamidur.springBootRESTfulWebservices.services;

import com.hamidur.springBootRESTfulWebservices.models.Customer;
import com.hamidur.springBootRESTfulWebservices.repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {

    private CustomerRepository userRepository;
    private TokenService tokenService;

    @Autowired
    public UserRegistrationService(CustomerRepository userRepository, TokenService tokenService){
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    public Customer registrate(Customer user){
        user.setToken(tokenService.generateToken(user));
        return userRepository.save(user);
    }

}
