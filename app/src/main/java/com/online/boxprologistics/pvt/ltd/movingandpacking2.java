package com.online.boxprologistics.pvt.ltd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class movingandpacking2 extends AppCompatActivity {
 EditText p1,p2,p3,p4,p5,p6;
  Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movingandpacking2);
   p1=findViewById(R.id.editTextTextPersonName13);
        p2=findViewById(R.id.editTextTextPersonName14);
        p3=findViewById(R.id.editTextTextPersonName15);
        p4=findViewById(R.id.editTextTextPersonName16);
        p5=findViewById(R.id.editTextTextPersonName17);
        p6=findViewById(R.id.editTextTextPersonName18);
btn2 = findViewById(R.id.button2);
btn2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        SendMail();
    }
});

    }
    public void SendMail(){
        Intent intent = getIntent();


        JavaMailAPI javaMailAPI = new JavaMailAPI(this ,"abdulsattar1993@gmail.com","aliyansatti98@gmail.com","Box pro Logistics(Moving And Packing)","<h1>User Courier Detail</h1>" +
                "<p>Pickup Details: "+p1.getText()+"<br/>Dropoff Details: "+p2.getText()+"<br/>Number of Rooms :"+p3.getText()+"<br/>List Of Items: "+p4.getText()+"<br/>Phone Number: "+p5.getText()+"<br/>Email Address"+p6.getText()+"</p>");
        javaMailAPI.execute();


    }
}