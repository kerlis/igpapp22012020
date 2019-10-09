package sistemasfireg.igp.org.sismosperu
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.support.v4.app.NotificationCompat
import android.widget.Toast
import com.google.firebase.database.*
import java.util.*

class Serviciorevisionsismosnotificaciones : BroadcastReceiver() {

    var isinserted: Boolean = false
    var isupdated: Boolean = false
    lateinit var myDb: Databasehelper
    lateinit var  fv: SQLiteDatabase
    var ubicacion: String = ""
    var magni: String = ""
    var profundidad: String = ""
    var echadato: String = ""
    var intensidad: String = ""
    var hora: String = ""
    var latitud: String = ""
    var longitud: String = ""
    var epicentro: String = ""
    var simulacro: String = ""
    var fecha: String = ""
    var horadato: String = ""
    var valor1: String = ""
    var valor2: String = ""

    override fun onReceive(context: Context, intent: Intent) {
        myDb = Databasehelper(context)
        fv = myDb.readableDatabase
        //)  val db = this.getWritableDatabase()
        val cmanager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        var info: NetworkInfo? = null
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            info = Objects.requireNonNull<ConnectivityManager>(cmanager).activeNetworkInfo
        }
        if (info != null && info.isConnected) {
            detecta_sismo(context)
        } else {
            //Toast.makeText(this@Ultimosismo4, "Su Equipo ha Bloquedo la Conexión", Toast.LENGTH_LONG).show()
        }

    }

    fun detecta_sismo(context: Context?) {
        val mDatabase: DatabaseReference?
        mDatabase = FirebaseDatabase.getInstance("https://igpsismos2.firebaseio.com/").getReference("messages")
       // mDatabase.keepSynced(true)
        mDatabase.orderByKey().limitToLast(1).addChildEventListener(object : ChildEventListener {

            override fun onChildAdded(dataSnapshot: DataSnapshot?, s: String?) {
                val sreporte: DatSismo? = dataSnapshot?.getValue(DatSismo::class.java)
                guardar_pref(sreporte, context)
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String) {
                val sreporte = dataSnapshot.getValue(DatSismo::class.java)
                guardar_pref(sreporte, context)
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                val sreporte = dataSnapshot.getValue(DatSismo::class.java)
                guardar_pref(sreporte, context)
            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String) {
                val sreporte = dataSnapshot.getValue(DatSismo::class.java)
                guardar_pref(sreporte, context)
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }

    fun guardar_pref(sreporte: DatSismo?, context: Context?) {
        val prefs: SharedPreferences = context!!.getSharedPreferences("ultsismo", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString("incategoria", sreporte?.categoria)
        editor.putString("inreferencia", sreporte?.referencia)
        editor.putString("inmagnitud", sreporte?.magnitud)
        editor.putString("inprof", sreporte?.profundidad)
        editor.putString("infecha", sreporte?.fechautc)
        editor.putString("txintenso", sreporte?.intenso)
        editor.putString("inhora", sreporte?.horautc)
        editor.putString("inlat", sreporte?.lat)
        editor.putString("inlong", sreporte?.lon)
        editor.putString("inepic", sreporte?.epicentro)
        editor.putString("insim", sreporte?.simulacro)
        editor.apply()
        mostrar_ult_sismo(context)
    }

    fun mostrar_ult_sismo(context: Context) {
        val prefs = context.getSharedPreferences("ultsismo", Context.MODE_PRIVATE)
        ubicacion = prefs.getString("inreferencia", "Aqui")
        magni = prefs.getString("inmagnitud", "")
        profundidad = prefs.getString("inprof", "")
        intensidad = prefs.getString("txintenso", "")
        hora = prefs.getString("inhora", "")
        latitud = prefs.getString("inlat", "0")
        longitud = prefs.getString("inlong", "0")
        epicentro = prefs.getString("inepic", "")
        simulacro = prefs.getString("insim", "")
        fecha = prefs.getString("infecha", "")
        horadato = prefs.getString("inhora", "0")
        figurarnotificacion(context, "totulo", "mensaje",fecha, hora, magni, ubicacion)
    }

    fun figurarnotificacion(context: Context?, title: String, message: String, fechautc: String, horautc: String, magnitud: String, ubicacion: String) {

/*


        val notificationintent = Intent(context, Ultimosismo4::class.java)
        val stackbuilder = TaskStackBuilder.create(context)
        stackbuilder.addParentStack(Ultimosismo4::class.java)
        stackbuilder.addNextIntent(notificationintent)
        val defaultSoundUri = Uri.parse("android.resource://" + context!!.packageName + "/" + R.raw.beep2)
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
        val notificationManager = context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        //notificationManager.notify(5, notification)

        val random = Random()
        notificationManager.notify(random.nextInt(100), notification)





         //     isinserted  = myDb.insertData("1",fechautc,horautc,magnitud)
         //     if (isinserted == true){
        //          Toast.makeText(context, "valores ingrresados $fechautc  , $horautc ,  $magnitud ", Toast.LENGTH_SHORT).show()
        //      }

        */




        var cursor: Cursor = myDb.getcontact("1", fv)
        if (cursor.moveToLast()){
            valor1 = cursor.getString(1)
            valor2 = cursor.getString(2)
        }
      //  Toast.makeText(context, "valores buscados $valor1  , $valor2 ", Toast.LENGTH_SHORT).show()


        // LOS DATOS SON IGUALES Y NO HAY NADA POR ACTUALIZAR
        if((valor1 == fechautc) && (valor2 == horautc)){
          //  Toast.makeText(context, "nada por actualizar", Toast.LENGTH_SHORT).show()


            val notificationintent = Intent(context, Ultimosismo4::class.java)
            val stackbuilder = TaskStackBuilder.create(context)
            stackbuilder.addParentStack(Ultimosismo4::class.java)
            stackbuilder.addNextIntent(notificationintent)
            val defaultSoundUri = Uri.parse("android.resource://" + context!!.packageName + "/" + R.raw.beep2)
            val pendingintent = stackbuilder.getPendingIntent(100, PendingIntent.FLAG_ONE_SHOT)
            val b =  NotificationCompat.Builder(context)
            val notification = b.setContentTitle("Sismo")
                    .setContentTitle("Sismo Perú   - Mag: $magnitud")
                    .setContentText("$ubicacion")
                    .setSubText("$fechautc     $horautc")
                    .setColor(Color.parseColor("#001665"))
                    .setAutoCancel(true)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingintent).build()
            val rand = Random()
            val codigo = rand.nextInt(100)
            val notificationManager = context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            //notificationManager.notify(5, notification)
            val random = Random()
            notificationManager.notify(random.nextInt(100), notification)


/*
            val notificationintent = Intent(context, Ultimosismo4::class.java)
            val stackbuilder = TaskStackBuilder.create(context)
            stackbuilder.addParentStack(Ultimosismo4::class.java)
            stackbuilder.addNextIntent(notificationintent)
            val defaultSoundUri = Uri.parse("android.resource://" + context!!.packageName + "/" + R.raw.beep2)
            val pendingintent = stackbuilder.getPendingIntent(100, PendingIntent.FLAG_ONE_SHOT)
            val b =  NotificationCompat.Builder(context)
            val notification = b.setContentTitle("Sismo")
                    .setContentTitle("Sismo Perú   - Mag: $magnitud")
                    .setContentText("$ubicacion")
                    .setSubText("$fechautc     $horautc")
                    .setColor(Color.parseColor("#001665"))
                    .setAutoCancel(true)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingintent).build()
            val rand = Random()
            val codigo = rand.nextInt(100)
            val notificationManager = context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            //notificationManager.notify(5, notification)
            val random = Random()
            notificationManager.notify(random.nextInt(100), notification)
*/

        }

        else if((valor1.length <3) && (valor2.length<3)){
            isinserted  = myDb.insertData(fechautc,horautc,magnitud)
            if (isinserted == true){
              //  Toast.makeText(context, "valores ingresados $fechautc  , $horautc ,  $magnitud ", Toast.LENGTH_SHORT).show()
            }


            val notificationintent = Intent(context, Ultimosismo4::class.java)
            val stackbuilder = TaskStackBuilder.create(context)
            stackbuilder.addParentStack(Ultimosismo4::class.java)
            stackbuilder.addNextIntent(notificationintent)
            val defaultSoundUri = Uri.parse("android.resource://" + context!!.packageName + "/" + R.raw.beep2)
            val pendingintent = stackbuilder.getPendingIntent(100, PendingIntent.FLAG_ONE_SHOT)
            val b =  NotificationCompat.Builder(context)
            val notification = b.setContentTitle("Sismo")
                    .setContentTitle("Sismo Perú   - Mag: $magnitud")
                    .setContentText("$ubicacion")
                    .setSubText("$fechautc     $horautc")
                    .setColor(Color.parseColor("#001665"))
                    .setAutoCancel(true)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingintent).build()
            val rand = Random()
            val codigo = rand.nextInt(100)
            val notificationManager = context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            //notificationManager.notify(5, notification)
            val random = Random()
            notificationManager.notify(random.nextInt(100), notification)


        }

        //LOS DATOS NO SON IGUALES Y SE DEBEN DE ACTUALIZAR LOS DATOS
        else if((valor1 != fechautc) && (valor2 != horautc)) {

            isupdated  = myDb.updateDataRow("1", fechautc, horautc, magnitud)
            if (isupdated == true){
               // Toast.makeText(context, "valores actualizados $fechautc  , $horautc ,  $magnitud ", Toast.LENGTH_SHORT).show()
            }

            val notificationintent = Intent(context, Ultimosismo4::class.java)
            val stackbuilder = TaskStackBuilder.create(context)
            stackbuilder.addParentStack(Ultimosismo4::class.java)
            stackbuilder.addNextIntent(notificationintent)
            val defaultSoundUri = Uri.parse("android.resource://" + context!!.packageName + "/" + R.raw.beep2)
            val pendingintent = stackbuilder.getPendingIntent(100, PendingIntent.FLAG_ONE_SHOT)
            val b =  NotificationCompat.Builder(context)
            val notification = b.setContentTitle("Sismo")
                    .setContentTitle("Sismo Perú   - Mag: $magnitud")
                    .setContentText("$ubicacion")
                    .setSubText("$fechautc     $horautc")
                    .setColor(Color.parseColor("#001665"))
                    .setAutoCancel(true)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingintent).build()
            val rand = Random()
            val codigo = rand.nextInt(100)
            val notificationManager = context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            //notificationManager.notify(5, notification)
            val random = Random()
            notificationManager.notify(random.nextInt(100), notification)
        }

        //NO EXISTEN DATA POR LO CUAL SE DEBE DE INGRESAR DATOS NUEVOS
        else{
            Toast.makeText(context, "no se hizo nada", Toast.LENGTH_SHORT).show()
        }

    }

}