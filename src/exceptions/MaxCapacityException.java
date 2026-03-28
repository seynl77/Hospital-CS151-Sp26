package src.exceptions;
import src.model.Hospital;

public class MaxCapacityException extends Exception {
    public MaxCapacityException(String message) {
        super("Can't add " + message + ". Maximum capacity of " + Hospital.MAX_INSTANCES + " has been reached.");
    }
}
