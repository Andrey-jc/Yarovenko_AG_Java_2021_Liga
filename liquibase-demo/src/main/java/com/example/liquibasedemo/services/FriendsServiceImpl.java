package com.example.liquibasedemo.services;

import com.example.liquibasedemo.dto.FriendDTO;
import com.example.liquibasedemo.dto.UserDTO;
import com.example.liquibasedemo.dto.UserFullInfoDTO;
import com.example.liquibasedemo.entity.Friend;
import com.example.liquibasedemo.entity.User;
import com.example.liquibasedemo.repository.FriendRepository;
import com.example.liquibasedemo.repository.UserRepository;
import com.example.liquibasedemo.services.interfaces.FriendsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class FriendsServiceImpl implements FriendsService {

    private final UserRepository userRepository;
    private final FriendRepository friendRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public FriendsServiceImpl(UserRepository userRepository, FriendRepository friendRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.friendRepository = friendRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public String saveUserFriend(int useId, UserDTO userFriendId) {
        User user = userRepository.getById(useId);
        User userFriend = userRepository.getById(userFriendId.getId());
        Friend friendTo = new Friend();
        friendTo.setUser(user);
        friendTo.setFriend(userFriend);
        for (Friend friend :
                user.getFriends()) {
            if (friend.getFriend().getId() == userFriend.getId()) {
                return user.getLastName() + " " + user.getFirstName() + " и " +
                        userFriend.getLastName() + " " + userFriend.getFirstName() + " уже друзья";
            }
        }
        friendRepository.save(friendTo);
        user.getFriends().add(friendTo);
        Friend friendFrom = new Friend();
        friendFrom.setUser(userFriend);
        friendFrom.setFriend(user);
        userFriend.getFriends().add(friendFrom);
        friendRepository.save(friendFrom);
        userRepository.save(user);
        userRepository.save(userFriend);
        return "Ok";
    }


    @Override
    public void deleteUserFriend(int useId, int userFriendId) {
        Friend friendTo = friendRepository.findByFriendIdAndUserId(userFriendId, useId);
        Friend friendFrom = friendRepository.findByFriendIdAndUserId(useId, userFriendId);
        friendRepository.deleteById(friendTo.getId());
        friendRepository.deleteById(friendFrom.getId());
    }

    @Transactional(readOnly = true)
    @Override
    public List<FriendDTO> buildFriendList(int id) {
        User user = userRepository.getById(id);
        List<Friend> friends = user.getFriends();
        List<FriendDTO> friendDTO = new ArrayList<>();
        for (Friend friend: friends) {
            friendDTO.add(new FriendDTO(friend));
        }
        return friendDTO;
    }
}
