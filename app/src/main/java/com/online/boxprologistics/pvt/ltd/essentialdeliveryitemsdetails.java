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

public class essentialdeliveryitemsdetails extends AppCompatActivity {

    private Button btn;
    EditText courierdetails,pickupdetails,deliveryaddress,phonenumber,emailaddress;
    String pickup,dropoff;
    TextView textview16;
    int fare;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_essentialdeliveryitemsdetails);
        textview16 = findViewById(R.id.textView19);
        Intent intent = getIntent();
        double pickLat=intent.getDoubleExtra("distance",0);
        fare =130;

        if(pickLat <=5 ){
            textview16.setText("Your fare will be PKR " +fare );
        }
        if(pickLat>5 && pickLat <=10){
            fare=fare+18;
            textview16.setText("Your fare will be PKR " +fare );
        }
        if(pickLat>10 && pickLat <=15){
            fare=fare+16;
            textview16.setText("Your fare will be PKR " +fare );
        }
        if(pickLat>15 && pickLat <=20){
            fare=fare+14;
            textview16.setText("Your fare will be PKR " +fare );
        }
        if(pickLat>20 && pickLat <=25){
            fare=fare+12;
            textview16.setText("Your fare will be PKR " +fare );
        }
        if(pickLat>25 && pickLat <=30){
            fare=fare+10;
            textview16.setText("Your fare will be PKR " +fare );
        }
        if(pickLat>35){
            fare=fare+10;
            textview16.setText("Your fare will be PKR " +fare );
        }

        if(pickLat>30 && pickLat <=35){
            fare=fare+10;
            textview16.setText("Your fare will be PKR " +fare );
        }

        courierdetails=findViewById(R.id.essentialdeliveryitejms);

        phonenumber=findViewById(R.id.essentialdeliveryphoneno);
        emailaddress=findViewById(R.id.essentialdeliveryemailaddress);
        btn=findViewById(R.id.confirmdetailsessentialdeliverybtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendMail();
                String courierdetailss=courierdetails.getText().toString().trim();

                String phone=phonenumber.getText().toString().trim();
                String email=emailaddress.getText().toString().trim();

                if (TextUtils.isEmpty(courierdetailss)){
                    Toast.makeText(essentialdeliveryitemsdetails.this, "Enter courier details", Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(phone)){
                    Toast.makeText(essentialdeliveryitemsdetails.this, "Enter courier details", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(essentialdeliveryitemsdetails.this, "Enter courier details", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }
    public void SendMail(){
        Intent intent = getIntent();
        dropoff=intent.getStringExtra("dropoff");
        pickup=intent.getStringExtra("pickup");


        JavaMailAPI javaMailAPI = new JavaMailAPI(this ,"abdulsattar1993@gmail.com","aliyansatti98@gmail.com","Box pro Logistics(Essential Delivery)","<h1>User Essential Delivery Detail</h1>" +
                "<p>Courier Details: "+courierdetails.getText()+"<br/>Pickup Details: "+pickup+"<br/>DropOff Details: "+dropoff+"<br/>Phone Number :"+phonenumber.getText()+"<br/>Email Address: "+emailaddress.getText()+"<br/>Fare :"+fare+"</p>");
        javaMailAPI.execute();

    }
}