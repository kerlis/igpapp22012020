package sistemasfireg.igp.org.sismosperu

import android.app.Activity
import android.hardware.Sensor
import android.hardware.SensorManager
import android.hardware.TriggerEvent
import android.hardware.TriggerEventListener





 class Triggerandroid : TriggerEventListener() {
    override fun onTrigger(event: TriggerEvent) {
        // Do Work.

        // As it is a one shot sensor, it will be canceled automatically.
        // SensorManager.requestTriggerSensor(this, mSigMotion); needs to
        // be called again, if needed.
    }
}

class SensorActivity : Activity() {
    private val mSensorManager: SensorManager
    private val mSigMotion: Sensor
    private val mListener : TriggerEventListener? = null

    init {
        mSensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        mSigMotion = mSensorManager.getDefaultSensor(Sensor.TYPE_SIGNIFICANT_MOTION)
    }

    override fun onResume() {
        super.onResume()
        mSensorManager.requestTriggerSensor(mListener, mSigMotion)
    }

    override fun onPause() {
        super.onPause()
        // Call disable to ensure that the trigger request has been canceled.
        mSensorManager.cancelTriggerSensor(mListener, mSigMotion)
    }

}
