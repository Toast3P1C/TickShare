package com.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.model.IUser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DBHelper extends SQLiteOpenHelper {

    private final static Logger LOG = LogManager.getLogger(DBHelper.class);

    private final static String TABLE_NAME = "user";
    private final static String ID = "ID";
    private final static String COL_NAME = "name";
    private final static String COL_LASTNAME = "lastname";
    private final static String COL_REGION = "region";
    private final static String COL_EMAILADDRESS = "emailaddress";
    private final static String COL_PASSWORDHASH = "passwordhash";
    private final static String COL_TOKEN = "usertoken";
    private final static String COL_SALT = "salt";

    public DBHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_NAME + " TEXT, " + COL_LASTNAME + " TEXT," + COL_REGION + " TEXT, " +
                COL_EMAILADDRESS + " TEXT, " + COL_PASSWORDHASH + " TEXT, " +
                COL_TOKEN + " TEXT, " + COL_SALT + " TEXT " + ")";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropIfTableExists = "DROP IF TABLE EXISTS " + TABLE_NAME;
        onCreate(db);
    }

    public boolean addData(IUser user) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, user.getName());
        values.put(COL_LASTNAME, user.getLastName());
        values.put(COL_REGION, user.getRegion());
        values.put(COL_EMAILADDRESS, user.getEmailAddress());
        values.put(COL_PASSWORDHASH, user.getPassword());
        values.put(COL_TOKEN, user.getToken());
        values.put(COL_SALT, user.getSalt());
        LOG.info("Putting values for" + user.toString() + " in the Table: " + TABLE_NAME);

        long result = database.insert(TABLE_NAME, null, values);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
    public Cursor getData(){
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;
        Cursor data = database.rawQuery(query,null);
        return data;
    }
}
