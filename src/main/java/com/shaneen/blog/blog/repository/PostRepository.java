package com.shaneen.blog.blog.repository;

import com.shaneen.blog.blog.domain.PostEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<PostEntity, Long> {
    List<PostEntity> findByTitle(String title);
}
