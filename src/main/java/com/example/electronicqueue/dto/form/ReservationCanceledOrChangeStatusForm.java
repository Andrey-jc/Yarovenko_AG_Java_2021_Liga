package com.example.electronicqueue.dto.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReservationCanceledOrChangeStatusForm {

    private Long id;

    private String login;
}
