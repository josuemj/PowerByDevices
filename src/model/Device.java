package model;

public class Device {
    public Device(String deviceName, String imageName, double voltage, double current) {
        this.deviceName = deviceName;
        this.imageName = imageName;
        this.voltage = voltage;
        this.current = current;
    }

    private String deviceName;
    private String imageName;

    private double voltage;
    private double current;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public double getVoltage() {
        return voltage;
    }

    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }

    public double getCurrent() {
        return current;
    }

    public void setCurrent(double current) {
        this.current = current;
    }
}
