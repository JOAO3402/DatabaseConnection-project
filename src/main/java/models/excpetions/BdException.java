package models.excpetions;

public class BdException extends RuntimeException {

    public BdException(String message) {
        super(message);
    }
}
