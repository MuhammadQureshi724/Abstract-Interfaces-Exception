package Appliance;

public interface SmartDevice {
    public void connect(String wifiSsid) throws NetworkException;
    public void disconnect() throws NetworkException;
    public String ping();
}
