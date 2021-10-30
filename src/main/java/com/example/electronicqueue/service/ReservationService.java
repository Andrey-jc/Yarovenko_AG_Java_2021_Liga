package com.example.electronicqueue.service;

import com.example.electronicqueue.dto.ReservationDTO;
import com.example.electronicqueue.dto.form.ReservationAddForm;
import com.example.electronicqueue.dto.form.ReservationCanceledOrChangeStatusForm;
import com.example.electronicqueue.dto.form.ReservationFormForAllFreeTimeToDay;
import com.example.electronicqueue.dto.form.UserFormAllReservationForUser;

import java.text.ParseException;
import java.time.LocalTime;
import java.util.List;

public interface ReservationService {

    List<ReservationDTO> getAllReservationForUser(UserFormAllReservationForUser login);

    void cancellationReservationUser(ReservationCanceledOrChangeStatusForm form);

    ReservationDTO addReservation(ReservationAddForm form) throws ParseException;

    ReservationDTO getActiveReservationFirst();

    void deleteReservation(Long id);

    ReservationDTO nextReservationArrived();

    ReservationDTO changeStatusReservationToArrived(ReservationCanceledOrChangeStatusForm form);

    void changeStatusReservationToDone(Long id);

    void changeStatusReservationToTimeOut(Long id);

    ReservationDTO nextReservationTimeOut();

    List<LocalTime> getAllBusyTimeForDay(ReservationFormForAllFreeTimeToDay day);

    void changeStatusReservationToAccept(Long id, String User);

    void checkForNoAcceptReservation();
}
