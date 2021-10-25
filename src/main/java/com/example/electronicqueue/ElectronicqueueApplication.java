package com.example.electronicqueue;

import com.example.electronicqueue.dto.ReservationDTO;
import com.example.electronicqueue.dto.RoleDTO;
import com.example.electronicqueue.dto.form.RegistrationRequest;
import com.example.electronicqueue.dto.form.ReservationAddForm;
import com.example.electronicqueue.dto.form.RoleToUserForm;
import com.example.electronicqueue.dto.form.UserFormAllReservationForUser;
import com.example.electronicqueue.service.ReservationService;
import com.example.electronicqueue.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class ElectronicqueueApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElectronicqueueApplication.class, args);
    }

    /**
     * бин для демо заполнения бд и проверки некоторых методов
     */
    @Bean
    public CommandLineRunner demo(UserService userService, ReservationService reservationService) {
        return (args) -> {
            userService.saveRole(new RoleDTO("ROLE_USER"));
            userService.saveRole(new RoleDTO("ROLE_ADMIN"));

            userService.saveUser(new RegistrationRequest("Andrey Yarovenko", "Andrey",
                    "1234"));

            userService.saveUser(new RegistrationRequest("Alina Yarovenko", "Alina",
                    "1234"));
            userService.saveUser(new RegistrationRequest("Natali Wilson", "Natali",
                    "1234"));
            userService.saveUser(new RegistrationRequest("Oleg Ivanov", "Oleg",
                    "123456"));
            userService.saveUser(new RegistrationRequest("Ivan Smirnov", "Ivan",
                    "54321"));

            RoleToUserForm formRole = new RoleToUserForm("Andrey", "ROLE_ADMIN");
            userService.addRoleToUser(formRole);

            ReservationAddForm reservationAddForm1 = new ReservationAddForm(
                    "Alina", LocalDate.parse("2021-10-25"), LocalTime.parse("19:15"));
            ReservationAddForm reservationAddForm2 = new ReservationAddForm(
                    "Alina", LocalDate.parse("2021-10-25"), LocalTime.parse("18:45"));
            ReservationAddForm reservationAddForm3 = new ReservationAddForm(
                    "Alina", LocalDate.parse("2021-10-25"), LocalTime.parse("19:30"));
            ReservationAddForm reservationAddForm4 = new ReservationAddForm(
                    "Natali", LocalDate.parse("2021-10-25"), LocalTime.parse("19:45"));
            ReservationAddForm reservationAddForm5 = new ReservationAddForm(
                    "Natali", LocalDate.parse("2021-10-27"), LocalTime.parse("12:00"));
            ReservationAddForm reservationAddForm6 = new ReservationAddForm(
                    "Natali", LocalDate.parse("2021-10-27"), LocalTime.parse("08:00"));
            ReservationAddForm reservationAddForm7 = new ReservationAddForm(
                    "Natali", LocalDate.parse("2021-10-27"), LocalTime.parse("09:00"));
            ReservationAddForm reservationAddForm8 = new ReservationAddForm(
                    "Andrey", LocalDate.parse("2021-10-28"), LocalTime.parse("19:20"));
            ReservationAddForm reservationAddForm9 = new ReservationAddForm(
                    "Natali", LocalDate.parse("2021-10-29"), LocalTime.parse("18:45"));

            reservationService.addReservation(reservationAddForm1);
            reservationService.addReservation(reservationAddForm2);
            reservationService.addReservation(reservationAddForm3);
            reservationService.addReservation(reservationAddForm5);
            reservationService.addReservation(reservationAddForm4);
            reservationService.addReservation(reservationAddForm6);
            reservationService.addReservation(reservationAddForm7);
            reservationService.addReservation(reservationAddForm8);
            reservationService.addReservation(reservationAddForm9);

            // изменить статус на accept
            reservationService.changeStatusReservationToAccept(1L);
            reservationService.changeStatusReservationToAccept(2L);
            reservationService.changeStatusReservationToAccept(3L);
            reservationService.changeStatusReservationToAccept(4L);
            reservationService.changeStatusReservationToAccept(5L);
            reservationService.changeStatusReservationToAccept(6L);

            //ближайшая активная бронь
            ReservationDTO reservationUsers = reservationService.getActiveReservationFirst();
            System.out.println(reservationUsers.toString());

            // список всех активных броней пользователя
            UserFormAllReservationForUser userFormAllReservationForUser = new UserFormAllReservationForUser("Natali");
            List<ReservationDTO> allReservationForUser = reservationService.getAllReservationForUser(userFormAllReservationForUser);
            System.out.println(allReservationForUser);
        };
    }
}

