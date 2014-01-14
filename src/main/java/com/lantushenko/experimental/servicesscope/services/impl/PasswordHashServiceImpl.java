package com.lantushenko.experimental.servicesscope.services.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.lantushenko.experimental.servicesscope.services.PasswordHashService;

@Component
@Scope
public class PasswordHashServiceImpl implements PasswordHashService {

    @Override
    public String generatePasswordHash(String plainTextPassword) {
        //TODO: implement
        return plainTextPassword;
    }

}
