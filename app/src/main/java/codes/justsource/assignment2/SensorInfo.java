package codes.justsource.assignment2;

public class SensorInfo {
    private float accX, accY, accZ;
    private int shakeThreshold;

    public SensorInfo() {
        accX = 0;
        accY = 0;
        accZ = 0;
        shakeThreshold = 0;
    }

    public void setShakeThreshold(int shakeThreshold) {
        this.shakeThreshold = shakeThreshold;
    }

    public void setAcc(int accX, int accY, int accZ) {
        this.accX = accX;
        this.accY = accY;
        this.accZ = accZ;
    }

    public boolean isShake() {
        return false;
    }

}
