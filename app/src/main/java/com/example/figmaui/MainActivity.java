package com.example.figmaui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import java.net.Inet4Address;

public class MainActivity extends AppCompatActivity {
    AppCompatButton btnlogin;
    TextView signup;
    EditText email, pass;
    SharedPreferenceManager sharedPreferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //hiding action bar
        //getSupportActionBar().hide();

        //hiding status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signup = findViewById(R.id.tvsignup);
        btnlogin = findViewById(R.id.loginbtn);
        email = findViewById(R.id.etloginemail);
        pass = findViewById(R.id.etloginpassword);

        //allocating memory and functionality to sharedPreferenceManager object
        sharedPreferenceManager = new SharedPreferenceManager(getApplicationContext());

        //checking if the user has previously logged in or not!
        if (sharedPreferenceManager.isloogedin()){
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checking if the email textfield is empty or not
                if (email.getText().toString().isEmpty()){
                    email.setError("Please enter email");
                    email.requestFocus();
                    return;
                }
                //checking the pattern of email address
                if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
                    email.setError("Please enter correct email");
                    email.requestFocus();
                    return;
                }
                //checking if the password is empty or not!
                if (pass.getText().toString().isEmpty()){
                    pass.setError("Please enter password");
                    pass.requestFocus();
                    return;
                }
                //saving user details for direct login using sharedpreferencemanager object calling function
                sharedPreferenceManager.saveuser(email.getText().toString(), pass.getText().toString());
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
}