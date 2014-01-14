package com.lantushenko.experimental.servicesscope.services;

public interface PasswordHashService {

    String generatePasswordHash(String plainTextPassword);

}