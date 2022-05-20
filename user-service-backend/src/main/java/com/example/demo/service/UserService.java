package com.example.demo.service;

import com.example.demo.model.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserEntity> getAllUsers();
    UserEntity getByFirstName(String firstName);
    Optional<UserEntity> getUserById(long id);
    void createUser(UserEntity userEntity);
    UserEntity updateUserById(long id, UserEntity userEntity);
    void deleteUserById(long id);
    void deleteAllUsers();

}
