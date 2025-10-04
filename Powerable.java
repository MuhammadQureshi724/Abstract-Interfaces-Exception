package Appliance;

public interface Powerable { 
    
    public void powerOn() throws PowerException; 
    
    public void powerOff() throws PowerException; 
    
    public boolean isOn();
    
    public String powerStatus(); 
    
    public static void safeShutdownAll(Powerable... devices) {
        for (Powerable device : devices) {
            if (device.isOn()) {
                try {
                    device.powerOff();
                } catch (PowerException e) {
                    System.out.println("Error shutting down: " + e.getMessage());
                }
            }
        }
    }
}
