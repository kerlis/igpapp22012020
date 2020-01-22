package sistemasfireg.igp.org.sismosperu

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RelativeLayout

class Alarmatemprana : AppCompatActivity() {

    lateinit var alarmapantalla: RelativeLayout;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarmatemprana)

        alarmapantalla = findViewById(R.id.pantalla)

        val sb = StringBuilder()

        val n = 97

        for (j in 0 until n) {

            sb.append("*")

            var d = j;

            Log.d("cscsdc", d.toString())

             if(d % 2 == 0){
                alarmapantalla.setBackgroundColor(Color.RED)
             }
             else{
                 alarmapantalla.setBackgroundColor(Color.GREEN)
             }

        }


    }
}
