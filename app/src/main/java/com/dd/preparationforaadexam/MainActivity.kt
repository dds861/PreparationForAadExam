package com.dd.preparationforaadexam

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


//implement SensorEventListener
class MainActivity : AppCompatActivity(), SensorEventListener {

    //Create 2 objects: SensorManager and Sensor
    private lateinit var sensorManager: SensorManager
    private lateinit var accelerometer: Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialize SensorManager and Sensor
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    //register SensorManager
    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
    }

    //unregister SensorManager
    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    //override 2 functions
    override fun onSensorChanged(event: SensorEvent) {}
    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
}