package mk.ukim.finki.rentscoot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Vehicle {
    @Id
    private String serialNo;

    private String description;
    @NotNull
    private LocalDate dateBought;
    @ManyToOne
    @NotNull
    private VehicleModel model;
    @JsonIgnore //Only for client app. For management app you will need the location
    @ManyToOne
    private Location location;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Reservation> reservations;
    @NotNull
    private boolean onTheRoad=false;
}
