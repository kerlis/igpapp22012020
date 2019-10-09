package sistemasfireg.igp.org.sismosperu;
/*
public class Firebasemessagingthree {
}
*/


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

import java.util.Random;

public class Firebasemessagingthree extends GcmListenerService {

    private static final String TAG = "Firebasemessagingthree";

   // LocalDataBaseManager mDbManager;



    String message;
    Random randomNumber;
    long ID;
    /**
     * Called when message is received.
     *
     * @param from SenderID of the sender.
     * @param data Data bundle containing message data as key/value pairs.
     *             For Set of keys use data.keySet().
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(String from, Bundle data) {
        String message ;
        String title;
//        ID = Utils.getIDForPush("pushId",this);
//        if(ID == 0){
//            ID = 1;
//        }else {
//            ID += 1;
//        }
//        Utils.saveIDForPush("pushId",ID,this);
        Bundle bundle = data.getBundle("notification");
        if(bundle!= null){
            message = bundle.getString("body");
            title = bundle.getString("title");
            Log.d(TAG, "From: " + from);
            Log.d(TAG, "Message: " + message);}
        else {
            message ="";
            title = "NCMS";
        }

        /*
       // mDbManager = LocalDataBaseManager.getInstance(this);
        if (from.startsWith("/topics/")) {
            Calendar c = Calendar.getInstance();
            SimpleDateFormat s = new SimpleDateFormat("ddMMyyyyhhmmss");
            String format = s.format(new Date());
            ID = Long.parseLong(format);
            String date = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.ENGLISH).format(new Date());
            Warnings warnings = new Warnings();
            warnings.setWARNING_ID(ID);
            warnings.setWARNING_EN(message);
            warnings.setWARNING_AR(message);
            warnings.setSTART_DATE_TIME(date);
            warnings.setNotification_type(String.valueOf(Constant.NotificationType.PUSH));
            warnings.setSEVERITY("");
            warnings.setEND_DATE_TIME("");
            warnings.setUPDATE_NO("");
            mDbManager.insertNotificationInfo(warnings);
            // message received from some topic.
        }

       else {

            // normal downstream message.
        }  */


        // [START_EXCLUDE]
        /**
         * Production applications would usually process the message here.
         * Eg: - Syncing with server.
         *     - Store message in local database.
         *     - Update UI.
         */

        /**
         * In some cases it may be useful to show a notification indicating to the user
         * that a message was received.
         */
//        KeyguardManager km = (KeyguardManager) this.getSystemService(Context.KEYGUARD_SERVICE);
//        boolean locked = km.inKeyguardRestrictedInputMode();
//
//        String release = android.os.Build.VERSION.RELEASE;
//
//
//        if (Integer.parseInt(String.valueOf(release.charAt(0))) < 5 && locked) {
//
//            this.stopService(new Intent(this, NotificationService.class));
//            Intent serviceIntent = new Intent(this, NotificationService.class);
//            this.startService(serviceIntent);
//
//        }

        sendNotification(title,message);



        // [END_EXCLUDE]
    }
    // [END receive_message]

    /**
     * Create and show a simple notification containing the received GCM message.
     *
     * @param message GCM message received.
     */
    private void sendNotification(String title,String message) {
        Intent intent = new Intent(this, Ultimosismo4.class);
        intent.putExtra("message",message);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("ffffff" + title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}
