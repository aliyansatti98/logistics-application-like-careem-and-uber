package com.online.boxprologistics.pvt.ltd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class bikecourier extends AppCompatActivity {

    private Button btn;
    EditText typeofanimals,numberofanimals,phonenumber,emailaddress;
    private int fare;
    private TextView textview16;
    String pickup,dropoff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bikecourier);

        Intent intent = getIntent();
        double pickLat=intent.getDoubleExtra("distance",0);


        phonenumber=findViewById(R.id.bikecourierphoneno);
        emailaddress=findViewById(R.id.bikecourieremail);
        btn=findViewById(R.id.confirmdetailsbikecourier);
        typeofanimals = findViewById(R.id.editTextTextPersonName11);
        numberofanimals=findViewById(R.id.editTextTextPersonName12);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SendMail();
                String phone=phonenumber.getText().toString().trim();
                String email=emailaddress.getText().toString().trim();
                if (TextUtils.isEmpty(phone)){
                    Toast.makeText(bikecourier.this, "Enter courier details", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(bikecourier.this, "Enter courier details", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }
    public void SendMail(){
        Intent intent = getIntent();
        dropoff=intent.getStringExtra("dropoff");
        pickup=intent.getStringExtra("pickup");

        JavaMailAPI javaMailAPI = new JavaMailAPI(this ,"abdulsattar1993@gmail.com","aliyansatti98@gmail.com","Box pro Logistics(Animal Transportation)","<h1>User Courier Detail</h1>" +
                "<p>Pickup Details: "+pickup+"<br/>Dropoff Details: "+dropoff+"<br/>Type Of Animals :"+typeofanimals.getText()+"<br/>Number Of Animals: "+numberofanimals.getText()+"<br/>Email Address: "+emailaddress.getText()+"<br/>Phone Number"+phonenumber.getText()+"</p>");
        javaMailAPI.execute();


    }
}