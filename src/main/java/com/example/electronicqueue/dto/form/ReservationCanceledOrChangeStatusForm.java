package com.example.electronicqueue.dto.form;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservationCanceledOrChangeStatusForm {

    private Long id;

    private String login;
}
