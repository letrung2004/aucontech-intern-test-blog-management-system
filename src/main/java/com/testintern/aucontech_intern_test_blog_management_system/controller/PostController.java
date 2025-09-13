package com.testintern.aucontech_intern_test_blog_management_system.controller;

import com.testintern.aucontech_intern_test_blog_management_system.dto.request.PostRequest;
import com.testintern.aucontech_intern_test_blog_management_system.dto.response.ApiResponse;
import com.testintern.aucontech_intern_test_blog_management_system.dto.response.PostResponse;
import com.testintern.aucontech_intern_test_blog_management_system.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ApiResponse<PostResponse> createPost(@RequestBody PostRequest request) {
        return ApiResponse.<PostResponse>builder()
                .result(postService.createPost(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<PostResponse>> getAllPosts() {
        return ApiResponse.<List<PostResponse>>builder()
                .result(postService.getPosts())
                .build();
    }

    @GetMapping("/user/{userId}")
    public ApiResponse<List<PostResponse>> getUserPosts(@PathVariable String userId) {
        return ApiResponse.<List<PostResponse>>builder()
                .result(postService.getUserPosts(userId))
                .build();
    }

    @PutMapping("/{postId}")
    public ApiResponse<PostResponse> updatePost(
            @PathVariable(value = "postId") String postId,
            @RequestBody PostRequest request) {
        return ApiResponse.<PostResponse>builder()
                .result(postService.updatePost(postId, request))
                .build();
    }

    @DeleteMapping("/{postId}")
    public ApiResponse<Void> deletePost(@PathVariable(value = "postId") String postId) {
        postService.deletePost(postId);
        return ApiResponse.<Void>builder().build();
    }
}
