package com.example.btl_nhom_7.Warranty.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.btl_nhom_7.Warranty.model.WarrantyModel;

import java.util.ArrayList;
import java.util.List;

public class WarrantyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "motor.db";
    private static final int DATABASE_VERSION = 1;

    public WarrantyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Tạo bảng FormWarranty
        String createFormWarrantyTableQuery = "CREATE TABLE IF NOT EXISTS FormWarranty (" +
                "NameWarranty TEXT," +
                "PhoneNumWarranty TEXT," +
                "WarrantyDate TEXT," +
                "Option TEXT," +
                "WarrantyMotor TEXT," +
                "WarrantyStore TEXT," +
                "id INTEGER," +
                "FOREIGN KEY (id) REFERENCES user (id))";

        db.execSQL(createFormWarrantyTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xóa bảng cũ nếu tồn tại và tạo lại
        db.execSQL("DROP TABLE IF EXISTS FormWarranty");
        onCreate(db);
    }

}
