package com.example.btl_nhom_7;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.btl_nhom_7.User.model.User;

public class SignUpActivity extends AppCompatActivity {
    Button signUpBtn;
    ImageView imageX8Char,imageXCapsChar,imageXNumberChar,
            imageTick8Char,imageTickCapsChar,imageTickNumberChar;
    EditText editPhoneNumberSignUp,editPasswordSignUp;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signUpBtn = (Button) findViewById(R.id.signUpBtn);
        editPhoneNumberSignUp = (EditText)findViewById(R.id.editPhoneNumberSignUp);
        editPasswordSignUp = (EditText)findViewById(R.id.editPasswordSignUp);
        imageX8Char = (ImageView)findViewById(R.id.imageX8Char);
        imageXCapsChar = (ImageView)findViewById(R.id.imageXCapsChar);
        imageXNumberChar = (ImageView)findViewById(R.id.imageXNumberChar);
        imageTick8Char = (ImageView)findViewById(R.id.imageTick8Char);
        imageTickCapsChar = (ImageView)findViewById(R.id.imageTickCapsChar);
        imageTickNumberChar = (ImageView)findViewById(R.id.imageTickNumberChar);
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

            }
        });
    }
    public void checkPassword(){
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
    }
}