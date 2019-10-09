package sistemasfireg.igp.org.sismosperu
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.CountDownTimer
import android.support.v4.app.NotificationCompat
import android.util.Log
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessagingService
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.Reader
import java.util.*
import android.support.v4.app.NotificationManagerCompat
import android.support.v4.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T

class Removenotificationtwo : FirebaseMessagingService() {

    var TAG_REGISTER: String? = null
    var valorsonido: String? = null
    var valornotificacion: String? = null
    var valortipo: String? = null
    var valorringtone: String? = null
    var sonido_notificacion = null
    var valorentero:Int = 0
    var valorramdomesound: String? = null

    fun removerz(context: Context?, title: String?, message: String?) {

          var file2 = InputStreamReader(context!!.openFileInput("vibrar_file"))
          var br2 = BufferedReader(file2 as Reader?)
          var line2 = br2.readLine()
          var all2 = StringBuilder()
          while (line2 != null) {
              all2.append(line2 + "\n")
              line2 = br2.readLine()
          }
          br2.close()
          file2.close()
          valorsonido = all2.toString()


          val file3 = InputStreamReader(context!!.openFileInput("notificar_file"))
          val br3 = BufferedReader(file3 as Reader?)
          var line3 = br3.readLine()
          val all3 = StringBuilder()
          while (line3 != null) {
              all3.append(line3 + "\n")
              line3 = br3.readLine()
          }
          br3.close()
          file3.close()
          valornotificacion = all3.toString()


          val file4 = InputStreamReader(context!!.openFileInput("tipo_file"))
          val br4 = BufferedReader(file4 as Reader?)
          var line4 = br4.readLine()
          val all4 = StringBuilder()
          while (line4 != null) {
              all4.append(line4 + "\n")
              line4 = br4.readLine()
          }
          br4.close()
          file4.close()
          valortipo = all4.toString()
          valorentero = valortipo!!.length


          val file5 = InputStreamReader(context!!.openFileInput("ring_tone_file"))
          val br5 = BufferedReader(file5 as Reader?)
          var line5 = br5.readLine()
          val all5 = StringBuilder()
          while (line5 != null) {
              all5.append(line5 + "\n")
              line5 = br5.readLine()
          }
          br5.close()
          file5.close()
          valorringtone = all5.toString()


        val file6 = InputStreamReader(context!!.openFileInput("ramdom_number_file"))
        val br6 = BufferedReader(file6)
        var line6 = br6.readLine()
        val all6 = StringBuilder()
        while (line6 != null) {
            all6.append(line6 + "\n")
            line6 = br6.readLine()
        }
        br6.close()
        file6.close()
        valorramdomesound = all6.toString()

          val de: Int? = null
          figurarnotificacion(context, title, message, valorsonido, valornotificacion, valortipo, valorringtone)
          startTimer(context)
    }

    fun figurarnotificacion(context: Context?, title: String?, message: String?, valorsonido: String?, valornotificacionm: String?, valortipo: String?, valorringtone: String?) {

        if(valornotificacion!!.length > 4){

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                if (valorsonido!!.length > 4) {
                    if (valorentero == 6) {
                        val intent = Intent(context, Ultimosismo4::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        }
                        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
                        val channelId = "canal_sonido_alarmas_sismos_id"
                        val notificationBuilder = NotificationCompat.Builder(context!!, channelId)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setColor(Color.parseColor("#001665"))
                                .setContentTitle("Sismo Perú...........")
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                .setAutoCancel(true)
                                .setContentIntent(pendingIntent)
                        val notificationManager = NotificationManagerCompat.from(context)
                        notificationManager.notify(5, notificationBuilder.build())
                    }
                    else if (valorentero == 7) {
                        val intent = Intent(context, Ultimosismo4::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        }
                        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
                        val channelId = "canal_sonido_notificacion_sismos_id"
                        val notificationBuilder = NotificationCompat.Builder(context!!, channelId)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setColor(Color.parseColor("#001665"))
                                .setContentTitle("Sismo Perú...........")
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                .setAutoCancel(true)
                                .setContentIntent(pendingIntent)
                        val notificationManager = NotificationManagerCompat.from(context)
                        notificationManager.notify(5, notificationBuilder.build())
                    }
                    else {

                        val intent = Intent(context, Ultimosismo4::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        }
                        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

                        val file6 = InputStreamReader(context!!.openFileInput("ramdom_number_file"))
                        val br6 = BufferedReader(file6)
                        var line6 = br6.readLine()
                        val all6 = StringBuilder()
                        while (line6 != null) {
                            all6.append(line6)
                            line6 = br6.readLine()
                        }
                        br6.close()
                        file6.close()
                        valorramdomesound = all6.toString()


                        var stingh: String = valorringtone.toString()
                        val lastChar = stingh.substring(0,2)
                        val lastCharrw = stingh.substring(0, (stingh.length - 1))
                        Log.v(TAG_REGISTER, " Valor pux :$lastChar")
                        val sonido_notificacion_android  = Uri.parse(lastCharrw)

                        val channelId = "canal_sonido_personalizado_sismos_id"+valorramdomesound
                        val channelName = "canal_sonido_personalizado_sismos_id"+valorramdomesound

                        val notificationBuilder = NotificationCompat.Builder(context!!, channelId)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setColor(Color.parseColor("#001665"))
                                .setContentTitle("Alerta de Lahar")
                               // .setContentText(" $nobrevolcan_r2  $asubstring | $hora ")
                                .setAutoCancel(true)
                                .setSound(sonido_notificacion_android)
                                .setContentIntent(pendingIntent)
                        val notificationManager = NotificationManagerCompat.from(context)
                        notificationManager.notify(5, notificationBuilder.build())
                        Log.v(TAG_REGISTER, " Valor pi :$channelId")
                    }
                }
                else{
                    val intent = Intent(context, Ultimosismo4::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                    val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

                    val channelId = "canal_sin_sonido_con_vibracion_sismos_id"
                     val notificationBuilder = NotificationCompat.Builder(context!!, channelId)
                            .setSmallIcon(R.mipmap.ic_launcher)
                             .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                             .setColor(Color.parseColor("#001665"))
                            .setContentTitle("Sismo Perú...........")
                             .setAutoCancel(true)
                            .setVibrate(longArrayOf(50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100))
                            .setContentIntent(pendingIntent)
                    val notificationManager = NotificationManagerCompat.from(context)

                    val random = Random()
                    notificationManager.notify(5, notificationBuilder.build())
                }
            }

            else{
                if (valorsonido!!.length > 4) {
                    if (valorentero == 6) {
                        val sonido_notificacion_android = Uri.parse("android.resource://" + context?.packageName + "/" + R.raw.alarmasonido)

                        val notificationintent = Intent(context, Ultimosismo4::class.java)
                        val stackbuilder = TaskStackBuilder.create(context)
                        stackbuilder.addParentStack(Ultimosismo4::class.java)
                        stackbuilder.addNextIntent(notificationintent)
                        val defaultSoundUri = Uri.parse("android.resource://" + context?.packageName + "/" + R.raw.beep2)
                        val pendingintent = stackbuilder.getPendingIntent(100, PendingIntent.FLAG_ONE_SHOT)
                        val b = NotificationCompat.Builder(context)
                        val notification = b.setContentTitle("->$title")
                                //.setContentTitle("$valorsonido , $valornotificacionm, $valortipo, $valorringtone")
                                .setContentTitle("Sismo Perú...........")
                                .setColor(Color.parseColor("#001665"))
                                .setAutoCancel(true)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setSound(sonido_notificacion_android)
                                .setContentIntent(pendingintent).build()
                        val rand = Random()
                        val codigo = rand.nextInt(100)
                        val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                        notificationManager.notify(5, notification)
                    }
                    else if (valorentero == 7) {
                        val sonido_notificacion_android = Uri.parse("android.resource://" + context?.packageName + "/" + R.raw.beep2)

                        val notificationintent = Intent(context, Ultimosismo4::class.java)
                        val stackbuilder = TaskStackBuilder.create(context)
                        stackbuilder.addParentStack(Ultimosismo4::class.java)
                        stackbuilder.addNextIntent(notificationintent)
                        val defaultSoundUri = Uri.parse("android.resource://" + context?.packageName + "/" + R.raw.beep2)
                        val pendingintent = stackbuilder.getPendingIntent(100, PendingIntent.FLAG_ONE_SHOT)
                        val b = NotificationCompat.Builder(context)
                        val notification = b.setContentTitle("->$title")
                                //.setContentTitle("$valorsonido , $valornotificacionm, $valortipo, $valorringtone")

                                .setContentTitle("Sismo Perú...........")
                                .setColor(Color.parseColor("#001665"))
                                .setAutoCancel(true)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setSound(sonido_notificacion_android)
                                .setContentIntent(pendingintent).build()
                        val rand = Random()
                        val codigo = rand.nextInt(100)
                        val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                        notificationManager.notify(5, notification)
                    }
                    else{
                        var stingh: String = valorringtone.toString()
                        val lastChar = stingh.substring(0,2)
                        val lastCharrw = stingh.substring(0, (stingh.length - 1))
                        Log.v(TAG_REGISTER, " Valor pux :$lastChar")
                        val sonido_notificacion_android  = Uri.parse(lastCharrw)


                        val notificationintent = Intent(context, Ultimosismo4::class.java)
                        val stackbuilder = TaskStackBuilder.create(context)
                        stackbuilder.addParentStack(Ultimosismo4::class.java)
                        stackbuilder.addNextIntent(notificationintent)
                        val defaultSoundUri = Uri.parse("android.resource://" + context?.packageName + "/" + R.raw.beep2)
                        val pendingintent = stackbuilder.getPendingIntent(100, PendingIntent.FLAG_ONE_SHOT)
                        val b = NotificationCompat.Builder(context)
                        val notification = b.setContentTitle("->$title")
                                //.setContentTitle("$valorsonido , $valornotificacionm, $valortipo, $valorringtone")
                                .setContentTitle("Sismo Perú...........")

                                .setColor(Color.parseColor("#001665"))
                                .setAutoCancel(true)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setSound(sonido_notificacion_android)
                                .setContentIntent(pendingintent).build()
                        val rand = Random()
                        val codigo = rand.nextInt(100)
                        val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                        notificationManager.notify(5, notification)
                    }
                }

                else{


                    val notificationintent = Intent(context, Ultimosismo4::class.java)
                    val stackbuilder = TaskStackBuilder.create(context)
                    stackbuilder.addParentStack(Ultimosismo4::class.java)
                    stackbuilder.addNextIntent(notificationintent)
                    val defaultSoundUri = Uri.parse("android.resource://" + context?.packageName + "/" + R.raw.beep2)
                    val pendingintent = stackbuilder.getPendingIntent(100, PendingIntent.FLAG_ONE_SHOT)
                    val b = NotificationCompat.Builder(context)
                    val notification = b.setContentTitle("->$title")
                            //.setContentTitle("$valorsonido , $valornotificacionm, $valortipo, $valorringtone")
                            .setContentTitle("Sismo Perú...........")

                            .setColor(Color.parseColor("#001665"))
                            .setAutoCancel(true)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setVibrate(longArrayOf(50, 300, 50, 300,50, 300,50, 300,50, 300,50, 300,50, 300,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,   50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100))
                            .setContentIntent(pendingintent).build()
                    val rand = Random()
                    val codigo = rand.nextInt(100)
                    val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    notificationManager.notify(5, notification)
                }
            }
        }
        else{
            Toast.makeText(context, "notificaciones bloqueadas", Toast.LENGTH_LONG).show()
            val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.cancelAll()
            startTimer3(context)
        }
    }


    var cTimer: CountDownTimer? = null

    fun startTimer(context: Context?) {
        cTimer = object : CountDownTimer(6000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                borrar(context)
            }
        }
        cTimer!!.start()
    }

    fun startTimer2(context: Context?) {
        cTimer = object : CountDownTimer(1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                borrar2(context)
            }
        }
        cTimer!!.start()
    }

    fun startTimer3(context: Context?) {
        cTimer = object : CountDownTimer(1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                borrar3(context)
            }
        }
        cTimer!!.start()
    }

    fun cancelTimer() {
        if (cTimer != null)
            cTimer!!.cancel()
    }

    fun borrar(context: Context?) {
        val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.cancel(5)
            startTimer2(context)
    }

    fun borrar2(context: Context?) {
        val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(5)
       // notificationManager.cancel("".toInt())
    }

    fun borrar3(context: Context?) {
        val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancelAll()
        // notificationManager.cancel("".toInt())
    }

}