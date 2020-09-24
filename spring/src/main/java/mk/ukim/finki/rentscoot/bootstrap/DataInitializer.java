package mk.ukim.finki.rentscoot.bootstrap;

import lombok.Getter;
import mk.ukim.finki.rentscoot.model.*;
import mk.ukim.finki.rentscoot.repository.jpa.*;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataInitializer {
    public static final List<Location> locations = new ArrayList<>();

    public static final List<User> users = new ArrayList<>();

    public static final List<Vehicle> vehicles = new ArrayList<>();

    public static final List<VehicleModel> models = new ArrayList<>();

    public static final List<Promotion> promotions = new ArrayList<>();

    public final JpaLocationRepository locationRepository;
    public final JpaUserRepository userRepository;
    public final JpaPromotionRepository promotionRepository;
    public final JpaVehicleModelRepository modelRepository;
    public final JpaVehicleRepository vehicleRepository;

    public DataInitializer(JpaLocationRepository locationRepository, JpaUserRepository userRepository, JpaPromotionRepository promotionRepository, JpaVehicleModelRepository modelRepository, JpaVehicleRepository vehicleRepository) {
        this.locationRepository = locationRepository;
        this.userRepository = userRepository;
        this.promotionRepository = promotionRepository;
        this.modelRepository = modelRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @PostConstruct
    public void initialize(){

        locations.add(new Location("Железничка","Македонија","Скопје", "","Кај таксистите",new Point(41.991340,21.445175),new Point(41.991000,21.445475)));
        locations.add(new Location("City Mall","Македонија","Скопје", "","Пред Play Cafe",new Point(42.004129,21.392004),new Point(42.003829,21.392404)));
        locations.add(new Location("Градски плоштад","Македонија","Скопје", "","Кај Аце",new Point(41.996005,21.430838),new Point(41.995905,21.431138)));
        locations.add(new Location("Capitol Mall","Македонија","Скопје", "","Пред Deja Vu",new Point(41.985774,21.466149),new Point(41.985274,21.466449)));

        users.add(new User("User","user@email.com","userPass",Role.CLIENT));//client
        users.add(new User("Employee","employee@rentScoot.com","empPass",Role.EMPLOYEE));//employee
        users.add(new User("Manager","manager@rentScoot.com","managerPass",Role.MANAGER));//Manager
        users.add(new User("Admin","admin@rentScoot.com","adminPass",Role.ADMIN));//admin

        promotions.add(new Promotion("Father's day weekend","So the fathers can take their children around the city",0.5,new ArrayList<>(),LocalDate.of(2020,6,19),LocalDate.of(2020,6,22)));
        promotions.add(new Promotion("May - take the scooters out","Finally some good weather to cruise around",0.15,new ArrayList<>(),LocalDate.of(2020,5,1),LocalDate.of(2020,5,31)));
        promotions.add(new Promotion("Corona Free Weekend","As the situation is coming to a rest, this weekend is for you to open up you lungs around the city",0.25,new ArrayList<>(),LocalDate.of(2020,5,29),LocalDate.of(2020,5,31)));
        promotions.add(new Promotion("Regular - 5","For the loyal clients out there that use our services at least 5 times a month",0.2,new ArrayList<>(),LocalDate.of(2020,5,15),LocalDate.of(2022,1,1)));
        promotions.add(new Promotion("Regular","For the loyal clients out there that use our services at least 5 times a month",0,new ArrayList<>(),LocalDate.of(2020,5,1),LocalDate.of(2022,1,1)));

        models.add(new VehicleModel("XIAOMI M365",VehicleType.SCOOTER,"Одличен за да ве одведе до посакуваната локација на време и со стил",2,new ArrayList<>(),16));
        models.add(new VehicleModel("Power Core E100",VehicleType.SCOOTER,"Одличен за да ве одведе до посакуваната локација не грижејќи се за густиот сообраќај",1,new ArrayList<>(),20));
        models.add(new VehicleModel("NanRobot LS7",VehicleType.SCOOTER,"Веќе доцните ?! Не грижете се овој тротинет ќе ве воодушеви од брзината и растојанието кое моќната батерија ги нуди",10,new ArrayList<>(),5));
        models.add(new VehicleModel("Еlevate eMTB",VehicleType.eBIKE,"Одличен за до блиските планински патеки.Искачете врвови без да се уморите и имајте незаборавни брзи и екстремни возења надолу, верувајте можете да одите по најтешките патеки, а сепак ќе се чувствувате сигурни.",20,new ArrayList<>(),4));
        models.add(new VehicleModel("GHOST Square",VehicleType.eBIKE,"Одличен за брзо и комфортно возење низ град. Соодветен за избегнување на rush hour.",10,new ArrayList<>(),8));

        vehicles.add(new Vehicle("M365-1","Одлична состојба",LocalDate.of(2020,5,1),models.get(0),locations.get(0),new ArrayList<>(),true));
        vehicles.add(new Vehicle("M365-2","Одлична состојба",LocalDate.of(2020,5,1),models.get(0),locations.get(0),new ArrayList<>(),true));
        vehicles.add(new Vehicle("M365-3","Одлична состојба",LocalDate.of(2020,5,1),models.get(0),locations.get(0),new ArrayList<>(),true));
        vehicles.add(new Vehicle("M365-4","Одлична состојба",LocalDate.of(2020,5,1),models.get(0),locations.get(0),new ArrayList<>(),true));
        vehicles.add(new Vehicle("M365-5","Одлична состојба",LocalDate.of(2020,5,1),models.get(0),locations.get(1),new ArrayList<>(),true));
        vehicles.add(new Vehicle("M365-6","Одлична состојба",LocalDate.of(2020,5,1),models.get(0),locations.get(1),new ArrayList<>(),true));
        vehicles.add(new Vehicle("M365-7","Одлична состојба",LocalDate.of(2020,5,1),models.get(0),locations.get(1),new ArrayList<>(),true));
        vehicles.add(new Vehicle("M365-8","Одлична состојба",LocalDate.of(2020,5,1),models.get(0),locations.get(1),new ArrayList<>(),true));
        vehicles.add(new Vehicle("M365-9","Одлична состојба",LocalDate.of(2020,5,1),models.get(0),locations.get(2),new ArrayList<>(),true));
        vehicles.add(new Vehicle("M365-10","Одлична состојба",LocalDate.of(2020,5,1),models.get(0),locations.get(2),new ArrayList<>(),true));
        vehicles.add(new Vehicle("M365-11","Одлична состојба",LocalDate.of(2020,5,1),models.get(0),locations.get(2),new ArrayList<>(),true));
        vehicles.add(new Vehicle("M365-12","Одлична состојба",LocalDate.of(2020,5,1),models.get(0),locations.get(2),new ArrayList<>(),true));
        vehicles.add(new Vehicle("M365-13","Одлична состојба",LocalDate.of(2020,5,1),models.get(0),locations.get(3),new ArrayList<>(),true));
        vehicles.add(new Vehicle("M365-14","Одлична состојба",LocalDate.of(2020,5,1),models.get(0),locations.get(3),new ArrayList<>(),true));
        vehicles.add(new Vehicle("M365-15","Одлична состојба",LocalDate.of(2020,5,1),models.get(0),locations.get(3),new ArrayList<>(),true));
        vehicles.add(new Vehicle("M365-16","Одлична состојба",LocalDate.of(2020,5,1),models.get(0),locations.get(3),new ArrayList<>(),true));
        vehicles.add(new Vehicle("E100-1","Одлична состојба",LocalDate.of(2020,5,1),models.get(1),locations.get(0),new ArrayList<>(),true));
        vehicles.add(new Vehicle("E100-2","Одлична состојба",LocalDate.of(2020,5,1),models.get(1),locations.get(0),new ArrayList<>(),true));
        vehicles.add(new Vehicle("E100-3","Одлична состојба",LocalDate.of(2020,5,1),models.get(1),locations.get(0),new ArrayList<>(),true));
        vehicles.add(new Vehicle("E100-4","Одлична состојба",LocalDate.of(2020,5,1),models.get(1),locations.get(0),new ArrayList<>(),true));
        vehicles.add(new Vehicle("E100-5","Одлична состојба",LocalDate.of(2020,5,1),models.get(1),locations.get(0),new ArrayList<>(),true));
        vehicles.add(new Vehicle("E100-6","Одлична состојба",LocalDate.of(2020,5,1),models.get(1),locations.get(1),new ArrayList<>(),true));
        vehicles.add(new Vehicle("E100-7","Одлична состојба",LocalDate.of(2020,5,1),models.get(1),locations.get(1),new ArrayList<>(),true));
        vehicles.add(new Vehicle("E100-8","Одлична состојба",LocalDate.of(2020,5,1),models.get(1),locations.get(1),new ArrayList<>(),true));
        vehicles.add(new Vehicle("E100-9","Одлична состојба",LocalDate.of(2020,5,1),models.get(1),locations.get(1),new ArrayList<>(),true));
        vehicles.add(new Vehicle("E100-10","Одлична состојба",LocalDate.of(2020,5,1),models.get(1),locations.get(1),new ArrayList<>(),true));
        vehicles.add(new Vehicle("E100-11","Одлична состојба",LocalDate.of(2020,5,1),models.get(1),locations.get(2),new ArrayList<>(),true));
        vehicles.add(new Vehicle("E100-12","Одлична состојба",LocalDate.of(2020,5,1),models.get(1),locations.get(2),new ArrayList<>(),true));
        vehicles.add(new Vehicle("E100-13","Одлична состојба",LocalDate.of(2020,5,1),models.get(1),locations.get(2),new ArrayList<>(),true));
        vehicles.add(new Vehicle("E100-14","Одлична состојба",LocalDate.of(2020,5,1),models.get(1),locations.get(2),new ArrayList<>(),true));
        vehicles.add(new Vehicle("E100-15","Одлична состојба",LocalDate.of(2020,5,1),models.get(1),locations.get(2),new ArrayList<>(),true));
        vehicles.add(new Vehicle("E100-16","Одлична состојба",LocalDate.of(2020,5,1),models.get(1),locations.get(3),new ArrayList<>(),true));
        vehicles.add(new Vehicle("E100-17","Одлична состојба",LocalDate.of(2020,5,1),models.get(1),locations.get(3),new ArrayList<>(),true));
        vehicles.add(new Vehicle("E100-18","Одлична состојба",LocalDate.of(2020,5,1),models.get(1),locations.get(3),new ArrayList<>(),true));
        vehicles.add(new Vehicle("E100-19","Одлична состојба",LocalDate.of(2020,5,1),models.get(1),locations.get(3),new ArrayList<>(),true));
        vehicles.add(new Vehicle("E100-20","Одлична состојба",LocalDate.of(2020,5,1),models.get(1),locations.get(3),new ArrayList<>(),true));
        vehicles.add(new Vehicle("LS7-1","Одлична состојба",LocalDate.of(2020,5,1),models.get(2),locations.get(0),new ArrayList<>(),true));
        vehicles.add(new Vehicle("LS7-2","Одлична состојба",LocalDate.of(2020,5,1),models.get(2),locations.get(1),new ArrayList<>(),true));
        vehicles.add(new Vehicle("LS7-3","Одлична состојба",LocalDate.of(2020,5,1),models.get(2),locations.get(2),new ArrayList<>(),true));
        vehicles.add(new Vehicle("LS7-4","Одлична состојба",LocalDate.of(2020,5,1),models.get(2),locations.get(2),new ArrayList<>(),true));
        vehicles.add(new Vehicle("LS7-5","Одлична состојба",LocalDate.of(2020,5,1),models.get(2),locations.get(3),new ArrayList<>(),true));
        vehicles.add(new Vehicle("eMTB-1","Одлична состојба",LocalDate.of(2020,5,1),models.get(3),locations.get(0),new ArrayList<>(),true));
        vehicles.add(new Vehicle("eMTB-2","Одлична состојба",LocalDate.of(2020,5,1),models.get(3),locations.get(1),new ArrayList<>(),true));
        vehicles.add(new Vehicle("eMTB-3","Одлична состојба",LocalDate.of(2020,5,1),models.get(3),locations.get(2),new ArrayList<>(),true));
        vehicles.add(new Vehicle("eMTB-4","Одлична состојба",LocalDate.of(2020,5,1),models.get(3),locations.get(3),new ArrayList<>(),true));
        vehicles.add(new Vehicle("GT-Sq-1","Одлична состојба",LocalDate.of(2020,5,1),models.get(4),locations.get(0),new ArrayList<>(),true));
        vehicles.add(new Vehicle("GT-Sq-2","Одлична состојба",LocalDate.of(2020,5,1),models.get(4),locations.get(0),new ArrayList<>(),true));
        vehicles.add(new Vehicle("GT-Sq-3","Одлична состојба",LocalDate.of(2020,5,1),models.get(4),locations.get(1),new ArrayList<>(),true));
        vehicles.add(new Vehicle("GT-Sq-4","Одлична состојба",LocalDate.of(2020,5,1),models.get(4),locations.get(1),new ArrayList<>(),true));
        vehicles.add(new Vehicle("GT-Sq-5","Одлична состојба",LocalDate.of(2020,5,1),models.get(4),locations.get(2),new ArrayList<>(),true));
        vehicles.add(new Vehicle("GT-Sq-6","Одлична состојба",LocalDate.of(2020,5,1),models.get(4),locations.get(2),new ArrayList<>(),true));
        vehicles.add(new Vehicle("GT-Sq-7","Одлична состојба",LocalDate.of(2020,5,1),models.get(4),locations.get(3),new ArrayList<>(),true));
        vehicles.add(new Vehicle("GT-Sq-8","Одлична состојба",LocalDate.of(2020,5,1),models.get(4),locations.get(3),new ArrayList<>(),true));

        models.get(0).getVehicles().add(vehicles.get(0));
        models.get(0).getVehicles().add(vehicles.get(1));
        models.get(0).getVehicles().add(vehicles.get(2));
        models.get(0).getVehicles().add(vehicles.get(3));
        models.get(0).getVehicles().add(vehicles.get(4));
        models.get(0).getVehicles().add(vehicles.get(5));
        models.get(0).getVehicles().add(vehicles.get(6));
        models.get(0).getVehicles().add(vehicles.get(7));
        models.get(0).getVehicles().add(vehicles.get(8));
        models.get(0).getVehicles().add(vehicles.get(9));
        models.get(0).getVehicles().add(vehicles.get(10));
        models.get(0).getVehicles().add(vehicles.get(11));
        models.get(0).getVehicles().add(vehicles.get(12));
        models.get(0).getVehicles().add(vehicles.get(13));
        models.get(0).getVehicles().add(vehicles.get(14));
        models.get(0).getVehicles().add(vehicles.get(15));
        models.get(1).getVehicles().add(vehicles.get(16));
        models.get(1).getVehicles().add(vehicles.get(17));
        models.get(1).getVehicles().add(vehicles.get(18));
        models.get(1).getVehicles().add(vehicles.get(19));
        models.get(1).getVehicles().add(vehicles.get(20));
        models.get(1).getVehicles().add(vehicles.get(21));
        models.get(1).getVehicles().add(vehicles.get(22));
        models.get(1).getVehicles().add(vehicles.get(23));
        models.get(1).getVehicles().add(vehicles.get(24));
        models.get(1).getVehicles().add(vehicles.get(25));
        models.get(1).getVehicles().add(vehicles.get(26));
        models.get(1).getVehicles().add(vehicles.get(27));
        models.get(1).getVehicles().add(vehicles.get(28));
        models.get(1).getVehicles().add(vehicles.get(29));
        models.get(1).getVehicles().add(vehicles.get(30));
        models.get(1).getVehicles().add(vehicles.get(31));
        models.get(1).getVehicles().add(vehicles.get(32));
        models.get(1).getVehicles().add(vehicles.get(33));
        models.get(1).getVehicles().add(vehicles.get(34));
        models.get(1).getVehicles().add(vehicles.get(35));
        models.get(2).getVehicles().add(vehicles.get(36));
        models.get(2).getVehicles().add(vehicles.get(37));
        models.get(2).getVehicles().add(vehicles.get(38));
        models.get(2).getVehicles().add(vehicles.get(39));
        models.get(2).getVehicles().add(vehicles.get(40));
        models.get(3).getVehicles().add(vehicles.get(41));
        models.get(3).getVehicles().add(vehicles.get(42));
        models.get(3).getVehicles().add(vehicles.get(43));
        models.get(3).getVehicles().add(vehicles.get(44));
        models.get(4).getVehicles().add(vehicles.get(45));
        models.get(4).getVehicles().add(vehicles.get(46));
        models.get(4).getVehicles().add(vehicles.get(47));
        models.get(4).getVehicles().add(vehicles.get(48));
        models.get(4).getVehicles().add(vehicles.get(49));
        models.get(4).getVehicles().add(vehicles.get(50));
        models.get(4).getVehicles().add(vehicles.get(51));
        models.get(4).getVehicles().add(vehicles.get(52));

        locations.get(0).getVehicles().add(vehicles.get(0));
        locations.get(0).getVehicles().add(vehicles.get(1));
        locations.get(0).getVehicles().add(vehicles.get(2));
        locations.get(0).getVehicles().add(vehicles.get(3));
        locations.get(1).getVehicles().add(vehicles.get(4));
        locations.get(1).getVehicles().add(vehicles.get(5));
        locations.get(1).getVehicles().add(vehicles.get(6));
        locations.get(1).getVehicles().add(vehicles.get(7));
        locations.get(2).getVehicles().add(vehicles.get(8));
        locations.get(2).getVehicles().add(vehicles.get(9));
        locations.get(2).getVehicles().add(vehicles.get(10));
        locations.get(2).getVehicles().add(vehicles.get(11));
        locations.get(3).getVehicles().add(vehicles.get(12));
        locations.get(3).getVehicles().add(vehicles.get(13));
        locations.get(3).getVehicles().add(vehicles.get(14));
        locations.get(3).getVehicles().add(vehicles.get(15));
        locations.get(0).getVehicles().add(vehicles.get(16));
        locations.get(0).getVehicles().add(vehicles.get(17));
        locations.get(0).getVehicles().add(vehicles.get(18));
        locations.get(0).getVehicles().add(vehicles.get(19));
        locations.get(0).getVehicles().add(vehicles.get(20));
        locations.get(1).getVehicles().add(vehicles.get(21));
        locations.get(1).getVehicles().add(vehicles.get(22));
        locations.get(1).getVehicles().add(vehicles.get(23));
        locations.get(1).getVehicles().add(vehicles.get(24));
        locations.get(1).getVehicles().add(vehicles.get(25));
        locations.get(2).getVehicles().add(vehicles.get(26));
        locations.get(2).getVehicles().add(vehicles.get(27));
        locations.get(2).getVehicles().add(vehicles.get(28));
        locations.get(2).getVehicles().add(vehicles.get(29));
        locations.get(2).getVehicles().add(vehicles.get(30));
        locations.get(3).getVehicles().add(vehicles.get(31));
        locations.get(3).getVehicles().add(vehicles.get(32));
        locations.get(3).getVehicles().add(vehicles.get(33));
        locations.get(3).getVehicles().add(vehicles.get(34));
        locations.get(3).getVehicles().add(vehicles.get(35));
        locations.get(0).getVehicles().add(vehicles.get(36));
        locations.get(1).getVehicles().add(vehicles.get(37));
        locations.get(2).getVehicles().add(vehicles.get(38));
        locations.get(2).getVehicles().add(vehicles.get(39));
        locations.get(3).getVehicles().add(vehicles.get(40));
        locations.get(0).getVehicles().add(vehicles.get(41));
        locations.get(1).getVehicles().add(vehicles.get(42));
        locations.get(2).getVehicles().add(vehicles.get(43));
        locations.get(3).getVehicles().add(vehicles.get(44));
        locations.get(0).getVehicles().add(vehicles.get(45));
        locations.get(0).getVehicles().add(vehicles.get(46));
        locations.get(1).getVehicles().add(vehicles.get(47));
        locations.get(1).getVehicles().add(vehicles.get(48));
        locations.get(2).getVehicles().add(vehicles.get(49));
        locations.get(2).getVehicles().add(vehicles.get(50));
        locations.get(3).getVehicles().add(vehicles.get(51));
        locations.get(3).getVehicles().add(vehicles.get(52));

        if(this.locationRepository.count() == 0){
            this.userRepository.saveAll(users);
            this.modelRepository.saveAll(models);
            this.locationRepository.saveAll(locations);
            this.vehicleRepository.saveAll(vehicles);
            this.promotionRepository.saveAll(promotions);
        }
    }
}
