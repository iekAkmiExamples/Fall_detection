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
        this.alertView = findViewById(R.id.TextAlert);

        //sensors objects
        this.sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        this.accelerometerSensorEventListener = new AccelerometerSensorEventListener(this.getApplicationContext(),alertView);
        //get sensor
        this.accelerometerSensor = this.sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //check if sensor exists
        if (this.accelerometerSensor != null){
            // Success!
            Toast.makeText(MainActivity.this, "All sensors are available", Toast.LENGTH_SHORT).show();
        } else {
            // Failure!
            Toast.makeText(MainActivity.this, "Not all sensors are available", Toast.LENGTH_SHORT).show();
            //stop process
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register a listener for the sensor.
        this.sensorManager.registerListener(this.accelerometerSensorEventListener, this.accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Be sure to unregister the sensor when the activity pauses.
        this.sensorManager.unregisterListener(this.accelerometerSensorEventListener);
    }

}