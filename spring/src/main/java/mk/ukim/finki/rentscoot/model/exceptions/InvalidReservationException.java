package mk.ukim.finki.rentscoot.model.exceptions;

public class InvalidReservationException extends RuntimeException{
    public InvalidReservationException() {
        super();
    }

    public InvalidReservationException(String message) {
        super(message);
    }
}
