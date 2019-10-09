package sistemasfireg.igp.org.sismosperu
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.util.*
class Messagingservicef : FirebaseMessagingService() {
    private var titletema: String? = null
    private var messagetema: String? = null

    var prueba = 5

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
      //  FirebaseMessaging.getInstance().subscribeToTopic("SISMOSANDROIDDOSTRES")
       // FirebaseMessaging.getInstance().subscribeToTopic("SISMOSANDROIDDOSTRES")
        //titletema = remoteMessage!!.getNotification()!!.title //get title
        //messagetema = remoteMessage.getNotification()!!.body //get message
        //sendNotification(titletema, messagetema)
        //sendNotification()


          val TAG = Messagingservicedata::class.java.simpleName

        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: ${remoteMessage?.from}")

        // Check if message contains a data payload.
        remoteMessage?.data?.isNotEmpty()?.let {
            Log.d(TAG, "Message data payload: " + remoteMessage.data)

            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher. scheduleJob()
            } else {
                // Handle message within 10 seconds handleNow()
            }
        }

        // Check if message contains a notification payload.
        remoteMessage?.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body}")
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.





        /*
          if (remoteMessage!!.notification != null && remoteMessage!!.notification!!.title != null && remoteMessage!!.notification!!.title != null) {
            titletema = remoteMessage!!.notification!!.title
            messagetema = remoteMessage.notification!!.body
            sendNotification(titletema, messagetema)
          } else {
              titletema = remoteMessage!!.notification!!.title
              messagetema = remoteMessage.notification!!.body
              sendNotification(titletema, messagetema)

         }
         */


    }


    @SuppressLint("NewApi")
    private fun sendNotification(title: String?, messageBody: String?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val intent = Intent(this, Ultimosismo3::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            val defaultSoundUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.beep2)

            val defaultSoundUri2 = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

            val audioAttributes = AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .setUsage(AudioAttributes.USAGE_ALARM)
                        .build()

            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
            val channelId = getString(R.string.default_notification_channel_id_sismos)
            val channelName = getString(R.string.default_notification_channel_name_sismos)
            val notificationBuilder = NotificationCompat.Builder(this, channelId)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(title)
                    .setContentText("mensaje:" + messageBody)
                    .setAutoCancel(true)
                    //.setSound(defaultSoundUri)

                    .setContentIntent(pendingIntent)
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
                channel.setSound(defaultSoundUri, audioAttributes)
                channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.vibrationPattern = longArrayOf(0, 1000, 500, 1000)
            channel.enableVibration(true)
            notificationManager.createNotificationChannel(channel)
            val random = Random()
            notificationManager.notify(random.nextInt(100), notificationBuilder.build())
        }

        else {
            val defaultSoundUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.beep2)
            val intent = Intent(this, Ultimosismo3::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent, PendingIntent.FLAG_ONE_SHOT)
            val notificationBuilder = NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(title)
                    .setContentText("mensaje:" + messageBody)
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent)

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val random = Random()
            notificationManager.notify(random.nextInt(100), notificationBuilder.build())
        }
    }
}



