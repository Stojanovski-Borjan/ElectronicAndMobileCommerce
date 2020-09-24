package mk.ukim.finki.rentscoot.service;

import mk.ukim.finki.rentscoot.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface ReservationService {
    Reservation createReservation(Integer locationId, String[] modelName,Long userId,String promotionName, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime);

    Reservation updateReservation(Long id,Integer locationId,String[] modelName,Long userId,String promotionName, LocalDate startDate, LocalTime startTime,LocalDate endDate,LocalTime endTime);

    List<Reservation> getAllReservations();

    void deleteReservation(Long id);

    void deleteAllDueReservations(LocalDate date);

    Reservation getReservation(Long id);

    List<Reservation> findReservationsByUser(String email);

    List<Reservation> findReservationsByLocation(Integer locationId);

    List<Reservation> findReservationsByDate(LocalDate date);

}

