package sistemasfireg.igp.org.sismosperu;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.StringTokenizer;
import me.leolin.shortcutbadger.ShortcutBadger;

public class Ultimosismo3 extends FragmentActivity implements OnMapReadyCallback {
    private SupportMapFragment sMapFragment;
    private Button mk;
    private TextView text_hora;
    private TextView text_intensidad;
    private TextView text_ubicacion;
    private TextView text_profundidad;
    private TextView txtmagnitud;
    private TextView txtubicacion;
    private TextView texto;
    private TextView iconocompartir;
    private RelativeLayout textoencabezado;
    private String fechadato;
    private String horadato;
    private String ubicacion;
    private String latitud;
    private String longitud;
    private Double lati;
    private Double longit;
    private String magni;
    private Button satelite;
    private Button terreno;
    private static final String TAG = Ultimosismo3.class.getSimpleName();
    private Button datox;
    private String k;
    private static final int READ_BLOCK_SIZE = 100;
    private DrawerLayout mDrawerLayout;
    private android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;


    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultimosismo3);


    }


     @Override
    protected void onStart() {
         super.onStart();



         onNewIntent(getIntent());
         Context context = this;
         String Message3 = "0";
         String file_name = "valorcero";
         try {
             FileOutputStream fileOutputStream = openFileOutput(file_name, MODE_PRIVATE);
             fileOutputStream.write(Message3.getBytes());
             fileOutputStream.close();
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }

         ShortcutBadger.applyCount(context, 0);
         String file2 = "datos_ordences";
         try {
             FileInputStream fileIn = openFileInput(file2);
             InputStreamReader InputRead= new InputStreamReader(fileIn);
             char[] inputBuffer= new char[READ_BLOCK_SIZE];
             int charRead;
             charRead=InputRead.read(inputBuffer);
             String readstring=String.copyValueOf(inputBuffer,0,charRead);
             k +=readstring;
             InputRead.close();
             StringTokenizer st = new StringTokenizer(k, ",");
             String ajustes = st.nextToken();
             String tipo = st.nextToken();

             Log.d(TAG, "valor: json: " + k);

         } catch (Exception e) {
             e.printStackTrace();
         }





         materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
         floatingActionButton1 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
         floatingActionButton2 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2);
 
         floatingActionButton1.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 //TODO something when floating action menu first item clicked

             }
         });
         floatingActionButton2.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 //TODO something when floating action menu second item clicked

             }
         });






         ConnectivityManager cmanager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
         NetworkInfo info = null;
         if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
             info = Objects.requireNonNull(cmanager).getActiveNetworkInfo();
         }
         if(info!= null && info.isConnected()){
             if(info.getType() == ConnectivityManager.TYPE_WIFI) {
                 Toast.makeText(Ultimosismo3.this, "Conexión Establecida", Toast.LENGTH_LONG).show();
             }
             else if(info.getType() == ConnectivityManager.TYPE_MOBILE){
                 Toast.makeText(Ultimosismo3.this, "Conexión Establecida", Toast.LENGTH_LONG).show();
             }
         }
         else{
             Toast.makeText(Ultimosismo3.this, "Su Equipo ha Bloquedo la Conexión", Toast.LENGTH_LONG).show();
         }

         detecta_sismo();
         sMapFragment = SupportMapFragment.newInstance();
         FragmentManager sFm = getSupportFragmentManager();
         sFm.beginTransaction().add(R.id.mapx, sMapFragment).commit();
         sMapFragment.getMapAsync(this);

         Typeface fontAwesomeFont = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
         TextView ihoras = findViewById(R.id.iconohora);
         TextView iubicaciones = findViewById(R.id.iconoubicacion);
         ihoras.setTypeface(fontAwesomeFont);
         iubicaciones.setTypeface(fontAwesomeFont);
         satelite = findViewById(R.id.satelite);
         terreno =  findViewById(R.id.terreno);
         datox = findViewById(R.id.localizacion);
         datox.setTypeface(fontAwesomeFont);
         text_hora =  findViewById(R.id.text_hora);
         text_intensidad =  findViewById(R.id.text_intensidad);
         text_ubicacion = findViewById(R.id.text_ubicacion);
         text_profundidad = findViewById(R.id.text_profundidad);
         txtmagnitud = findViewById(R.id.grados);
         txtubicacion = findViewById(R.id.txtubicacion);
         texto = findViewById(R.id.texto);

         iconocompartir = findViewById(R.id.iconocompartir);
         iconocompartir.setTypeface(fontAwesomeFont);
         iconocompartir.setBackgroundResource(R.drawable.circuloblanco);
         textoencabezado = findViewById(R.id.textoencabezado);

         ImageView sliderz = findViewById(R.id.sliderz);
         mDrawerLayout = findViewById(R.id.drawer_layout);
         mDrawerLayout = findViewById(R.id.drawer_layout);
         mDrawerLayout.setDrawerListener(mDrawerToggle);
         setupDrawerToggle();
         sliderz.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 mDrawerLayout.openDrawer(GravityCompat.START);
             }
         });

         RelativeLayout blocke1a = findViewById(R.id.blocke1);
         RelativeLayout blocke2a = findViewById(R.id.blocke2);
         RelativeLayout blocke3a = findViewById(R.id.blocke3);
         RelativeLayout blocke4a = findViewById(R.id.blocke4);
         RelativeLayout blocke5a = findViewById(R.id.blocke5);
         RelativeLayout blocke6a = findViewById(R.id.blocke6);
         RelativeLayout blocke9 = findViewById(R.id.blocke9);

         blocke1a.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View arg0) {
                 Intent intent = new Intent(Ultimosismo3.this,Ultimosismo3.class);
                 startActivity(intent);
             }
         });
         blocke2a.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View arg0) {
                 Intent intent = new Intent(Ultimosismo3.this,Ultimosismoslist.class);
                 startActivity(intent);
             }
         });
         blocke3a.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View arg0) {
                 Intent intent = new Intent(Ultimosismo3.this,Listadoenmapa.class);
                 startActivity(intent);
             }
         });
         blocke4a.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View arg0) {
                 Intent intent = new Intent(Ultimosismo3.this,Notificacionesconfig.class);
                 startActivity(intent);
             }
         });
         blocke5a.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View arg0) {
                 Intent intent = new Intent(Ultimosismo3.this,Glosarioestatico.class);
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
                 Intent intent = new Intent(Ultimosismo3.this,Listadoredessociales.class);
                 startActivity(intent);
             }
         });


         mk = findViewById(R.id.button);
         mk.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View v) {
                 PopupMenu popup = new PopupMenu(Ultimosismo3.this, mk);
                 popup.getMenuInflater().inflate(R.menu.menu_main, popup.getMenu());

                 popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                     public boolean onMenuItemClick(MenuItem item) {

                         int id = item.getItemId();

                         if (id == R.id.ultimo_sismo) {
                             Intent intent = new Intent(Ultimosismo3.this,Listadoredessociales.class);
                             startActivity(intent);
                             return true;
                         }


                         if (id == R.id.listado_ultimos_sismos) {
                             Intent intent = new Intent(Ultimosismo3.this,Listadoredessociales.class);
                             startActivity(intent);
                             return true;
                         }

                         if (id == R.id.mapa_sismos) {
                             Intent intent = new Intent(Ultimosismo3.this,Listadoenmapa.class);
                             startActivity(intent);
                             return true;
                         }

                         if (id == R.id.glosario) {
                             Intent intent = new Intent(Ultimosismo3.this,Glosarioestatico.class);
                             startActivity(intent);
                             return true;
                         }

                         if (id == R.id.notificarsino) {
                             Intent intent = new Intent(Ultimosismo3.this,Notificacionesconfig.class);
                             startActivity(intent);
                             return true;
                         }

                         if (id == R.id.salir) {
                             Intent intent = new Intent(Intent.ACTION_MAIN);
                             intent.addCategory(Intent.CATEGORY_HOME);
                             intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                             startActivity(intent);
                             finish();
                         }
                         return Ultimosismo3.super.onOptionsItemSelected(item);
                     }
                 });
                 popup.show();
             }
         });
         mostrar_ult_sismo();
          Toast.makeText(Ultimosismo3.this, "Actvidad en pausa", Toast.LENGTH_LONG).show();
     }

    @Override
    protected void onStop() {
         super.onStop();
        Toast.makeText(Ultimosismo3.this, "Actvidad en pausa", Toast.LENGTH_LONG).show();


    }


    private void setupDrawerToggle(){
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,R.string.app_name, R.string.app_name);
        mDrawerToggle.syncState();
    }

    private void detecta_sismo() {

        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance("https://igpsismos2.firebaseio.com/").getReference("messages");
        mDatabase.keepSynced(true);
        mDatabase.orderByKey().limitToLast(1).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                DatSismo sreporte = dataSnapshot.getValue(DatSismo.class);
                guardar_pref(sreporte);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                DatSismo sreporte = dataSnapshot.getValue(DatSismo.class);
                guardar_pref(sreporte);
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                DatSismo sreporte = dataSnapshot.getValue(DatSismo.class);
                guardar_pref(sreporte);
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                DatSismo sreporte = dataSnapshot.getValue(DatSismo.class);
                guardar_pref(sreporte);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void guardar_pref(DatSismo sreporte) {
        SharedPreferences prefs = getSharedPreferences("ultsismo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("incategoria", sreporte.getCategoria());
        editor.putString("inreferencia", sreporte.getReferencia());
        editor.putString("inmagnitud", sreporte.getMagnitud());
        editor.putString("inprof", sreporte.getProfundidad());
        editor.putString("infecha", sreporte.getFechautc());
        editor.putString("txintenso", sreporte.getIntenso());
        editor.putString("inhora", sreporte.getHorautc());
        editor.putString("inlat", sreporte.getLat());
        editor.putString("inlong", sreporte.getLon());
        editor.putString("inepic", sreporte.getEpicentro());
        editor.putString("insim", sreporte.getSimulacro());
        editor.apply();
        mostrar_ult_sismo();
    }

    private void mostrar_ult_sismo() {
        SharedPreferences prefs = getSharedPreferences("ultsismo", Context.MODE_PRIVATE);
        String intensidad = prefs.getString("txintenso", "");
        String hora = prefs.getString("inhora", "");
        String fecha = prefs.getString("infecha", "");
        String simulacro  = prefs.getString("insim", "");
        String profundidad = prefs.getString("inprof","");
        fechadato= prefs.getString("infecha","0");
        horadato = prefs.getString("inhora","0");
        latitud = prefs.getString("inlat","0");
        longitud = prefs.getString("inlong","0");
        String epicentro = prefs.getString("inepic", "");
        magni = prefs.getString("inmagnitud","");
        ubicacion = prefs.getString("inreferencia","Aqui");

        iconocompartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Reporte de Último Sismo Perú" +  "\n";
                String shareSub = "Reporte de Último Sismo Perú" +  "\n" + "Fecha Local: " + fechadato + "  " + horadato +  "\n Magnitud: " + magni + "\n" +  "Latitud: " + latitud + " " + "Longitud: " + longitud + "\n" + "Referencia: " + ubicacion + "\n"  +   "\n"  ;
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareBody);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareSub);
                startActivity(Intent.createChooser(sharingIntent, "compartir último sismo"));
            }
        });

        //evaluar dato intensidad
        String dato_intensidad;
        if (intensidad.length() == 0){
            dato_intensidad = "- -";
        }
        else{
            dato_intensidad = intensidad;
        }

        text_intensidad.setText(dato_intensidad);



        //evaluar dato hora
        String dato_hora;
        if (hora.length() == 0){
            dato_hora = "- -";
        }
        else{
            dato_hora = hora;
        }

        //evaluar dato fecha
        String dato_fecha;
        if (fecha.length() == 0){
            dato_fecha = "- -";
        }
        else{
            dato_fecha = fecha;
        }
        text_hora.setText(dato_fecha +"   "+ dato_hora);


        //evaluar dato profundidad
        String dato_profundidad;
        if (profundidad.length() == 0){
            dato_profundidad = "- -";
        }
        else{
            dato_profundidad = profundidad;
        }
        text_profundidad.setText(dato_profundidad +""+" km");

        //evaluar dato magnitud
        double w;

        try {
            w = Double.valueOf(magni);
        } catch (NumberFormatException e) {
            w = 0;
        }

        String magnitud2;
        if (w == 0){
            magnitud2 = "0";
        }
        else
        {
            magnitud2 = magni;
        }

        Double val = Double.parseDouble(magnitud2);
        txtmagnitud.setText(magnitud2);

        if (val >= 0 && val < 4.5){
            txtmagnitud.setBackgroundResource(R.drawable.circuloverde);
            textoencabezado.setBackgroundColor(getResources().getColor(R.color.verdeigp));
        }

        else if (val >= 4.5 && val <= 6.0){
            txtmagnitud.setBackgroundResource(R.drawable.circuloamarillo);
            textoencabezado.setBackgroundColor(getResources().getColor(R.color.orangeyellow));
        }

        else if (val > 6.0 && val <= 15){
            txtmagnitud.setBackgroundResource(R.drawable.circulorojo);
            textoencabezado.setBackgroundColor(getResources().getColor(R.color.rojoigp));
        }

        else  {
            txtmagnitud.setBackgroundResource(R.drawable.circulorojo);
            textoencabezado.setBackgroundColor(getResources().getColor(R.color.rojoigp));
        }

        //evaluar dato ubicacion
        String dato_ubicacion;
        if (ubicacion.length() == 0){
            dato_ubicacion = "null";
        }
        else{
            dato_ubicacion = ubicacion;
        }

        String z = dato_ubicacion.replace("-","\n");
        txtubicacion.setText(z);

        //evaluar dato simulacro
        int g;

        try {
            g = Integer.valueOf(simulacro);
        } catch (NumberFormatException e) {
            g = 0;
        }

        if (g == 0){
            texto.setText("Último Sismo");
        }
        else if (g == 1){
            texto.setText("Simulacro");
        }
        else if (g == 2){
            texto.setText("Simulación");
        }
        else{
            texto.setText("Último Sismo");
        }

        lati= Double.parseDouble(latitud);
        longit = Double.parseDouble(longitud);
        sMapFragment.getMapAsync(this);
        DecimalFormat form = new DecimalFormat("0.00");
        text_ubicacion.setText(form.format(lati)+"  "+" "+form.format(longit));
    }

    public void onMapReady(final GoogleMap googleMap) {
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.iconored);
        LatLng latLng = new LatLng(lati,longit);
        googleMap.clear();
        googleMap.addMarker(new MarkerOptions().position(latLng).title("Epicentro").icon(icon).anchor(0.5f, 0.5f)).showInfoWindow();
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 6));
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(6), 1500, null);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setIndoorLevelPickerEnabled(true);
        googleMap.getUiSettings().setTiltGesturesEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);

        satelite.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            }
        });

        terreno.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            }
        });

        datox.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lati, longit), 6));
            }
        });
    }
}