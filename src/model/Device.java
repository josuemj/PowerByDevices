package model;

public class Device {
    public Device(String deviceName, String imageName, double voltage, double current,double power,double time) {
        this.deviceName = deviceName;
        this.imageName = imageName;
        this.voltage = voltage;
        this.power = power;
        this.time = time;
        this.current = current;
    }

    private double power;
    private double time; //hours
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

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }
}
