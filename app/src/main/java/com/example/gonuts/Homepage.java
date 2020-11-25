package com.example.gonuts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class Homepage extends AppCompatActivity {

    EditText Nutname, Desc;
    private TextView show;
    TextView sNut;
    FirebaseFirestore fstore = FirebaseFirestore.getInstance();
    DocumentReference notRef = fstore.collection("Nuts").document("NutInfo");
    private static final String KEY_TITLE = "Nutname";
    private static final String TAG = "Gagandeep";
    private static final String KEY_DESC = "Desc";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        Nutname = findViewById(R.id.InsertTitle);
        Desc = findViewById(R.id.InsertDesc);

        sNut = findViewById(R.id.Preview);

    }

    public void Upload(View view)
    {
        final String nutname = Nutname.getText().toString();
        final String desc = Desc.getText().toString();

        Map<String, Object> user = new HashMap<>();
        user.put("Nutname", nutname);
        user.put("Desc", desc);

        notRef.set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Homepage.this,"uploaded",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Homepage.this,"Failure",Toast.LENGTH_SHORT).show();
                        Log.d(TAG,e.toString());

                    }
                });

    }

    public void Show(View view)
    {
        notRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        if (documentSnapshot.exists())
                        {
                            String title = documentSnapshot.getString(KEY_TITLE);
                            String desc = documentSnapshot.getString(KEY_DESC);

                            sNut.setText("Nutname :   " + title + "\n" + "\n" +"Description:   " + desc );

                        } else
                        {
                            Toast.makeText(Homepage.this,"Doc does not ecist",Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Homepage.this,"Error",Toast.LENGTH_LONG).show();

                    }
                });
    }
}
