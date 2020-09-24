package mk.ukim.finki.rentscoot.repository.jpa;

import mk.ukim.finki.rentscoot.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaLocationRepository extends JpaRepository<Location, Integer> {
    //Location findLocationByName(String name);

    List<Location> findByCityOrCountryOrderByCity(String city, String country);

    @Query("select l from Location l " +
            "WHERE l.name like %:term% or l.address like %:term% or l.city like %:term% or " +
            "l.Municipality like %:term% or l.country like %:term% or l.description like %:term%")
    List<Location> searchLocations(@Param("term") String term);

}
