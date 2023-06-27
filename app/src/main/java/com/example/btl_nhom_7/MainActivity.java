package com.example.btl_nhom_7;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btl_nhom_7.Motor.MotorActivity;
import com.example.btl_nhom_7.User.database.UserDatabaseHelper;
import com.example.btl_nhom_7.User.model.User;

public class MainActivity extends AppCompatActivity {
    Button loginBtn,forgotPWBtn;
    TextView tvForgotPW;
    EditText editPhoneNumber,editPassword;
    Context context;
    UserDatabaseHelper userDatabaseHelper;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginBtn = findViewById(R.id.btnSubmit);
        tvForgotPW = findViewById(R.id.tvForgotPW);
        editPassword = findViewById(R.id.editPassword);
        editPhoneNumber = findViewById(R.id.editOldPassword);
        editPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        userDatabaseHelper = new UserDatabaseHelper(this);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = editPhoneNumber.getText().toString();
                String password = editPassword.getText().toString();
                User user = userDatabaseHelper.getUser(phoneNumber);
                if (user != null && user.getPassword().equals(password)) {
                    Toast.makeText(getApplicationContext(),"Đăng nhập thành công!",Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("phoneNumber", phoneNumber);
                    editor.putString("password",password);
                    editor.putInt("id", userDatabaseHelper.getUserIdByPhoneNumber(phoneNumber));
                    editor.putBoolean("isLoggedIn", true);
                    editor.apply();
                    Intent intent = new Intent(MainActivity.this, Home.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(),"Sai tài khoản hoặc mật khẩu!",Toast.LENGTH_SHORT).show();
                }

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