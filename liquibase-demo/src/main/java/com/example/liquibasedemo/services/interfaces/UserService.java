package com.example.liquibasedemo.services.interfaces;

import com.example.liquibasedemo.dto.UserDTO;
import com.example.liquibasedemo.dto.UserFullInfoDTO;
import com.example.liquibasedemo.entity.User;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers( );

    void saveUser(UserFullInfoDTO user);

    User getUser(int id);

    UserDTO getUserDTO(int id);

    void deleteUser(int id);
}
