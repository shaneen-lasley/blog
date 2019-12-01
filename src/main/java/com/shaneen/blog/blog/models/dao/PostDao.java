package com.shaneen.blog.blog.models.dao;

import com.shaneen.blog.blog.models.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface PostDao extends CrudRepository<Post, Integer> {

    List<Post> findByAuthor(int authorId);
    Post findByUid(int uid);
    List<Post> findAll();


}
