package com.online.boxprologistics.pvt.ltd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class courierr2 extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
     EditText p1,p2,p3,p4,p5;
      Spinner spinner;
       Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courierr2);
        p1= findViewById(R.id.editTextTextPersonName19);
        p2=findViewById(R.id.editTextTextPersonName20);
        p3=findViewById(R.id.editTextTextPersonName21);
        p4=findViewById(R.id.editTextTextPersonName22);
        p5=findViewById(R.id.editTextTextPersonName23);
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendMail();
            }
        });
    }
    public void SendMail(){


        JavaMailAPI javaMailAPI = new JavaMailAPI(this ,"abdulsattar1993@gmail.com","aliyansatti98@gmail.com","Box pro Logistics(Animal Transportation)","<h1>User Courier Detail</h1>" +
                "<p>Pickup Details: "+p1.getText()+"<br/>Dropoff Details: "+p2.getText()+"<br/>Courier Details :"+p3.getText()+"<br/>Phone No: "+p4.getText()+"<br/>Email Address: "+p5.getText()+"</p>");
        javaMailAPI.execute();


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, adapterView.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}