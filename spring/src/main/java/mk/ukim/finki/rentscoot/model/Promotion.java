package mk.ukim.finki.rentscoot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Promotion {

    @Id
    private String name;

    private String description;
    @NotNull
    private double discount; //percentage from 0 to 1
    @JsonIgnore
    @OneToMany(mappedBy = "promotion")
    private List<Reservation> reservations;

    private LocalDate validFrom;

    private LocalDate validTo;

}
