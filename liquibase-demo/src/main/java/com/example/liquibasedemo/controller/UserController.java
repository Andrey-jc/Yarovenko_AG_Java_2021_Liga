package com.example.liquibasedemo.controller;

import com.example.liquibasedemo.dto.UserDTO;
import com.example.liquibasedemo.dto.UserFullInfoDTO;
import com.example.liquibasedemo.entity.User;
import com.example.liquibasedemo.exceptions_handling.NoSuchExceptionSocialNetwork;
import com.example.liquibasedemo.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public UserDTO getUser(@PathVariable("id") int id) {
        UserDTO userDTO = userService.getUserDTO(id);
        if (userDTO == null) {
            throw new NoSuchExceptionSocialNetwork("There is no user with ID = " +
                    id + " in Database");
        }
        return userDTO;
    }

    @GetMapping("/users")
    public List<UserDTO> showAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public UserDTO addNewUser(@RequestBody UserFullInfoDTO user) {
        userService.saveUser(user);
        return userService.getUserDTO(user.getId());
    }

    @PutMapping("/users")
    public UserDTO updateUser(@RequestBody UserFullInfoDTO user) {
        userService.saveUser(user);
        return userService.getUserDTO(user.getId());
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable int id) {
        User user = userService.getUser(id);
        if (user == null) {
            throw new NoSuchExceptionSocialNetwork("There is no user with ID = " +
                    id + " in Database");
        }
        userService.deleteUser(id);
        return "User with ID = " + id + " was deleted";
    }

    @GetMapping("/users-post/{id}")
    public String findPosts(@PathVariable("id") Integer id) {
        User user = userService.getUser(id);
        if (user == null) {
            throw new NoSuchExceptionSocialNetwork("There is no user with ID = " +
                    id + " in Database");
        }
        return user.getStringPosts();
    }
}
