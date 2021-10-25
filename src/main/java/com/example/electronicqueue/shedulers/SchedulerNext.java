package com.example.electronicqueue.shedulers;

import com.example.electronicqueue.dto.ReservationDTO;
import com.example.electronicqueue.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchedulerNext {
    private final ReservationService reservationService;

    //Метод без вызова юзеров администратором при отсутсвии подтвержденной брони вызываеться следующий в порядке живой очереди
    @Scheduled(cron = "${variable.name.inside.properties}")
    public void reportCurrentData() {
        ReservationDTO reservationOpen = reservationService.nextReservationTimeOut();
        if (reservationOpen != null) {
            reservationService.changeStatusReservationToTimeOut(reservationOpen.getId_reservation());
        }
        System.out.print("\nПриглашается: ");
        ReservationDTO reservationDTO = reservationService.nextReservationArrived();
        if (reservationDTO != null) {
            System.out.println(reservationDTO.getLogin());
            System.out.println();
            reservationService.changeStatusReservationToDone(reservationDTO.getId_reservation());
        } else {
            System.out.println("Следующий в порядке живой очереди");
        }
    }
}
