package com.online.boxprologistics.pvt.ltd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class cramessengeractivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button btn;
    TextView textview16;
    int fare;
    EditText numberofvehicles,origin,phonenumber,emailaddress;
    String pickup,dropoff;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cramessengeractivity);
spinner = findViewById(R.id.spinner1);
spinner.setOnItemSelectedListener(this);
numberofvehicles = findViewById(R.id.editTextTextPersonName7);
origin = findViewById(R.id.editTextTextPersonName8);
        Intent intent = getIntent();
        double pickLat=intent.getDoubleExtra("distance",0);




        phonenumber=findViewById(R.id.carmessengerphonenumber);
        emailaddress=findViewById(R.id.carmessengeremailaddress);
        btn=findViewById(R.id.confirmdetailscarmessenger);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone=phonenumber.getText().toString().trim();
                String email=emailaddress.getText().toString().trim();

                if (TextUtils.isEmpty(phone)){
                    Toast.makeText(cramessengeractivity.this, "Enter courier details", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(cramessengeractivity.this, "Enter courier details", Toast.LENGTH_SHORT).show();
                }
                SendMail();
            }
        });


    }
    public void SendMail(){
        Intent intent = getIntent();
        dropoff=intent.getStringExtra("dropoff");
        pickup=intent.getStringExtra("pickup");


       JavaMailAPI javaMailAPI = new JavaMailAPI(this ,"abdulsattar1993@gmail.com","aliyansatti98@gmail.com","Box pro Logistics(Vehicle Carrier)","<h1>User Courier Detail</h1>" +
        "<p>Pickup Details: "+pickup+"<br/>Dropoff Details: "+dropoff+"<br/>Type of vehicle :"+spinner.getSelectedItem().toString()+"<br/>Number Of Vehicles :"+numberofvehicles.getText()+"<br/>Origin :"+origin.getText()+"<br/>Phone Number :"+phonenumber.getText()+"<br/>Email Address: "+emailaddress.getText()+"</p>");
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