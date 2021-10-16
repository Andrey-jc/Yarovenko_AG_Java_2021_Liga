package com.example.liquibasedemo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {
    private String name;

    public CustomerDTO(String name) {
        this.name = name;
    }
}
