package sistemasfireg.igp.org.sismosperu


import android.app.Activity
import android.app.IntentService
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import sistemasfireg.igp.org.sismosperu.entity.Notification
import sistemasfireg.igp.org.sismosperu.utils.Constants
import sistemasfireg.igp.org.sismosperu.utils.Extras


/*

public class Pushnotificationservicetwo : IntentService("Pushnotificationservicetwo") {



    private val callback = { msg ->
        throw IllegalArgumentException("PUSH_RECEIVED NOT HANDLED!")
    }

    private fun sendNotification(notification: Notification, extras: Bundle = Bundle()) {

        extras.putParcelable(Extras.NOTIFICATION, notification);

        val broadcast = Intent();
        broadcast.putExtras(extras);
        broadcast.setAction(Constants.BROADCAST_NOTIFICATION);

        sendOrderedBroadcast(broadcast, null, null, android.os.Handler(callback), Activity.RESULT_OK, null, extras);
    }

    override fun onHandleIntent(intent: Intent) {
        val extras = intent.extras;

        val notification: Notification? = extras.getParcelable(Extras.NOTIFICATION)

        if (notification != null) {
            sendNotification(notification)
        } else {

            val message = extras.getString("message");
            val url = extras.getString("url");

            sendNotification(Notification(url, message), extras);

        }
    }

}


*/





private class Pushnotificationservicetwo : IntentService("Pushnotificationservicetwo") {

    val callback: Handler.Callback = Handler.Callback({
        msg ->
        throw IllegalArgumentException("PUSH_RECEIVED NOT HANDLED!")
    })



    override fun onHandleIntent(intent: Intent) {
        val extras = intent.extras;

        val notification: Notification? = extras.getParcelable(Extras.NOTIFICATION)

        if (notification != null) {
            sendNotification(notification)


        }

        else {

            val message = extras.getString("some_key");
            val url = extras.getString("some_key");

           sendNotification(Notification(url, message), extras);
            Log.d(TAG, "notif recivida");

        }
    }



    private fun sendNotification(notification: Notification, extras: Bundle = Bundle()) {

        extras.putParcelable(Extras.NOTIFICATION, notification);

        val broadcast = Intent();
        broadcast.putExtras(extras);
        broadcast.setAction(Constants.BROADCAST_NOTIFICATION);

        sendOrderedBroadcast(broadcast, null, null, Handler(callback), Activity.RESULT_OK, null, extras);

        Log.d(TAG, "notif recivida");

    }


}



/*

public class Pushnotificationservicetwo extends IntentService {

    private Handler.Callback callback = msg -> {
        throw new IllegalArgumentException("PUSH_RECEIVED NOT HANDLED!");
    };

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();

        if (extras.getParcelable("notification") != null) {
            sendNotification(extras.getParcelable("notification"));
        } else {

            String message = extras.getString("message");
            String url = extras.getString("url");
            Notification notification = new Notification(url, message);

            sendNotification(notification, extras);

            GcmBroadcastReceiver.completeWakefulIntent(intent);

        }
    }

    private void sendNotification(Notification notification) {
        sendNotification(notification, new Bundle());
    }

    private void sendNotification(Notification notification, Bundle extras) {
        Intent broadcast = new Intent();
        extras.putParcelable(Extras.NOTIFICATION, notification);
        broadcast.putExtras(extras);
        broadcast.setAction(Events.BROADCAST_NOTIFICATION);

        sendOrderedBroadcast(broadcast, null, null, new Handler(callback), Activity.RESULT_OK, null, extras);
    }
    }
        */