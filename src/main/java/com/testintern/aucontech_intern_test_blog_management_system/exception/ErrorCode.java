package com.testintern.aucontech_intern_test_blog_management_system.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USERNAME_EXISTED(1001, "Username already exists"),
    EMAIL_EXISTED(1002, "Email already exists"),
    USER_NOT_EXISTED(1003, "User not exists"),
    UNAUTHENTICATED(1004, "Unauthenticated"),
    POST_NOT_FOUND(1005, "Post not found"),
    UNAUTHORIZED(1006, "Unauthorized"),
    TOKEN_INVALID(1007, "Token invalid"),
    INVALID_INPUT(1008, "Invalid input");

    private int code;
    private String message;
}
