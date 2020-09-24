package mk.ukim.finki.rentscoot.service.implementation;

import mk.ukim.finki.rentscoot.model.*;
import mk.ukim.finki.rentscoot.model.exceptions.*;
import mk.ukim.finki.rentscoot.repository.LocationRepository;
import mk.ukim.finki.rentscoot.repository.PromotionRepository;
import mk.ukim.finki.rentscoot.repository.ReservationRepository;
import mk.ukim.finki.rentscoot.repository.UserRepository;
import mk.ukim.finki.rentscoot.service.ReservationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImplementation implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;
    private final PromotionRepository promotionRepository;

    public ReservationServiceImplementation(ReservationRepository reservationRepository, LocationRepository locationRepository, UserRepository userRepository, PromotionRepository promotionRepository) {
        this.reservationRepository = reservationRepository;
        this.locationRepository = locationRepository;
        this.userRepository = userRepository;
        this.promotionRepository = promotionRepository;
    }

    @Override
    public Reservation createReservation(Integer locationId, String[] modelName, Long userId, String promotionName, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        Reservation reservation = constructReservation(new Reservation(),locationId,modelName,userId,promotionName,startDate,startTime,endDate,endTime);
        return this.reservationRepository.createReservation(reservation);
    }

    @Override
    public Reservation updateReservation(Long id, Integer locationId, String[] modelName, Long userId, String promotionName, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        Reservation reservation = this.reservationRepository.getReservationById(id).orElseThrow(InvalidReservationException::new);
        Reservation updatedReservation = constructReservation(reservation,locationId,modelName,userId,promotionName,startDate,startTime,endDate,endTime);
        return this.reservationRepository.createReservation(updatedReservation);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return this.reservationRepository.getAllReservations();
    }

    @Override
    public void deleteReservation(Long id) {
        Reservation reservation = this.reservationRepository.getReservationById(id).orElseThrow(InvalidReservationException::new);
        this.reservationRepository.deleteReservation(reservation);
    }

    @Override
    public void deleteAllDueReservations(LocalDate date) {
        List<Reservation> reservations = this.reservationRepository.getAllReservations();
        List<Reservation> dueReservations = reservations.stream().filter(reservation -> reservation.getDateEnd().isBefore(date)).collect(Collectors.toList());
        dueReservations.forEach(this.reservationRepository::deleteReservation); //replaced lambda expresion with method reference
    }

    @Override
    public Reservation getReservation(Long id) {
        return this.reservationRepository.getReservationById(id).orElseThrow(InvalidReservationException::new);
    }

    @Override
    public List<Reservation> findReservationsByUser(String email){
        return this.reservationRepository.getAllReservations().stream().filter(reservation -> email.equals(reservation.getClient().getEmail())).collect(Collectors.toList());
    }

    @Override
    public List<Reservation> findReservationsByLocation(Integer locationId) {
        return this.reservationRepository.getAllReservations().stream().filter(reservation -> locationId.equals(reservation.getLocation().getId())).collect(Collectors.toList());
    }

    @Override
    public List<Reservation> findReservationsByDate(LocalDate date) {
        return this.reservationRepository.findReservationsByDate(date);
    }

    private Reservation constructReservation(Reservation reservation,Integer locationId, String[] modelName, Long userId, String promotionName, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime){
        Location location = this.locationRepository.findLocationById(locationId).orElseThrow(InvalidLocationException::new);
        reservation.setLocation(location);
        if(reservation.getDateSubmited()==null) {
            reservation.setDateSubmited(LocalDateTime.now());
        }
        reservation.setDateStart(startDate);
        reservation.setDateEnd(endDate);
        reservation.setTimeStart(startTime);
        reservation.setTimeEnd(endTime);
        if(modelName.length == 0) throw new InvalidReservationException("There are no vehicles chosen for this reservation");
        List<Vehicle> vehicles = location.getVehicles().stream().filter(isAvailable(startDate,startTime,endDate,endTime)).collect(Collectors.toList());
        List<Vehicle> vehiclesForReservation = new ArrayList<>();
        if(vehicles.size()>0) {
            Arrays.stream(modelName).forEach(model -> {
                // najdi takov model na vozilo na datumot
                Vehicle vehicle = vehicles.stream().filter(vehicle1 -> model.equals(vehicle1.getModel().getModelName())).findFirst().orElseThrow(InvalidVehicleException::new);
                // stavi go vo lista na vozila
                vehiclesForReservation.add(vehicle);
            });
            double totalPrice;
            reservation.setVehicles(vehiclesForReservation);
            double totalPricePerMin = vehiclesForReservation.stream().mapToDouble(vehicle -> vehicle.getModel().getPricePerMinute()).sum();
            LocalDateTime start = LocalDateTime.of(startDate,startTime);
            LocalDateTime end = LocalDateTime.of(endDate,endTime);
            long minutesOfReservation = ChronoUnit.MINUTES.between(start,end);
            totalPrice = totalPricePerMin * (double)minutesOfReservation;
            if(this.promotionRepository.exists(promotionName)) {
                Promotion promotion = this.promotionRepository.findById(promotionName).orElseThrow(InvalidPromotionException::new);
                reservation.setPromotion(promotion);
                //discount from promotion
                totalPrice = totalPrice - promotion.getDiscount() * totalPrice; // could easily be totalPrice*(1-DISCOUNT)
            }
            User user = this.userRepository.getUserById(userId).orElseThrow(InvalidUserException::new);
            reservation.setClient(user);
            reservation.setTotalPrice(totalPrice);
            return reservation;
        }
        else throw new InvalidReservationException("No vehicles are chosen for this reservation.Development state only");
    }
    private Predicate<Vehicle> isAvailable(LocalDate startDate,LocalTime startTime,LocalDate endDate,LocalTime endTime) {
        return  v -> v.getReservations().stream()
                .noneMatch( reservation1 -> (startDate.isAfter(reservation1.getDateStart())
                        && startTime.isAfter(reservation1.getTimeStart()) && startDate.isBefore(reservation1.getDateEnd())
                        && startTime.isBefore(reservation1.getTimeEnd())) || (endDate.isAfter(reservation1.getDateStart())
                        && endTime.isAfter(reservation1.getTimeStart()) && endDate.isBefore(reservation1.getDateEnd())
                        && endTime.isBefore(reservation1.getTimeEnd()))
                );
    }
}
