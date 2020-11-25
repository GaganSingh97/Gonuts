package com.example.gonuts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    private static final String TAG = "Gagandeep";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    public void Home(View view)
    {
        Intent i = new Intent(this,Homepage.class);
        startActivity(i);
    }

    public void Signup(View view)
    {
        Intent i = new Intent(this,Signup.class);
        startActivity(i);

    }

    public void RestPassword(View view)
    {
        Intent i = new Intent(this,ResetPassword.class);
        startActivity(i);
    }
}