package com.shaneen.blog.blog.services;

import com.shaneen.blog.blog.models.Post;
import com.shaneen.blog.blog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class PostServiceJpa implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> findAll() {
        return this.postRepository.findAll();
    }
    @Override
    public Page<Post> findAll(Pageable pageable){
        return this.postRepository.findAll(pageable);
    }

    @Override
    public List<Post> findLatest5() {

        return this.postRepository.findLatest5Posts( PageRequest.of(0,5) );

    }
    @Override
    public Post findById(Long id) {
        return this.postRepository.findById(id).orElse(null);
    }
    @Override
    public Post create(Post post) {
        return this.postRepository.save(post);
    }
    @Override
    public Post edit(Post post) {
        return this.postRepository.save(post);
    }
    @Override
    public void deleteById(Long id) {
        this.postRepository.deleteById(id);
    }
}
