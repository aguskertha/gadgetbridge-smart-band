package nodomain.freeyourgadget.gadgetbridge.model;

public class HeartRateRequest {
    private int heartRate;

    public HeartRateRequest(int heartRate) {
        this.heartRate = heartRate;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }
}
