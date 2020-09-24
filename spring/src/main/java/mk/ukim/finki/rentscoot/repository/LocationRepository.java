package mk.ukim.finki.rentscoot.repository;

import mk.ukim.finki.rentscoot.model.Location;

import java.util.List;
import java.util.Optional;

public interface LocationRepository {
    Location createLocation(Location location);

    List<Location> getAllLocations();

    void deleteLocation(Location location);

    Optional<Location> findLocationById(Integer id);

    List<Location> findLocationsByCityOrCountry(String city,String country);

    List<Location> searchLocations(String term);

    boolean exists(Integer id);
}
