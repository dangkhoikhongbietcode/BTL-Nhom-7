package com.example.btl_nhom_7.Motor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MotorDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "motor.db";
    private static final int DATABASE_VERSION = 3;

    private static final String TABLE_NAME = "motor";
    private static final String TBL_PDM_KEY_ID = "id";
    private static final String TBL_PDM_KEY_NAME = "name";
    private static final String TBL_PDM_KEY_PRICE = "price";

    public MotorDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s INTEGER)",TABLE_NAME, TBL_PDM_KEY_ID, TBL_PDM_KEY_NAME,TBL_PDM_KEY_PRICE);
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableQuery = "DROP TABLE IF EXISTS motor";
        db.execSQL(dropTableQuery);
        onCreate(db);
    }


    public List<Motor> getAllMotor() {
        List<Motor> listMotor = new ArrayList<>();
        String query = "SELECT * FROM " + "motor";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            int id = cursor.getInt(0) ;
            String name = cursor.getString(1);
            int price = cursor.getInt(2);


            listMotor.add(new Motor(name, price, ""));
            cursor.moveToNext();
        }

        return listMotor;
    }

    public void createMotor(Motor item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TBL_PDM_KEY_NAME, item.getName());
        values.put(TBL_PDM_KEY_PRICE, item.getPrice());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }


    public void clearMotorTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
    }

}
