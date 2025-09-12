package com.testintern.aucontech_intern_test_blog_management_system.service;

import org.springframework.stereotype.Service;

import com.testintern.aucontech_intern_test_blog_management_system.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthenticationService {
    UserRepository userRepository;

}
