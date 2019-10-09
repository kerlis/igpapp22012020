package sistemasfireg.igp.org.sismosperu;
import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Notificacionesconfig extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    String TAG_REGISTER;
    private OkHttpClient client = new OkHttpClient();
    private static final String BASE_URL = "http://arteypixel.com/enviarnotificacionesdataandroidios/enviarrectificacionvolcanes.php";
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
    // GPSTracker gps;
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
    String valorguardadoenmemoriaa, valorguardadoenmemoriab, valorguardadoenmemoriac, valorguardadoenmemoriad,valorguardadoenmemoriae;
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
    TextView igpdireccion,ovsdireccion;
    TextView igpweb;
    TextView ovsweb;
    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    Toolbar toolbar;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;
    private RelativeLayout mDrawerBlock;
    ImageView sliderz;
    RelativeLayout blocke12;
    RelativeLayout blocke1a;
    RelativeLayout blocke2a;
    RelativeLayout blocke4a;
    RelativeLayout blocke5a;
    RelativeLayout blocke9;
    RelativeLayout blocke6a;
    RelativeLayout blocke92;
    TextView estado;
    String titulo,ko;
    NotificationManager notificationManager;
    Uri  sonido_notificacion_android2;
    String ramdom = "ramdom_number_file";

    @SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacionesconfig);

        Snackbar.make(findViewById(android.R.id.content),"Mantén actualizada la aplicación", Snackbar.LENGTH_LONG)
                .setAction("Play store", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Toast.makeText(getApplicationContext(), "Configurado", Toast.LENGTH_LONG).show();


                        Intent intent = new Intent(Intent.ACTION_VIEW)
                                .setData(Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName()));
                        try {
                            startActivity(new Intent(intent)
                                    .setPackage("com.android.vending"));
                        } catch (android.content.ActivityNotFoundException exception) {
                            startActivity(intent);
                        }
                        //whatever you want to do when "Action" is clicked in the SnackBar
                    }
                }).show();



        estado = (Button) findViewById(R.id.estadodeconexion);


/*
        blocke1a = (RelativeLayout) findViewById(R.id.blocke1);
        blocke2a = (RelativeLayout) findViewById(R.id.blocke2);
        blocke4a = (RelativeLayout) findViewById(R.id.blocke4);
        blocke5a = (RelativeLayout) findViewById(R.id.blocke5);
        blocke6a = (RelativeLayout) findViewById(R.id.blocke6);
        blocke12 = (RelativeLayout) findViewById(R.id.blocke12);
        blocke92 = (RelativeLayout) findViewById(R.id.blocke92);
        blocke9 = (RelativeLayout) findViewById(R.id.blocke9);

*/



        RelativeLayout blocke1a = findViewById(R.id.blocke1);
        RelativeLayout blocke2a = findViewById(R.id.blocke2);
        RelativeLayout blocke3a = findViewById(R.id.blocke3);
        RelativeLayout blocke4a = findViewById(R.id.blocke4);
        RelativeLayout blocke5a = findViewById(R.id.blocke5);
        RelativeLayout blocke6a = findViewById(R.id.blocke6);
        RelativeLayout blocke9 = findViewById(R.id.blocke9);

        RelativeLayout blocke92 = findViewById(R.id.blocke92);



        blocke1a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Notificacionesconfig.this,Ultimosismo4.class);
                startActivity(intent);
            }
        });
        blocke2a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Notificacionesconfig.this,Ultimosismoslist.class);
                startActivity(intent);
            }
        });
        blocke3a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Notificacionesconfig.this,Listadoenmapa.class);
                startActivity(intent);
            }
        });
        blocke4a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Notificacionesconfig.this,Notificacionesconfig.class);
                startActivity(intent);
            }
        });
        blocke5a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Notificacionesconfig.this,Glosarioestatico.class);
                startActivity(intent);
            }
        });
        blocke6a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
        blocke9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Notificacionesconfig.this,Listadoredessociales.class);
                startActivity(intent);
            }
        });


        blocke92.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Notificacionesconfig.this, Contactenos.class);
                startActivity(intent);
            }
        });



/*
        blocke92.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Notificacionesconfig.this,Contactar.class);
                startActivity(intent);
            }
        });



        blocke12.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Notificacionesconfig.this,Ultimasnotificaciones.class);
                startActivity(intent);
            }
        });



        blocke1a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Notificacionesconfig.this,pagedivisor.class);
                startActivity(intent);
            }
        });
        blocke2a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Notificacionesconfig.this,Informacion.class);
                startActivity(intent);
            }
        });
        blocke4a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Notificacionesconfig.this,Notificacionesconfig.class);
                startActivity(intent);
            }
        });
        blocke5a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Notificacionesconfig.this,Convenciones.class);
                startActivity(intent);
            }
        });


        blocke9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Notificacionesconfig.this,Listadoredessociales.class);
                startActivity(intent);
            }
        });

        blocke6a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        */





        sliderz = (ImageView) findViewById(R.id.sliderz);
        mTitle = mDrawerTitle = getTitle();
        mNavigationDrawerItemTitles= getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerBlock = (RelativeLayout) findViewById(R.id.mDrawerBlock);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawerToggle();
        sliderz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });




        igpdireccion = (TextView) findViewById(R.id.igpdireccion);
        ovsdireccion = (TextView) findViewById(R.id.ovsdireccion);
        igpdireccion.setText("Calle Badajoz N° 169 Urb. Mayorazgo IV Etapa,Ate,\n Lima 15012 - Perú\n" +
                "+51 1 3172300");
        ovsdireccion.setText("Urb. La Marina B-19, Cayma, \n Arequipa 04017 - Perú\n" +
                "+51 54 251373");
        igpweb = (TextView) findViewById(R.id.igpweb);
        ovsweb = (TextView) findViewById(R.id.ovsweb);
        igpweb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://portal.igp.gob.pe/"));
                startActivity(intent);
            }
        });
        ovsweb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://ovs.igp.gob.pe/instituto-geofisico-peru"));
                startActivity(intent);
            }
        });




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



        //valortipo = valorguardadoenmemoriac;

        //valorentero = valorguardadoenmemoriac.length();


        valorguardadoenmemoriaa_int = valorguardadoenmemoriaa.length();
        valorguardadoenmemoriab_int = valorguardadoenmemoriab.length();
        valorguardadoenmemoriac_int = valorguardadoenmemoriac.length();


        if (valorguardadoenmemoriab_int > 4){
            notificar.setChecked(true);
            nonotificar.setChecked(false);

            estado.setBackgroundColor(Color.parseColor("#00FF79"));

            // estado.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.colorconectado)));




            estado.setText("conectado");


        }
        else{
            notificar.setChecked(false);
            nonotificar.setChecked(true);


            estado.setBackgroundColor(Color.parseColor("#FF0035"));

            // estado.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.colordesconectado)));

            estado.setText("desconectado");

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

                        estado.setBackgroundColor(Color.parseColor("#FF0035"));


                        /// estado.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.colordesconectado)));

                        estado.setText("desconectado");


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

                        estado.setBackgroundColor(Color.parseColor("#00FF79"));

                        // estado.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.colorconectado)));

                        estado.setText("conectado");

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
                        Toast.makeText(getApplicationContext(), "Vibrar al Notificar", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(getApplicationContext(), "Sonido al Notificar", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(getApplicationContext(), "Sonido Alarma", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(getApplicationContext(), "Sonido Notificacion", Toast.LENGTH_LONG).show();
                        break;

                    case R.id.sonidopersonalizado:
                        picksound.setVisibility(View.VISIBLE);
                      //  pickRingtone();
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
                        Toast.makeText(getApplicationContext(), "Sonido Personalizado", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });



    }


    public void vervalorguardados(String valor1, String valor2, String valor3, String valor4, String valor5, String valor6){
        //Toast.makeText(getApplicationContext(),  valor1  + " - " + valor2 + " - " + valor3 + "Nro: " + valor4 + "ringtone: " + valor5,  Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(),"-"+valor6+"-",  Toast.LENGTH_LONG).show();

    }


    public void pickRingtone() {
        // TODO Auto-generated method.   stub
        Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI,  RingtoneManager.TYPE_RINGTONE);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Seleccionar Ringtone");
        Uri urie2 =     RingtoneManager.getActualDefaultRingtoneUri(getApplicationContext(), RingtoneManager.TYPE_NOTIFICATION);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI, urie2);
        startActivityForResult(intent, 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri uri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1:
                    ringtone = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
                    titulo = ringtone.getUserInfo();

                    Ringtone ringtone2 = RingtoneManager.getRingtone(this, ringtone);
                    String title = ringtone2.getTitle(this);

                    try {
                        FileOutputStream fileOutputStream_ring_tone = openFileOutput("ring_tone_file", MODE_PRIVATE);
                        fileOutputStream_ring_tone.write(ringtone.toString().getBytes());
                        fileOutputStream_ring_tone.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;
                default:
                    break;
            }
        }

        ringtone = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
        Ringtone ringtone2 = RingtoneManager.getRingtone(this, ringtone);
        String title = ringtone2.getTitle(this);
        eliminar_notificacion(uri);
    }

    private void eliminar_notificacion(Uri v) {

        try {
            FileInputStream fileInputStream =  openFileInput("ring_tone_file");
            InputStreamReader inputStreamReader =  new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer =  new StringBuffer();
            try {
                while ((Message = bufferedReader.readLine())!=null)
                {
                    stringBuffer.append(Message);
                }
                ko = stringBuffer.toString();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String stingh = ko;
        String lastChar = stingh.substring(0,2);
        String  lastCharrw = stingh.substring(0, (stingh.length() - 1));
        //  Log.v(TAG_REGISTER, " Valor pux :$lastChar");
        sonido_notificacion_android2  = Uri.parse(lastCharrw);

        String channelId = getString(R.string.canal_sonido_personalizado_sismos_id)+sonido_notificacion_android2;

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //String id = "canal_sonido_personalizado_id";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.deleteNotificationChannel(channelId);
        }

        crear_canal_sonido_personalizado(v);
    }

    private void crear_canal_sonido_personalizado(Uri g) {
        //guardar ramdom para nuevo nombre de canal de notificacion personalizado
        Random rand = new Random();
        int n = rand.nextInt(1000000);
        String valorrandom = String.valueOf(n);

        try {
            FileOutputStream fileOutputStream_notificar = openFileOutput(ramdom, MODE_PRIVATE);
            fileOutputStream_notificar.write(valorrandom.getBytes());
            fileOutputStream_notificar.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String stingh = ko;
        String lastChar = stingh.substring(0,2);
        String  lastCharrw = stingh.substring(0, (stingh.length() - 1));
        //  Log.v(TAG_REGISTER, " Valor pux :$lastChar");
        sonido_notificacion_android2  = Uri.parse(lastCharrw);

        String channelId = getString(R.string.canal_sonido_personalizado_sismos_id)+valorrandom;
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.canal_sonido_personalizado_sismos_nombre)+valorrandom;
            String description = getString(R.string.canal_sonido_personalizado_sismos_descripcion)+valorrandom;
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(channelId, name, importance);
            channel.setDescription(description);

            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();
            channel.setSound(g, audioAttributes);

            notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }











/*
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1:
                    ringtone = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
                    String TITULO = ringtone.getUserInfo();

                    Ringtone ringtone2 = RingtoneManager.getRingtone(this, ringtone);
                    String title = ringtone2.getTitle(this);

                    try {
                        FileOutputStream fileOutputStream_ring_tone = openFileOutput("ring_tone_file", MODE_PRIVATE);
                        fileOutputStream_ring_tone.write(ringtone.toString().getBytes());
                        fileOutputStream_ring_tone.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // Toast.makeText(getApplicationContext(), " Ringtone Seleccionado: " + ringtone, Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), " Ringtone Seleccionado: " + title, Toast.LENGTH_LONG).show();

                    break;

                default:
                    break;
            }
        }
    }
    **/



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



    void setupDrawerToggle(){
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name, R.string.app_name);
        mDrawerToggle.syncState();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }




}
