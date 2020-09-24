package mk.ukim.finki.rentscoot.service;

import mk.ukim.finki.rentscoot.model.Location;
import mk.ukim.finki.rentscoot.model.exceptions.InvalidLocationException;
import org.springframework.data.geo.Point;

import java.util.List;

public interface LocationsService {
    Location createLocation(String name, String country, String city, String municipality, String address, String description, Point coordinates, Point popupCoordinates);

    Location updateLocation(Integer id,String name, String country, String city, String municipality, String address, String description, Point coordinates, Point popupCoordinates);

    List<Location> getAllLocations();

    void deleteLocation(Integer id);

    Location getLocation(Integer id);

    List<Location> findLocationsByCityOrCountry(String city,String country);

    List<Location> searchLocations(String term);
}
