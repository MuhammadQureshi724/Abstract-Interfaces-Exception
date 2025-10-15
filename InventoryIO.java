package Appliance;

import java.io.*;      // covers BufferedWriter, FileWriter, IOException
import java.util.*;    // covers List, ArrayList, Locale, Collection

public class InventoryIO {

    public static void saveEnergyReport(String path, Collection<? extends MeasurableEnergy> items)
            throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write("Energy Report (daily estimates)\n");
            bw.write("================================\n");
            for (MeasurableEnergy m : items) {
                String line = m.getClass().getSimpleName() +
                        " -> " + String.format(Locale.US, "%.2f kWh/day", m.kWhPerDay());
                bw.write(line);
                bw.newLine();
            }
            System.out.println("Energy report successfully written to " + path);
        }
    }

    public static void main(String[] args) {
        WashingMachine wm = new WashingMachine();
        Refrigerator fridge = new Refrigerator();
        AirPurifier ap = new AirPurifier();

        try {
            wm.setSpinRpm(1200);
            wm.powerOn();
            fridge.setTemperature(4);
            fridge.powerOn();
            ap.setFanLevel(3);
            ap.powerOn();

            List<Appliance> appliances = new ArrayList<>();
            appliances.add(wm);
            appliances.add(fridge);
            appliances.add(ap);

            saveEnergyReport("energy_report.txt", appliances);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            Powerable.safeShutdownAll(wm, fridge, ap);
            System.out.println("All devices have been safely powered off.");
        }
    }
}
