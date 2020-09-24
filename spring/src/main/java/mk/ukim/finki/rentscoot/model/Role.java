package mk.ukim.finki.rentscoot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    ADMIN("System Admin"),
    MANAGER("Responsible for prices,available amount of vehicles and variety of promotions"),
    EMPLOYEE("Responsible to safe keep the vehicles at a given location and receive the client reservations"),
    CLIENT("Users of the app that reserve and rent vehicles");

    private String description;
}
