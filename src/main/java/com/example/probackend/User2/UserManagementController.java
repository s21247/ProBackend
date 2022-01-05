package com.example.probackend.User2;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
public class UserManagementController {

    private static final List<User2> USER_2s = Arrays.asList(
            new User2(1, "James Bond"),
            new User2(2, "Maria Jones"),
            new User2(3, "Anna Smith")
    );

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMINTRAINEE')")
    public List<User2> getAllStudents(){
        return USER_2s;
    }
    @PostMapping
    @PreAuthorize("hasAuthority('user:write')")
    public void registerNewStudent(@RequestBody User2 user2){
        System.out.println(user2);
    }
    @DeleteMapping(path = "{userId}")
    @PreAuthorize("hasAuthority('user:write')")
    public void deleteUser(@PathVariable("userId") Integer userId){
        System.out.println(userId);
    }

    @PutMapping(path = "{userId}")
    @PreAuthorize("hasAuthority('user:write')")
    public void updateUser(@PathVariable("userId") Integer userId,@RequestBody User2 user2){
        System.out.printf("%s,%s%n",userId, user2);
    }
}
