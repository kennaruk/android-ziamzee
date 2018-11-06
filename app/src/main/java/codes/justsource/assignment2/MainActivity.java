package codes.justsource.assignment2;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private static Context mContext;

    /* Animation */
    private static AnimationDrawable mAnimation = new AnimationDrawable();
    private ImageView img;

    /* Sensor */
    private static final int SHAKE_THRESHOLD = 800;
    private int lastUpdate;
    private static SensorInfo sensorInfo = new SensorInfo();;

    private SensorManager sensorManager;
    private Sensor accelerometerSensor;

    final int POLL_INTERVAL = 500; //ms
    private Handler handler = new Handler();
    private Runnable pollTask = new Runnable() {
        @Override
        public void run() {
            sensorInfo.checkZiamZee(mAnimation, mContext);
            handler.postDelayed(pollTask, POLL_INTERVAL);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        /* Animation */
        final int resonableDuration = 50; //ms
        img = (ImageView) findViewById(R.id.img);
        BitmapDrawable[] frames = new BitmapDrawable[4];
        for(int i = 0; i < 4; i++) {
            frames[i] = (BitmapDrawable) getResources().getDrawable(getResources().getIdentifier("frame_" + i, "drawable", this.getPackageName()));
            mAnimation.addFrame(frames[i], resonableDuration);
        }
        img.setImageDrawable(mAnimation);

//        mAnimation.start();
    }

    @Override
    protected void onResume() {
        super.onResume();

        /* Sensor */
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_GAME);
        handler.postDelayed(pollTask, POLL_INTERVAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float xAcc = sensorEvent.values[0];
        float yAcc = sensorEvent.values[1];
        float zAcc = sensorEvent.values[2];

        sensorInfo.setAcc(xAcc, yAcc, zAcc);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
