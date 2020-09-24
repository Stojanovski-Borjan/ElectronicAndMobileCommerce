package mk.ukim.finki.rentscoot.repository.jpa;

import mk.ukim.finki.rentscoot.model.Location;
import mk.ukim.finki.rentscoot.repository.LocationRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LocationRepositoryImpl implements LocationRepository {

    private final JpaLocationRepository repository;

    public LocationRepositoryImpl(JpaLocationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Location createLocation(Location location) {
        return this.repository.save(location);
    }

    @Override
    public List<Location> getAllLocations() {
        return this.repository.findAll();
    }

    @Override
    public void deleteLocation(Location location) {
        this.repository.delete(location);
    }

    @Override
    public Optional<Location> findLocationById(Integer id) {
        return this.repository.findById(id);
    }

    @Override
    public List<Location> findLocationsByCityOrCountry(String city,String country) {
        return this.repository.findByCityOrCountryOrderByCity(city,country);
    }

    @Override
    public List<Location> searchLocations(String term) {
        return this.repository.searchLocations(term);
    }

    @Override
    public boolean exists(Integer id) {
        return this.repository.existsById(id);
    }
}
