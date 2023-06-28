package com.example.btl_nhom_7.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.btl_nhom_7.Motor.Motor;
import com.example.btl_nhom_7.database.DatabaseHelper;
import com.example.btl_nhom_7.R;
import com.google.gson.Gson;

public class MotorDetailActivity extends AppCompatActivity {

    private TextView textView;
    private DatabaseHelper databaseHelper;
    Motor motor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor_detail);
        Intent intent = getIntent();
        String motorstr = intent.getStringExtra("Motor");
        Gson gson = new Gson();
        motor = gson.fromJson(motorstr, Motor.class);

        textView = findViewById(R.id.textView);
        databaseHelper = new DatabaseHelper(this);

        // Nhận dữ liệu được chuyển từ MotorActivity
        String selectedItem = getIntent().getStringExtra("selectedItem");

        // Hiển thị chi tiết của xe máy được chọn

        textView.setText("Chi tiết của xe máy: " + motor.getName() + "\nGiá: " + motor.getPrice());
    }

    // Lấy giá của xe máy từ cơ sở dữ liệu
    private String getMotorPrice(String name) {
        String price = "";
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String[] projection = {"price"};
        String selection = "name=?";
        String[] selectionArgs = {name};
        Cursor cursor = db.query("motor", projection, selection, selectionArgs, null, null, null);
        if (cursor.moveToNext()) {
            price = cursor.getString(cursor.getColumnIndexOrThrow("price"));
        }
        cursor.close();
        db.close();
        return price;
    }
}
