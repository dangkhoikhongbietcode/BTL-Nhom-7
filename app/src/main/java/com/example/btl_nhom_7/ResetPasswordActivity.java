package com.example.btl_nhom_7;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.btl_nhom_7.User.database.UserDatabaseHelper;
import com.example.btl_nhom_7.User.model.User;

public class ResetPasswordActivity extends AppCompatActivity {
    EditText editPassword, editPasswordRepeat;
    Button btnSubmit;
    UserDatabaseHelper userDatabaseHelper;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        editPassword = findViewById(R.id.editPassword);
        editPasswordRepeat = findViewById(R.id.editPasswordRepeat);
        btnSubmit = findViewById(R.id.btnSubmit);
        userDatabaseHelper = new UserDatabaseHelper(this);
        SharedPreferences sharedPreferences =getSharedPreferences("MyPrefs2", Context.MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLoggedIn) {
                    String phoneNumber = sharedPreferences.getString("phoneNumber", "");
                    String password = editPassword.getText().toString();
                    if (checkPasswordRepeat()){
                        User user = new User(phoneNumber, password);
                        if (user.checkValidCapsChar() && user.checkValid8Char() && user.checkValidNumberChar()){
                            userDatabaseHelper.updatePassword(phoneNumber,password);
                            Toast.makeText(getApplicationContext(),"Thay đổi mật khẩu thành công",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ResetPasswordActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Mật khẩu không hợp lệ",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Mật khẩu không trùng khớp",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public boolean checkPasswordRepeat(){
        String password = editPassword.getText().toString();
        String passwordRepeat = editPasswordRepeat.getText().toString();
        if (password.equals(passwordRepeat)) return true;
        return false;
    }
}