package nodomain.freeyourgadget.gadgetbridge.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SensorRequest implements Parcelable {
    private int heartRate;
    private int oxiRate;

    public SensorRequest(int heartRate, int oxiRate) {
        this.heartRate = heartRate;
        this.oxiRate = oxiRate;
    }

    protected SensorRequest(Parcel in) {
        heartRate = in.readInt();
        oxiRate = in.readInt();
    }

    public static final Creator<SensorRequest> CREATOR = new Creator<SensorRequest>() {
        @Override
        public SensorRequest createFromParcel(Parcel in) {
            return new SensorRequest(in);
        }

        @Override
        public SensorRequest[] newArray(int size) {
            return new SensorRequest[size];
        }
    };

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public int getOxiRate() {
        return oxiRate;
    }

    public void setOxiRate(int oxiRate) {
        this.oxiRate = oxiRate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(heartRate);
        dest.writeInt(oxiRate);
    }
}
