package com.testintern.aucontech_intern_test_blog_management_system.controller;

import org.springframework.web.bind.annotation.RestController;

import com.testintern.aucontech_intern_test_blog_management_system.dto.request.UserCreateRequest;
import com.testintern.aucontech_intern_test_blog_management_system.dto.response.ApiResponse;
import com.testintern.aucontech_intern_test_blog_management_system.dto.response.UserResponse;
import com.testintern.aucontech_intern_test_blog_management_system.service.UserService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping("/register")
    public ApiResponse<UserResponse> createUser(@RequestBody UserCreateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }

}
