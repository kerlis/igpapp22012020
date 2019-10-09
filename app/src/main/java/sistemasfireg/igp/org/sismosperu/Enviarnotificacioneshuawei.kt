package sistemasfireg.igp.org.sismosperu

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.support.v4.app.NotificationCompat
import java.util.*

class Enviarnotificacioneshuawei {



    fun removerz(context: Context, title: String, message: String) {






      //  val de: Int? = null
        figurarnotificacion(context, title, message)
       // startTimer(context)
    }


    fun figurarnotificacion(context: Context, title: String, message: String) {
        val notificationintent = Intent(context, Ultimosismo4::class.java)
        val stackbuilder = TaskStackBuilder.create(context)
        stackbuilder.addParentStack(Ultimosismo4::class.java)
        stackbuilder.addNextIntent(notificationintent)
        val defaultSoundUri = Uri.parse("android.resource://" + context.packageName + "/" + R.raw.beep2)
        val pendingintent = stackbuilder.getPendingIntent(100, PendingIntent.FLAG_ONE_SHOT)
        val b = NotificationCompat.Builder(context)
        val notification = b.setContentTitle("->$title")
                .setContentTitle("Sismos Perú IGP")
                .setColor(Color.parseColor("#001665"))
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingintent).build()
        val rand = Random()
        val codigo = rand.nextInt(100)
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(5, notification)
    }


}