package com.testintern.aucontech_intern_test_blog_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testintern.aucontech_intern_test_blog_management_system.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {

}
