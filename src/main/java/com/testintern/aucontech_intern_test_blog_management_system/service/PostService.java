package com.testintern.aucontech_intern_test_blog_management_system.service;

import java.time.Instant;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.testintern.aucontech_intern_test_blog_management_system.dto.request.PostRequest;
import com.testintern.aucontech_intern_test_blog_management_system.dto.response.PostResponse;
import com.testintern.aucontech_intern_test_blog_management_system.entity.Post;
import com.testintern.aucontech_intern_test_blog_management_system.entity.User;
import com.testintern.aucontech_intern_test_blog_management_system.exception.AppException;
import com.testintern.aucontech_intern_test_blog_management_system.exception.ErrorCode;
import com.testintern.aucontech_intern_test_blog_management_system.repository.PostRepository;
import com.testintern.aucontech_intern_test_blog_management_system.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    private String getCurrentUserId() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private PostResponse toResponse(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .username(post.getUser().getUsername())
                .build();
    }

    public PostResponse createPost(PostRequest request) {
        String userId = getCurrentUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        Post newPost = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .user(user)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        postRepository.save(newPost);

        return toResponse(newPost);
    }

    public List<PostResponse> getPosts() {
        return postRepository.findAll().stream()
                .map(post -> this.toResponse(post))
                .toList();
    }

    public List<PostResponse> getUserPosts(String userId) {
        return postRepository.findByUserId(userId).stream()
                .map(post -> this.toResponse(post))
                .toList();
    }

    public PostResponse updatePost(String postId, PostRequest request) {
        String userId = getCurrentUserId();
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));

        if (!post.getUser().getId().equals(userId)) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setUpdatedAt(Instant.now());

        postRepository.save(post);
        return toResponse(post);
    }

    public void deletePost(String postId) {
        String userId = getCurrentUserId();
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));

        if (!post.getUser().getId().equals(userId)) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        postRepository.delete(post);
    }

}
