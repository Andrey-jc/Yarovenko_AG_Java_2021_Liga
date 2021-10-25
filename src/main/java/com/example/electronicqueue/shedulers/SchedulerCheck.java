package com.example.electronicqueue.shedulers;

import com.example.electronicqueue.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchedulerCheck {
    private final ReservationService reservationService;

    //Метод без вызова юзеров администратором при отсутсвии подтвержденной брони вызываеться следующий в порядке живой очереди
    @Scheduled(cron = "${variable.name.inside.properties}")
    public void reportCurrentData() {
        reservationService.checkForNoAcceptReservation();
    }
}
