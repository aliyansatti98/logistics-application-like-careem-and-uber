package com.online.boxprologistics.pvt.ltd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Customer_registeration extends AppCompatActivity {
    public static final String TAG = "TAG";
    private Button customerregisterationbtn;
    TextView customerregisterlogin;
    EditText txtemail,txtpassword,txtconfirmpassword,txtfullname,txtphoneno;
    private FirebaseAuth firebaseAuth;
   FirebaseFirestore fstore;
   String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registeration);
    customerregisterationbtn = findViewById(R.id.customerregisteration);
    customerregisterlogin = findViewById(R.id.customerregisterationlogin);
        txtemail=findViewById(R.id.customeremailregisteration);
        txtpassword=findViewById(R.id.customerregisterationpassword);
        txtconfirmpassword = findViewById(R.id.customerregisterationpassword2);
        firebaseAuth = FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        txtfullname=findViewById(R.id.customerfullname);
        txtphoneno =findViewById(R.id.customerphone);


    customerregisterlogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Customer_registeration.this,customerloginactivity.class);
            startActivity(intent);
        }
    });
    customerregisterationbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            final String email=txtemail.getText().toString().trim();
            String password=txtpassword.getText().toString().trim();
            String confirmpassword = txtconfirmpassword.getText().toString().trim();
            final String name= txtfullname.getText().toString().trim();
            final String phone = txtphoneno .getText().toString().trim();

            if(TextUtils.isEmpty(email)){
                Toast.makeText(Customer_registeration.this, "Please Enter Email ", Toast.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(password)){
                Toast.makeText(Customer_registeration.this, "Enter Password", Toast.LENGTH_SHORT).show();
                 return;
            }
            if(TextUtils.isEmpty(confirmpassword)){
                Toast.makeText(Customer_registeration.this, "Enter Confirm Password", Toast.LENGTH_SHORT).show();
            return;
            }
            if(password.length()<6){
                Toast.makeText(Customer_registeration.this, "Your Password Length should be greater then 6 characters", Toast.LENGTH_SHORT).show();
            }
            if(confirmpassword.length()<6){
                Toast.makeText(Customer_registeration.this, "Your Password Length should be greater then 6 characters", Toast.LENGTH_SHORT).show();
            }



            if(password.equals(confirmpassword)){

                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Customer_registeration.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Toast.makeText(Customer_registeration.this, "Welcome To boxpro Logistics Solutions you are registered successfully", Toast.LENGTH_SHORT).show();
                                    userID = firebaseAuth.getCurrentUser().getUid();
                                    DocumentReference documentReference = fstore.collection("Customers").document(userID);
                                    Map<String,Object>user = new HashMap<>();
                                    user .put("Full Name:",name);
                                    user.put("Email:",email);
                                    user.put("Phone No:",phone);
                                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {

                                            Log.d(TAG, "onSuccess:User profile is created for "+userID);
                                                                                    }
                                    });
                                    Intent intent = new Intent(Customer_registeration.this, customermainactivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(Customer_registeration.this, "Authentiaction Error", Toast.LENGTH_SHORT).show();

                                    // ...
                                }
                            }
                        });


            }
            else
            {
                Toast.makeText(Customer_registeration.this, "Password are not equal check Both password ", Toast.LENGTH_SHORT).show();
            }









        }
    });


    }
}