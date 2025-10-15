package Appliance;

import java.util.ArrayList;
import java.util.List;

public class AppliancesTester {

    public static void main(String[] args) {

        WashingMachine wm = new WashingMachine();
        Refrigerator fridge = new Refrigerator();
        AirPurifier ap = new AirPurifier();

        try {
            // Configure and run each appliance
            wm.setSpinRpm(1200);
            wm.powerOn();
            wm.runCycle();

            fridge.setTemperature(4);
            fridge.powerOn();
            fridge.runCycle();

            ap.setFanLevel(3);
            ap.powerOn();
            ap.runCycle();

            // Example: Trigger duplicate powerOn exception
            try {
                wm.powerOn();
            } catch (PowerException e) {
                System.out.println("Handled exception: " + e.getMessage());
            }

            // Example: Running while device is OFF
            try {
                WashingMachine wm2 = new WashingMachine();
                wm2.runCycle();
            } catch (PowerException e) {
                System.out.println("Handled exception: " + e.getMessage());
            }

            // Example: Invalid setting
            try {
                wm.setSpinRpm(2500);
            } catch (InvalidSetting e) {
                System.out.println("Handled exception: " + e.getMessage());
            }

            // Example Wi-Fi connection simulation
            ap.connectWifi("HomeNet", "123");

        } catch (PowerException e) {
            System.out.println("Power error: " + e.getMessage());
        } finally {
            // Safely power off all appliances
            Powerable.safeShutdownAll(wm, fridge, ap);
            System.out.println("All devices have been safely powered off.");
        }

        // Display appliance summary
        List<Appliance> appliances = new ArrayList<>();
        appliances.add(fridge);
        appliances.add(wm);
        appliances.add(ap);

        System.out.println("\n=== Appliance Summary ===");
        for (Powerable device : appliances) {
            System.out.println(device.getClass().getSimpleName()
                    + " | Status=" + device.powerStatus()
                    + " | Watts=" + ((MeasurableEnergy) device).watts());
        }

        // Final shutdown confirmation
        Powerable.safeShutdownAll();
        System.out.println("Verified all devices are OFF.");
    }
}
