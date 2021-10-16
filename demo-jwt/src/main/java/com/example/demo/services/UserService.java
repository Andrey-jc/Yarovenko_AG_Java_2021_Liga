package com.example.demo.services;

import com.example.demo.entity.UserEntity;

public interface UserService {
     UserEntity saveUser(UserEntity user);

     UserEntity findByLogin(String login);

     UserEntity findByLoginAndPassword(String login, String password);
}
