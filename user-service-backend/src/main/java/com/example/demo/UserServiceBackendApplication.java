package com.example.demo;

import com.example.demo.model.UserEntity;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@AllArgsConstructor
public class UserServiceBackendApplication {
    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(UserServiceBackendApplication.class, args);
    }

    @PostConstruct
    public void loadData() {

        List<UserEntity> list = Stream.of(
                new UserEntity("John", "Doe", "john@doe.com"),
                new UserEntity("Sam", "Smith", "sam@smith.com"),
                new UserEntity("Taylor", "Swift", "taylor@swift.com"),
                new UserEntity("Stephen", "Curry", "steph@curry.com"),
                new UserEntity("Klay", "Thompson", "klay@thompson.com")
        ).collect(Collectors.toList());

        userRepository.saveAll(list);
    }

}
