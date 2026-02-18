package models.excpetions;

public class BdIntegrityException extends RuntimeException {

    public BdIntegrityException(String message) {
        super(message);
    }
}
