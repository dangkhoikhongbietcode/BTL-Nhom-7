package com.example.btl_nhom_7.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.btl_nhom_7.Motor.Motor;
import com.example.btl_nhom_7.R;
import com.example.btl_nhom_7.database.DatabaseHelper;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MotorDetailActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView nameTextView;
    private TextView priceTextView;
    private TextView detailsTextView;

    private DatabaseHelper databaseHelper;
     private Motor motor;

    @SuppressLint("SuspiciousIndentation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor_detail);

        // Initialize views
        imageView = findViewById(R.id.iv_motor_image);
        nameTextView = findViewById(R.id.tv_motor_details_head);
        priceTextView = findViewById(R.id.tv_motor_details);
        detailsTextView = findViewById(R.id.tv_motor_details_weight);

        // Get the motor json from the intent
        Intent intent = getIntent();
        String motorstr = intent.getStringExtra("Motor");
        Gson gson = new Gson();
        motor = gson.fromJson(motorstr, Motor.class);

        // Create an instance of the DatabaseHelper class
        databaseHelper = new DatabaseHelper(this);


        // Retrieve motor details from the database
//        Motor motor = (Motor) databaseHelper.getAllMotor();
//
//        // Display motor details in the views
//        if (motor != null) {
//            Glide.with(this)
//                    .load(motor.getImage())
//                    .into(imageView);
//            nameTextView.setText(motor.getDetailshead());
//            priceTextView.setText(motor.getDetails());
//            detailsTextView.setText(motor.getDetailsweight());
//        }
//        ArrayList<Motor> motors = (ArrayList<Motor>) databaseHelper.getAllMotor();
//
//// Display motor details in the views
//        if (motors != null && !motors.isEmpty()) {
//            Motor motor = motors.get(0); // Lấy phần tử đầu tiên từ danh sách motors
            Glide.with(this)
                    .load(motor.getImage())
                    .into(imageView);
            nameTextView.setText(motor.getName().toString());
            priceTextView.setText("Giá tiền: "+Integer.toString(motor.getPrice()));
            detailsTextView.setText("Mô tả: "+ motor.getDetails().toString());
//        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the database connection
        databaseHelper.close();
    }
}
