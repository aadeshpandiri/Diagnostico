package com.example.diagnostico;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;

    Animation topAnim,bottomAnim;
    ImageView image;
    TextView logotxt,tagline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        image = findViewById(R.id.imageView);
        logotxt = findViewById(R.id.textView);
        tagline = findViewById(R.id.textView2);

        image.setAnimation(topAnim);
        logotxt.setAnimation(bottomAnim);
        tagline.setAnimation(bottomAnim);

        //method to delay the process
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, OnboardingActivity.class);
                startActivity(i);
                finish();
            }
        },SPLASH_SCREEN); //delayed time

    }
}