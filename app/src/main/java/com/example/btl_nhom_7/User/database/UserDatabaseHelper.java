package com.example.btl_nhom_7.User.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.btl_nhom_7.User.model.User;

public class UserDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "motor.db";
    private static final int DATABASE_VERSION = 3;

    public UserDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng
        String createTableQuery = "CREATE TABLE IF NOT EXISTS user (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "phoneNumber TEXT , password TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableQuery = "DROP TABLE IF EXISTS user";
        db.execSQL(dropTableQuery);
        onCreate(db);
    }
    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("phoneNumber", user.getPhoneNumber());
        values.put("password", user.getPassword());
        db.insert("user", null, values);
        db.close();
    }
    public User getUser(String phoneNumber) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("user", null, "phoneNumber" + "=?",
                new String[]{phoneNumber}, null, null, null, null);
        User user = null;
        if (cursor != null && cursor.moveToFirst()) {
            String password = cursor.getString(cursor.getColumnIndexOrThrow("password"));
            user = new User(phoneNumber, password);
        }
        cursor.close();
        db.close();
        return user;
    }
    public int getUserIdByPhoneNumber(String phoneNumber) {
        SQLiteDatabase db = this.getReadableDatabase();
        int userId = -1; // Giá trị mặc định khi không tìm thấy

        Cursor cursor = db.query("user", new String[] {"id"},
                "phoneNumber"+ "=?", new String[] {phoneNumber},
                null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            userId = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            cursor.close();
        }

        db.close();
        return userId;
    }
    public void updatePassword(int id, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("password", newPassword);

        // Cập nhật mật khẩu mới dựa vào id
        db.update("user", values, "id = ?", new String[]{String.valueOf(id)});

        db.close();
    }
    public void updatePassword(String phoneNumber, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("password", newPassword);

        // Cập nhật mật khẩu mới dựa vào id
        db.update("user", values, "phoneNumber = ?", new String[]{String.valueOf(phoneNumber)});

        db.close();
    }
}
