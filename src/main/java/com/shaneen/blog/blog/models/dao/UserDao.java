package com.shaneen.blog.blog.models.dao;

import com.shaneen.blog.blog.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface UserDao extends CrudRepository<User, Integer> {

    User findByUid(int uid);

    List<User> findAll();

    User findByUsername(String username);


}



