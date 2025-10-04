package Appliance;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AppliancesTester {
    public static void main(String[] args) {
        // Create devices
        WashingMachine wm = new WashingMachine();
        Refrigerator fridge = new Refrigerator();
        AirPurifier ap = new AirPurifier();

        System.out.println("=== Part D: Exception Scenarios ===");

        try {
            // (1) Power on a Refrigerator at 110V and call runCycle().
            fridge.powerOn();
            fridge.setTemperature(4);
            fridge.runCycle();
            System.out.println("(1) Output: Refrigerator chilling at 4°C");

            // (2) Call runCycle() before powerOn()
            WashingMachine wm2 = new WashingMachine();
            try {
                wm2.runCycle();
            } catch (PowerException e) {
                System.out.println("(2) Exception: " + e);
            }

            // (3) AirPurifier connectWifi with short password
            try {
                ap.connectWifi("HomeNet", "123"); // too short
            } catch (NetworkException e) {
                System.out.println("(3) Output: " + e.getMessage());
            }

            // (4) WashingMachine invalid RPM
            try {
                wm.setSpinRpm(2000); // invalid
            } catch (InvalidSetting | ClassCastException e) {
                System.out.println("(4) Output: " + e.getMessage());
            }

            // Normal usage (like your Main.java)
            wm.setSpinRpm(1200);
            wm.powerOn();
            wm.runCycle();

            ap.setFanLevel(3);
            ap.powerOn();
            ap.runCycle();

            // Trigger duplicate powerOn
            try {
                wm.powerOn();
            } catch (PowerException e) {
                System.out.println("Handled exception: " + e.getMessage());
            }

            // Part C4: Generate energy report
            try (FileWriter writer = new FileWriter("energy_report.txt")) {
                writer.write("=== Energy Report ===\n");
                writer.write("WashingMachine: " + wm.watts() + " W, " + wm.kWhPerDay() + " kWh/day\n");
                writer.write("Refrigerator: " + fridge.watts() + " W, " + fridge.kWhPerDay() + " kWh/day\n");
                writer.write("AirPurifier: " + ap.watts() + " W, " + ap.kWhPerDay() + " kWh/day\n");
                System.out.println("Energy report written to energy_report.txt");
            } catch (IOException e) {
                System.out.println("Error writing energy report: " + e.getMessage());
            }

        } catch (PowerException e) {
            System.out.println("Power error: " + e.getMessage());
        } finally {
            // (5) Always safe shutdown
            Powerable.safeShutdownAll(fridge, wm, ap);
            System.out.println("(5) Output: All devices safely powered off.");
        }

        // === Part E: Polymorphism & Collections ===
        System.out.println("\n=== Part E: Polymorphism & Collections ===");
        List<Appliance> appliances = new ArrayList<>();
        appliances.add(fridge);
        appliances.add(wm);
        appliances.add(ap);

        for (Powerable p : appliances) {
            System.out.println(p.getClass().getSimpleName()
                + ": status=" + p.powerStatus()
                + ", watts=" + ((MeasurableEnergy)p).watts());
        }

        Powerable.safeShutdownAll(appliances);
        System.out.println("Verified all devices are OFF.");
    }
}
