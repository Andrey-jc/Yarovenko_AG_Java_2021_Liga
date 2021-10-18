package com.example.liquibasedemo.services.interfaces;

import com.example.liquibasedemo.dto.FriendDTO;
import com.example.liquibasedemo.dto.UserDTO;

import java.util.List;

public interface FriendsService {

    String saveUserFriend(int idUser, UserDTO userFriendId);

    void deleteUserFriend(int idUser, int idFriend);

    List<FriendDTO> buildFriendList(int id);
}
