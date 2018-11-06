package codes.justsource.assignment2;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements SensorListener{
    private static final int SHAKE_THRESHOLD = 800;
    private int lastUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener((SensorListener) this,
                SensorManager.SENSOR_ACCELEROMETER,
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onSensorChanged(int sensor, float[] values) {
        if(sensor == SensorManager.SENSOR_ACCELEROMETER) {
            long curTime = System.currentTimeMillis();
            //only allow one update every 100ms.
            if((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
//                lastUpdate = curTime;
//
//                x = values[SensorManager.DATA_X];
            }
        }
    }

    @Override
    public void onAccuracyChanged(int sensor, int accuracy) {

    }
}
