package com.testintern.aucontech_intern_test_blog_management_system.service;

import java.time.Instant;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.testintern.aucontech_intern_test_blog_management_system.dto.request.UserCreateRequest;
import com.testintern.aucontech_intern_test_blog_management_system.dto.response.UserResponse;
import com.testintern.aucontech_intern_test_blog_management_system.entity.User;
import com.testintern.aucontech_intern_test_blog_management_system.exception.AppException;
import com.testintern.aucontech_intern_test_blog_management_system.exception.ErrorCode;
import com.testintern.aucontech_intern_test_blog_management_system.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserCreateRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USERNAME_EXISTED);
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }

        User newUser = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .createdAt(Instant.now())
                .build();

        userRepository.save(newUser);

        return UserResponse.builder()
                .id(newUser.getId())
                .fullName(newUser.getFullName())
                .username(newUser.getUsername())
                .email(newUser.getEmail())
                .build();
    }
}
