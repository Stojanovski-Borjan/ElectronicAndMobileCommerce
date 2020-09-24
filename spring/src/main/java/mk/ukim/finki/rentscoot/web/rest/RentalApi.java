package mk.ukim.finki.rentscoot.web.rest;

import mk.ukim.finki.rentscoot.model.*;
import mk.ukim.finki.rentscoot.model.exceptions.InvalidLocationException;
import mk.ukim.finki.rentscoot.model.exceptions.InvalidUserException;
import mk.ukim.finki.rentscoot.service.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://${client.url}:3000")
@RequestMapping(path = "api/rental", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class RentalApi {
    private final LocationsService locationsService;
    private final ReservationService reservationService;
    private final VehicleManagementService vehicleService;
    private final PromotionService promotionService;
    private final UserManagementService userService;


    public RentalApi(LocationsService locationsService, ReservationService reservationService, VehicleManagementService vehicleService, PromotionService promotionService, UserManagementService userService) {
        this.locationsService = locationsService;
        this.reservationService = reservationService;
        this.vehicleService = vehicleService;
        this.promotionService = promotionService;
        this.userService = userService;
    }
// LOCATIONS ENDPOINT
    @GetMapping(value = "/locations")
    public List<Location> getAllLocations(@RequestHeader(name="city",required = false) String city,
                                          @RequestHeader(name="country",required = false) String country,
                                          @RequestParam(value = "term",required = false) String term){
        if(term!=null) return this.locationsService.searchLocations(term);
        if(city!=null || country!=null) return this.locationsService.findLocationsByCityOrCountry(city,country);
        return this.locationsService.getAllLocations();
    }

    @GetMapping("/locations/{locationId}")
    public Location getLocation(@PathVariable Integer locationId){
        return this.locationsService.getLocation(locationId);
    }
// RESERVATIONS ENDPOINT

    @GetMapping("/reservations")
    public List<Reservation> getAllUserReservations(@RequestHeader(name="userEmail") String userEmail){
        return this.reservationService.findReservationsByUser(userEmail);
    }

    @PostMapping("/reservations") //If there is time do this with User ID
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation addReservation(@RequestHeader(name="userId") Long userId,
                                      @RequestHeader(name="locationId") int locationId,
                                      @RequestHeader(name="modelNames") String modelNames,
                                      @RequestHeader(name = "promotion") String promotion,
                                      @RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                      @RequestParam(name = "startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
                                      @RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                      @RequestParam(name = "endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime,
                                      HttpServletResponse response,
                                      UriComponentsBuilder builder){
        //list of models ex: bike,bike,scoot,scoot,scoot
        String[] modelName = modelNames.split(",");
        try {
                Reservation result = this.reservationService.createReservation(locationId, modelName, userId, promotion, startDate, startTime, endDate, endTime);
                response.setHeader("Location", builder.path("/api/manage/reservations/{reservationId}").buildAndExpand(result.getId()).toUriString());
                return result;

        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
// PROMOTIONS ENDPOINT
    @GetMapping("/promotions")
    public List<Promotion> findAllValidPromotions(@RequestParam(name = "date",required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        return this.promotionService.findAllValidPromotions(date!=null?date:LocalDate.now());
    }
    @GetMapping("/promotions/{promoName}")
    public Promotion getPromotion(@PathVariable String promoName){
        return this.promotionService.getPromotion(promoName);
    }
    @GetMapping("/promotions/best")
    public List<Promotion> findBestPromotion(@RequestHeader double discount){
        return this.promotionService.findBestDiscountPromotions(discount);
    }
    @GetMapping("/promotions/newest")
    public Promotion findNewestPromotion(){
        return this.promotionService.findNewestPromotion();
    }
// MODELS ENDPOINT
    @GetMapping("/models")
    public List<VehicleModel> getAllModels(){
        return this.vehicleService.getAllModels();
    }
    @GetMapping("/models/{modelName}")
    public VehicleModel getModel(@PathVariable String modelName){
        return this.vehicleService.findModelById(modelName);
    }
// VEHICLES ENDPOINT
    @GetMapping("/vehicles/{locationId}")
    public List<Vehicle> getAllVehiclesOnAGivenLocationInsideInterval(@PathVariable int locationId,
                                                                      @RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate startDate,
                                                                      @RequestParam(name = "startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)LocalTime startTime,
                                                                      @RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate endDate,
                                                                      @RequestParam(name = "endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)LocalTime endTime){
        try{
            return this.vehicleService.findVehiclesBetweenIntervalOnAGivenLocation(locationId,startDate,startTime,endDate,endTime);
        }
        catch (InvalidLocationException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"There is no location with the provided id");
        }
    }

// USERS ENDPOINT
//    @GetMapping("/user")
//    public Principal getUser(Principal user){
//        return user;
//    } Facebook authentication ?
}
