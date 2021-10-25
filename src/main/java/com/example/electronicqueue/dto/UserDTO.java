package com.example.electronicqueue.dto;

import com.example.electronicqueue.entity.UserApp;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;

    private String name;

    private String role;

    public UserDTO(UserApp userApp) {
        this.id = userApp.getId();
        this.name = userApp.getName();
        this.role = userApp.getRole().getName();
    }
}
