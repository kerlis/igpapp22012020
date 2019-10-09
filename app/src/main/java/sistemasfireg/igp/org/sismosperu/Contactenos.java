package sistemasfireg.igp.org.sismosperu;
import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Contactenos extends AppCompatActivity {
    TextView texto;
    TextView email;
    Button enviar;
    String texto_val;
    String email_val;
    private OkHttpClient client = new OkHttpClient();
    String TAG_REGISTER;
    String file_name;
    private static final String BASE_URL = "http://arteypixel.com/admvolcanestrtes/contactanossismosperu.php";
    private ProgressBar spinner;
    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private RelativeLayout mDrawerBlock;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;
    ImageView sliderz;
    RelativeLayout blocke1a;
    RelativeLayout blocke2a;
    RelativeLayout blocke4a;
    RelativeLayout blocke5a;
    RelativeLayout blocke9;
    RelativeLayout menuright;
    RelativeLayout blocke6a;
    RelativeLayout blocke12;
    RelativeLayout blocke92;

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    private static final int READ_REQUEST_CODE = 42;
    ImageButton attachfile;
    ProgressDialog dialog = null;
    final String uploadFilePath = "/mnt/sdcard/";
    final String uploadFileName = "service_lifecycle.png";
    String upLoadServerUri = null;
    int serverResponseCode = 0;
    int PICK_REQUEST_CODE = 0;
    InputStream is;
    EditText urlfile;
    String filevalue;

    ImageButton descartar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactenos);

        Snackbar.make(findViewById(android.R.id.content),"Mantén actualizada la aplicación", Snackbar.LENGTH_LONG)
                .setActionTextColor(ContextCompat.getColor(this, R.color.celesteigp))
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

        descartar =  findViewById(R.id.discardfile);
        descartar.setVisibility(View.GONE);
        urlfile = findViewById(R.id.urlfile);
        urlfile.setHint("Adjunta algun archivo");
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        texto = findViewById(R.id.texto);
        email = findViewById(R.id.email);
        enviar = findViewById(R.id.enviar);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filevalue =  urlfile.getText().toString();
                texto_val =   texto.getText().toString();
                email_val =   email.getText().toString();
                if(texto_val.length()<2 || email_val.length()<2){
                    Toast.makeText(getApplicationContext(), "Completar los datos" , Toast.LENGTH_LONG).show();
                }
                else if((!CheckEmail(email_val))){
                    Toast.makeText(getApplicationContext(), "Ingresar un correo electronico valido" , Toast.LENGTH_LONG).show();
                }
                else{
                    spinner.setVisibility(view.VISIBLE);

                    enviaremail(texto_val, email_val, filevalue);
                }
            }
        });

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

        attachfile = findViewById(R.id.attachfile);
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
            Intent intent = new Intent(Contactenos.this, Ultimosismo4.class);
            startActivity(intent);
        }
    });
        blocke2a.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View arg0) {
            Intent intent = new Intent(Contactenos.this, Ultimosismoslist.class);
            startActivity(intent);
        }
    });
        blocke3a.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View arg0) {
            Intent intent = new Intent(Contactenos.this, Listadoenmapa.class);
            startActivity(intent);
        }
    });
        blocke4a.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View arg0) {
            Intent intent = new Intent(Contactenos.this, Notificacionesconfig.class);
            startActivity(intent);
        }
    });
        blocke5a.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View arg0) {
            Intent intent = new Intent(Contactenos.this, Glosarioestatico.class);
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
            Intent intent = new Intent(Contactenos.this, Listadoredessociales.class);
            startActivity(intent);
        }
    });

        blocke92.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Contactenos.this, Contactenos.class);
                startActivity(intent);
            }
        });

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);
                return;
            }
        }
        enable_button();


        descartar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            descartar.setVisibility(View.GONE);
                            attachfile.setVisibility(View.VISIBLE);
                            urlfile.setText("");
            }
        });
    }

    public void enviaremail(String texto, String email, String urlvalue) {
        spinner.setVisibility(View.VISIBLE);
        RequestBody body = new FormBody.Builder()
                .add("Informacion", texto+"&"+email+"&"+urlvalue)
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
                     //   Toast.makeText(getApplicationContext(), "Datos enviados correctamente" , Toast.LENGTH_LONG).show();
                    } else {
                    ///    Toast.makeText(getApplicationContext(), "envio fallido" , Toast.LENGTH_LONG).show();

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
                spinner.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),   dato , Toast.LENGTH_LONG).show();
            }
        });
    }

    void setupDrawerToggle(){
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,R.string.app_name, R.string.app_name);
        mDrawerToggle.syncState();
    }

    private boolean CheckEmail(String sEmailId) {

        return EMAIL_PATTERN.matcher(sEmailId).matches();
    }

    private void enable_button() {
        attachfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialFilePicker()
                            .withActivity(Contactenos.this)
                        .withRequestCode(10)
                        .start();
            }
        });
    }

    ProgressDialog progress;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 100 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)){
            enable_button();
        }else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {

        if(data!=null){
            File f2  = new File(data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH));
            String content_type  = getMimeType(f2.getPath());
            String file_path2 = f2.getAbsolutePath();
            urlfile.setText(file_path2.substring(file_path2.lastIndexOf("/")+1));

            attachfile.setVisibility(View.GONE);

            descartar.setVisibility(View.VISIBLE);
        }

        if(requestCode == 10 && resultCode == RESULT_OK){

            progress = new ProgressDialog(Contactenos.this);
           progress.setTitle("Cargando Archivo");
            progress.setMessage("Espere por favor...");
            progress.show();

            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    File f  = new File(data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH));
                    String content_type  = getMimeType(f.getPath());

                    String file_path = f.getAbsolutePath();
                    OkHttpClient client = new OkHttpClient();
                    RequestBody file_body = RequestBody.create(MediaType.parse(content_type),f);

                    RequestBody request_body = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM)
                            .addFormDataPart("type",content_type)
                            .addFormDataPart("uploaded_file",file_path.substring(file_path.lastIndexOf("/")+1), file_body)
                            .build();

                    Log.i("NOMBREFILE", file_path.substring(file_path.lastIndexOf("/")+1));


                    Request request = new Request.Builder()
                            .url("http://www.arteypixel.com/admvolcanestrtes/enviararchivo.php")
                            .post(request_body)
                            .build();

                    try {
                        Response response = client.newCall(request).execute();

                        if(!response.isSuccessful()){
                            throw new IOException("Error : "+response);
                        }

                        progress.dismiss();
                      //  attachfile.setVisibility(View.GONE);

                        //descartar.setVisibility(View.VISIBLE);


                       // progress.hide();
                     //   enviarurl();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            t.start();
        }
    }

    private String getMimeType(String path) {
        String extension = MimeTypeMap.getFileExtensionFromUrl(path);
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
    }
}
