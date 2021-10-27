package com.example.electronicqueue.dto.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
public class RegistrationRequest {

    @NotEmpty
    private String name;

    @NotEmpty
    private String login;

    @NotEmpty
    private String password;
}