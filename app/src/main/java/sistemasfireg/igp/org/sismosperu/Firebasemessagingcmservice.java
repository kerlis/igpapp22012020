package sistemasfireg.igp.org.sismosperu;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

public class Firebasemessagingcmservice extends GcmListenerService {
    private static final String TAG = "Something";



        private final Firebasenotificationlistener.InterceptedNotificationCode intercomPushClient = new Firebasenotificationlistener.InterceptedNotificationCode();

    @Override
    public void onMessageReceived(String from, Bundle data) {


        if (intercomPushClient != null) {
            Log.i(TAG, "notif recivida" + from);

        } else {
            Log.i(TAG, "notif recivida" + from);

            //DO HOST LOGIC HERE
        }



        Intent intent = new Intent(this, Pushnotificationservicetwo.class);
        intent.putExtras(data);
        startService(intent);



            Log.i(TAG, "notif recivida" + from);



    }
}
