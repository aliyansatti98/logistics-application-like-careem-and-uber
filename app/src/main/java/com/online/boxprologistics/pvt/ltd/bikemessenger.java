package com.online.boxprologistics.pvt.ltd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class bikemessenger extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{
      int fare;
    private Button btn;
    EditText numberofvehicles,origin,phonenumber,emailaddress,itemdetails;
    private TextView textView6,textview16;
    String pickup,dropoff;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bikemessenger);
        spinner = findViewById(R.id.spinner2);
        spinner.setOnItemSelectedListener(this);
        numberofvehicles = findViewById(R.id.editTextNumber2);
        origin = findViewById(R.id.editTextTextPersonName9);
        itemdetails = findViewById(R.id.editTextTextPersonName10);
        Intent intent = getIntent();
        double pickLat=intent.getDoubleExtra("distance",0);


        phonenumber=findViewById(R.id.bikemessengerphoneno);
        emailaddress=findViewById(R.id.bikemessengeremailaddress);
        btn=findViewById(R.id.confirmdetailsbikemessenger);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendMail();
                String phone=phonenumber.getText().toString().trim();
                String email=emailaddress.getText().toString().trim();
                if (TextUtils.isEmpty(phone)){
                    Toast.makeText(bikemessenger.this, "Enter courier details", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(bikemessenger.this, "Enter courier details", Toast.LENGTH_SHORT).show();
                }



            }
        });


    }
    public void SendMail(){
        Intent intent = getIntent();
        dropoff=intent.getStringExtra("dropoff");
        pickup=intent.getStringExtra("pickup");
        JavaMailAPI javaMailAPI = new JavaMailAPI(this ,"abdulsattar1993@gmail.com","aliyansatti98@gmail.com","Box pro Logistics(Loading and Unloading)","<h1>User Courier Detail</h1>" +
                "<p>Pickup Details: "+pickup+"<br/>Dropoff Details: "+dropoff+"<br/>Type of service :"+spinner.getSelectedItem().toString()+"<br/>Number Of Items :"+numberofvehicles.getText()+"<br/>Detail Of Items :"+itemdetails.getText()+"<br/>Origin :"+origin.getText()+"<br/>Phone Number :"+phonenumber.getText()+"<br/>Email Address: "+emailaddress.getText()+" </p>");
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