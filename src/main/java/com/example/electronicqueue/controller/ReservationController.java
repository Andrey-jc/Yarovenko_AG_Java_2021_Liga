package com.example.electronicqueue.controller;

import com.example.electronicqueue.dto.ReservationDTO;
import com.example.electronicqueue.dto.form.ReservationAddForm;
import com.example.electronicqueue.dto.form.ReservationCanceledOrChangeStatusForm;
import com.example.electronicqueue.dto.form.ReservationFormForAllFreeTimeToDay;
import com.example.electronicqueue.dto.form.UserFormAllReservationForUser;
import com.example.electronicqueue.service.ReservationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(value = "Reservation for User and Admin CRUD operations", description = "Reservation CRUD operations")
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping("/reservations/users")
    @ApiOperation(value = "Get all active reservation the current user")
    public ResponseEntity<List<ReservationDTO>> getAllActiveReservationUser(
            @RequestBody UserFormAllReservationForUser login) {
        return ResponseEntity.ok().body(reservationService.getAllReservationForUser(login));
    }

    @DeleteMapping("/reservations/users/delete")
    @ApiOperation(value = "Canceled reservation for user")
    public ResponseEntity<?> cancelReservationForUser(@RequestBody ReservationCanceledOrChangeStatusForm form) {
        reservationService.cancellationReservationUser(form);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reservations/users/add")
    @ApiOperation(value = "Add reservation user")
    public ResponseEntity<ReservationDTO> addReservation(@RequestBody ReservationAddForm form) throws ParseException {
        return ResponseEntity.ok().body(reservationService.addReservation(form));
    }

    @PutMapping("/reservations/users")
    @ApiOperation(value = "Mark user on arrival")
    public ResponseEntity<ReservationDTO> changeStatusArrived(@RequestBody ReservationCanceledOrChangeStatusForm form) {
        return ResponseEntity.ok().body(reservationService.changeStatusReservationToArrived(form));
    }

    @GetMapping("/activate/*/{id}")
    @ApiOperation(value = "Accept reservation for user")
    public void acceptReservation(@PathVariable("id") Long id) {
        reservationService.changeStatusReservationToAccept(id);
    }

    @GetMapping("/reservations/admin")
    @ApiOperation(value = "Get first active reservation")
    public ResponseEntity<ReservationDTO> getAllActiveReservationFirst() {
        return ResponseEntity.ok().body(reservationService.getActiveReservationFirst());
    }

    @DeleteMapping("/reservations/admin/delete/{id}")
    @ApiOperation(value = "Delete reservation api for admin")
    public ResponseEntity<?> deleteReservation(@PathVariable("id") Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/reservations/admin/done/{id}")
    @ApiOperation(value = "Change status reservation for done")
    public ResponseEntity<?> changeStatusDone(@PathVariable("id") Long idReservation) {
        reservationService.changeStatusReservationToDone(idReservation);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/reservations/admin/timeout/{id}")
    @ApiOperation(value = "Change status reservation for time out")
    public ResponseEntity<?> changeStatusTimeOut(@PathVariable("id") Long idReservation) {
        reservationService.changeStatusReservationToTimeOut(idReservation);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/reservations/users/busytime")
    @ApiOperation(value = "Get all busy time for the current day")
    public ResponseEntity<List<LocalTime>> getAllBusyTimeForDay(@RequestBody ReservationFormForAllFreeTimeToDay form) {
        return ResponseEntity.ok().body(reservationService.getAllBusyTimeForDay(form));
    }
}
