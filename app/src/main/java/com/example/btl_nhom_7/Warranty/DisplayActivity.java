package com.example.btl_nhom_7.Warranty;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.btl_nhom_7.R;
import com.example.btl_nhom_7.database.DatabaseHelper;

public class DisplayActivity extends AppCompatActivity {

    private TextView textViewName;
    private TextView textViewWarrantyDate;
    private TextView textViewOption;
    private TextView textViewMotor;
    private TextView textViewStore;
    private Button buttonBack;

    public DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        textViewName = findViewById(R.id.textViewName);
        textViewWarrantyDate = findViewById(R.id.textViewWarrantyDate);
        textViewOption = findViewById(R.id.textViewOption);
        textViewMotor = findViewById(R.id.textViewMotor);
        textViewStore = findViewById(R.id.textViewStore);
        buttonBack = findViewById(R.id.buttonBack);

        SharedPreferences sharedPreferences = getSharedPreferences("Warranty", Context.MODE_PRIVATE);
        String nameWarranty = sharedPreferences.getString("NameWarranty","");
        String warrantyDate = sharedPreferences.getString("WarrantyDate","");
        String option = sharedPreferences.getString("Option","");
        String motor = sharedPreferences.getString("WarrantyMotor","");
        String store = sharedPreferences.getString("WarrantyStore","");

        textViewName.setText("Họ Tên: " + nameWarranty);
        textViewWarrantyDate.setText("Lịch bảo hành: " + warrantyDate);
        textViewOption.setText("Loại dịch vụ: " + option);
        textViewMotor.setText("Loại xe: " + motor);
        textViewStore.setText("Cửa hàng: " + store);
        // Xử lý sự kiện khi nhấn nút Back
        buttonBack.setOnClickListener(view -> onBackPressed());

    }
}