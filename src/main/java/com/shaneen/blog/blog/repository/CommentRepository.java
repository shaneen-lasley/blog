package com.shaneen.blog.blog.repository;

import com.shaneen.blog.blog.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}




