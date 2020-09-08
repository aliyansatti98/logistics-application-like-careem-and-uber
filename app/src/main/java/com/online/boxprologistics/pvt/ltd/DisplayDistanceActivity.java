package com.online.boxprologistics.pvt.ltd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DisplayDistanceActivity extends AppCompatActivity {

    private TextView textView6,textview16;
    private double fare;
    private Button cnfrm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_distance);

        textView6 = (TextView) findViewById(R.id.textView6);
        textview16 = (TextView) findViewById(R.id.textView16);
        cnfrm = findViewById(R.id.confirmbookingcarmessenger1);
        Intent intent = getIntent();
        double pickLat=intent.getDoubleExtra("distance",0);
    fare =120;
        textview16.setText("Your Distance is " + Math.round(pickLat) + "Km");


        cnfrm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}