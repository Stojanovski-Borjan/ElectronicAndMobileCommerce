package mk.ukim.finki.rentscoot.repository;

import mk.ukim.finki.rentscoot.model.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository {
    Reservation createReservation(Reservation reservation);

    List<Reservation> getAllReservations();

    void deleteReservation(Reservation reservation);

    Optional<Reservation> getReservationById(Long id);

    List<Reservation> findReservationsByDate(LocalDate date);
}
