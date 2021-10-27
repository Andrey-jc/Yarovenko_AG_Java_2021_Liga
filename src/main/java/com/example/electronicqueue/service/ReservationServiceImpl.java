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
import com.example.electronicqueue.exceptions_handling.NoSuchElectronicQueueException;
import com.example.electronicqueue.repository.ReservationRepository;
import com.example.electronicqueue.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;

    @Value("${app.date.first.weekend.day}")
    private Integer firstWeekendDay;

    @Value("${app.time.open}")
    private Integer timeOpen;

    @Value("${app.time.closed}")
    private Integer timeClosed;

    @Value("${app.time.toConfirm}")
    private Integer timeToConfirm;

    @Value("${app.port}")
    private String port;

    @Value("${app.host}")
    private String host;

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
        Long idReservation = form.getId();
        Reservation reservation = reservationRepository.getById(idReservation);
        UserApp userApp = reservation.getUserApp();
        String loginUser = userApp.getLogin();
        if (loginUser.equals(form.getLogin())) {
            reservationRepository.deleteById(form.getId());
        } else {
            throw new NoSuchElectronicQueueException("Not your reservation");
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
        checkValidDate(form.getDate(), form.getTime());
        reservation.setDate(form.getDate());
        reservation.setTime(form.getTime());
        reservation.setDateOpenReservation(new Date());
        reservation.setStatus(Status.OPEN.getName());
        checkTimeReservation(reservation);
        reservationRepository.save(reservation);
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
        Long id = reservationRepository.getFirstByStatusOrderByDateTime(Status.ACCEPT.getName());
        if (id == null) {
            throw new NoSuchElectronicQueueException("No confirmed reservations");
        }
        Reservation reservation = reservationRepository.getById(id);
        return new ReservationDTO(reservation);
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
        String login = form.getLogin();
        if (optional.isPresent() && login.equals(optional.get().getUserApp().getLogin())) {
            reservationRepository.updateStatusById(form.getId(), Status.ARRIVED.getName());
        } else {
            throw new NoSuchElectronicQueueException("Bad login or number reservation");
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
        Long idReservation = reservationRepository.getFirstByStatusOrderByDateTime(Status.ARRIVED.getName());
        if (idReservation == null) {
            throw new NoSuchElectronicQueueException("No users who came");
        }
        Reservation reservation = reservationRepository.getById(idReservation);
        if (checkCurrentTime(reservation)) {
            return new ReservationDTO(reservation);
        } else {
            return null;
        }
    }

    /**
     * метод для авто отправки в таймаут
     *
     * @return инфо о брони отправленной в Time Out
     */
    @Override
    public ReservationDTO nextReservationTimeOut() {
        Long idReservation = reservationRepository.getFirstByStatusOrderByDateTime(Status.ACCEPT.getName());
        Reservation reservation = reservationRepository.getById(idReservation);
        if (checkCurrentTime(reservation)) {
            return new ReservationDTO(reservation);
        } else {
            return null;
        }
    }

    /**
     * метод для проверки времени брони с текущим временем
     *
     * @param reservation текущий резерв
     * @return
     */
    private boolean checkCurrentTime(Reservation reservation) {
        return (reservation.getDate().getDayOfMonth()) == (LocalDateTime.now().getDayOfMonth())
                && (reservation.getTime().getHour() == (LocalDateTime.now().getHour()))
                && (reservation.getTime().getMinute()) - (LocalDateTime.now().getMinute()) <= 0;
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
        List<ReservationForAcceptDTO> list = reservationRepository.findAllByStatusNoAccept(Status.OPEN.getName());
        list.forEach(reservationDate -> {
            long result = Math.abs((reservationDate.getDate().getTime()) - (new Date().getTime()));
            if (result > (timeToConfirm)) {
                reservationRepository.deleteById(reservationDate.getId());
            }
        });
    }

    /**
     * метод для проверки даты и время брони
     *
     * @param date дата брони
     * @param time время брони
     */
    private void checkValidDate(LocalDate date, LocalTime time) {
        boolean dayOfWeek = date.getDayOfWeek().getValue() < firstWeekendDay;
        boolean hourJob = time.getHour() >= timeOpen && time.getHour() < timeClosed;
        boolean today = date.getDayOfMonth() == (LocalDateTime.now().getDayOfMonth());
        boolean currentMonth = date.getMonth() == (LocalDate.now().getMonth());
        boolean notToday = date.getDayOfMonth() > (LocalDateTime.now().getDayOfMonth());
        boolean timeHoursNowTru = time.getHour() == (LocalDateTime.now().getHour());
        boolean timeHoursMore = time.getHour() > (LocalDateTime.now().getHour());
        boolean minuteMore = time.getMinute() > (LocalDateTime.now().getMinute());
        if (!(dayOfWeek && hourJob &&
                (!currentMonth ||
                        today && (timeHoursNowTru && minuteMore || timeHoursMore) || notToday))
        ) {
            throw new NoSuchElectronicQueueException("You reservation date not working hours or incorrect date");
        }
    }

    /**
     * метод для проверки время брони
     * занято ли оно или нет
     *
     * @param reservation текущая бронь
     */
    private void checkTimeReservation(Reservation reservation) {
        List<Reservation> allReservation = reservationRepository.findAll();
        for (Reservation r :
                allReservation) {
            boolean monthIsTrue = (r.getDate().getDayOfMonth()) == reservation.getDate().getDayOfMonth();
            boolean timeIsTrue = (r.getTime().getHour()) == reservation.getTime().getHour();
            boolean minuteIsTrue = r.getTime().getMinute() == reservation.getTime().getMinute();
            if (monthIsTrue && (timeIsTrue && minuteIsTrue)) {
                throw new NoSuchElectronicQueueException("Time busy");
            } else if (monthIsTrue && timeIsTrue && ((Math.abs((r.getTime().getMinute()) - (reservation.getTime().getMinute())) * 2) < 15)) {
                throw new NoSuchElectronicQueueException("Time busy");
            }
        }
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
                        "Welcome to our company. Please, visit next link: http://%s:%s/api/v1/activate/%s/%s " +
                        "for accept you reservation",
                userApp.getName(),
                host,
                port,
                uuid,
                id);
    }
}
