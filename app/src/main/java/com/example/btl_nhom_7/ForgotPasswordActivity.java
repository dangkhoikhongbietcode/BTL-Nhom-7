package com.example.btl_nhom_7;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.btl_nhom_7.User.database.UserDatabaseHelper;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

public class ForgotPasswordActivity extends AppCompatActivity {
    EditText editPhoneNumberForgotPw, editEmailForgotPw,editVerificationCode;
    Button btnGetVerificationCode,btnForgotPwSubmit;
    UserDatabaseHelper userDatabaseHelper;
    Context context;
    int randomCode, randomCodeValue;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        editPhoneNumberForgotPw = findViewById(R.id.editPhoneNumberForgotPw);
        editEmailForgotPw = findViewById(R.id.editEmailForgotPw);
        editVerificationCode = findViewById(R.id.editVerificationCode);
        btnGetVerificationCode = findViewById(R.id.btnGetVerificationCode);
        btnForgotPwSubmit = findViewById(R.id.btnForgotPwSubmit);
        userDatabaseHelper = new UserDatabaseHelper(this);
        btnGetVerificationCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmailForgotPw.getText().toString();
                Random random = new Random();
                int randomCode = random.nextInt(900000) + 100000;
                randomCodeValue = randomCode;
                String body = "Mã xác nhận của bạn là: " + randomCode;

                if (checkPhoneNumber()){
                    if (checkEmail()){
                        sendEmail(email, "Mã xác nhận", body);
                        Toast.makeText(getApplicationContext(),"Gửi mail thành công",
                                Toast.LENGTH_SHORT).show();
                        editVerificationCode.setVisibility(View.VISIBLE);
                        btnForgotPwSubmit.setVisibility(View.VISIBLE);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Email không hợp lệ",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"Số điện thoại chưa được đăng ký",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnForgotPwSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String verificationCodeAsString = editVerificationCode.getText().toString();
                try {
                    int verificationCode = Integer.parseInt(verificationCodeAsString);
                    if (verificationCode == randomCodeValue){
                        String phoneNumber = editPhoneNumberForgotPw.getText().toString();
                        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs2", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("phoneNumber", phoneNumber);
                        editor.putBoolean("isLoggedIn", true);
                        editor.apply();
                        Toast.makeText(getApplicationContext(),"Chuyển đến trang tạo mật khảu mới"
                                , Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ForgotPasswordActivity.this, ResetPasswordActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Mã không chính xác",
                                Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(),"Mã không hợp lệ",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean checkPhoneNumber(){
        String phoneNumber = editPhoneNumberForgotPw.getText().toString();
        SQLiteDatabase db = userDatabaseHelper.getReadableDatabase();

        String[] columns = {"phoneNumber"};
        String selection = "phoneNumber = ?";
        String[] selectionArgs = {phoneNumber};

        Cursor cursor = db.query("user", columns, selection, selectionArgs, null, null, null);
        boolean exists = cursor.moveToFirst();

        cursor.close();
        db.close();

        return exists;
    }
    public boolean checkEmail(){
        String email = editEmailForgotPw.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(emailPattern);
    }
    public void sendEmail(String toEmail, String subject, String body) {
        JavaMailAPI javaMailAPI = new JavaMailAPI(this,toEmail,subject,body);
        javaMailAPI.execute();
    }
}