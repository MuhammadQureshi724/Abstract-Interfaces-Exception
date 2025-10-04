package Appliance;

public class Refrigerator extends Appliance { 
    
    private int temperature;

    public void setTemperature(int current) {
        this.temperature = current;
    }

    @Override
    public void runCycle() throws PowerException {
        if (!on) {
            throw new PowerException("Refrigerator is OFF. Cannot run cycle.");
        }
        System.out.println("Refrigerator running at temperature " + temperature + "°C");
    }

    @Override
    public double watts() {
        return 150; // Example constant consumption
    }
}
