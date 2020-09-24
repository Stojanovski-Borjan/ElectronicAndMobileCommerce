package mk.ukim.finki.rentscoot.repository.jpa;

import mk.ukim.finki.rentscoot.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaVehicleRepository extends JpaRepository<Vehicle,String> {
}
