package com.example.liquibasedemo.controller;

import com.example.liquibasedemo.dto.FriendDTO;
import com.example.liquibasedemo.dto.UserDTO;
import com.example.liquibasedemo.services.interfaces.FriendsService;
import com.example.liquibasedemo.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FriendsController {

    private final FriendsService friendsService;

    @Autowired
    public FriendsController(FriendsService friendsService, UserService userService) {
        this.friendsService = friendsService;
    }

    @GetMapping("/friends/{id}")
    public List<FriendDTO> showFriendUser(@PathVariable("id") int id) {
        return friendsService.buildFriendList(id);
    }

    @PostMapping("/friends/add/{idUser}")
    public String addNewFriend(@PathVariable("idUser") int idUser, @RequestBody UserDTO userDTO ) {
        return friendsService.saveUserFriend(idUser, userDTO);
    }

    @DeleteMapping("/friends/delete")
    public String deleteFriend(@RequestParam("idUser") int idUser, @RequestParam("idFriend") int idFriend) {
        friendsService.deleteUserFriend(idUser, idFriend);
        return "User with ID = " + idUser + " and user with ID = " + idFriend + " not friends anymore";
    }
}
