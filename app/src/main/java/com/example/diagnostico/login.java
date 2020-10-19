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

public class login extends AppCompatActivity {
    EditText user;
    EditText pass;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = findViewById(R.id.email_login);
        pass = findViewById(R.id.pass_login);
        mAuth=FirebaseAuth.getInstance();
    }

    public void signinhere(View view){
         String email = user.getText().toString();
         String password = pass.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(login.this,dashboard.class);
                            intent.putExtra("email",mAuth.getCurrentUser().getEmail());
                            intent.putExtra("UID",mAuth.getCurrentUser().getUid());
                            startActivity(intent);
                        } else {
                            user.setText("");
                            pass.setText("");
                            Toast.makeText(login.this, "Invalid Details", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
}