package com.example.btl_nhom_7.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.btl_nhom_7.Motor.Motor;
import com.example.btl_nhom_7.User.model.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "motor.db";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_MOTOR_NAME = "motor";
    private static final String TBL_MOTOR_KEY_ID = "id";
    private static final String TBL_MOTOR_KEY_NAME = "name";

    private static final String TBL_MOTOR_KEY_PRICE = "price";
    private static final String TBL_MOTOR_KEY_DETAILS = "details";
    private static final String TBL_MOTOR_KEY_DETAILSHEAD = "details_head";
    private static final String TBL_MOTOR_KEY_DETAILWEIGHT = "details_weight";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableMotor = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s INTEGER, %s TEXT)",TABLE_MOTOR_NAME, TBL_MOTOR_KEY_ID, TBL_MOTOR_KEY_NAME, TBL_MOTOR_KEY_PRICE, TBL_MOTOR_KEY_DETAILS);
        String createTableUser = "CREATE TABLE IF NOT EXISTS user (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "phoneNumber TEXT , password TEXT)";
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
        db.execSQL(createTableMotor);
        db.execSQL(createTableUser);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableMotor = String.format("DROP TABLE IF EXISTS %s" , TABLE_MOTOR_NAME) ;
        String dropTableUser = "DROP TABLE IF EXISTS user";
        db.execSQL("DROP TABLE IF EXISTS FormWarranty");
        db.execSQL(dropTableMotor);
        db.execSQL(dropTableUser);
        onCreate(db);
    }


    public List<Motor> getAllMotor() {
        List<Motor> listMotor = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_MOTOR_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            int id = cursor.getInt(0) ;
            String name = cursor.getString(1);
            int price = cursor.getInt(2);
            int details = cursor.getInt(3);

            listMotor.add(new Motor(name, price, ""));
            cursor.moveToNext();
        }

        return listMotor;
    }

    public void createMotor(Motor item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TBL_MOTOR_KEY_NAME, item.getName());
        values.put(TBL_MOTOR_KEY_PRICE, item.getPrice());
        values.put(TBL_MOTOR_KEY_DETAILS, item.getDetails());
        db.insert(TABLE_MOTOR_NAME, null, values);
        db.close();
    }


    public void clearMotorTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MOTOR_NAME, null, null);
    }


    /// user

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
