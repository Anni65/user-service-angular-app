package com.example.demo.controller;

import com.example.demo.model.UserEntity;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> getAllUsers(@RequestParam(required = false) String firstName) {
            List<UserEntity> userEntityList = new ArrayList<>();
            if (firstName == null){
                userEntityList.addAll(userService.getAllUsers());
            } else {
                userEntityList.add(userService.getByFirstName(firstName));
            }

            if (userEntityList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(userEntityList, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable("id") int id) {
        Optional<UserEntity> userData = userService.getUserById(id);
        return userData.map(
                userEntity -> new ResponseEntity<>(userEntity, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)
                );
    }

    @PostMapping("/users")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userEntity) {
            userService.createUser(userEntity);
            return new ResponseEntity<>(userEntity, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserEntity> updateUserById(@PathVariable("id") int id, @RequestBody UserEntity userEntity) {
        UserEntity user = userService.updateUserById(id, userEntity);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") int id) {
            userService.deleteUserById(id);
            return ResponseEntity.ok("Deleted user [" + id + "] successfully...");
    }

    @DeleteMapping("/user")
    public ResponseEntity<?> deleteAllUsers() {
            userService.deleteAllUsers();
            return ResponseEntity.ok("Deleted All Users successfully...");
    }

}
