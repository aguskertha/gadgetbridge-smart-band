package nodomain.freeyourgadget.gadgetbridge.Repository;

import android.util.Log;

import com.google.gson.Gson;

import nodomain.freeyourgadget.gadgetbridge.API.APIConfig;
import nodomain.freeyourgadget.gadgetbridge.API.APIMessage;
import nodomain.freeyourgadget.gadgetbridge.Listener.CreateSensorRecordListener;
import nodomain.freeyourgadget.gadgetbridge.model.SensorRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SensorRepository {
    public static final void createSensorRecord(SensorRequest sensorRequest, final CreateSensorRecordListener createSensorRecordListener){
        try {
            Call<APIMessage> callCreateSensorRecord = APIConfig.getService().createSensorRecord(sensorRequest);
            callCreateSensorRecord.enqueue(new Callback<APIMessage>() {
                @Override
                public void onResponse(Call<APIMessage> call, Response<APIMessage> response) {
                    if(response.isSuccessful()){
                        APIMessage message = response.body();
                        APIMessage sensorMessage = new APIMessage();
                        sensorMessage.setMessage("Heart Rate: "+sensorRequest.getHeartRate()+" || Spo2 Rate: "+ sensorRequest.getOxiRate()+" are successfully created!");
                        createSensorRecordListener.onCreateSensorRecord(sensorMessage);
                    }
                    else if(response.code() == 400){
                        APIMessage message = new Gson().fromJson(response.errorBody().charStream(), APIMessage.class);
                        Log.d("Repository", message.getMessage());
                    }
                    else {
                        Log.d("Repository", "Server Error!");
                    }
                }

                @Override
                public void onFailure(Call<APIMessage> call, Throwable t) {

                }
            });
        }
        catch (Exception err){
            Log.d("Repository", err.toString());
        }
    }
}
