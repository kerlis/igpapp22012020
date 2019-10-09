package sistemasfireg.igp.org.sismosperu;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.HashMap;



public class DbHandler extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;

    private static final String DB_NAME = "notificacionesdb";
    private static final String TABLE_Users = "userdetails";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_LOC = "location";
    private static final String KEY_DESG = "designation";

    private static final String TABLE_Notificaciones = "notificaciones";
    private static final String KEY_CODIGO = "codigo";
    private static final String KEY_CATEGORIA = "categoria";
    private static final String KEY_EPICENTRO = "epicentro";
    private static final String KEY_FECHAUTC = "fechautc";
    private static final String KEY_HORAUTC = "horautc";
    private static final String KEY_INTENSO= "intenso";
    private static final String KEY_LAT = "lat";
    private static final String KEY_LON = "lon";
    private static final String KEY_MAGNITUD = "magnitud";
    private static final String KEY_PROFUNDIDAD = "profundidad";
    private static final String KEY_REFERENCIA = "referencia";
    private static final String KEY_SIMULARO= "simulacro";
    private static final String KEY_TIPODEREPORTE = "tipodereporte";


    public DbHandler(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){

        String CREATE_TABLE = "CREATE TABLE " + TABLE_Users + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                + KEY_LOC + " TEXT,"
                + KEY_DESG + " TEXT"+ ")";
        db.execSQL(CREATE_TABLE);


        String CREATE_TABLE_NOTIFICACIONES = "CREATE TABLE " + TABLE_Notificaciones + "("
                + KEY_CODIGO + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_CATEGORIA + " TEXT,"
                + KEY_EPICENTRO + " TEXT,"
                + KEY_FECHAUTC + " TEXT,"
                + KEY_HORAUTC + " TEXT,"
                + KEY_INTENSO + " TEXT,"
                + KEY_LAT + " TEXT,"
                + KEY_LON + " TEXT,"
                + KEY_MAGNITUD + " TEXT,"
                + KEY_PROFUNDIDAD + " TEXT,"
                + KEY_REFERENCIA + " TEXT,"
                + KEY_SIMULARO + " TEXT,"
                + KEY_TIPODEREPORTE + " TEXT" + ")";
        db.execSQL(CREATE_TABLE_NOTIFICACIONES);


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Notificaciones);
        // Create tables again
        onCreate(db);
    }
    // **** CRUD (Create, Read, Update, Delete) Operations ***** //

    // Adding new User Details

    void insertUserDetails(String categoria,
                           String epicentro,
                           String fechautc,
                           String horautc,
                           String intenso,
                           String lat,
                           String lon,
                           String magnitud,
                           String profundidad,
                           String referencia,
                           String simulacro,
                           String tipodereporte){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_CATEGORIA, categoria);
        cValues.put(KEY_EPICENTRO, epicentro);
        cValues.put(KEY_FECHAUTC, fechautc);
        cValues.put(KEY_HORAUTC, horautc);
        cValues.put(KEY_INTENSO, intenso);
        cValues.put(KEY_LAT, lat);
        cValues.put(KEY_LON, lon);
        cValues.put(KEY_MAGNITUD, magnitud);
        cValues.put(KEY_PROFUNDIDAD, profundidad);
        cValues.put(KEY_REFERENCIA, referencia);
        cValues.put(KEY_SIMULARO, simulacro);
        cValues.put(KEY_TIPODEREPORTE, tipodereporte);



        // Insert the new row, returning the primary key value of the new row
        //long newRowId = db.insert(TABLE_Users,null, cValues);

        long newRowId = db.insert(TABLE_Notificaciones,null, cValues);
        db.close();
    }


    // Get User Details
    public ArrayList<HashMap<String, String>> GetUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT name, location, designation FROM "+ TABLE_Users;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("categoria",cursor.getString(cursor.getColumnIndex(KEY_CATEGORIA)));
            user.put("epicentro",cursor.getString(cursor.getColumnIndex(KEY_EPICENTRO)));
            user.put("fechautc",cursor.getString(cursor.getColumnIndex(KEY_FECHAUTC)));
            user.put("horautc",cursor.getString(cursor.getColumnIndex(KEY_HORAUTC)));
            user.put("intenso",cursor.getString(cursor.getColumnIndex(KEY_INTENSO)));
            user.put("lat",cursor.getString(cursor.getColumnIndex(KEY_LAT)));
            user.put("lon",cursor.getString(cursor.getColumnIndex(KEY_LON)));
            user.put("magnitud",cursor.getString(cursor.getColumnIndex(KEY_MAGNITUD)));
            user.put("profundidad",cursor.getString(cursor.getColumnIndex(KEY_PROFUNDIDAD)));
            user.put("referencia",cursor.getString(cursor.getColumnIndex(KEY_REFERENCIA)));
            user.put("simulacro",cursor.getString(cursor.getColumnIndex(KEY_SIMULARO)));
            user.put("tipodereporte",cursor.getString(cursor.getColumnIndex(KEY_TIPODEREPORTE)));
            userList.add(user);
        }
        return  userList;
    }
    // Get User Details based on userid
    public ArrayList<HashMap<String, String>> GetUserByUserId(int userid){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT name, location, designation FROM "+ TABLE_Users;
        Cursor cursor = db.query(TABLE_Users, new String[]{KEY_NAME, KEY_LOC, KEY_DESG}, KEY_ID+ "=?",new String[]{String.valueOf(userid)},null, null, null, null);
        if (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("name",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            user.put("designation",cursor.getString(cursor.getColumnIndex(KEY_DESG)));
            user.put("location",cursor.getString(cursor.getColumnIndex(KEY_LOC)));
            userList.add(user);
        }
        return  userList;
    }
    // Delete User Details
    public void DeleteUser(int userid){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Users, KEY_ID+" = ?",new String[]{String.valueOf(userid)});
        db.close();
    }
    // Update User Details
    public int UpdateUserDetails(String location, String designation, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(KEY_LOC, location);
        cVals.put(KEY_DESG, designation);
        int count = db.update(TABLE_Users, cVals, KEY_ID+" = ?",new String[]{String.valueOf(id)});
        return  count;
    }
}
