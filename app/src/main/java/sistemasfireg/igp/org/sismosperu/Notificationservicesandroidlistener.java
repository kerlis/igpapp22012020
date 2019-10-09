/*package sistemasfireg.igp.org.sismosperu;

public class Notificationservicesandroidlistener {
}
*/


package sistemasfireg.igp.org.sismosperu;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;


public class Notificationservicesandroidlistener extends NotificationListenerService {

    Context context;

    @Override

    public void onCreate() {

        super.onCreate();
        context = getApplicationContext();

    }
    @Override

    public void onNotificationPosted(StatusBarNotification sbn) {


        String pack = sbn.getPackageName();

        String text = "";
        String title = "";

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            Bundle extras = extras = sbn.getNotification().extras;
            text = extras.getCharSequence("android.text").toString();
            title = extras.getString("android.title");
        }
        else{
            @SuppressLint({"NewApi", "LocalSuppress"}) Bundle extras = extras = sbn.getNotification().extras;
            text = extras.getCharSequence("android.text").toString();
            title = extras.getString("android.title");
        }

        Log.i("Package", "paquete : " + pack);
        Log.i("Title","titulo : " +title);
        Log.i("Text","texto : " +text);

    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.i("Msg","Notification was removed");
    }
}