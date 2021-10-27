package com.example.electronicqueue.service;

import com.example.electronicqueue.dto.RoleDTO;
import com.example.electronicqueue.dto.UserDTO;
import com.example.electronicqueue.dto.form.RegistrationRequest;
import com.example.electronicqueue.dto.form.RoleToUserForm;
import com.example.electronicqueue.entity.Role;
import com.example.electronicqueue.entity.UserApp;
import com.example.electronicqueue.exceptions_handling.NoSuchElectronicQueueException;
import com.example.electronicqueue.repository.RoleRepository;
import com.example.electronicqueue.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    // UserDetails override
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserApp userApp = userRepository.findByLogin(username);
        if (userApp == null) {
            throw new UsernameNotFoundException("User not found in the database");
        } else {
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        изменить тут если будет необходимо много ролей для 1го пользователя
        authorities.add(new SimpleGrantedAuthority(userApp.getRole().getName()));
        return new org.springframework.security.core.userdetails.User(
                userApp.getLogin(), userApp.getPassword(), authorities
        );
    }

    @Override
    public RoleDTO saveRole(RoleDTO role) {
        Role roleEntity = modelMapper.map(role, Role.class);
        roleRepository.save(roleEntity);
        return role;
    }

    @Override
    public UserDTO addRoleToUser(RoleToUserForm form) {
        UserApp userApp = userRepository.findByLogin(form.getUserName());
        if (userApp == null) {
            throw new NoSuchElectronicQueueException("User not found in the database");
        } else {
            Role role = roleRepository.findByName(form.getRoleName());
            userApp.setRole(role);
            userRepository.save(userApp);
        }
        return new UserDTO(userApp);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDTO getUser(Long id) {
        UserApp user;
        Optional<UserApp> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            user = optional.get();
            return new UserDTO(user);
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDTO> getUsers() {
        List<UserApp> userAppList = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        userAppList.forEach(userApp -> {
            userDTOList.add(new UserDTO(userApp));
        });
        return userDTOList;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO saveUser(RegistrationRequest registrationRequest) {
        UserApp userApp = new UserApp();
        userApp.setLogin(registrationRequest.getLogin());
        userApp.setPassword((registrationRequest.getPassword()));
        userApp.setName(registrationRequest.getName());
        userApp.setPassword(passwordEncoder.encode(userApp.getPassword()));
        Role role = roleRepository.findByName("ROLE_USER");
        userApp.setRole(role);
        return new UserDTO(userRepository.save(userApp));
    }
}
