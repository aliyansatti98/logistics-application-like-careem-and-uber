package com.online.boxprologistics.pvt.ltd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class driverloginactivity extends AppCompatActivity {
    private TextView create;
    EditText memail,mpassword;
    Button mloginbtn;
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driverloginactivity);
         firebaseAuth=FirebaseAuth.getInstance();
       create = findViewById(R.id.drivercreateaccount1);
       create.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent= new Intent(driverloginactivity.this,driverregisterationactivity.class);
               startActivity(intent);
           }
       });

     memail=findViewById(R.id.driveremail);
     mpassword=findViewById(R.id.driverpassword);
     mloginbtn=findViewById(R.id.driverloginbutton);
     mloginbtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             String email=memail.getText().toString().trim();
             String password=mpassword.getText().toString().trim();

             if (TextUtils.isEmpty(email)){
                 Toast.makeText(driverloginactivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
             }
             if (TextUtils.isEmpty(password)){
                 Toast.makeText(driverloginactivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
             }
             if ((password.length()<6)){
                 Toast.makeText(driverloginactivity.this, "Password should have more then 6 characters", Toast.LENGTH_SHORT).show();
             }

             firebaseAuth.signInWithEmailAndPassword(email, password)
                     .addOnCompleteListener(driverloginactivity.this, new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {
                             if (task.isSuccessful()) {

                                 Intent intent =new Intent(driverloginactivity.this,drivermapactivity.class);
                                 startActivity(intent);
                             }

                             else {
                                 // If sign in fails, display a message to the user.
                                 Toast.makeText(driverloginactivity.this, "Login Failed & User not availaible", Toast.LENGTH_SHORT).show();

                             }// ...
                         }
                     });

         }
     });


    }
}