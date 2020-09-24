package mk.ukim.finki.rentscoot.model.exceptions;

public class InvalidPromotionException extends RuntimeException {
    public InvalidPromotionException(String message) {
        super(message);
    }

    public InvalidPromotionException() {
    }
}
