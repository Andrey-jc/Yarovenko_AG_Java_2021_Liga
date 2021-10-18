package com.example.liquibasedemo.services.interfaces;

import com.example.liquibasedemo.dto.FriendDTO;

public interface FriendsService {

    void saveUserFriend(int idUser, int idFriend);

    void deleteUserFriend(int idUser, int idFriend);

    FriendDTO buildFriendList(int id);
}
