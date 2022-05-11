package com.example.motionsensor.eventListeners;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.icu.text.DecimalFormat;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;
import android.widget.Toast;

import com.example.motionsensor.R;

//import java.text.DecimalFormat;

public class AccelerometerSensorEventListener implements SensorEventListener {
    private Context activityContext;
    private TextView alertView;
    final Handler handler;

    public AccelerometerSensorEventListener(Context activityContext, TextView alertView) {
        this.handler = new Handler(Looper.getMainLooper());
        this.alertView = alertView;
        this.activityContext = activityContext;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        // Remove the gravity contribution with the high-pass filter.
        double x = sensorEvent.values[0];
        double y = sensorEvent.values[1];
        double z = sensorEvent.values[2];
        //mathematics detection
        double loAccelerationReader = Math.sqrt(
                Math.pow(x, 2) +
                Math.pow(y, 2) +
                Math.pow(z, 2)
        );

        DecimalFormat precision = new DecimalFormat("0.00");
        double ldAccRound = Double.parseDouble(precision.format(loAccelerationReader));

        if (ldAccRound > 0.3d && ldAccRound < 0.5d) {
            //Do your stuff
            Toast.makeText(this.activityContext, "The phone is falling", Toast.LENGTH_SHORT).show();
            //play sound from local file
            MediaPlayer mediaPlayer = MediaPlayer.create(this.activityContext, R.raw.alarm);
            mediaPlayer.start();
            //check text color and background in order to show the event
            this.alertView.setText(R.string.alert);
            this.alertView.setTextColor(Color.WHITE);
            this.alertView.setBackgroundColor(Color.RED);
            //use a handler to change the appearance at the end of the event
            this.handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    alertView.setText(R.string.title);
                    alertView.setTextColor(Color.BLACK);
                    alertView.setBackgroundColor(Color.WHITE);
                }
            }, 4000);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
