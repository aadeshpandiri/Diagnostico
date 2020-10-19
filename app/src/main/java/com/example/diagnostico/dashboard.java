package com.example.diagnostico;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class dashboard extends AppCompatActivity {
    TextView emailhome;
    TextView uidhome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        emailhome = findViewById(R.id.editTextTextPersonName3);
        uidhome = findViewById(R.id.editTextTextPersonName4);
        emailhome.setText(getIntent().getStringExtra("email").toString());
        uidhome.setText(getIntent().getStringExtra("UID").toString());

    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(dashboard.this,MainActivity.class));
    }
}