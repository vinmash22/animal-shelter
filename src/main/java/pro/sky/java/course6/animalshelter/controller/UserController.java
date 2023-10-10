package pro.sky.java.course6.animalshelter.controller;


import org.springframework.http.ResponseEntity;
import pro.sky.java.course6.animalshelter.entity.User;

import org.springframework.web.bind.annotation.*;
import pro.sky.java.course6.animalshelter.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }
}




