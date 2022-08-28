package nodomain.freeyourgadget.gadgetbridge.Scheduler;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;

import androidx.annotation.RequiresApi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import nodomain.freeyourgadget.gadgetbridge.impl.GBDevice;
import nodomain.freeyourgadget.gadgetbridge.model.SensorRequest;
import nodomain.freeyourgadget.gadgetbridge.service.DeviceCommunicationService;
import nodomain.freeyourgadget.gadgetbridge.util.ParcelableUtil;

public class AlarmReceiver extends BroadcastReceiver {
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent)
    {
        byte[] device = intent.getByteArrayExtra("device");
        byte[] sensor = intent.getByteArrayExtra("sensor");
        GBDevice gbDevice = ParcelableUtil.unmarshall(device, GBDevice.CREATOR);
        SensorRequest sensorRequest = ParcelableUtil.unmarshall(sensor, SensorRequest.CREATOR);
        Intent intent1 = new Intent(context, DeviceCommunicationService.class);
        intent1.putExtra("data", "sensor");
        intent1.putExtra("device", device);
        intent1.putExtra("sensor", sensor);
        context.startService(intent1);
        setAlarm(context,sensorRequest, gbDevice);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setAlarm(Context context, SensorRequest sensorRequest, GBDevice gbDevice)
    {
        AlarmManager am =( AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, AlarmReceiver.class);

        byte[] device = ParcelableUtil.marshall(gbDevice);
        byte[] sensor = ParcelableUtil.marshall(sensorRequest);

        i.putExtra("device", device);
        i.putExtra("sensor", sensor);

        PendingIntent pi = PendingIntent.getBroadcast(context, 999, i, PendingIntent.FLAG_UPDATE_CURRENT);
        assert am != null;
        am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, (System.currentTimeMillis()/1000L + 20L) *1000L, pi); //Next alarm in 15s
    }

    public void cancelAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, (int)999, intent,  PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(sender);
    }
}
