package sistemasfireg.igp.org.sismosperu;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Databasehelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Sismos.db";
    public static final String TABLE_NAME = "Sismos_table";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CATEGORIA = "categoria";
    public static final String COLUMN_EPICENTRO = "epicentro";
    public static final String COLUMN_FECHAUTC = "fechautc";
    public static final String COLUMN_HORAURC = "horautc";
    public static final String COLUMN_INTENSO = "intenso";
    public static final String COLUMN_LAT = "lat";
    public static final String COLUMN_LON = "lon";
    public static final String COLUMN_MAGNITUD = "magnitud";
    public static final String COLUMN_PROFUNDIDAD = "profundidad";
    public static final String COLUMN_REFERENCIA = "referencia";
    public static final String COLUMN_SIMULACRO = "simulacro";
    public static final String COLUMN_TIPOREPORTE = "tiporeporte";

    public Databasehelper(Context context ) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     //   db.execSQL("create table "  + TABLE_NAME +" (id INTEGER PRIMARY KEY AUTOINCREMENT, fechautc TEXT, horautc TEXT, magnitud TEXT)");

        db.execSQL("create table "  + TABLE_NAME +" (id INTEGER PRIMARY KEY, fechautc TEXT, horautc TEXT, magnitud TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // onUpgrade(db, oldVersion, newVersion);
      //  db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
      //  onCreate(db);
    }


//    public  boolean insertData(String fechautc, String horutc, String magnitud,String valorigual){

        public  boolean insertData(String fechautc, String horutc, String magnitud){
        SQLiteDatabase db =  this.getWritableDatabase();

      //  SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =  new ContentValues();

      //  contentValues.put(COLUMN_ID, id);

       // contentValues.put(COLUMN_FECHAUTC, fechautc);

        contentValues.put(COLUMN_FECHAUTC, fechautc);
        contentValues.put(COLUMN_HORAURC, horutc);
        contentValues.put(COLUMN_MAGNITUD, magnitud);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean updateDataRow(String id, String fechautc, String horautc, String magnitud){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =  new ContentValues();
        //String selection = FeedEntry.COLUMN_NAME_TITLE + " LIKE ?";
        //contentValues.put(COLUMN_ID, id);
        contentValues.put(COLUMN_FECHAUTC, fechautc);
        contentValues.put(COLUMN_HORAURC, horautc);
        contentValues.put(COLUMN_MAGNITUD, magnitud);
        long result = db.update(TABLE_NAME, contentValues, "ID = ?", new String[] {id} );
        if(result == -1)
            return false;
        else
            return true;

    }


/*
    public void getNote(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(Note.TABLE_NAME,
                new String[]{Note.COLUMN_ID, Note.COLUMN_NOTE, Note.COLUMN_TIMESTAMP},
                Note.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Note note = new Note(
                cursor.getInt(cursor.getColumnIndex(Note.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Note.COLUMN_NOTE)),
                cursor.getString(cursor.getColumnIndex(Note.COLUMN_TIMESTAMP)));

        // close the db connection
        cursor.close();

        return note;
    }
    */
/*
    private Cursor getSingleRow(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cur = db.query(TABLE_NAME, null,
                "_id= ?", new String[] { "" + id }, null,
                null, null);
        if (cur != null) {
            cur.moveToFirst();
        }
        return cur;
    }


    public String getEmployeeName(String empNo) {
        Cursor cursor = null;
        String empName = "";
        try {
            cursor = SQLiteDatabase.rawQuery("SELECT EmployeeName FROM Employee WHERE EmpNo=?", new String[] {empNo + ""});
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                empName = cursor.getString(cursor.getColumnIndex("EmployeeName"));
            }
            return empName;
        }finally {
            cursor.close();
        }
    }
*/
    public Cursor getcontact(String id, SQLiteDatabase db){
        String[]  projections = {Sismossqlite.NewSismossqlite.COLUMN_ID, Sismossqlite.NewSismossqlite.COLUMN_FECHAUTC, Sismossqlite.NewSismossqlite.COLUMN_HORAURC, Sismossqlite.NewSismossqlite.COLUMN_MAGNITUD};
        String selection = Sismossqlite.NewSismossqlite.COLUMN_ID+ "= ?";
        String[] selection_args = {id};
        Cursor cursor = db.query(Sismossqlite.NewSismossqlite.table_name,projections, selection,selection_args, null,null,null);
        return  cursor;
    }

}

