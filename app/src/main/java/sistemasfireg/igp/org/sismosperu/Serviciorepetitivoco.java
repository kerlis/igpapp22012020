package sistemasfireg.igp.org.sismosperu;
/*
public class Serviciorepetitivoco {
    private static final Serviciorepetitivoco ourInstance = new Serviciorepetitivoco();

    public static Serviciorepetitivoco getInstance() {
        return ourInstance;
    }

    private Serviciorepetitivoco() {
    }
}


*/
////


import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class Serviciorepetitivoco extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager pm = (PowerManager) context
                .getSystemService(Context.POWER_SERVICE);
        @SuppressLint("InvalidWakeLockTag") PowerManager.WakeLock wl = pm.newWakeLock(
                PowerManager.PARTIAL_WAKE_LOCK, "");
        wl.acquire();


        Toast.makeText(context, "Alarm !!!!!!!!!!", Toast.LENGTH_LONG).show(); // For

       // Intent scheduledIntent = new Intent(context, ReminderMessage.class);
      //  scheduledIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
       // context.startActivity(scheduledIntent);
        // example

        wl.release();
    }

    public void SetAlarm(Context context) {
        AlarmManager am = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);

        Date dat = new Date();
        Calendar cal_alarm = Calendar.getInstance();
        Calendar cal_now = Calendar.getInstance();
        cal_now.setTime(dat);
        cal_alarm.setTime(dat);
        cal_alarm.set(Calendar.HOUR_OF_DAY, 10);
        cal_alarm.set(Calendar.MINUTE, 43);
        cal_alarm.set(Calendar.SECOND, 0);

        if (cal_alarm.before(cal_now)) {
            cal_alarm.add(Calendar.DATE, 1);
        }


        Intent i = new Intent(context, Serviciorepetitivoco.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), cal_alarm.getTimeInMillis(), pi); // Millisec * Second * Minute
    }
/*
    public void CancelAlarm(Context context) {
        Intent intent = new Intent(context, Serviciorepetitivoco.class);
        PendingIntent sender = PendingIntent
                .getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }

    */
}