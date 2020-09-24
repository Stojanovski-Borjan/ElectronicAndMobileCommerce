package mk.ukim.finki.rentscoot.repository.jpa;

import mk.ukim.finki.rentscoot.model.Location;
import mk.ukim.finki.rentscoot.model.Reservation;
import mk.ukim.finki.rentscoot.repository.ReservationRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservationRepositoryImpl implements ReservationRepository {
    private final JpaReservationRepository repository;

    public ReservationRepositoryImpl(JpaReservationRepository repository) {
        this.repository = repository;
    }


    @Override
    public Reservation createReservation(Reservation reservation) {
        return this.repository.save(reservation);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return this.repository.findAll();
    }

    @Override
    public void deleteReservation(Reservation reservation) {
        this.repository.delete(reservation);
    }

    @Override
    public Optional<Reservation> getReservationById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public List<Reservation> findReservationsByDate(LocalDate date) {
        return this.repository.findAllByDateStartEquals(date);
    }
}
