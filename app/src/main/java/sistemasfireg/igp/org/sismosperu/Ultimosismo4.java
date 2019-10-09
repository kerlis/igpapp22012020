package sistemasfireg.igp.org.sismosperu;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.AudioAttributes;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.StringTokenizer;
import me.leolin.shortcutbadger.ShortcutBadger;

public class Ultimosismo4 extends FragmentActivity implements OnMapReadyCallback  {
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
    private ObjectAnimator obte;
    private Button rocket;
    boolean isUp;
    RelativeLayout myView;
    RelativeLayout expandir;
    RelativeLayout blocktwo, blockone;
    FrameLayout mapxSS;
    Button sharebutton, glosariobutton;
    Context context;
    TextView datoz;
    private static final String ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
    private static final String ACTION_NOTIFICATION_LISTENER_SETTINGS = "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS";
    private ImageView interceptedNotificationImageView;
    private AlertDialog enableNotificationListenerAlertDialog;
    private PendingIntent pendingIntent;
    private static final long FREQUENCY = 8000;
    Databasehelper myDb;
    Intent alarmIntent;
    public View preView;
    private ImageView iv;
    private String sharePath = "no";
    public View main;
    public ImageView imageView;
    public GoogleMap map;
    public GoogleMap mapx;
    RelativeLayout encabezado2;
    ImageView previa;
    RelativeLayout blocke92;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultimosismo4);



        Snackbar.make(findViewById(android.R.id.content),"Mantén actualizada la aplicación", Snackbar.LENGTH_LONG)
                .setAction("Play store", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW)
                                .setData(Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName()));
                        try {
                            startActivity(new Intent(intent)
                                    .setPackage("com.android.vending"));
                        } catch (android.content.ActivityNotFoundException exception) {
                            startActivity(intent);
                        }
                    }
                }).show();


        preView = getWindow().getDecorView();


        alarmIntent = new Intent(Ultimosismo4.this, Serviciorevisionsismosnotificaciones.class);

        pendingIntent = PendingIntent.getBroadcast(Ultimosismo4.this, 123, alarmIntent, 0);
        myDb = new Databasehelper(this);



        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        mapxSS = findViewById(R.id.mapx);

        encabezado2 = findViewById(R.id.encabezado2);


        previa = findViewById(R.id.previa);


        //canales de notificacion
        crear_canal_sonido_alarma();
        crear_canal_sonido_notificacion();
        crear_canal_sin_sonido_con_vibracion();
    }

    private void crear_canal_sonido_alarma() {
        String channelId = getString(R.string.canal_sonido_alarmas_sismos_id);
        Uri sonido_notificacion_android = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.alarmasonido);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.canal_sonido_alarmas_sismos_id_nombre);
            String description = getString(R.string.canal_sonido_alarmas_sismos_id_descripcion);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(channelId, name, importance);
            channel.setDescription(description);

            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();
            channel.setSound(sonido_notificacion_android, audioAttributes);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void crear_canal_sonido_notificacion() {
        String channelId = getString(R.string.canal_sonido_notificacion_sismos_id);
        Uri sonido_notificacion_android = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.beep2);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.canal_sonido_notificacion_sismos_nombre);
            String description = getString(R.string.canal_sonido_notificacion_sismos_descripcion);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(channelId, name, importance);
            channel.setDescription(description);

            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();
            channel.setSound(sonido_notificacion_android, audioAttributes);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void crear_canal_sin_sonido_con_vibracion() {
        String channelId = getString(R.string.canal_sin_sonido_con_vibracion_sismos_id);
        Uri sonido_notificacion_android = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.alarmasonido);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.canal_sin_sonido_con_vibracion_sismos_nombre);
            String description = getString(R.string.canal_sin_sonido_con_vibracion_sismos_descripcion);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(channelId, name, importance);
            channel.setDescription(description);
            channel.setVibrationPattern(new long[]{50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100});
            channel.enableVibration(true);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();


        main =  findViewById(R.id.main);



        stopService(new Intent(Ultimosismo4.this, Serviciorevisionsismosnotificaciones.class));


//        String reqString = Build.MANUFACTURER + " " + Build.MODEL + " " + Build.VERSION.RELEASE + " " + Build.VERSION_CODES.class.getFields()[android.os.Build.VERSION.SDK_INT].getName();
        String fabricante = Build.MANUFACTURER;


/*
        if(fabricante ==  "huawei"){
            //Toast.makeText(Ultimosismo4.this, "es", Toast.LENGTH_LONG).show();
            empezar();

        }
        else{
            //Toast.makeText(Ultimosismo4.this, "no es", Toast.LENGTH_LONG).show();

        }
*/
      // empezar();


        glosariobutton = findViewById(R.id.glosariobutton);
        sharebutton = findViewById(R.id.sharebutton);
        mapxSS = findViewById(R.id.mapx);
        blocktwo = findViewById(R.id.blocktwo);
        blockone = findViewById(R.id.blockone);
        expandir = findViewById(R.id.expandir);
        myView = findViewById(R.id.my_view);
        myView.setVisibility(View.INVISIBLE);
        rocket = findViewById(R.id.btnNovaCompra);

        rocket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                slideUp(myView);

            }
        });
        slidedown();
        mapxSS.setOnTouchListener(new View.OnTouchListener() {


            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                /// slideDown2(myView);
                // Toast.makeText(Ultimosismo4.this, "tocaste", Toast.LENGTH_LONG).show();


                return false;
            }


        });

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
            InputStreamReader InputRead = new InputStreamReader(fileIn);
            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            int charRead;
            charRead = InputRead.read(inputBuffer);
            String readstring = String.copyValueOf(inputBuffer, 0, charRead);
            k += readstring;
            InputRead.close();
            StringTokenizer st = new StringTokenizer(k, ",");
            String ajustes = st.nextToken();
            String tipo = st.nextToken();

            Log.d(TAG, "valor: json: " + k);

        } catch (Exception e) {
            e.printStackTrace();
        }


        ConnectivityManager cmanager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            info = Objects.requireNonNull(cmanager).getActiveNetworkInfo();
        }
        if (info != null && info.isConnected()) {
            if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                Toast.makeText(Ultimosismo4.this, "Conexión Establecida ", Toast.LENGTH_LONG).show();
            } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                Toast.makeText(Ultimosismo4.this, "Conexión Establecida", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(Ultimosismo4.this, "Su Equipo ha Bloquedo la Conexión", Toast.LENGTH_LONG).show();
        }

        detecta_sismo();
        sMapFragment = SupportMapFragment.newInstance();
        FragmentManager sFm = getSupportFragmentManager();
        sFm.beginTransaction().add(R.id.mapx, sMapFragment).commit();
        sMapFragment.getMapAsync(this);

        Typeface fontAwesomeFont = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        satelite = findViewById(R.id.satelite);
        terreno = findViewById(R.id.terreno);
        datox = findViewById(R.id.localizacion);
        datox.setTypeface(fontAwesomeFont);
        text_hora = findViewById(R.id.text_hora);
        text_intensidad = findViewById(R.id.text_intensidad);
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


        RelativeLayout blocke92 = findViewById(R.id.blocke92);



        blocke1a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Ultimosismo4.this, Ultimosismo4.class);
                startActivity(intent);
            }
        });
        blocke2a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Ultimosismo4.this, Ultimosismoslist.class);
                startActivity(intent);
            }
        });
        blocke3a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Ultimosismo4.this, Listadoenmapa.class);
                startActivity(intent);
            }
        });
        blocke4a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Ultimosismo4.this, Notificacionesconfig.class);
                startActivity(intent);
            }
        });
        blocke5a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Ultimosismo4.this, Glosarioestatico.class);
                startActivity(intent);
            }
        });
        blocke6a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
        blocke9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Ultimosismo4.this, Listadoredessociales.class);
                startActivity(intent);
            }
        });



        blocke92.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Ultimosismo4.this, Contactenos.class);
                startActivity(intent);
            }
        });



/*
        mk = findViewById(R.id.button);
        mk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(Ultimosismo4.this, mk);
                popup.getMenuInflater().inflate(R.menu.menu_main, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    public boolean onMenuItemClick(MenuItem item) {

                        int id = item.getItemId();

                        if (id == R.id.ultimo_sismo) {
                            Intent intent = new Intent(Ultimosismo4.this, Ultimosismo4.class);
                            startActivity(intent);
                            return true;
                        }


                        if (id == R.id.listado_ultimos_sismos) {
                            Intent intent = new Intent(Ultimosismo4.this, Ultimosismoslist.class);
                            startActivity(intent);
                            return true;
                        }

                        if (id == R.id.mapa_sismos) {
                            Intent intent = new Intent(Ultimosismo4.this, Listadoenmapa.class);
                            startActivity(intent);
                            return true;
                        }

                        if (id == R.id.glosario) {
                            Intent intent = new Intent(Ultimosismo4.this, Glosarioestatico.class);
                            startActivity(intent);
                            return true;
                        }

                        if (id == R.id.notificarsino) {
                            Intent intent = new Intent(Ultimosismo4.this, Notificacionesconfig.class);
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
                        return Ultimosismo4.super.onOptionsItemSelected(item);
                    }
                });
                popup.show();
            }
        });
        */
        mostrar_ult_sismo();


        glosariobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verpopup();
            }
        });


    }

    @Override
    protected void onStop() {
        super.onStop();
        //   Toast.makeText(Ultimosismo4.this, "Actvidad en pausa", Toast.LENGTH_LONG).show();
    }

    private void setupDrawerToggle() {
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this, mDrawerLayout, R.string.app_name, R.string.app_name);
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
        ubicacion = prefs.getString("inreferencia", "Aqui");
        magni = prefs.getString("inmagnitud", "");
        String profundidad = prefs.getString("inprof", "");
        fechadato = prefs.getString("infecha", "0");
        String intensidad = prefs.getString("txintenso", "");
        String hora = prefs.getString("inhora", "");
        latitud = prefs.getString("inlat", "0");
        longitud = prefs.getString("inlong", "0");
        String epicentro = prefs.getString("inepic", "");
        String simulacro = prefs.getString("insim", "");


        String fecha = prefs.getString("infecha", "");
        horadato = prefs.getString("inhora", "0");

        iconocompartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Reporte de Último Sismo Perú" + "\n";
                String shareSub = "Reporte de Último Sismo Perú" + "\n" + "Fecha Local: " + fechadato + "  " + horadato + "\n Magnitud: " + magni + "\n" + "Latitud: " + latitud + " " + "Longitud: " + longitud + "\n" + "Referencia: " + ubicacion + "\n" + "\n";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareBody);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareSub);
                startActivity(Intent.createChooser(sharingIntent, "compartir último sismo"));
            }
        });


        sharebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mapx.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                    @Override
                    public void onMapLoaded() {
                        shareScreenshot();

                    }

                });


                /*
                GoogleMap.SnapshotReadyCallback callback = new GoogleMap.SnapshotReadyCallback() {
                    Bitmap bitmap;
                    @Override
                    public void onSnapshotReady(Bitmap snapshot) {
                        glosariobutton.setVisibility(View.GONE);
                        sharebutton.setVisibility(View.GONE);

                      //  ActionBar actionBar = getActionBar();
                      //  actionBar.hide();

                        Rect rectangle = new Rect();
                        Window window = getWindow();
                        window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
                        int statusBarHeight = rectangle.top;
                        int contentViewTop = window.findViewById(Window.ID_ANDROID_CONTENT).getTop();
                        int titleBarHeight= contentViewTop - statusBarHeight;

                        Bitmap encabezado = null;
                        encabezado = ScreenshotUtils.getScreenShot(encabezado2);






                        int valor_altura = encabezado2.getHeight();
                        int valor_altura2 = textoencabezado.getHeight();
                        int suma =  valor_altura + valor_altura2 + statusBarHeight;


                        previa.setImageBitmap(encabezado);

                        int alto = encabezado.getHeight();

                        Log.d("Notif", "alto del relative: " + suma);



                        Bitmap jungle = null;
                        jungle = ScreenshotUtils.getScreenShot(main);


                        bitmap = snapshot;
                        FileOutputStream out;
                        File saveFile = ScreenshotUtils.getMainDirectoryName(getApplicationContext());

                        Canvas canvas = new Canvas(jungle);
                        canvas.drawBitmap(snapshot, (jungle.getWidth()) / -240, suma, new Paint());

                        File file = ScreenshotUtils.store(jungle, "Parámetros_sísmicos.jpg",  saveFile);//save the screenshot to selected path
                        shareScreenshot(file);




                    }
                };
                mapx.snapshot(callback);

                */






            }

        });


        //evaluar dato intensidad
        String dato_intensidad;
        if (intensidad.length() == 0) {
            dato_intensidad = "- -";
        } else {
            dato_intensidad = intensidad;
        }

        text_intensidad.setText(dato_intensidad);


        //evaluar dato hora
        String dato_hora;
        if (hora.length() == 0) {
            dato_hora = "- -";
        } else {
            dato_hora = hora;
        }

        //evaluar dato fecha
        String dato_fecha;
        if (fecha.length() == 0) {
            dato_fecha = "- -";
        } else {
            dato_fecha = fecha;
        }
        text_hora.setText(dato_fecha + "   " + dato_hora);


        //evaluar dato profundidad
        String dato_profundidad;
        if (profundidad.length() == 0) {
            dato_profundidad = "- -";
        } else {
            dato_profundidad = profundidad;
        }
        text_profundidad.setText(dato_profundidad + "" + " km");

        //evaluar dato magnitud
        double w;

        try {
            w = Double.valueOf(magni);
        } catch (NumberFormatException e) {
            w = 0;
        }

        String magnitud2;
        if (w == 0) {
            magnitud2 = "0";
        } else {
            magnitud2 = magni;
        }

        Double val = Double.parseDouble(magnitud2);
        txtmagnitud.setText(magnitud2);

        if (val >= 0 && val < 4.5) {
            txtmagnitud.setBackgroundResource(R.drawable.circuloverde);
            textoencabezado.setBackgroundColor(getResources().getColor(R.color.verdeigp));
        } else if (val >= 4.5 && val <= 6.0) {
            txtmagnitud.setBackgroundResource(R.drawable.circuloamarillo);
            textoencabezado.setBackgroundColor(getResources().getColor(R.color.orangeyellow));
        } else if (val > 6.0 && val <= 15) {
            txtmagnitud.setBackgroundResource(R.drawable.circulorojo);
            textoencabezado.setBackgroundColor(getResources().getColor(R.color.rojoigp));
        } else {
            txtmagnitud.setBackgroundResource(R.drawable.circulorojo);
            textoencabezado.setBackgroundColor(getResources().getColor(R.color.rojoigp));
        }

        //evaluar dato ubicacion
        String dato_ubicacion;
        if (ubicacion.length() == 0) {
            dato_ubicacion = "null";
        } else {
            dato_ubicacion = ubicacion;
        }

      //  String z = dato_ubicacion.replace("-", "\n");
        txtubicacion.setText(dato_ubicacion);

        //txtubicacion.setText(dato_ubicacion);


        //evaluar dato simulacro
        int g;

        try {
            g = Integer.valueOf(simulacro);
        } catch (NumberFormatException e) {
            g = 0;
        }

        if (g == 0) {
            texto.setText("Último Sismo ");
        } else if (g == 1) {
            texto.setText("Simulacro ");
        } else if (g == 2) {
            texto.setText("Simulación ");
        } else {
            texto.setText("Último Sismo ");
        }

        lati = Double.parseDouble(latitud);
        longit = Double.parseDouble(longitud);
        sMapFragment.getMapAsync(this);
        DecimalFormat form = new DecimalFormat("0.00");
        text_ubicacion.setText(form.format(lati) + "  " + " " + form.format(longit));
    }

    public void onMapReady(final GoogleMap googleMap) {

        mapx =  googleMap;

        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.iconored);
        LatLng latLng = new LatLng(lati, longit);

        mapx.clear();
        mapx.addMarker(new MarkerOptions().position(latLng).title("Epicentro").icon(icon).anchor(0.5f, 0.5f)).showInfoWindow();
        mapx.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 6));
        mapx.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mapx.animateCamera(CameraUpdateFactory.zoomTo(6), 1500, null);
        mapx.getUiSettings().setZoomControlsEnabled(true);
        mapx.getUiSettings().setIndoorLevelPickerEnabled(true);
        mapx.getUiSettings().setTiltGesturesEnabled(true);
        mapx.getUiSettings().setCompassEnabled(true);


        mapx.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {

            }
        });


        satelite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            }
        });

        terreno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            }
        });

        datox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lati, longit), 6));
            }
        });


        mapx.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {

                if (myView.getVisibility() == View.VISIBLE) {
                    slideDown2(myView);

                } else {
                    slideDown3(myView);
                }
            }
        });
        mapx.setPadding(0, 0, 0, 35);

        mapx = googleMap ;


    }

    private void slidedown() {
        obte = ObjectAnimator.ofFloat(rocket, "Y", 20f);

        obte.setDuration(400);
        obte.setRepeatCount(ValueAnimator.INFINITE);
        obte.setRepeatMode(ValueAnimator.RESTART);
        obte.start();
    }


    public void slideUp(View view) {
        view.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                view.getHeight(),  // fromYDelta
                0);                // toYDelta
        animate.setDuration(300);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }

    public void slideUp2(View view) {
        view.setVisibility(View.VISIBLE);

    }

    // slide the view from its current position to below itself
    public void slideDown2(View view) {
        view.setVisibility(View.GONE);

        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                0,                 // fromYDelta
                view.getHeight()); // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);

    }

    public void slideDown3(View view) {
        view.setVisibility(View.GONE);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public void verpopup() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.datosglosariopopup, null);
        dialogBuilder.setView(dialogView);

        Button cerrar = dialogView.findViewById(R.id.close);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                alertDialog.hide();
            }
        });
    }


    public void empezar() {




/*
      Intent myIntent = new Intent(getApplicationContext(), Serviciorevisionsismosnotificaciones.class);


        boolean isWorking = (PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent(getApplicationContext(), Serviciorevisionsismosnotificaciones.class), PendingIntent.FLAG_NO_CREATE) != null);
        if (isWorking) {Log.d("alarm" + isWorking, "is working");} else {Log.d("alarm", "is not working");}

        if(!isWorking) {
            Calendar calendar = new GregorianCalendar();

            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            //pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, myIntent,    PendingIntent.FLAG_UPDATE_CURRENT);
           // alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            int timeNotif = 6000;
            Log.d("Notif", "Notification every (ms): " + timeNotif);
            alarmManager.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), timeNotif, pendingIntent);
        }



*/


        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        // int interval = 10;
        //  int interval = 30 * 1000; // 30 seconds of interval.

        //int interval = 120000;
        int interval2 = 8000;

        int interval = 240000;

        Calendar cur_cal = new GregorianCalendar();

        //  PendingIntent pintent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        //AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
        manager.setRepeating(AlarmManager.RTC, cur_cal.getTimeInMillis(), interval, pendingIntent);


        // manager.setRepeating(AlarmManager.RTC_WAKEUP, interval2, interval, pendingIntent);
        //Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();


        ///     manager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);

        // manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);


        // AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        /*
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            manager.setExact(AlarmManager.RTC_WAKEUP, 3000, pendingIntent);
        } else {
            manager.set(AlarmManager.RTC_WAKEUP, 3000, pendingIntent);

         }
*/
        //manager.set(AlarmManager.RTC_WAKEUP, (System.currentTimeMillis() + FREQUENCY), pendingIntent);

        // manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, (Calendar.getInstance().getTimeInMillis() + FREQUENCY),FREQUENCY, pendingIntent);

        //  manager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, System.currentTimeMillis(), 3000, pendingIntent);

        Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
    }

    private void shareScreenshot(File file) {
        Uri uri = Uri.fromFile(file);//Convert file path into Uri for sharing
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, getString(R.string.sharing_text));
        intent.putExtra(Intent.EXTRA_STREAM, uri);//pass uri here
        startActivity(Intent.createChooser(intent, getString(R.string.share_title)));
        glosariobutton.setVisibility(View.VISIBLE);
        sharebutton.setVisibility(View.VISIBLE);
    }

    private void showScreenShotImage(Bitmap b) {
       // imageView.setImageBitmap(b);
    }



    private void shareScreenshot() {

        GoogleMap.SnapshotReadyCallback callback = new GoogleMap.SnapshotReadyCallback() {
            Bitmap bitmap;
            @Override
            public void onSnapshotReady(Bitmap snapshot) {
                glosariobutton.setVisibility(View.GONE);
                sharebutton.setVisibility(View.GONE);

                //  ActionBar actionBar = getActionBar();
                //  actionBar.hide();

                Rect rectangle = new Rect();
                Window window = getWindow();
                window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
                int statusBarHeight = rectangle.top;
                int contentViewTop = window.findViewById(Window.ID_ANDROID_CONTENT).getTop();
                int titleBarHeight= contentViewTop - statusBarHeight;

                Bitmap encabezado = null;
                encabezado = ScreenshotUtils.getScreenShot(encabezado2);






                int valor_altura = encabezado2.getHeight();
                int valor_altura2 = textoencabezado.getHeight();
                int suma =  valor_altura + valor_altura2 + statusBarHeight;


                previa.setImageBitmap(encabezado);

                int alto = encabezado.getHeight();

                Log.d("Notif", "alto del relative: " + suma);



                Bitmap jungle = null;
                jungle = ScreenshotUtils.getScreenShot(main);


                bitmap = snapshot;
                FileOutputStream out;
                File saveFile = ScreenshotUtils.getMainDirectoryName(getApplicationContext());

                Canvas canvas = new Canvas(jungle);
                canvas.drawBitmap(snapshot, (jungle.getWidth()) / -240, suma, new Paint());

                File file = ScreenshotUtils.store(jungle, "Parámetros_sísmicos.jpg",  saveFile);//save the screenshot to selected path
                shareScreenshot(file);

            }
        };
        mapx.snapshot(callback);
    }


}