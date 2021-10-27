package com.example.electronicqueue.dto;

import com.example.electronicqueue.entity.Reservation;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class ReservationForAcceptDTO {

    private Long id;

    private Date date;

    public ReservationForAcceptDTO(Reservation reservation) {
        this.id = reservation.getId();
        this.date = reservation.getDateOpenReservation();
    }
}
