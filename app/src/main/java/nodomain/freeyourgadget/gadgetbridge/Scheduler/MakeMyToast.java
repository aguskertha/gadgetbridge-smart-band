package nodomain.freeyourgadget.gadgetbridge.Scheduler;

import android.app.Service;
import android.content.Intent;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import nodomain.freeyourgadget.gadgetbridge.API.APIMessage;
import nodomain.freeyourgadget.gadgetbridge.Listener.CreateSensorRecordListener;


public class MakeMyToast extends Service implements CreateSensorRecordListener {
    @Override
    public void onCreate() {
        HandlerThread thread = new HandlerThread("ServiceStartArguments",
                Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();
        Log.d("onCreate()", "After service created");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        int heartRate = intent.getIntExtra("heart", 0);
        int oxiRate = intent.getIntExtra("oxi", 0);
        Toast.makeText(this, heartRate+" <> "+oxiRate, Toast.LENGTH_SHORT).show();
        for (int i=0; i<5; i++){
            Log.d("Success", "Success alarm!");
        }
        //Here is the source of the TOASTS :D
//        SensorRepository.createSensorRecord();

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // We don't provide binding
        return null;
    }

    @Override
    public void onCreateSensorRecord(APIMessage message) {
        Toast.makeText(this, message.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
