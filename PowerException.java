package Appliance;

public class PowerException extends Exception {
    public PowerException() {
        super("Power Exception occurred");
    }

    public PowerException(String message) {
        super(message);
    }
}
