package com.example.diagnostico;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText user;
    EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = findViewById(R.id.user);
        pass = findViewById(R.id.pass);

    }

    public void signuphere(View view) {
        String email = user.getText().toString();
        String password = pass.getText().toString();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                                user.setText("");
                                pass.setText("");
                            Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_SHORT).show();
                        } else {
                            user.setText("");
                            pass.setText("");
                            Toast.makeText(getApplicationContext(), "Not Registered", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    public void gotosignin(View view) {
        startActivity(new Intent(MainActivity.this,login.class));
    }
}