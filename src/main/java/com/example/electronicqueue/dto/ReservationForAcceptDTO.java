package com.example.electronicqueue.dto;

import com.example.electronicqueue.entity.Reservation;
import lombok.Data;

import java.util.Date;

@Data
public class ReservationForAcceptDTO {

    private Long id;

    private Date date;

    public ReservationForAcceptDTO(Reservation reservation) {
        this.id = reservation.getId();
        this.date = reservation.getDateOpenReservation();
    }
}
