package com.example.btl_nhom_7.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btl_nhom_7.MainActivity;
import com.example.btl_nhom_7.R;
import com.example.btl_nhom_7.User.database.UserDatabaseHelper;
import com.example.btl_nhom_7.User.model.User;

public class ChangePasswordActivity extends AppCompatActivity {
    TextView tvGetIdOnLogo;
    UserDatabaseHelper userDatabaseHelper;
    Button btnSubmit;
    EditText editOldPassword,editNewPassword,editPasswordRepeat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        tvGetIdOnLogo = findViewById(R.id.tvGetIdOnLogo);
        btnSubmit = findViewById(R.id.btnSubmit);
        editOldPassword = findViewById(R.id.editOldPassword);
        editNewPassword = findViewById(R.id.editNewPassword);
        editPasswordRepeat = findViewById(R.id.editPasswordRepeat);
        userDatabaseHelper = new UserDatabaseHelper(this);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
        if (isLoggedIn) {
            // Người dùng đã đăng nhập
            int id = sharedPreferences.getInt("id", 0);
            tvGetIdOnLogo.setText(String.valueOf(id));
        }
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
                if (isLoggedIn) {
                    String password = sharedPreferences.getString("password", "");
                    String oldPassword = editOldPassword.getText().toString();
                    String newPassword = editNewPassword.getText().toString();
                    int id = sharedPreferences.getInt("id", 0);
                    if (oldPassword.equals(password)){
                        if (checkPasswordRepeat()){
                            if (checkValidPassword()){
                                userDatabaseHelper.updatePassword(id,newPassword);
                                Toast.makeText(getApplicationContext(),"Thay đổi mật khẩu thành công",Toast.LENGTH_SHORT).show();
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.remove("isLoggedIn");
                                editor.remove("phoneNumber");
                                editor.remove("id");
                                editor.remove("password");
                                editor.apply();
                                // Điều hướng đến màn hình đăng nhập
                                Intent intent = new Intent(ChangePasswordActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Mật khẩu không hợp lệ",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Mật khẩu không trùng khớp",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Mật khẩu cũ không đúng",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public boolean checkPasswordRepeat(){
        String password = editNewPassword.getText().toString();
        String passwordRepeat = editPasswordRepeat.getText().toString();
        if (password.equals(passwordRepeat)) return true;
        return false;
    }
    public boolean checkValidPassword(){
        String password = editNewPassword.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
        if (isLoggedIn){
            String phoneNumber = sharedPreferences.getString("phoneNumber", "");
            User user = new User(phoneNumber,password);
            if (user.checkValidNumberChar()&&user.checkValid8Char()&&user.checkValidCapsChar()){
                return true;
            }
        }
        return false;
    }
}