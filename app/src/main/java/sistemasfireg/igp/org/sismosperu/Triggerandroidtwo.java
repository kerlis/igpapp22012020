package sistemasfireg.igp.org.sismosperu;


import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.TriggerEvent;
import android.hardware.TriggerEventListener;


public class Triggerandroidtwo extends Activity {
    private final SensorManager mSensorManager;
    private final Sensor mSigMotion;


    public TriggerEventListener mListener = new TriggerEventListener() {
        @Override
        public void onTrigger(TriggerEvent triggerEvent) {


         //   MediaPlayer mp = MediaPlayer.create(this, R.raw.beep2);
          ///  mp.start();


        }
    };

    public Triggerandroidtwo() {
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mSigMotion = mSensorManager.getDefaultSensor(Sensor.TYPE_SIGNIFICANT_MOTION);
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.requestTriggerSensor(mListener, mSigMotion);
    }

    protected void onPause() {
        super.onPause();
        // Call disable to ensure that the trigger request has been canceled.
        mSensorManager.cancelTriggerSensor(mListener, mSigMotion);
    }

}
