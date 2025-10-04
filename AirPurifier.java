package Appliance;

public class AirPurifier extends Appliance implements Powerable, MeasurableEnergy {

    private int fanLevel;
    private int event;
    private boolean on; // track power status

    public AirPurifier() {
        this.fanLevel = 1;
        this.on = false;
    }

    public void setFanLevel(int level) {
        if (level < 0 || level > 5) {
            throw new InvalidSetting("Fan level must be between 0 and 5");
        }
        this.fanLevel = level;
    }

    @Override
    public void runCycle() throws PowerException {
        if (!on) {
            throw new PowerException("AirPurifier is OFF. Cannot run cycle.");
        }
        System.out.println("AirPurifier running at fan level " + fanLevel);
    }

    @Override
    public double watts() {
        return 100 + (fanLevel * 20); // keep your calculation
    }

    @Override
    public double kWhPerDay() {
        return watts() * 24 / 1000.0;
    }

    // === Powerable methods ===
    @Override
    public void powerOn() throws PowerException {
        if (on) throw new PowerException("AirPurifier already ON");
        on = true;
        System.out.println("AirPurifier powered ON");
    }

    @Override
    public void powerOff() throws PowerException {
        if (!on) throw new PowerException("AirPurifier already OFF");
        on = false;
        System.out.println("AirPurifier powered OFF");
    }

    @Override
    public boolean isOn() {
        return on;
    }

    @Override
    public String powerStatus() {
        return on ? "ON" : "OFF";
    }

    // === WiFi method ===
    public void connectWifi(String ssid, String password) throws NetworkException {
        if (password == null || password.length() < 6) {
            throw new NetworkException("WiFi password too short! Must be at least 6 characters.");
        }
        System.out.println("AirPurifier connected to WiFi network: " + ssid);
    }
}
