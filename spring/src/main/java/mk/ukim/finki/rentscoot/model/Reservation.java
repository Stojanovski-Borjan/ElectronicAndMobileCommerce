package mk.ukim.finki.rentscoot.model;

import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "reservations",fetch = FetchType.EAGER)
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Vehicle> vehicles;
    @ManyToOne
    private Promotion promotion;
    @ManyToOne
    private Location location;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE) // when a client is deleted the Reservation will be deleted also
    private User client;

    private LocalDateTime dateSubmited;

    private LocalDate dateStart;

    private LocalDate dateEnd;

    private LocalTime timeStart;

    private LocalTime timeEnd;

    private double totalPrice;

//    public void addVehicle(Vehicle vehicle){
//        this.vehicles.add(vehicle);
//        vehicle.getReservations().add(this);
//    }
//    public void removeVehicle(Vehicle vehicle){
//        this.vehicles.remove(vehicle);
//        vehicle.getReservations().remove(this);
//    }
}
