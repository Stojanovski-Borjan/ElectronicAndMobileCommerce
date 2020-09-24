package mk.ukim.finki.rentscoot.model.exceptions;

public class InvalidVehicleModelException extends RuntimeException {
    public InvalidVehicleModelException() {
        super();
    }

    public InvalidVehicleModelException(String message) {
        super(message);
    }
}
