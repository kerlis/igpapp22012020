package sistemasfireg.igp.org.sismosperu
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*


class Messagingservicedata : FirebaseMessagingService()  {
    var TAG_REGISTER: String? = null
    var valorsonido: String? = null
    var valornotificacion: String? = null
    var valortipo: String? = null
    var valorringtone: String? = null

    var sonido_notificacion = null

    var valorentero:Int = 0

    companion object {
        private val TAG = Messagingservicedata::class.java.simpleName
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {

        var novibrar_val: String
        var vibrar_val: String
        var sonidoalarma_val: String
        var sonidonotificacion_val: String
        var sonidopersonalizado_val: String
        var sonidovalorelegido_val: String
        var Message: String
        var valorguardadoenmemoria: String

        val valor_datos: String? = remoteMessage!!.data.get("cuerpo")
        val longitud: String = valor_datos!!.split("&")[0]
        val latitud: String = valor_datos!!.split("&")[1]
        val epicentro: String = valor_datos!!.split("&")[2]
        val profundo: String = valor_datos!!.split("&")[3]
        val referencia: String = valor_datos!!.split("&")[4]
        val magnitud: String = valor_datos!!.split("&")[5]
        val publicar: String = valor_datos!!.split("&")[6]
        val intensidad: String = valor_datos!!.split("&")[7]
        val hora_local: String = valor_datos!!.split("&")[8]
        val fecha: String = valor_datos!!.split("&")[9]
      //  enviarnotificaciondatossismos(remoteMessage.getData().get("cuerpo"))

    //    if(fileList().contains("datos_configuracion")) {
/*
        val file = InputStreamReader(openFileInput("datos_configuracion"))
        val br = BufferedReader(file)
        var line = br.readLine()
        val all = StringBuilder()
        while (line != null) {
            all.append(line + "\n")
            line = br.readLine()
        }
        br.close()
        file.close()
*/

        var file2 = InputStreamReader(openFileInput("vibrar_file"))
        var br2 = BufferedReader(file2)
        var line2 = br2.readLine()
        var all2 = StringBuilder()
        while (line2 != null) {
            all2.append(line2 + "\n")
            line2 = br2.readLine()
        }
        br2.close()
        file2.close()

        valorsonido = all2.toString()


        val file3 = InputStreamReader(openFileInput("notificar_file"))
        val br3 = BufferedReader(file3)
        var line3 = br3.readLine()
        val all3 = StringBuilder()
        while (line3 != null) {
            all3.append(line3 + "\n")
            line3 = br3.readLine()
        }
        br3.close()
        file3.close()
        valornotificacion = all3.toString()





        val file4 = InputStreamReader(openFileInput("tipo_file"))
        val br4 = BufferedReader(file4)
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





        val file5 = InputStreamReader(openFileInput("ring_tone_file"))
        val br5 = BufferedReader(file5)
        var line5 = br5.readLine()
        val all5 = StringBuilder()
        while (line5 != null) {
            all5.append(line5 + "\n")
            line5 = br5.readLine()
        }
        br5.close()
        file5.close()
        valorringtone = all5.toString()

        val alarmSoundper = Uri.parse("$valorringtone")



        //Log.v(TAG_REGISTER, " daTOS : $all")


        /*
        val datos_memory: String? = all.toString()
        val parametro1: String = datos_memory!!.split(",")[0]
        val parametro2: String = datos_memory!!.split(",")[1]
        val parametro3: String = datos_memory!!.split(",")[2]
        val parametro4: String = datos_memory!!.split(",")[3]
        val parametro5: String = datos_memory!!.split(",")[4]
        val parametro6: String = datos_memory!!.split(",")[5]


        Log.v(TAG_REGISTER, " daTOS : $parametro1")
        Log.v(TAG_REGISTER, " daTOS : $parametro2")
        Log.v(TAG_REGISTER, " daTOS : $parametro3")
        Log.v(TAG_REGISTER, " daTOS : $parametro4")
        Log.v(TAG_REGISTER, " daTOS : $parametro5")
        */

       // var tres: String = (valorsonido!!.length).toString()

        //Log.v(TAG_REGISTER, tres)
        Log.v(TAG_REGISTER, " Sonido : $all3")

        Log.v(TAG_REGISTER, " Valor pi :$valorringtone")



        if (valornotificacion!!.length > 4){
            enviarnotificaciondatossismos(remoteMessage.getData().get("cuerpo"))

        }
        else{
            norecibir()
        }
        //et1.setText(all)

              //  Log.v(TAG_REGISTER, " daTOS : $all")

            //    ver2(all)

     //   }


      /*

        val `in` = openFileInput("datos_configuracion")
        val inputStreamReader = InputStreamReader(`in`)
        val bufferedReader = BufferedReader(inputStreamReader)
        val sb = StringBuilder()
        var line: String
        line = bufferedReader.readLine()
        while ((line) != null) {
            sb.append(line)

        }
        Log.v(TAG_REGISTER, " daTOS : $line")
*/

/*
        var text = ""
        var reader: BufferedReader? = null

        try {
            reader = BufferedReader(InputStreamReader(assets.open("datos_configuracion")))
            text = reader.readLines().joinToString("\n")
        } catch (e: IOException) {
          // Toast.makeText(applicationContext, "Error reading license file!", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        } finally {
            try {
                reader?.close()
            } catch (e: IOException) {
                //log the exception
                e.printStackTrace()
            }

            Log.v(TAG_REGISTER, " daTOS : $text")

            // textView.text = text
        }
*/

/*
        try {
            val fileInputStream = openFileInput("datos_configuracion")
            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            val stringBuffer = StringBuffer()
            try {
                Message = bufferedReader.readLine()

                while (Message != null) {
                    stringBuffer.append(Message)
                }
                valorguardadoenmemoria = stringBuffer.toString()
                val st = StringTokenizer(stringBuffer.toString(), ",")

                novibrar_val = st.nextToken()
                vibrar_val = st.nextToken()
                sonidoalarma_val = st.nextToken()
                sonidonotificacion_val = st.nextToken()
                sonidopersonalizado_val = st.nextToken()
                sonidovalorelegido_val = st.nextToken()

                // vervalor(novibrar_val, vibrar_val, sonidoalarma_val, sonidonotificacion_val, sonidopersonalizado_val, sonidovalorelegido_val);
            } catch (e: IOException) {
                e.printStackTrace()
            }

        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
*/




    }

    private fun enviarnotificaciondatossismos(remoteMessage: String?) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val longitud: String = remoteMessage!!.split("&")[0]
            val latitud: String = remoteMessage!!.split("&")[1]
            val epicentro: String = remoteMessage!!.split("&")[2]
            val profundo: String = remoteMessage!!.split("&")[3]
            val referencia: String = remoteMessage!!.split("&")[4]
            val magnitud: String = remoteMessage!!.split("&")[5]
            val publicar: String = remoteMessage!!.split("&")[6]
            val intensidad: String = remoteMessage!!.split("&")[7]
            val hora_local: String = remoteMessage!!.split("&")[8]
            val fecha: String = remoteMessage!!.split("&")[9]

            val defaultSoundUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.beep2)

            //val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
            val alarmSound = Uri.parse("content://media/internal/audio/media/38")


            //  .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))

            val intent = Intent(this, Ultimosismo3::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("NOTIFICACIONDATA", remoteMessage)
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
            val channelId = getString(R.string.default_notification_channel_id_sismos)
            val channelName = getString(R.string.default_notification_channel_name_sismos)


            if (valorsonido!!.length > 4){

                if(valorentero <= 6){
                   val sonido_notificacion_android = Uri.parse("android.resource://" + packageName + "/" + R.raw.alarmasonido)
                   var notificationBuilder = NotificationCompat.Builder(this, channelId)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("SISMOS PERÚ IGP")
                            .setContentText(" $longitud  $latitud | $epicentro ")
                            .setAutoCancel(true)
                            .setSound(sonido_notificacion_android)
                            .setContentIntent(pendingIntent)
                    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
                        notificationManager.createNotificationChannel(channel)
                    }
                    val random = Random()
                    notificationManager.notify(random.nextInt(100), notificationBuilder.build())
                 }

                else if(valorentero <= 7){
                    val sonido_notificacion_android = Uri.parse("android.resource://" + packageName + "/" + R.raw.beep2)
                    var notificationBuilder = NotificationCompat.Builder(this, channelId)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("SISMOS PERÚ IGP")
                            .setContentText(" $longitud  $latitud | $epicentro ")
                            .setAutoCancel(true)
                            .setSound(sonido_notificacion_android)
                            .setContentIntent(pendingIntent)
                    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
                        notificationManager.createNotificationChannel(channel)
                    }
                    val random = Random()
                    notificationManager.notify(random.nextInt(100), notificationBuilder.build())
                }

                else{

                    val sonido_notificacion_android  = Uri.parse(valorringtone)

                    var notificationBuilder = NotificationCompat.Builder(this, channelId)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("SISMOS PERÚ IGP")
                            .setContentText(" $longitud  $latitud | $epicentro ")
                            .setAutoCancel(true)
                            .setSound(sonido_notificacion_android)
                            .setContentIntent(pendingIntent)
                    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
                        notificationManager.createNotificationChannel(channel)
                    }
                    val random = Random()
                    notificationManager.notify(random.nextInt(100), notificationBuilder.build())
                }

            }


            else {
                 var notificationBuilder = NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("SISMOS PERÚ IGP")
                        .setContentText(" $longitud  $latitud | $epicentro ")
                        .setAutoCancel(true)
                         .setVibrate(longArrayOf(50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100))
                        .setContentIntent(pendingIntent)
                val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
                    notificationManager.createNotificationChannel(channel)
                }
                val random = Random()
                notificationManager.notify(random.nextInt(100), notificationBuilder.build())
            }




/*
            val notificationBuilder = NotificationCompat.Builder(this, channelId)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("SISMOS PERÚ IGP")
                    .setContentText(" $longitud  $latitud | $epicentro ")
                    .setAutoCancel(true)
                    .setSound(alarmSound)
                    .setContentIntent(pendingIntent)
*/

        }





        else {

            val longitud: String = remoteMessage!!.split("&")[0]
            val latitud: String = remoteMessage!!.split("&")[1]
            val epicentro: String = remoteMessage!!.split("&")[2]
            val profundo: String = remoteMessage!!.split("&")[3]
            val referencia: String = remoteMessage!!.split("&")[4]
            val magnitud: String = remoteMessage!!.split("&")[5]
            val publicar: String = remoteMessage!!.split("&")[6]
            val intensidad: String = remoteMessage!!.split("&")[7]
            val hora_local: String = remoteMessage!!.split("&")[8]
            val fecha: String = remoteMessage!!.split("&")[9]

            val defaultSoundUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.beep2)


            val alarmSound = Uri.parse("content://media/internal/audio/media/38")


            val intent = Intent(this, Ultimosismo3::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("NOTIFICACIONDATA", remoteMessage);
            val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                    PendingIntent.FLAG_ONE_SHOT)

            // val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

            if (valorsonido!!.length > 4) {

                if(valorentero == 6){
                    val sonido_notificacion_android = Uri.parse("android.resource://" + packageName + "/" + R.raw.alarmasonido)

                    val notificationBuilder = NotificationCompat.Builder(this)
                            .setContentTitle("SISMOS PERÚ IGP")
                            .setContentText(" $longitud  $latitud | $epicentro ")
                            .setAutoCancel(true)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setSound(sonido_notificacion_android)
                            .setContentIntent(pendingIntent)
                    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    val random = Random()
                    notificationManager.notify(random.nextInt(100), notificationBuilder.build())
                }


                else if(valorentero == 7){
                    val sonido_notificacion_android = Uri.parse("android.resource://" + packageName + "/" + R.raw.beep2)

                    val notificationBuilder = NotificationCompat.Builder(this)
                            .setContentTitle("SISMOS PERÚ IGP")
                            .setContentText(" $longitud  $latitud | $epicentro ")
                            .setAutoCancel(true)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setSound(sonido_notificacion_android)
                            .setContentIntent(pendingIntent)
                    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    val random = Random()
                    notificationManager.notify(random.nextInt(100), notificationBuilder.build())
                }

                else{
                   // val sonido_notificacion_android  = Uri.parse(valorringtone)

                    var stingh: String = valorringtone.toString()

                    //var stingh2: String = "38"


                  //  val de:Int = stingh.length

                    val lastChar = stingh.substring(0,2)


                   //val lastCharr= StringUtils.substring("string", 0, -1);

                    val lastCharrw = stingh.substring(0, (stingh.length - 1))


                    Log.v(TAG_REGISTER, " Valor pux :$lastChar")


                    //val sonido_notificacion_android  = Uri.parse("content://media/internal/audio/media/$lastCharrw")

                    val sonido_notificacion_android  = Uri.parse(lastCharrw)

                    val notificationBuilder = NotificationCompat.Builder(this)
                            .setContentTitle("SISMOS PERÚ IGP")
                            .setContentText(" $longitud  $latitud | $epicentro ")
                            .setAutoCancel(true)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setSound(sonido_notificacion_android)
                            .setContentIntent(pendingIntent)
                    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    val random = Random()
                    notificationManager.notify(random.nextInt(100), notificationBuilder.build())
                }
            }
            else {
                val notificationBuilder = NotificationCompat.Builder(this)
                        .setContentTitle("SISMOS PERÚ IGP")
                        .setContentText(" $longitud  $latitud | $epicentro ")
                        .setAutoCancel(true)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setVibrate(longArrayOf(50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100))
                        .setContentIntent(pendingIntent)
                val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                val random = Random()
                notificationManager.notify(random.nextInt(100), notificationBuilder.build())
            }
        }
    }


    private fun ver2(dato: StringBuilder) {
        Log.v(TAG_REGISTER, " daTOS : $dato")

    }


    private fun norecibir() {
        Log.v(TAG_REGISTER, "ENVIO DE NOTIFICACIONES BLOQUEADO")

    }
}