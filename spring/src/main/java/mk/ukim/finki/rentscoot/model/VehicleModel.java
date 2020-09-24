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
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleModel {
    @Id
    private String modelName;

    @Enumerated(EnumType.STRING)
    private VehicleType type;

    private String description;
    @NotNull
    private double pricePerMinute;
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "model")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Vehicle> vehicles;

    private int amountAvailable=0;

    public void addVehicle(Vehicle vehicle){
        this.vehicles.add(vehicle);
        //vehicle.setModel(this);
    }
//    void removeVehicle(Vehicle vehicle){
//        this.vehicles.remove(vehicle);
//    }
}
