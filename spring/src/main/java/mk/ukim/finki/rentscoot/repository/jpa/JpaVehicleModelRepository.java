package mk.ukim.finki.rentscoot.repository.jpa;

import mk.ukim.finki.rentscoot.model.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaVehicleModelRepository extends JpaRepository<VehicleModel,String> {
    List<VehicleModel> findAllByModelNameContains(String modelName);
}
