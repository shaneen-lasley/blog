package com.shaneen.blog.blog.services;

import com.shaneen.blog.blog.models.Post;

import java.awt.print.Pageable;
import java.util.List;

public interface PostService {
    List<Post> findAll();
    Page<Post> findAll(Pageable pageable);
    List<Post> findLatest5();
    Post findById(Long id);
    Post create (Post post);
    Post edit(Post post);
    void deleteById(Long id);
}
