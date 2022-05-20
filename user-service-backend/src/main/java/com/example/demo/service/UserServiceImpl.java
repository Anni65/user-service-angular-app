package com.example.demo.service;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.UserEntity;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public List<UserEntity> getAllUsers() {
        List<UserEntity> userEntityList = userRepository.findAll();
        if (userEntityList.isEmpty()) {
            throw new  UserNotFoundException("No User Found... [ Please add users first]");
        }
        return userEntityList;
    }

    @Override
    public UserEntity getByFirstName(String firstName) {
        UserEntity userEntity = userRepository.findByFirstName(firstName);
        if (userEntity == null) {
            throw new  UserNotFoundException("No User Found with name: [" + firstName + "]");
        }
        return userEntity;
    }

    @Override
    public Optional<UserEntity> getUserById(long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("No user found with id: [" + id + "]");
        }
        return user;
    }

    @Override
    public void createUser(UserEntity userEntity) {
        userRepository.save(new UserEntity(
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getEmail())
        );
    }

    @Override
    public UserEntity updateUserById(long id, UserEntity userEntity) {
        Optional<UserEntity> userData = userRepository.findById(id);
        if (userData.isEmpty()) {
            throw new UserNotFoundException("No user found with id: [" + id + "]");
        }
        UserEntity user = userData.get();
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        user.setEmail(userEntity.getEmail());

        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(long id) {
        Optional<UserEntity> userData = userRepository.findById(id);
        if (userData.isEmpty()) {
            throw new UserNotFoundException("No user found with id: [" + id + "]");
        }
        userRepository.deleteById(id);
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
}
