package com.example.btl_nhom_7;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button loginBtn,forgotPWBtn;
    TextView tvForgotPW;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginBtn = findViewById(R.id.loginBtn);
        tvForgotPW = findViewById(R.id.tvForgotPW);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this , Home.class);
                startActivity(intent);
            }
        });
    }
    public void onForgotPasswordButtonClick(View v){
        Intent intent = new Intent(MainActivity.this,ForgotPasswordActivity.class);
        startActivity(intent);
    }
    public void onSignUpButtonClick(View v){
        Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
        startActivity(intent);
    }
}