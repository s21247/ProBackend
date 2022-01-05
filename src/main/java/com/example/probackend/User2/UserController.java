package com.example.probackend.User2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class UserController {

    private static final List<User2> USER_2s = Arrays.asList(
            new User2(1, "James Bond"),
            new User2(2, "Maria Jones"),
            new User2(3, "Anna Smith")
    );

    @GetMapping(path = "{userId}")
    public User2 getStudent(@PathVariable("userId") Integer userId) {
        return USER_2s.stream()
                .filter(user2 -> userId.equals(user2.getUserId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "Student " + userId + " does not exists"
                ));
    }
}
