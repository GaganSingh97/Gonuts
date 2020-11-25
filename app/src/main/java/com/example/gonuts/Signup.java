package com.example.gonuts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {

    private static final String TAG = "Gagandeep";
    EditText etemail, etpwd, etphone, etName;
    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore fstore = FirebaseFirestore.getInstance();
    DocumentReference notRef = fstore.collection("User").document("Values");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etemail = findViewById(R.id.Email);
        etpwd = findViewById(R.id.Password);
        etphone = findViewById(R.id.Number);
        etName = findViewById(R.id.Name);

    }

    public void RestPassword(View view)
    {
        final String email = etemail.getText().toString();
        final String pwd = etpwd.getText().toString();
        final String phone = etphone.getText().toString();
        final String Name = etName.getText().toString();

                                                      // userID = mFirebaseAuth.getCurrentUser().getUid();
                            // DocumentReference documentReference = fstore.collection("Users").document(userID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("Email", email);
                            user.put("Name", Name);
                            user.put("Phone Number", phone);
                            user.put("Password", pwd);

                            notRef.set(user)

                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(Signup.this,"Sign up Successfull",Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Signup.this,"Failure",Toast.LENGTH_SHORT).show();
                                    Log.d(TAG,e.toString());

                                }
                            });
        {}

        Intent i = new Intent(this,Homepage.class);
        startActivity(i);

    }
}