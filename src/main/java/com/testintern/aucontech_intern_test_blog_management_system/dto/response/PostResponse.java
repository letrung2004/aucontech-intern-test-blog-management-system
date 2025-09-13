package com.testintern.aucontech_intern_test_blog_management_system.dto.response;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponse {
    private String id;
    private String title;
    private String content;
    private Instant createdAt;
    private Instant updatedAt;
    private String username;

}
