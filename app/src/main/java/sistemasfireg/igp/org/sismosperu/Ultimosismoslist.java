package sistemasfireg.igp.org.sismosperu;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import me.leolin.shortcutbadger.ShortcutBadger;
import sistemasfireg.igp.org.sismosperu.m_model.messages;
import sistemasfireg.igp.org.sismosperu.m_ui.CustomAdapter;
public class Ultimosismoslist extends Activity {
    private Button mk;
    private final ArrayList<messages> objetosismos= new ArrayList<>();
    private CustomAdapter adapter;
    private ListView lv;
    private DrawerLayout mDrawerLayout;
    private android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;


    RelativeLayout blocke92;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultimossismoslist);

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
        ShortcutBadger.applyCount(context, 0); //for 1.1.4+
        ConnectivityManager cmanager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = cmanager != null ? cmanager.getActiveNetworkInfo() : null;
        if(info!= null && info.isConnected()){
            if(info.getType() == ConnectivityManager.TYPE_WIFI) {
                Toast.makeText(Ultimosismoslist.this, "Conexión Establecida", Toast.LENGTH_LONG).show();
            }
            else if(info.getType() == ConnectivityManager.TYPE_MOBILE){
                Toast.makeText(Ultimosismoslist.this, "Conexión Establecida", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(Ultimosismoslist.this, "Su Equipo ha Bloquedo la Conexión", Toast.LENGTH_LONG).show();
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mFirebaseDatabase5 = database.getReference("messages");
        mFirebaseDatabase5.keepSynced(true);
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



        blocke1a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Ultimosismoslist.this,Ultimosismo4.class);
                startActivity(intent);
            }
        });
        blocke2a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Ultimosismoslist.this,Ultimosismoslist.class);
                startActivity(intent);
            }
        });
        blocke3a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Ultimosismoslist.this,Listadoenmapa.class);
                startActivity(intent);
            }
        });
        blocke4a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {




              //  Intent intent = new Intent(Ultimosismoslist.this,Configuraciones.class);

                Intent intent = new Intent(Ultimosismoslist.this,Notificacionesconfig.class);
                startActivity(intent);
            }
        });
        blocke5a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Ultimosismoslist.this,Glosarioestatico.class);
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
                Intent intent = new Intent(Ultimosismoslist.this,Listadoredessociales.class);
                startActivity(intent);
            }
        });


        blocke92.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Ultimosismoslist.this, Contactenos.class);
                startActivity(intent);
            }
        });


        lv = findViewById(R.id.lv);
        adapter=new CustomAdapter(Ultimosismoslist.this,retrieve());
        lv.setAdapter(adapter);
        mk= findViewById(R.id.button);

        TextView Button = findViewById(R.id.txtLostpassword);

        Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://ultimosismo.igp.gob.pe/bdsismos/ultimosSismosSentidos.php#tabs-2"));
                startActivity(intent);
            }
        });

        mk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(Ultimosismoslist.this, mk);
                popup.getMenuInflater().inflate(R.menu.menu_main, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();

                        if (id == R.id.ultimo_sismo) {
                            Intent intent = new Intent(Ultimosismoslist.this,Ultimosismo4.class);
                            startActivity(intent);
                            return true;
                        }
                        if (id == R.id.listado_ultimos_sismos) {
                            Intent intent = new Intent(Ultimosismoslist.this,Ultimosismoslist.class);
                            startActivity(intent);
                            return true;
                        }
                        if (id == R.id.mapa_sismos) {
                            Intent intent = new Intent(Ultimosismoslist.this,Listadoenmapa.class);
                            startActivity(intent);
                            return true;
                        }
                        if (id == R.id.glosario) {
                            Intent intent = new Intent(Ultimosismoslist.this,Glosarioestatico.class);
                            startActivity(intent);
                            return true;
                        }
                        if (id == R.id.notificarsino) {
                            Intent intent = new Intent(Ultimosismoslist.this,Notificacionesconfig.class);
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
                        return Ultimosismoslist.super.onOptionsItemSelected(item);
                    }
                });
                popup.show();
            }
        });
    }

    private void setupDrawerToggle(){
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,R.string.app_name, R.string.app_name);
        mDrawerToggle.syncState();
    }

    private ArrayList<messages> retrieve() {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance("https://igpsismos2.firebaseio.com/").getReference("messages");
        mDatabase.orderByKey().limitToLast(20).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                messages objetosismo = dataSnapshot.getValue(messages.class);
                objetosismos.add(objetosismo);
                int iSwapCount = objetosismos.size() - 1;
                int iPosition = objetosismos.size()- 1;
                for (int j = 0; j < iSwapCount; j++)
                {
                    Collections.swap(objetosismos, iPosition, iPosition - 1);
                    iPosition = iPosition - 1;
                }
                lv.setAdapter(adapter);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                messages objetosismo = dataSnapshot.getValue(messages.class);
                objetosismos.add(objetosismo);

                int iSwapCount = objetosismos.size() - 1;
                int iPosition = objetosismos.size()- 1;
                for (int j = 0; j < iSwapCount; j++)
                {
                    Collections.swap(objetosismos, iPosition, iPosition - 1);
                    iPosition = iPosition - 1;



                 }
                lv.setAdapter(adapter);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return objetosismos;
    }
}