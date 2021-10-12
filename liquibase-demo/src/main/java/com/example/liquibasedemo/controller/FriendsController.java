package com.example.liquibasedemo.controller;

import com.example.liquibasedemo.dto.FriendDTO;
import com.example.liquibasedemo.entity.User;
import com.example.liquibasedemo.exceptions_handling.NoSuchExceptionSocialNetwork;
import com.example.liquibasedemo.services.interfaces.FriendsService;
import com.example.liquibasedemo.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FriendsController {

    private FriendsService friendsService;
    private UserService userService;

    @Autowired
    public FriendsController(FriendsService friendsService, UserService userService) {
        this.friendsService = friendsService;
        this.userService = userService;
    }

    @GetMapping("/friends/{id}")
    public FriendDTO showFriendUser(@PathVariable("id") int id) {
        User userCurrent = userService.getUser(id);
        if (userCurrent == null) {
            throw new NoSuchExceptionSocialNetwork("There is no user with ID = " +
                    id + " in Database");
        }
        return friendsService.buildFriendList(userCurrent);
    }

    @PostMapping("/friends/add")
    public String addNewFriend(@RequestParam("idUser") int idUser, @RequestParam("idFriend") int idFriend) {
        friendsService.saveUserFriend(idUser, idFriend);
        return "User with ID = " + idUser + " and user with ID = " + idFriend + " friends now";
    }

    @DeleteMapping("/friends/delete")
    public String deleteFriend(@RequestParam("idUser") int idUser, @RequestParam("idFriend") int idFriend) {
        friendsService.deleteUserFriend(idUser, idFriend);
        return "User with ID = " + idUser + " and user with ID = " + idFriend + " not friends anymore";
    }
}
