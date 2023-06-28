package com.example.btl_nhom_7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.btl_nhom_7.User.model.User;
import com.example.btl_nhom_7.database.DatabaseHelper;

public class SignUpActivity extends AppCompatActivity {
    Button signUpBtn;
    ImageView imageX8Char,imageXCapsChar,imageXNumberChar,
            imageTick8Char,imageTickCapsChar,imageTickNumberChar;
    EditText editPhoneNumberSignUp,editPasswordSignUp;
    Context context;
    DatabaseHelper userDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signUpBtn = (Button) findViewById(R.id.signUpBtn);
        editPhoneNumberSignUp = (EditText)findViewById(R.id.editPhoneNumberForgotPw);
        editPasswordSignUp = (EditText)findViewById(R.id.editPasswordSignUp);
        editPasswordSignUp.setTransformationMethod(PasswordTransformationMethod.getInstance());
        imageX8Char = (ImageView)findViewById(R.id.imageX8Char);
        imageXCapsChar = (ImageView)findViewById(R.id.imageXCapsChar);
        imageXNumberChar = (ImageView)findViewById(R.id.imageXNumberChar);
        imageTick8Char = (ImageView)findViewById(R.id.imageTick8Char);
        imageTickCapsChar = (ImageView)findViewById(R.id.imageTickCapsChar);
        imageTickNumberChar = (ImageView)findViewById(R.id.imageTickNumberChar);
        userDatabaseHelper = new DatabaseHelper(this);
        editPasswordSignUp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkPassword();
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = editPhoneNumberSignUp.getText().toString();
                String password = editPasswordSignUp.getText().toString();
                if (checkPassword()&&checkPhoneNumber()){
                    User existingUser = userDatabaseHelper.getUser(phoneNumber);
                    if (existingUser != null) {
                        Toast.makeText(SignUpActivity.this, "Số điện thoại này đã được sử dụng", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        // Tạo người dùng mới và thêm vào CSDL
                        User newUser = new User(phoneNumber, password);
                        userDatabaseHelper.addUser(newUser);
                        Toast.makeText(SignUpActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }
    public boolean checkPassword(){
        String phoneNumber = editPhoneNumberSignUp.getText().toString();
        String password =  editPasswordSignUp.getText().toString();
        User user = new User(phoneNumber,password);
        if (user.checkValid8Char()){
            imageX8Char.setVisibility(View.INVISIBLE);
            imageTick8Char.setVisibility(View.VISIBLE);
        }
        if (user.checkValidCapsChar()){
            imageXCapsChar.setVisibility(View.INVISIBLE);
            imageTickCapsChar.setVisibility(View.VISIBLE);
        }
        if (user.checkValidNumberChar()){
            imageXNumberChar.setVisibility(View.INVISIBLE);
            imageTickNumberChar.setVisibility(View.VISIBLE);
        }
        if (user.checkValid8Char()&&user.checkValidCapsChar()&&user.checkValidNumberChar())
            return true;
        else {
            Toast.makeText(getApplicationContext(),"Mật khẩu chưa đủ mạnh",Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    public boolean checkPhoneNumber() {
        String phoneNumber = editPhoneNumberSignUp.getText().toString();
        boolean hasLetter = phoneNumber.matches(".[*a-zA-Z].*");
        if (hasLetter) {
            Toast.makeText(getApplicationContext(), "Số điện thoại không được bao gồm chữ cái",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (phoneNumber.length()!=10) {
            Toast.makeText(getApplicationContext(), "Số điện thoại không hợp lệ",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}