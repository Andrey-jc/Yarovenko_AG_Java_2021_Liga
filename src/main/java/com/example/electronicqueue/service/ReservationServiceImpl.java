package com.example.electronicqueue.service;

import com.example.electronicqueue.dto.ReservationDTO;
import com.example.electronicqueue.dto.ReservationForAcceptDTO;
import com.example.electronicqueue.dto.form.ReservationAddForm;
import com.example.electronicqueue.dto.form.ReservationCanceledOrChangeStatusForm;
import com.example.electronicqueue.dto.form.ReservationFormForAllFreeTimeToDay;
import com.example.electronicqueue.dto.form.UserFormAllReservationForUser;
import com.example.electronicqueue.entity.Reservation;
import com.example.electronicqueue.entity.Status;
import com.example.electronicqueue.entity.UserApp;
import com.example.electronicqueue.exceptions_handling.NoSuchExceptionElectronicQueue;
import com.example.electronicqueue.repository.ReservationRepository;
import com.example.electronicqueue.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;

    /**
     * Просмотр всех броней пользователя
     *
     * @param login пользователя
     * @return лист резервов
     */
    @Override
    public List<ReservationDTO> getAllReservationForUser(UserFormAllReservationForUser login) {
        UserApp userApp = userRepository.findByLogin(login.getLogin());
        List<Reservation> allReservationUser = reservationRepository.findAllByUserApp(userApp);
        List<ReservationDTO> reservationDTO = new ArrayList<>();
        allReservationUser.forEach(reservation -> {
            if (reservation.getStatus().equals(Status.ACCEPT.getName())
                    && !reservation.getStatus().equals(Status.TIMEOUT.getName())) {
                reservationDTO.add(new ReservationDTO(reservation));
            }
        });
        return reservationDTO;
    }

    /**
     * отмена брони пользователем
     *
     * @param form в форме логин пользователя и id брони
     */
    @Transactional
    @Override
    public void cancellationReservationUser(ReservationCanceledOrChangeStatusForm form) {
        if (reservationRepository.getById(form.getId()).getUserApp().getLogin().equals(form.getLogin())) {
            reservationRepository.deleteById(form.getId());
        } else {
            log.error("Не ваша бронь");
        }
    }

    /**
     * Метод добавления резерва с дальнейшим перенаправлением на подтверждение
     *
     * @param form логин пользователя, дата и время брони
     * @return возвращает информацию о брони
     */
    @Transactional
    @Override
    public ReservationDTO addReservation(ReservationAddForm form) throws ParseException {
        UserApp userApp = userRepository.findByLogin(form.getLogin());
        Reservation reservation = new Reservation();
        reservation.setUserApp(userApp);
        boolean dayOfWeek = form.getDate().getDayOfWeek().getValue() < 6;
        boolean hourJob = form.getTime().getHour() > 7 && form.getTime().getHour() < 20;
        boolean today = form.getDate().getDayOfMonth() == (LocalDateTime.now().getDayOfMonth());
        boolean notToday = form.getDate().getDayOfMonth() > (LocalDateTime.now().getDayOfMonth());
        boolean timeHoursNowTru = form.getTime().getHour() == (LocalDateTime.now().getHour());
        boolean timeHoursMore = form.getTime().getHour() > (LocalDateTime.now().getHour());
        boolean minuteMore = form.getTime().getMinute() > (LocalDateTime.now().getMinute());
        if (dayOfWeek && hourJob && ((today && ((timeHoursNowTru && minuteMore) || (timeHoursMore))) || notToday)
        ) {
            reservation.setDate(form.getDate());
            reservation.setTime(form.getTime());
            reservation.setDateOpenReservation(new Date());
            reservation.setStatus(Status.OPEN.getName());
            List<Reservation> allReservation = reservationRepository.findAll();
            for (Reservation r :
                    allReservation) {
                boolean monthIsTrue = (r.getDate().getDayOfMonth()) == reservation.getDate().getDayOfMonth();
                boolean timeIsTrue = (r.getTime().getHour()) == reservation.getTime().getHour();
                boolean minuteIsTrue = r.getTime().getMinute() == reservation.getTime().getMinute();
                if (monthIsTrue && (timeIsTrue && minuteIsTrue)) {
                    log.error("Время занято");
                    throw new NoSuchExceptionElectronicQueue("Time busy");
                } else if (monthIsTrue && timeIsTrue && ((Math.abs((r.getTime().getMinute()) - (reservation.getTime().getMinute())) * 2) < 15)) {
                    log.error("В это время идёт приём");
                    throw new NoSuchExceptionElectronicQueue("Time busy");
                }
            }
            reservationRepository.save(reservation);
        } else {
            log.error("You reservation date not working hours or incorrect date");
            throw new NoSuchExceptionElectronicQueue("You reservation date not working hours or incorrect date");
        }
        System.out.println(uuidGenerate(userApp, reservation.getId()));
        return new ReservationDTO(reservation);
    }

    /**
     * Посмотреть ближайшую активную бронь
     *
     * @return возвращает ближайшую бронь
     */
    @Override
    public ReservationDTO getActiveReservationFirst() {
        List<Reservation> all = reservationRepository.findAll();
        List<ReservationDTO> reservationDTO = new ArrayList<>();
        all.forEach(reservation -> {
            if (reservation.getStatus().equals(Status.ACCEPT.getName())
                    && (reservation.getDate().getDayOfMonth() >= (LocalDateTime.now().getDayOfMonth()))
                    || (
                    (reservation.getTime().getHour() == (LocalDateTime.now().getHour()))
                            && (reservation.getTime().getMinute() >= (LocalDateTime.now().getMinute()))
            )
                    &&
                    ((reservation.getTime().getHour()) > (LocalDateTime.now().getHour()))
            ) {
                reservationDTO.add(new ReservationDTO(reservation));
            }
        });
        if (reservationDTO.isEmpty()) {
            return null;
        }
        Collections.sort(reservationDTO);
        return reservationDTO.get(0);
    }

    /**
     * удаление брони для пользователя
     *
     * @param idReservation брони
     */
    @Transactional
    @Override
    public void deleteReservation(Long idReservation) {
        reservationRepository.deleteById(idReservation);
    }

    /**
     * метод для пользователя подтвердить что явился
     *
     * @param form логин и id резерва
     * @return информацию о брони
     */
    @Transactional
    @Override
    public ReservationDTO changeStatusReservationToArrived(ReservationCanceledOrChangeStatusForm form) {
        Optional<Reservation> optional = reservationRepository.findById(form.getId());
        if (optional.isPresent() && form.getLogin().equals(optional.get().getUserApp().getLogin())) {
            reservationRepository.updateStatusById(form.getId(), Status.ARRIVED.getName());
        } else {
            throw new NoSuchExceptionElectronicQueue("Bad login or number reservation");
        }
        return new ReservationDTO(reservationRepository.getById(form.getId()));
    }

    @Transactional
    @Override
    public void changeStatusReservationToDone(Long id) {
        reservationRepository.updateStatusById(id, Status.DONE.getName());
    }

    @Transactional
    @Override
    public void changeStatusReservationToTimeOut(Long id) {
        reservationRepository.updateStatusById(id, Status.TIMEOUT.getName());
    }

    /**
     * метод для просмотра следующего пришедшего посетителя
     *
     * @return возвращает ближайшую подтвержденую бронь
     */
    @Override
    public ReservationDTO nextReservationArrived() {
        List<Reservation> all = reservationRepository.findAll();
        List<ReservationDTO> reservationDTO = new ArrayList<>();
        all.forEach(reservation -> {
            if (reservation.getStatus().equals(Status.ARRIVED.getName())
                    && (reservation.getDate().getDayOfMonth() >= (LocalDateTime.now().getDayOfMonth()))
                    && (reservation.getTime().getHour() == (LocalDateTime.now().getHour()))
                    && (reservation.getTime().getMinute()) - (LocalDateTime.now().getMinute()) <= 0) {

                reservationDTO.add(new ReservationDTO(reservation));
            }
        });
        if (reservationDTO.isEmpty()) {
            return null;
        } else {
            Collections.sort(reservationDTO);
            return reservationDTO.get(0);
        }
    }

    /**
     * метод для авто отправки в таймаут
     *
     * @return инфо о брони отправленной в Time Out
     */
    @Override
    public ReservationDTO nextReservationTimeOut() {
        List<Reservation> all = reservationRepository.findAll();
        List<ReservationDTO> reservationDTO = new ArrayList<>();
        all.forEach(reservation -> {
            if (reservation.getStatus().equals(Status.ACCEPT.getName())
                    && (reservation.getDate().getDayOfMonth()) == (LocalDateTime.now().getDayOfMonth())
                    && (reservation.getTime().getHour() == (LocalDateTime.now().getHour()))
                    && (reservation.getTime().getMinute()) - (LocalDateTime.now().getMinute()) <= 0) {
                reservationDTO.add(new ReservationDTO(reservation));
            }
        });
        if (reservationDTO.isEmpty()) {
            return null;
        } else {
            Collections.sort(reservationDTO);
            return reservationDTO.get(0);
        }
    }

    /**
     * просмотр всего занятого времени на определенный день
     *
     * @param date дата формата "yyyy-MM-dd"
     * @return список времени занятого на данную дату
     */
    @Override
    public List<LocalTime> getAllBusyTimeForDay(ReservationFormForAllFreeTimeToDay date) {
        List<Reservation> allByDate = reservationRepository.findAllByDate(date.getDate());
        List<LocalTime> localTimeList = new ArrayList<>();
        allByDate.forEach(reservation -> {
            localTimeList.add(reservation.getTime());
        });
        return localTimeList;
    }

    /**
     * метод для подтверждения открытой брони (после перехода по ссылке)
     *
     * @param id брони
     */
    @Transactional
    @Override
    public void changeStatusReservationToAccept(Long id) {
        reservationRepository.updateStatusById(id, Status.ACCEPT.getName());
    }

    /**
     * метод для автопроверки открытых броней
     * без своевременного подтверждения удаляет их из бд
     */
    @Transactional
    @Override
    public void checkForNoAcceptReservation() {
        long fifteenMinutes = 15 * 60 * 1000;
        List<ReservationForAcceptDTO> list = reservationRepository.findAllByStatusNoAccept(Status.OPEN.getName());
        list.forEach(reservationDate -> {
            long result = Math.abs((reservationDate.getDate().getTime()) - (new Date().getTime()));
            if (result > (fifteenMinutes)) {
                log.info("Deleted reservation ID = " + reservationRepository.getById(reservationDate.getId()).getId());
                reservationRepository.deleteById(reservationDate.getId());
            }
        });
    }

    /**
     * генератор uuid для формирования сообщения о необходимости подтверждения брони
     *
     * @param userApp пользователь для которого формируем сообщение
     * @param id      брони
     * @return готовое сообщение с формированной ссылкой для подтверждения
     */
    private String uuidGenerate(UserApp userApp, Long id) {
        String uuid = UUID.randomUUID().toString();
        return String.format(
                "Hello, %s! \n" +
                        "Welcome to our company. Please, visit next link: http://localhost:8080/api/v1/activate/%s/%s " +
                        "for accept you reservation",
                userApp.getName(),
                uuid,
                id);
    }
}


