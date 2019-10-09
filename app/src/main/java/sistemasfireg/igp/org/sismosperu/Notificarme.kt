package sistemasfireg.igp.org.sismosperu

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.support.v4.app.NotificationCompat
import com.google.android.gms.gcm.GcmReceiver
import java.util.*

class Notificarme : GcmReceiver() {

    fun figurarnotificacion(context: Context, title: String, message: String) {
        val notificationintent = Intent(context, Ultimosismo4::class.java)
        val stackbuilder = TaskStackBuilder.create(context)
        stackbuilder.addParentStack(Ultimosismo4::class.java)
        stackbuilder.addNextIntent(notificationintent)

        val defaultSoundUri = Uri.parse("android.resource://" + context.packageName + "/" + R.raw.beep2)
        val pendingintent = stackbuilder.getPendingIntent(100, PendingIntent.FLAG_ONE_SHOT)
        val b = NotificationCompat.Builder(context)

        val notification = b.setContentTitle("->$title")
                .setContentText("->$message")
                .setContentTitle("->$message")
                .setColor(Color.parseColor("#0D2142"))
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingintent).build()

        //Random rand = new Random();
        //int aNumber = (int) (20 * Math.random()) + 1;

        val rand = Random()
        val codigo = rand.nextInt(100)
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(codigo, notification)
        //  notificationManager.cancel(5);
    }

}