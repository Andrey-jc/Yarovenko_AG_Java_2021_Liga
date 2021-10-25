package com.example.electronicqueue.service;

import com.example.electronicqueue.dto.form.RegistrationRequest;
import com.example.electronicqueue.dto.RoleDTO;
import com.example.electronicqueue.dto.form.RoleToUserForm;
import com.example.electronicqueue.dto.UserDTO;

import java.util.List;

public interface UserService {

    RoleDTO saveRole(RoleDTO role);

    UserDTO addRoleToUser(RoleToUserForm form);

    UserDTO getUser(Long id);

    List<UserDTO> getUsers();

    void deleteUser(Long id);

    UserDTO saveUser(RegistrationRequest registrationRequest);
}
