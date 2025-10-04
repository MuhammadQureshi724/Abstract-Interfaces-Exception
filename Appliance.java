package Appliance;

public abstract class Appliance implements MeasurableEnergy, Powerable { 
    
    protected String model; 
    protected double watt; 
    protected int voltage; 
    protected boolean on;

    @Override
    public void powerOn() throws PowerException {
        if (on) {
            throw new PowerException("Device is already ON");
        }
        on = true;
    }

    @Override
    public void powerOff() throws PowerException {
        if (!on) {
            throw new PowerException("Device is already OFF");
        }
        on = false;
    }

    @Override
    public boolean isOn() {
        return on;
    }

    @Override
    public String powerStatus() {
        return on ? "ON" : "OFF";
    }

    @Override
    public abstract double watts();

    @Override
    public double kWhPerDay() {
        return (watts() * 24) / 1000.0;
    }

    public abstract void runCycle() throws PowerException;
}
