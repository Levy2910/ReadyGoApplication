package com.example.readygo.service;

import com.example.readygo.model.User;

import java.io.IOException;


public interface IUserService {
    String addUser(User user) throws IOException;

    String checkUser(String email, String password) throws IOException;

    boolean updateUser(Long userId, User user);
}
