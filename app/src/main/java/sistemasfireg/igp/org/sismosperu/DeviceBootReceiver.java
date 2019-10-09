package sistemasfireg.igp.org.sismosperu;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author Nilanchala
 *         <p/>
 *         Broadcast reciever, starts when the device gets starts.
 *         Start your repeating alarm here.
 */
public class DeviceBootReceiver extends BroadcastReceiver {
    private PendingIntent pendingIntent;

    @Override
    public void onReceive(Context context, Intent intent) {
    //    Intent i = new Intent(context, Ultimosismo4.class);
        //i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //context.startActivity(i);
      //  Intent myIntent = new Intent(context, Ultimosismo4.class);
  //      context.startService(myIntent);
/*
            Intent alarmIntent = new Intent(context, Ultimosismo4.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);
            AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            int interval = 6000;
            Calendar cur_cal = new GregorianCalendar();
            manager.cancel(pendingIntent);
            manager.setRepeating(AlarmManager.RTC, cur_cal.getTimeInMillis(), interval, pendingIntent);
            Toast.makeText(context, "Alarm establecida", Toast.LENGTH_SHORT).show();
*/

/*
            AlarmManager AlarmsInWeekAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            AlarmsInWeekAlarmManager.cancel(pendingIntent);
*/


        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            Intent alarmIntent = new Intent(context, Serviciorevisionsismosnotificaciones.class);
            pendingIntent = PendingIntent.getBroadcast(context, 123, alarmIntent, 0);
            AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            int interval2 = 8000;
            int interval = 6000;
            Calendar cur_cal = new GregorianCalendar();
            manager.cancel(pendingIntent);
            manager.setRepeating(AlarmManager.RTC, cur_cal.getTimeInMillis(), interval, pendingIntent);
        }

            // manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
            //  PendingIntent pintent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            //AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

       /* }*/

    }
}