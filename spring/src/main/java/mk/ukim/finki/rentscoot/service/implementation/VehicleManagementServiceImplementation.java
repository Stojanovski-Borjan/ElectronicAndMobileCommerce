package mk.ukim.finki.rentscoot.service.implementation;

import mk.ukim.finki.rentscoot.model.Location;
import mk.ukim.finki.rentscoot.model.Vehicle;
import mk.ukim.finki.rentscoot.model.VehicleModel;
import mk.ukim.finki.rentscoot.model.VehicleType;
import mk.ukim.finki.rentscoot.model.exceptions.InvalidLocationException;
import mk.ukim.finki.rentscoot.model.exceptions.InvalidVehicleException;
import mk.ukim.finki.rentscoot.model.exceptions.InvalidVehicleModelException;
import mk.ukim.finki.rentscoot.repository.LocationRepository;
import mk.ukim.finki.rentscoot.repository.VehicleModelRepository;
import mk.ukim.finki.rentscoot.repository.VehicleRepository;
import mk.ukim.finki.rentscoot.service.VehicleManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class VehicleManagementServiceImplementation implements VehicleManagementService {
    private final VehicleRepository vehicleRepository;
    private final VehicleModelRepository modelRepository;
    private final LocationRepository locationRepository;

    public VehicleManagementServiceImplementation(VehicleRepository vehicleRepository, VehicleModelRepository modelRepository, LocationRepository locationRepository) {
        this.vehicleRepository = vehicleRepository;
        this.modelRepository = modelRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public Vehicle addVehicle(String serialNo, String description, LocalDate dateBought, String modelName, Integer locationId) {
        Vehicle vehicle = new Vehicle();
        vehicle.setSerialNo(serialNo);
        vehicle.setDescription(description);
        vehicle.setDateBought(dateBought);
        VehicleModel vehicleModel = this.modelRepository.findModelById(modelName).orElseThrow(InvalidVehicleException::new);
        vehicle.setModel(vehicleModel);
        Location location = this.locationRepository.findLocationById(locationId).orElseThrow(InvalidLocationException::new);
        vehicle.setLocation(location);
        vehicle = this.vehicleRepository.addVehicle(vehicle);
        vehicleModel.setAmountAvailable(vehicleModel.getAmountAvailable()+1);
        this.modelRepository.addModel(vehicleModel);
        return vehicle;
    }

    @Override
    public VehicleModel addModel(String name, String description, double pricePerMinute, VehicleType type) {
        VehicleModel vehicleModel = new VehicleModel();
        vehicleModel.setModelName(name);
        vehicleModel.setDescription(description);
        vehicleModel.setPricePerMinute(pricePerMinute);
        vehicleModel.setType(type);
        return this.modelRepository.addModel(vehicleModel);
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return this.vehicleRepository.getAllVehicles();
    }

    @Override
    public List<VehicleModel> getAllModels() {
        return this.modelRepository.getAllModels();
    }

    @Override
    public Vehicle updateVehicle(String serialNo, String description, LocalDate dateBought, String modelName, Integer locationId, boolean onTheRoad) {
        Vehicle vehicle = this.vehicleRepository.findVehicleById(serialNo).orElseThrow(InvalidVehicleException::new);
        vehicle.setDescription(description);
        VehicleModel vehicleModel = this.modelRepository.findModelById(modelName).orElseThrow(InvalidVehicleModelException::new);
        VehicleModel oldModel = this.modelRepository.findModelById(vehicle.getModel().getModelName()).orElseThrow(InvalidVehicleModelException::new);
        if(vehicle.getModel().getModelName().compareTo(modelName)!=0){
            vehicleModel.setAmountAvailable(vehicleModel.getAmountAvailable()+1);
            oldModel.setAmountAvailable(oldModel.getAmountAvailable()-1);
        }
        vehicle.setModel(vehicleModel);
        Location location = this.locationRepository.findLocationById(locationId).orElseThrow(InvalidLocationException::new);
        vehicle.setLocation(location);
        vehicle.setOnTheRoad(onTheRoad);
        vehicle = this.vehicleRepository.addVehicle(vehicle);
        this.modelRepository.addModel(vehicleModel);
        this.modelRepository.addModel(oldModel);
        return vehicle;
    }

    @Override
    public VehicleModel updateModel(String oldName,String name, String description, double pricePerMinute, VehicleType type) {
        VehicleModel vehicleModel = this.modelRepository.findModelById(oldName).orElseThrow(InvalidVehicleException::new);
        if(vehicleModel.getModelName().compareTo(name)==0){
            vehicleModel.setDescription(description);
            vehicleModel.setPricePerMinute(pricePerMinute);
            vehicleModel.setType(type);
            return this.modelRepository.addModel(vehicleModel);
        }
        else {
            vehicleModel.setModelName(name);
            vehicleModel.setType(type);
            vehicleModel.setPricePerMinute(pricePerMinute);
            vehicleModel.setDescription(description);
            this.modelRepository.addModel(vehicleModel);
            vehicleModel.getVehicles().forEach(vehicle -> {
                vehicle.setModel(vehicleModel);
                if(vehicle.isOnTheRoad()){
                    int a = vehicleModel.getAmountAvailable();
                    vehicleModel.setAmountAvailable(a+1);
                }
            });
            this.modelRepository.deleteModel(this.modelRepository.findModelById(oldName).orElseThrow(InvalidVehicleModelException::new));
            return this.modelRepository.addModel(vehicleModel);
        }
    }

    @Override
    public void deleteVehicle(String serialNo) {
        Vehicle vehicle = this.vehicleRepository.findVehicleById(serialNo).orElseThrow(InvalidVehicleException::new);
        this.vehicleRepository.deleteVehicle(vehicle);
        VehicleModel model = vehicle.getModel();
        model.setAmountAvailable(model.getAmountAvailable()-1);
        this.modelRepository.addModel(model);
    }

    @Override
    public void deleteModel(String modelName) {
        VehicleModel vehicleModel = this.modelRepository.findModelById(modelName).orElseThrow(InvalidVehicleException::new);
        if(vehicleModel.getVehicles().size()==0)this.modelRepository.deleteModel(vehicleModel);
        else throw new InvalidVehicleModelException("Vehicle model can't be deleted if vehicles from that model are still being used.");
    }

    @Override
    public VehicleModel findModelById(String modelName) {
        return this.modelRepository.findModelById(modelName).orElseThrow(InvalidVehicleException::new);
    }

    @Override
    public Vehicle findVehicleById(String serialNo) {
        return this.vehicleRepository.findVehicleById(serialNo).orElseThrow(InvalidVehicleException::new);
    }

    @Override
    public List<Vehicle> findVehiclesBetweenIntervalOnAGivenLocation(Integer locationId, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        if(this.locationRepository.exists(locationId)) {
            List<Vehicle> vehiclesOnLocation = this.vehicleRepository.getAllVehicles().stream().filter(vehicle -> vehicle.getLocation()!=null && locationId.equals(vehicle.getLocation().getId())).collect(Collectors.toList());
            return vehiclesOnLocation.stream().filter(isAvailable(startDate, startTime, endDate, endTime)).collect(Collectors.toList());
        }
        else throw new InvalidLocationException();
    }
    private Predicate<Vehicle> isAvailable(LocalDate startDate,LocalTime startTime,LocalDate endDate,LocalTime endTime) {
        return  v -> v.getReservations().stream()
                .noneMatch( reservation1 -> (startDate.isAfter(reservation1.getDateStart())
                        && startTime.isAfter(reservation1.getTimeStart()) && startDate.isBefore(reservation1.getDateEnd())
                        && startTime.isBefore(reservation1.getTimeEnd())) || (endDate.isAfter(reservation1.getDateStart())
                        && endTime.isAfter(reservation1.getTimeStart()) && endDate.isBefore(reservation1.getDateEnd())
                        && endTime.isBefore(reservation1.getTimeEnd()))
                );
    }
}
