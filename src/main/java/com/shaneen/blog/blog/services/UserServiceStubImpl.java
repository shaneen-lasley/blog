package com.shaneen.blog.blog.services;


import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceStubImpl implements UserService {
    @Override
    public boolean authenticate(String userName, String password) {
        return Objects.equals(userName, password);
    }
}
