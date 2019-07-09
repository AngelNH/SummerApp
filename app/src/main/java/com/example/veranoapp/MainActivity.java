package com.example.veranoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startBtn = findViewById(R.id.main_start_btn);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Code executed when someone click on the button
                Animation  animation  = AnimationUtils.loadAnimation(MainActivity.this,R.anim.bounce);
                startBtn.startAnimation(animation);
                Intent intent = new Intent(MainActivity.this,ActivityGame.class);
                MainActivity.this.startActivity(intent);

            }
        });
    }
}
