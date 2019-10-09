package sistemasfireg.igp.org.sismosperu;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Getlocationandmore extends AppCompatActivity {


    String TAG_REGISTER;

    private OkHttpClient client = new OkHttpClient();
    private static final String BASE_URL = "http://arteypixel.com/enviarnotificacionesdataandroidios/enviarrectificacion.php";



    String vibrar_val =  "0";
    String sonido_val =  "11111";


    String no =  "0";
    String si =  "11111";


    String sonido_alarma =  "44444";
    String sonido_notificacion =  "555555";
    String sonido_personalizado =  "6666666";

    String indice_sonido_personalizado =  "11111";




    String file_vibrar = "vibrar_file";
    String file_notificar = "notificar_file";
    String file_tipo = "tipo_file";



    Button btnShowLocation, picksound;
    GPSTracker gps;
    Uri ringtone;

    RadioGroup rg;

    String longitudcmp1 = "-12.8120534";
    String longitudcmp2 = "-76.5416381";
    RelativeLayout bloquesonido;


    String novibrar;
    String vibrar;

    String sonidoalarma;
    String sonidonotificacion;
    String sonidopersonalizado;
    String sonidovalorelegido;




    String novibrar_val;


    String sonidoalarma_val;
    String sonidonotificacion_val;
    String sonidopersonalizado_val;
    String sonidovalorelegido_val;



    String Message;
    String valorguardadoenmemoriaa, valorguardadoenmemoriab, valorguardadoenmemoriac, valorguardadoenmemoriad,valorguardadoenmemoriae,valorguardadoenmemoriaf;

    Button vervalores;



    Integer valorguardadoenmemoriaa_int;
    Integer valorguardadoenmemoriab_int;
    Integer valorguardadoenmemoriac_int;

    // https://www.google.com/maps/@-12.8120534,-76.5416381,13z

    RadioButton nonotificar;
    RadioButton notificar;


    RadioButton vibrars_notificacion;
    RadioButton sonidos_notificacion;

    RadioButton alarma_notificacion;
    RadioButton notificar_notificacion;
    RadioButton sonidopersonalizado_notificacion;

    Integer valorentero;

    String valortipo;


    Button probar;

    TextView ringtoneelegido;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_location);

        ringtoneelegido = findViewById(R.id.ringtoneelegido);


        probar = findViewById(R.id.probar);

        bloquesonido = findViewById(R.id.bloquesonido);
        picksound = (Button) findViewById(R.id.picksound);
        picksound.setVisibility(View.GONE);

        picksound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                pickRingtone();

            }
        });


        probar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                enviarnotificaciondeprueba();

            }
        });

      //  valoresconfiguracion();



        vervalores =  findViewById(R.id.vervaloresdeconfiguracion);
        nonotificar = findViewById(R.id.nonotificar);
        notificar = findViewById(R.id.notificar);


        nonotificar = findViewById(R.id.nonotificar);
        notificar = findViewById(R.id.notificar);

        vibrars_notificacion = findViewById(R.id.vibrar);
        sonidos_notificacion = findViewById(R.id.novibrar);


        alarma_notificacion = findViewById(R.id.alarma);
        notificar_notificacion = findViewById(R.id.sonidonotificar);
        sonidopersonalizado_notificacion = findViewById(R.id.sonidopersonalizado);

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


        try {
            FileInputStream fileInputStreama =  openFileInput("notificar_file");
            InputStreamReader inputStreamReadera =  new InputStreamReader(fileInputStreama);
            BufferedReader bufferedReadera = new BufferedReader(inputStreamReadera);
            StringBuffer stringBuffera =  new StringBuffer();
            try {
                while ((Message = bufferedReadera.readLine())!=null)
                {
                    stringBuffera.append(Message);
                }
                valorguardadoenmemoriab = stringBuffera.toString();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        try {
            FileInputStream fileInputStreamc =  openFileInput("tipo_file");
            InputStreamReader inputStreamReaderc =  new InputStreamReader(fileInputStreamc);
            BufferedReader bufferedReaderc = new BufferedReader(inputStreamReaderc);
            StringBuffer stringBufferc =  new StringBuffer();
            try {
                while ((Message = bufferedReaderc.readLine())!=null)
                {
                    stringBufferc.append(Message);
                }
                valorguardadoenmemoriac = stringBufferc.toString();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        try {
            FileInputStream fileInputStreamd =  openFileInput("ring_tone_file");
            InputStreamReader inputStreamReaderd =  new InputStreamReader(fileInputStreamd);
            BufferedReader bufferedReaderd = new BufferedReader(inputStreamReaderd);
            StringBuffer stringBufferd =  new StringBuffer();
            try {
                while ((Message = bufferedReaderd.readLine())!=null)
                {
                    stringBufferd.append(Message);
                }
                valorguardadoenmemoriad = stringBufferd.toString();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        try {
            FileInputStream fileInputStreame =  openFileInput("eltoken");
            InputStreamReader inputStreamReadere =  new InputStreamReader(fileInputStreame);
            BufferedReader bufferedReadere = new BufferedReader(inputStreamReadere);
            StringBuffer stringBuffere =  new StringBuffer();
            try {
                while ((Message = bufferedReadere.readLine())!=null)
                {
                    stringBuffere.append(Message);
                }
                valorguardadoenmemoriae = stringBuffere.toString();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



        try {
            FileInputStream fileInputStreamf =  openFileInput("nombre_ringtone__file");
            InputStreamReader inputStreamReaderf =  new InputStreamReader(fileInputStreamf);
            BufferedReader bufferedReaderf = new BufferedReader(inputStreamReaderf);
            StringBuffer stringBufferf =  new StringBuffer();
            try {
                while ((Message = bufferedReaderf.readLine())!=null)
                {
                    stringBufferf.append(Message);
                }
                valorguardadoenmemoriaf = stringBufferf.toString();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }




        ringtoneelegido.setText(valorguardadoenmemoriaf);
        //valortipo = valorguardadoenmemoriac;

        //valorentero = valorguardadoenmemoriac.length();


        valorguardadoenmemoriaa_int = valorguardadoenmemoriaa.length();
        valorguardadoenmemoriab_int = valorguardadoenmemoriab.length();
        valorguardadoenmemoriac_int = valorguardadoenmemoriac.length();


        if (valorguardadoenmemoriab_int > 4){
            notificar.setChecked(true);
            nonotificar.setChecked(false);

        }
        else{
            notificar.setChecked(false);
            nonotificar.setChecked(true);
        }



        if (valorguardadoenmemoriaa_int > 4){
            vibrars_notificacion.setChecked(false);
            sonidos_notificacion.setChecked(true);

        }
        else{
            vibrars_notificacion.setChecked(true);
            sonidos_notificacion.setChecked(false);
        }






        if (valorguardadoenmemoriac_int == 5){
            alarma_notificacion.setChecked(true);
            notificar_notificacion.setChecked(false);
            sonidopersonalizado_notificacion.setChecked(false);

        }
        else if(valorguardadoenmemoriac_int == 6){
            alarma_notificacion.setChecked(false);
            notificar_notificacion.setChecked(true);
            sonidopersonalizado_notificacion.setChecked(false);
        }
        else if(valorguardadoenmemoriac_int == 7){
            alarma_notificacion.setChecked(false);
            notificar_notificacion.setChecked(false);
            sonidopersonalizado_notificacion.setChecked(true);
            picksound.setVisibility(View.VISIBLE);


        }
        else{
            alarma_notificacion.setChecked(false);
            notificar_notificacion.setChecked(false);
            sonidopersonalizado_notificacion.setChecked(true);
            picksound.setVisibility(View.VISIBLE);

        }






        vervalores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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


                try {
                    FileInputStream fileInputStreama =  openFileInput("notificar_file");
                    InputStreamReader inputStreamReadera =  new InputStreamReader(fileInputStreama);
                    BufferedReader bufferedReadera = new BufferedReader(inputStreamReadera);
                    StringBuffer stringBuffera =  new StringBuffer();
                    try {
                        while ((Message = bufferedReadera.readLine())!=null)
                        {
                            stringBuffera.append(Message);
                        }
                        valorguardadoenmemoriab = stringBuffera.toString();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }


                try {
                    FileInputStream fileInputStreamc =  openFileInput("tipo_file");
                    InputStreamReader inputStreamReaderc =  new InputStreamReader(fileInputStreamc);
                    BufferedReader bufferedReaderc = new BufferedReader(inputStreamReaderc);
                    StringBuffer stringBufferc =  new StringBuffer();
                    try {
                        while ((Message = bufferedReaderc.readLine())!=null)
                        {
                            stringBufferc.append(Message);
                        }
                        valorguardadoenmemoriac = stringBufferc.toString();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }





                try {
                    FileInputStream fileInputStreamd =  openFileInput("ring_tone_file");
                    InputStreamReader inputStreamReaderd =  new InputStreamReader(fileInputStreamd);
                    BufferedReader bufferedReaderd = new BufferedReader(inputStreamReaderd);
                    StringBuffer stringBufferd =  new StringBuffer();
                    try {
                        while ((Message = bufferedReaderd.readLine())!=null)
                        {
                            stringBufferd.append(Message);
                        }
                        valorguardadoenmemoriad = stringBufferd.toString();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }





                try {
                    FileInputStream fileInputStreame =  openFileInput("eltoken");
                    InputStreamReader inputStreamReadere =  new InputStreamReader(fileInputStreame);
                    BufferedReader bufferedReadere = new BufferedReader(inputStreamReadere);
                    StringBuffer stringBuffere =  new StringBuffer();
                    try {
                        while ((Message = bufferedReadere.readLine())!=null)
                        {
                            stringBuffere.append(Message);
                        }
                        valorguardadoenmemoriae = stringBuffere.toString();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }







                valorguardadoenmemoriaa_int = valorguardadoenmemoriaa.length();
                  valorguardadoenmemoriab_int = valorguardadoenmemoriaa.length();
                  valorguardadoenmemoriac_int = valorguardadoenmemoriaa.length();

/*
                if (valorguardadoenmemoriaa_int > 4){
                    notificar.setChecked(true);
                    nonotificar.setChecked(false);

                }
                else{
                    notificar.setChecked(false);
                    nonotificar.setChecked(true);
                }

*/
                String numerocaracteres = String.valueOf(valorguardadoenmemoriac.length());


                vervalorguardados(valorguardadoenmemoriaa,valorguardadoenmemoriab,valorguardadoenmemoriac,numerocaracteres,valorguardadoenmemoriad,valorguardadoenmemoriae);

            }
        });




        RadioGroup rgnoti = findViewById(R.id.grupo01);
        rgnoti.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.nonotificar:
                        try {
                            FileOutputStream fileOutputStream_notificar = openFileOutput(file_notificar, MODE_PRIVATE);
                            fileOutputStream_notificar.write(no.getBytes());
                            fileOutputStream_notificar.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                       // bloquesonido.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "No notificar", Toast.LENGTH_LONG).show();
                        break;

                    case R.id.notificar:
                        try {
                            FileOutputStream fileOutputStream_notificar = openFileOutput(file_notificar, MODE_PRIVATE);
                            fileOutputStream_notificar.write(si.getBytes());
                            fileOutputStream_notificar.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                       // bloquesonido.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(), "Notificar", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });

        RadioGroup rg = findViewById(R.id.grupo1);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){

                    case R.id.vibrar:

                        try {
                            FileOutputStream fileOutputStream_vibrar = openFileOutput(file_vibrar, MODE_PRIVATE);
                            fileOutputStream_vibrar.write(no.getBytes());
                            fileOutputStream_vibrar.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        bloquesonido.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "vibrar", Toast.LENGTH_LONG).show();
                        break;

                    case R.id.novibrar:

                        try {
                            FileOutputStream fileOutputStream_vibrar = openFileOutput(file_vibrar, MODE_PRIVATE);
                            fileOutputStream_vibrar.write(si.getBytes());
                            fileOutputStream_vibrar.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        bloquesonido.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(), "no vibrar", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });

        RadioGroup rg2 = findViewById(R.id.grupo2);
        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.alarma:
                        picksound.setVisibility(View.GONE);
                        try {
                            FileOutputStream fileOutputStream_tipo = openFileOutput(file_tipo, MODE_PRIVATE);
                            fileOutputStream_tipo.write(sonido_alarma.getBytes());
                            fileOutputStream_tipo.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        bloquesonido.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(), "sonido alarma", Toast.LENGTH_LONG).show();
                        break;

                    case R.id.sonidonotificar:
                        picksound.setVisibility(View.GONE);
                        try {
                            FileOutputStream fileOutputStream_tipo = openFileOutput(file_tipo, MODE_PRIVATE);
                            fileOutputStream_tipo.write(sonido_notificacion.getBytes());
                            fileOutputStream_tipo.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        bloquesonido.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(), "sonido notificacion", Toast.LENGTH_LONG).show();
                        break;

                    case R.id.sonidopersonalizado:
                        picksound.setVisibility(View.VISIBLE);
                        pickRingtone();
                        try {
                            FileOutputStream fileOutputStream_tipo = openFileOutput(file_tipo, MODE_PRIVATE);
                            fileOutputStream_tipo.write(sonido_personalizado.getBytes());
                            fileOutputStream_tipo.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        bloquesonido.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(), "sonido personalizado", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });



    }

/*

        btnShowLocation = (Button) findViewById(R.id.btnShowLocation);

        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // create class object
                gps = new GPSTracker(Getlocationandmore.this);
                // check if GPS enabled
                if(gps.canGetLocation()){
                    double longitude = gps.getLongitude();
                    double latitude = gps.getLatitude();
                    getAddress(latitude, longitude);
                }

                else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }

            }
        });
        */
   // }


    // public void pickRingtone(View view) {


    public void vervalorguardados(String valor1, String valor2, String valor3, String valor4, String valor5, String valor6){
       //Toast.makeText(getApplicationContext(),  valor1  + " - " + valor2 + " - " + valor3 + "Nro: " + valor4 + "ringtone: " + valor5,  Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(),"-"+valor6+"-",  Toast.LENGTH_LONG).show();

    }

    /*
    public void vervalor(String valor1, String valor2, String valor3, String valor4, String valor5, String valor6){
         Toast.makeText(getApplicationContext(),  valor1 + "-" + valor2 + "-" + valor3 + "-" + valor4 + "-" + valor5 + "-" + valor6 + "-"  , Toast.LENGTH_LONG).show();

    }

*/
   public void pickRingtone() {


        // TODO Auto-generated method.   stub
        Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI,  RingtoneManager.TYPE_RINGTONE);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Seleccionar Sonido de Notificación");

      // intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, Uri.parse(strUri));

        // for existing ringtone
        Uri urie2 =     RingtoneManager.getActualDefaultRingtoneUri(getApplicationContext(), RingtoneManager.TYPE_NOTIFICATION);


       //  String ringtone = RingtoneManager.EXTRA_RINGTONE_DEFAULT_URI;
         intent.putExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI, urie2);

       //  Toast.makeText(getApplicationContext(), "Select Ringtone: " +  ringtone , Toast.LENGTH_LONG).show();


      //  Toast.makeText(getApplicationContext(), "Select Ringtone" +  urie , Toast.LENGTH_LONG).show();
        startActivityForResult(intent, 1);






/*
       Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
       intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_NOTIFICATION);
       intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Select Tone");
       intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, (Uri) null);
       this.startActivityForResult(intent, 5);
       */

    }


     protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1:
                    ringtone = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
                    String TITULO = ringtone.getUserInfo();

                    Ringtone ringtone2 = RingtoneManager.getRingtone(this, ringtone);
                    String title = ringtone2.getTitle(this);

                 ///   Integer title2 = ringtone2.


                    // String title = ringtone.



                  //  String str = "India";
                    System.out.println("last char = " + ringtone.toString().charAt(ringtone.toString().length() - 2));

                 //   String eeee = ringtone.toString().charAt(ringtone.toString().length() - 2)


                    String lastChar = ringtone.toString().substring(ringtone.toString().length() - 2);


                    try {
                        FileOutputStream fileOutputStream_ring_tone = openFileOutput("ring_tone_file", MODE_PRIVATE);
                        fileOutputStream_ring_tone.write(ringtone.toString().getBytes());
                        fileOutputStream_ring_tone.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    try {
                        FileOutputStream fileOutputStream_nombre_ring_tone = openFileOutput("nombre_ringtone__file", MODE_PRIVATE);
                        fileOutputStream_nombre_ring_tone.write(title.getBytes());
                        fileOutputStream_nombre_ring_tone.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }



                    ringtoneelegido.setText(title);


                    Toast.makeText(getApplicationContext(), "Selected Ringtone: " + ringtone, Toast.LENGTH_LONG).show();


/*
                    String TITULO = data.


                    Uri uri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
                    Ringtone r=RingtoneManager.getRingtone(this, uri);
                    String ringToneName=r.getTitle(this);
                    Toast.makeText(getApplicationContext(), "Ringtone Name : "+ringToneName,Toast.LENGTH_SHORT).show();
*/

                 //   Toast.makeText(getBaseContext(),RingtoneManager.URI_COLUMN_INDEX,
                    //  Toast.LENGTH_SHORT).show();
                    break;

                default:
                    break;
            }
        }
    }


/*
    private void getAddress(double latitude, double longitude) {


        double distance;
        Location locationA = new Location("point A");
        locationA.setLatitude(latitude);
        locationA.setLongitude(longitude);

        Location locationB = new Location("point B");
        locationB.setLatitude(Double.parseDouble(longitudcmp1));
        locationB.setLongitude(Double.parseDouble(longitudcmp2));

        distance = locationA.distanceTo(locationB)/1000;

        Toast.makeText(getApplicationContext(), "la distancia es de :  " +  distance +  "km", Toast.LENGTH_LONG).show();

      //  Toast.makeText(getApplicationContext(), "latitud  " + String.valueOf(latitude) + " ," +   "longitud:"  + "," + String.valueOf(longitude) , Toast.LENGTH_LONG).show();



        StringBuilder result = new StringBuilder();
        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                result.append(address.getLocality()).append("\n");
                //result.append(address.getPostalCode());
                result.append(address.getAdminArea()).append("\n");
                result.append(address.getCountryName());
                String address1 = addresses.get(0).getAddressLine(0);
                String city = addresses.get(0).getAddressLine(1);
                String country = addresses.get(0).getAddressLine(2);
                Toast.makeText(getApplicationContext(), "tu direccion es   " + address1 + " ," +   city + "," + country , Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }
    }

   */



    private void valoresconfiguracion() {
        String file_vibrar = "vibrar_file";
        String file_sonido = "sonido_file";
        String file_notificar = "notificar_file";
        String file_tipo = "tipo_file";
        String file_ringtone = "ring_tone_file";
        String vibrar_val =  "0";
        String sonido_val =  "0";
        String notificacion_val =  "0";

        String tiposonidoval =  "11111";
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
            fileOutputStream_ring_tone.write(notificacion_val.getBytes());

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

      //  Ringtone ringtone = RingtoneManager.getRingtone(this, uri);
      //  String title = ((Ringtone) ringtone).getTitle(this);


    }


    public void enviarnotificaciondeprueba() {
        try {
            FileInputStream fileInputStreame =  openFileInput("eltoken");
            InputStreamReader inputStreamReadere =  new InputStreamReader(fileInputStreame);
            BufferedReader bufferedReadere = new BufferedReader(inputStreamReadere);
            StringBuffer stringBuffere =  new StringBuffer();
            try {
                while ((Message = bufferedReadere.readLine())!=null)
                {
                    stringBuffere.append(Message);
                }
                valorguardadoenmemoriae = stringBuffere.toString();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        RequestBody body = new FormBody.Builder()
                .add("Token", valorguardadoenmemoriae)
                .build();
        Request request = new Request.Builder().url(BASE_URL).post(body).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("Registration Error" + e.getMessage());

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    final String resp = response.body().string();
                    Log.v(TAG_REGISTER,resp);
                    ver2(resp);
                    if (response.isSuccessful()) {
                    } else {
                    }
                } catch (IOException e) {
                    // Log.e(TAG_REGISTER, "Exception caught: ", e);
                    System.out.println("Exception caught" + e.getMessage());
                }
            }

        });
    }


    private void ver2(final String dato) {
        runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(getApplicationContext(), ""+dato , Toast.LENGTH_LONG).show();
            }
        });
    }




}
