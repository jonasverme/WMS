package be.marbleous.wml2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import be.marbleous.wml2.Models.Pickslip;

/**
 * Created by jonasvermeulen on 14/11/15.
 */
public class DatabaseHandler extends SQLiteOpenHelper
{

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "WMS";

    // Contacts table name
    private static final String TABLE_PICKSLIPS = "pickslips";
    private static final String TABLE_PICKSLIPLINES = "picksliplines";
    private static final String TABLE_RESERVATIONLOCATIONS = "reservationlocations";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";


    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_PICKSLIP_TABLE = "CREATE TABLE" + TABLE_PICKSLIPS + "("
                + KEY_ID + " INTEGER PRIMARY KEY, " + ")";
        sqLiteDatabase.execSQL(CREATE_PICKSLIP_TABLE);

        String CREATE_PICKSLIPLINE_TABLE = "CREATE TABLE" + TABLE_PICKSLIPLINES + "("
                + KEY_ID + " INTEGER PRIMARY KEY, " + ")";
        sqLiteDatabase.execSQL(CREATE_PICKSLIPLINE_TABLE);

        String CREATE_RESERVATIONLOCATION_TABLE = "CREATE TABLE " + TABLE_RESERVATIONLOCATIONS + "("
                + KEY_ID + " INTEGER PRIMARY KEY, " + ")";
        sqLiteDatabase.execSQL(CREATE_RESERVATIONLOCATION_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLE_PICKSLIPS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLE_PICKSLIPLINES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLE_RESERVATIONLOCATIONS);

        onCreate(sqLiteDatabase);
    }


    //pickslips
    public void addPickslip(Pickslip p){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
      //  values.put(KEY_NAME, contact.getName()); // Contact Name
      //  values.put(KEY_PH_NO, contact.getPhoneNumber()); // Contact Phone Number

        // Inserting Row
        db.insert(TABLE_PICKSLIPS, null, values);
        db.close(); // Closing database connection
    }

    public void deletePickslip(){

    }

    public void updatePickslip(){

    }

    public void getPickslip(){
    /*    SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PICKSLIPS, new String[] { KEY_ID,
                        KEY_NAME, KEY_PH_NO }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Pickslip pickslip = new Pickslip(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return contact
        return pickslip;*/
    }

    //end pickslips




}
