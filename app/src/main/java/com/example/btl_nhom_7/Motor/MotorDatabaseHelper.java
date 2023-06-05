package com.example.btl_nhom_7.Motor;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MotorDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "motor.db";
    private static final int DATABASE_VERSION = 1;

    public MotorDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng
        String createTableQuery = "CREATE TABLE IF NOT EXISTS motor (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, price INTEGER)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xóa bảng xe máy nếu đã tồn tại và tạo lại
        String dropTableQuery = "DROP TABLE IF EXISTS motor";
        db.execSQL(dropTableQuery);
        onCreate(db);
    }
}
