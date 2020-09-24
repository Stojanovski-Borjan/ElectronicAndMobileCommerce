package mk.ukim.finki.rentscoot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.geo.Point;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String name;

    private String country;

    private String city;

    private String Municipality;

    private String address;

    private String description;

//    private Long latitude;
//    private Long longitude;
    @NotNull
    private Point coordinates;
    @NotNull
    private Point popupCoordinates;
    //private GeoPosition position;
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "location")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Vehicle> vehicles;
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "location")
    private List<Reservation> reservations;

    public Location(String name,String city,String country,String address,String description,Point coordinates,Point popupCoordinates) {
        this.name = name;
        this.city = city;
        this.country = country;
        this.address = address;
        this.description = description;
        this.coordinates = coordinates;
        this.popupCoordinates = popupCoordinates;
        this.vehicles = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }
    public void addVehicles(Vehicle vehicle){
        this.getVehicles().add(vehicle);
        //vehicle.setLocation(this);
    }
}
