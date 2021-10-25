package com.example.electronicqueue.repository;

import com.example.electronicqueue.dto.ReservationForAcceptDTO;
import com.example.electronicqueue.entity.Reservation;
import com.example.electronicqueue.entity.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Reservation findByDate(Date date);

    List<Reservation> findAllByUserApp(UserApp userApp);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE Reservation r SET r.status = :status WHERE r.id = :id")
    void updateStatusById(@Param("id") Long id, @Param("status") String  status);

    @Modifying(clearAutomatically = true)
    @Query(value = "SELECT r FROM Reservation r WHERE r.date = :date")
    List<Reservation> findAllByDate(@Param("date") LocalDate date);

    @Modifying(clearAutomatically = true)
    @Query(value = "SELECT r FROM Reservation r WHERE r.status = :status")
    List<ReservationForAcceptDTO> findAllByStatusNoAccept(@Param("status") String status);
}
