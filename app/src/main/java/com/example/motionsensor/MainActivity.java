package com.example.motionsensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.TriggerEvent;
import android.hardware.TriggerEventListener;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.motionsensor.eventListeners.AccelerometerSensorEventListener;

public class MainActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private Sensor accelerometerSensor;
    private TextView alertView;
    private AccelerometerSensorEventListener accelerometerSensorEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //UI elements
        alertView = findViewById(R.id.TextAlert);

        //sensors objects
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometerSensorEventListener = new AccelerometerSensorEventListener(this.getApplicationContext(),alertView);
        //get sensor
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //check if sensor exists
        if (accelerometerSensor != null){
            // Success!
            Toast.makeText(MainActivity.this, "All sensors are available", Toast.LENGTH_SHORT).show();
        } else {
            // Failure!
            Toast.makeText(MainActivity.this, "Not all sensors are available", Toast.LENGTH_SHORT).show();
            //stop process
        }
        sensorManager.registerListener(accelerometerSensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

}