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

    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public FriendsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
    @Override
    public FriendDTO buildFriendList(User userCurrent) {
        return modelMapper.map(userCurrent, FriendDTO.class);
    }
}
