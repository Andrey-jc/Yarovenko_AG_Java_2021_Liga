package com.example.liquibasedemo.services;

import com.example.liquibasedemo.dto.FriendDTO;
import com.example.liquibasedemo.entity.User;
import com.example.liquibasedemo.repository.UserRepository;
import com.example.liquibasedemo.services.interfaces.FriendsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class FriendsServiceImpl implements FriendsService {

    private UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public FriendsServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveUserFriend(int useId, int userFriendId) {
        User user = userRepository.getById(useId);
        User userFriend = userRepository.getById(userFriendId);
        user.addFriendUser(userFriend);
        userFriend.addFriendUser(user);
        userRepository.save(user);
        userRepository.save(userFriend);
    }

    @Override
    public void deleteUserFriend(int useId, int userFriendId) {
        User user = userRepository.getById(useId);
        User userFriend = userRepository.getById(userFriendId);
        user.deleteFriend(userFriend);
        userFriend.deleteFriend(user);
        userRepository.save(user);
        userRepository.save(userFriend);
    }

    @Transactional(readOnly = true)
    public FriendDTO buildFriendList(int id) {
        User user = userRepository.getById(id);
        return modelMapper.map(user, FriendDTO.class);
    }
}
