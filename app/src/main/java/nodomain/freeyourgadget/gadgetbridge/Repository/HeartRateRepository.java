package nodomain.freeyourgadget.gadgetbridge.Repository;

import android.util.Log;

import com.google.gson.Gson;

import nodomain.freeyourgadget.gadgetbridge.API.APIConfig;
import nodomain.freeyourgadget.gadgetbridge.API.APIMessage;
import nodomain.freeyourgadget.gadgetbridge.Listener.CreateHeartRateRecordListener;
import nodomain.freeyourgadget.gadgetbridge.Listener.CreateSensorRecordListener;
import nodomain.freeyourgadget.gadgetbridge.model.HeartRateRequest;
import nodomain.freeyourgadget.gadgetbridge.model.SensorRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeartRateRepository {
    public static final void createHeartRateRecord(HeartRateRequest heartRateRequest, final CreateHeartRateRecordListener createHeartRateRecordListener){
        try {
            Call<APIMessage> callCreateHeartRateRecord = APIConfig.getService().createHeartRateRecord(heartRateRequest);
            callCreateHeartRateRecord.enqueue(new Callback<APIMessage>() {
                @Override
                public void onResponse(Call<APIMessage> call, Response<APIMessage> response) {
                    if(response.isSuccessful()){
                        APIMessage message = response.body();
                        createHeartRateRecordListener.onCreateHeartRateRecord(message);
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
