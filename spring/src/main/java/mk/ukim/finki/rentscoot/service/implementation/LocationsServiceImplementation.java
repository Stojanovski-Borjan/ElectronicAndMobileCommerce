package mk.ukim.finki.rentscoot.service.implementation;

import mk.ukim.finki.rentscoot.model.Location;
import mk.ukim.finki.rentscoot.model.Vehicle;
import mk.ukim.finki.rentscoot.model.exceptions.InvalidLocationException;
import mk.ukim.finki.rentscoot.repository.LocationRepository;
import mk.ukim.finki.rentscoot.service.LocationsService;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LocationsServiceImplementation implements LocationsService {
    private final LocationRepository locationRepository;

    public LocationsServiceImplementation(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public Location createLocation(String name, String country, String city, String municipality, String address, String description, Point coordinates, Point popupCoordinates) {
        Location location = new Location();
        location.setName(name);
        location.setCountry(country);
        location.setCity(city);
        location.setAddress(address);
        location.setMunicipality(municipality);
        location.setCoordinates(coordinates);
        if(popupCoordinates==null){
            popupCoordinates = new Point(coordinates.getX()-0.000300,coordinates.getY()+0.000350);
        }
        location.setPopupCoordinates(popupCoordinates);
        //location.setVehicles(new ArrayList<>());
        return this.locationRepository.createLocation(location);
    }

    @Override
    public Location updateLocation(Integer id,String name, String country, String city, String municipality, String address, String description, Point coordinates, Point popupCoordinates){
        Location oldLocation = this.locationRepository.findLocationById(id).orElseThrow(InvalidLocationException::new);
        oldLocation.setName(name);
        oldLocation.setCountry(country);
        oldLocation.setCity(city);
        oldLocation.setMunicipality(municipality);
        oldLocation.setAddress(address);
        oldLocation.setDescription(description);
        oldLocation.setCoordinates(coordinates);
        if(popupCoordinates==null){
            popupCoordinates = new Point(coordinates.getX()-0.000300,coordinates.getY()+0.000350);
        }
        oldLocation.setPopupCoordinates(popupCoordinates);
        return this.locationRepository.createLocation(oldLocation);
    }

    @Override
    public List<Location> getAllLocations() {
//        List<Location> all = this.locationRepository.getAllLocations();
//        all.stream().forEach(location -> {if(location.getVehicles().size()==0)location.setVehicles(null);});
        return this.locationRepository.getAllLocations();
    }

    @Override
    public void deleteLocation(Integer id) {
        Location locDelete = this.locationRepository.findLocationById(id).orElseThrow(InvalidLocationException::new);
        this.locationRepository.deleteLocation(locDelete);
    }

    @Override
    public Location getLocation(Integer id) {
//        Location location = this.locationRepository.findLocationById(id).orElseThrow(InvalidLocationException::new);
//        if(location.getVehicles()==null)location.setVehicles(new ArrayList<>());
        return this.locationRepository.findLocationById(id).orElseThrow(InvalidLocationException::new);
    }

    @Override
    public List<Location> findLocationsByCityOrCountry(String place,String country) {
//        List<Location> locations = this.locationRepository.findLocationsByCityOrCountry(place, country);
//        locations.forEach(location -> {if(location.getVehicles()==null)location.setVehicles(new ArrayList<>());});
        return this.locationRepository.findLocationsByCityOrCountry(place, country);
    }

    @Override
    public List<Location> searchLocations(String term) {
//        List<Location> locations = this.locationRepository.searchLocations(term);
//        locations.stream().forEach(location -> {if(location.getVehicles().size()==0)location.setVehicles(null);});
        return this.locationRepository.searchLocations(term);
    }
}
