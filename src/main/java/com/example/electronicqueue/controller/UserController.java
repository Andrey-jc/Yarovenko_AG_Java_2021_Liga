package com.example.electronicqueue.controller;

import com.example.electronicqueue.dto.RoleDTO;
import com.example.electronicqueue.dto.UserDTO;
import com.example.electronicqueue.dto.form.RegistrationRequest;
import com.example.electronicqueue.dto.form.RoleToUserForm;
import com.example.electronicqueue.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(value = "User and Admin CRUD operations", description = "User and Admin CRUD operations")
public class UserController {
    private final UserService userService;

    @PostMapping("/registration")
    @ApiOperation(value = "Register user")
    public ResponseEntity<UserDTO> register(@RequestBody @Valid RegistrationRequest registrationRequest) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/registration").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(registrationRequest));
    }

    @GetMapping("/users/{id}")
    @ApiOperation(value = "Get user for he id")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(userService.getUser(id));
    }

    @GetMapping("/admin/users")
    @ApiOperation(value = "Enumerates all Users entities")
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/admin/role/save")
    @ApiOperation(value = "Save new role for database")
    public ResponseEntity<RoleDTO> saveRole(@RequestBody RoleDTO role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/admin/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PutMapping("/admin/role/add")
    @ApiOperation(value = "Save new role for database for user")
    public ResponseEntity<UserDTO> addRoleToUser(@RequestBody RoleToUserForm form) {
        return ResponseEntity.ok().body(userService.addRoleToUser(form));
    }

    @DeleteMapping("/admin/users/delete/{id}")
    @ApiOperation(value = "Delete user for he id")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
