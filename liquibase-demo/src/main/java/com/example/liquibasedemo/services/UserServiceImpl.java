package com.example.liquibasedemo.services;

import com.example.liquibasedemo.dto.UserDTO;
import com.example.liquibasedemo.dto.UserFullInfoDTO;
import com.example.liquibasedemo.entity.User;
import com.example.liquibasedemo.repository.UserRepository;
import com.example.liquibasedemo.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService {

    private  UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> allUser = userRepository.findAll();
        List<UserDTO> userDTO = new ArrayList<>();
        for (User user :
                allUser) {
            UserDTO mapUser = modelMapper.map(user, UserDTO.class);
            userDTO.add(mapUser);
        }
        return userDTO;
    }

    @Override
    @Transactional
    public void saveUser(UserFullInfoDTO userFullInfoDTO) {
        User user = modelMapper.map(userFullInfoDTO, User.class);
        userRepository.save(user);
    }

    @Override
    public User getUser(int id) {
        User user = null;
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            user = optional.get();
        }
        return user;
    }

    @Override
    public UserDTO getUserDTO(int id) {
        UserDTO userDTO = null;
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            userDTO = modelMapper.map(optional.get(), UserDTO.class);
        }
        return userDTO;
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
