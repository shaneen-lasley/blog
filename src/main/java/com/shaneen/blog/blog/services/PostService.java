package com.shaneen.blog.blog.services;

import com.shaneen.blog.blog.models.Post;
import com.shaneen.blog.blog.models.User;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface PostService {

    Optional<Post> findForId(Long id);

    Post save(Post post);

    Page<Post> findByUserOrderedByDatePageable(User user, int page);

    Page<Post> findAllOrderedByDatePageable(int page);

    void delete(Post post);
}
