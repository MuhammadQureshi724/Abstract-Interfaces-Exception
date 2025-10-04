package Appliance;

public class WashingMachine extends Appliance { 
    
    private int rpm;

    public void setSpinRpm(int rpm) {
        if (rpm < 0) {
            throw new InvalidSetting("RPM cannot be negative");
        }
        this.rpm = rpm;
    }

    @Override
    public void runCycle() throws PowerException {
        if (!on) {
            throw new PowerException("WashingMachine is OFF. Cannot run cycle.");
        }
        System.out.println("WashingMachine running at " + rpm + " RPM");
    }

    @Override
    public double watts() {
        return 500 + (rpm / 10.0); // Example calculation
    }
}
