package com.online.boxprologistics.pvt.ltd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class customermainactivity extends AppCompatActivity {
    private Button carbtn,bikebtn,courierbtn,essentialdeliverybtn,specialsrvicesbtn,profilebtn,moving;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customermainactivity);
        carbtn = findViewById(R.id.carimagebtn);
        courierbtn = findViewById(R.id.courierimagebtn);
        bikebtn = findViewById(R.id.bikeimagebtn);
        essentialdeliverybtn = findViewById(R.id.essentialdeliverybtn);
        profilebtn = findViewById(R.id.profilebtn);
        specialsrvicesbtn = findViewById(R.id.movingandpackingbtn);
        moving=findViewById(R.id.movingandpackingbtn1);
        moving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(customermainactivity.this,movingandpacking2.class);
                startActivity(intent);
            }
        });
      bikebtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(customermainactivity.this,map3Activity.class);
        startActivity(intent);
    }
});


        essentialdeliverybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(customermainactivity.this,maps7.class);
                startActivity(intent);
            }
        });

        carbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(customermainactivity.this, map.class);
                startActivity(intent);
            }
        });
        courierbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(customermainactivity.this, Maps5Activity.class);
                startActivity(intent);
            }
        });
        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(customermainactivity.this, courierr2.class);
                startActivity(intent);
            }
        });


    }
    @Override
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
    public void browser1(View view){
        Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://boxprologs.com/services/e-commerce-solutions/"));
        startActivity(browser);
    }


}