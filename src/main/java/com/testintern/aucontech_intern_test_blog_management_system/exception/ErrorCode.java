package com.testintern.aucontech_intern_test_blog_management_system.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USERNAME_EXISTED(1001, "Username already exists"),
    EMAIL_EXISTED(1002, "Email already exists"),
    USER_NOT_FOUND(1003, "User not found");

    private int code;
    private String message;
}
