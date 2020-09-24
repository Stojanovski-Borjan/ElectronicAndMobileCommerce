package mk.ukim.finki.rentscoot.service;

import mk.ukim.finki.rentscoot.model.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface VehicleManagementService {
    Vehicle addVehicle(String serialNo, String description, LocalDate dateBought,String modelName,Integer locationId);
    VehicleModel addModel(String name, String  description, double pricePerMinute, VehicleType type);

    List<Vehicle> getAllVehicles();
    List<VehicleModel> getAllModels();

    Vehicle updateVehicle(String serialNo, String description, LocalDate dateBought, String modelName,Integer locationId,boolean onTheRoad);
    VehicleModel updateModel(String oldName,String name, String  description, double pricePerMinute, VehicleType type);

    void deleteVehicle(String serialNo);
    void deleteModel(String modelName);

    VehicleModel findModelById(String modelName);
    Vehicle findVehicleById(String serialNo);

    List<Vehicle> findVehiclesBetweenIntervalOnAGivenLocation(Integer locationId,LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime);


}
