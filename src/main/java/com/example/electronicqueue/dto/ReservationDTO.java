package com.example.electronicqueue.dto;

import com.example.electronicqueue.entity.Reservation;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@ToString
public class ReservationDTO implements Comparable<ReservationDTO> {

    private Long id_reservation;

    private String login;

    private LocalDate date;

    private LocalTime time;

    private String status;

    public ReservationDTO(Reservation reservation) {
        this.id_reservation = reservation.getId();
        this.login = reservation.getUserApp().getLogin();
        this.date = reservation.getDate();
        this.time = reservation.getTime();
        this.status = reservation.getStatus();
    }

    @Override
    public int compareTo(ReservationDTO o) {
        return getDate().compareTo(o.getDate());
    }
}
