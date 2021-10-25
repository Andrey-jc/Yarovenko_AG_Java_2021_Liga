package com.example.electronicqueue.dto.form;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class RegistrationRequest {

    @NotEmpty
    private String name;

    @NotEmpty
    private String login;

    @NotEmpty
    private String password;
}