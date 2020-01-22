package sistemasfireg.igp.org.sismosperu;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SplashScreen extends Activity {
    String elvalor = "a";
    String elvalor2 = "bb";
    String Message;
    String ko,ajustes,tipo;
    Integer r,s;
    private static DatabaseReference mFirebaseDatabase;
    private static FirebaseDatabase database;
    TextView lema;
    String valorguardadoenmemoriaa, valorguardadoenmemoriab, valorguardadoenmemoriac;


    Integer valorguardadoenmemoriaa_int;
    Integer valorguardadoenmemoriab_int;
    Integer valorguardadoenmemoriac_int;

    String TAG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(database == null) {
            database = FirebaseDatabase.getInstance();
            Firebase.getDefaultConfig().setPersistenceEnabled(true);
            database.setPersistenceEnabled(true);
        }


        mFirebaseDatabase = database.getReference("message");
       // FirebaseMessaging.getInstance().subscribeToTopic("SISMOSANDROIDDOS");
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
        mFirebaseDatabase.keepSynced(true);
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        lema = (TextView) findViewById(R.id.lema);
        lema.setText(Html.fromHtml("<b>CIENCIA PARA PROTEGERNOS <b>"+"<br>"+"<b>CIENCIA PARA AVANZAR</b>"));


        try {
            FileInputStream fileInputStream =  openFileInput("vibrar_file");
            InputStreamReader inputStreamReader =  new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer =  new StringBuffer();
            try {
                while ((Message = bufferedReader.readLine())!=null)
                {
                    stringBuffer.append(Message);
                }
                valorguardadoenmemoriaa = stringBuffer.toString();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    //    valorguardadoenmemoriaa_int = valorguardadoenmemoriaa.length();

        Log.d(TAG, "VALOR EVALUADO: " + valorguardadoenmemoriaa  );


        if (valorguardadoenmemoriaa == null){
            valoresconfiguracion();

        }
        else{

        }





        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                   // Intent intent7 = new Intent(SplashScreen.this,Ultimosismo4.class);


                    Intent intent7 = new Intent(SplashScreen.this,Alarmatemprana.class);

                    // Intent intent7 = new Intent(SplashScreen.this,Getlocationandmore.class);
                    startActivity(intent7);
                }
            }
        };
        timerThread.start();




    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }




    private void valoresconfiguracion() {
        String file_vibrar = "vibrar_file";
        String file_sonido = "sonido_file";
        String file_notificar = "notificar_file";
        String file_tipo = "tipo_file";
        String file_ringtone = "ring_tone_file";

        String file_nombre_ringtone = "nombre_ringtone__file";



        String vibrar_val =  "11111";

        String sonido_val =  "0";

        String notificacion_val =  "11111";

        String ringtone_val =  "A";

        String tiposonidoval =  "11111";

        String nombre_ringtone_val =  "A";

        try {
            FileOutputStream fileOutputStream_vibrar = openFileOutput(file_vibrar, MODE_PRIVATE);
            fileOutputStream_vibrar.write(vibrar_val.getBytes());

            FileOutputStream fileOutputStream_sonido = openFileOutput(file_sonido, MODE_PRIVATE);
            fileOutputStream_sonido.write(sonido_val.getBytes());

            FileOutputStream fileOutputStream_notificar = openFileOutput(file_notificar, MODE_PRIVATE);
            fileOutputStream_notificar.write(notificacion_val.getBytes());

            FileOutputStream fileOutputStream_sonidotipo = openFileOutput(file_tipo, MODE_PRIVATE);
            fileOutputStream_sonidotipo.write(tiposonidoval.getBytes());

            FileOutputStream fileOutputStream_ring_tone = openFileOutput(file_ringtone, MODE_PRIVATE);
            fileOutputStream_ring_tone.write(ringtone_val.getBytes());

            FileOutputStream fileOutputStream_nombre_ring_tone = openFileOutput(file_nombre_ringtone, MODE_PRIVATE);
            fileOutputStream_nombre_ring_tone.write(nombre_ringtone_val.getBytes());

            fileOutputStream_nombre_ring_tone.close();


            fileOutputStream_ring_tone.close();
            fileOutputStream_sonidotipo.close();
            fileOutputStream_notificar.close();
            fileOutputStream_vibrar.close();
            fileOutputStream_sonido.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}