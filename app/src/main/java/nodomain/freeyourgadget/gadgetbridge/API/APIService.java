package nodomain.freeyourgadget.gadgetbridge.API;


import nodomain.freeyourgadget.gadgetbridge.model.HeartRateRequest;
import nodomain.freeyourgadget.gadgetbridge.model.SensorRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIService {
    @POST("sensor")
    Call<APIMessage> createSensorRecord(@Body SensorRequest sensorRequest);

    @POST("heart-rate")
    Call<APIMessage> createHeartRateRecord(@Body HeartRateRequest heartRateRequest);
}
