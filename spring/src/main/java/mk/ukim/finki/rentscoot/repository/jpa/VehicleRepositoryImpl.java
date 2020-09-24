package mk.ukim.finki.rentscoot.repository.jpa;

import mk.ukim.finki.rentscoot.model.Vehicle;
import mk.ukim.finki.rentscoot.repository.VehicleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class VehicleRepositoryImpl implements VehicleRepository {
    private final JpaVehicleRepository repository;

    public VehicleRepositoryImpl(JpaVehicleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Vehicle addVehicle(Vehicle vehicle) {
        return this.repository.save(vehicle);
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return this.repository.findAll();
    }

    @Override
    public void deleteVehicle(Vehicle vehicle) {
        this.repository.delete(vehicle);
    }

    @Override
    public Optional<Vehicle> findVehicleById(String serialNo) {
        return this.repository.findById(serialNo);
    }

    @Override
    public boolean exists(String id) {
        return this.repository.existsById(id);
    }
}
