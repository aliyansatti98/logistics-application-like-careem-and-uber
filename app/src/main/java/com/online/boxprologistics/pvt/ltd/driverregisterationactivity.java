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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class driverregisterationactivity extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText memail,mpassword,mphonenumber,mcnic,mconfirmpassword,mname;
    Button mregisterbtn;
    TextView createaccount;
    private FirebaseAuth firebaseAuth;
    FirebaseFirestore fstore;
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driverregisterationactivity);
    memail = findViewById(R.id.driveremailregisteration);
    mname = findViewById(R.id.driverregisterationname);
    mpassword=findViewById(R.id.driiverregisterpassword);
    mphonenumber = findViewById(R.id.driverphoneno);
    mregisterbtn= findViewById(R.id.driverregister);
    mcnic = findViewById(R.id.driverregisterationcnic);
    mconfirmpassword=findViewById(R.id.driverregisterationconfirmpassword);
        firebaseAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        createaccount =findViewById(R.id.driverloginactivitygo);
firebaseAuth = FirebaseAuth.getInstance();
createaccount.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(driverregisterationactivity.this,driverloginactivity.class);
        startActivity(intent);
    }
});
mregisterbtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
       final String email=memail.getText().toString().trim();
        final String password=mpassword.getText().toString().trim();
        String confirmpassword=mconfirmpassword.getText().toString().trim();
        final String cnic =mcnic.getText().toString().trim();
        final String phoneno = mphonenumber.getText().toString().trim();
        final String name = mname.getText().toString().trim();
        if (TextUtils.isEmpty(email)){

            Toast.makeText(driverregisterationactivity.this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();
          return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(driverregisterationactivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(confirmpassword)){
            Toast.makeText(driverregisterationactivity.this, "Enter Confirm Password", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(cnic)){
            Toast.makeText(driverregisterationactivity.this, "Enter Your CNIC ", Toast.LENGTH_SHORT).show();
        }
        if ((TextUtils.isEmpty(phoneno))){
            Toast.makeText(driverregisterationactivity.this, "Enter Your Phone no", Toast.LENGTH_SHORT).show();
        }
        if ((cnic.length()<13)){
            Toast.makeText(driverregisterationactivity.this, "Cnic should have 13 alphabets or there should be no space between it ", Toast.LENGTH_SHORT).show();
        }

        if ((password.equals(confirmpassword)))
        {
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(driverregisterationactivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        userID = firebaseAuth.getCurrentUser().getUid();
                        DocumentReference documentReference = fstore.collection("Fleet").document(userID);
                        Map<String,Object> user = new HashMap<>();
                        user .put("Full Name:",name);
                        user.put("Email:",email);
                        user.put("Phone No:",phoneno);
                        user.put("CNIC NUMBER:",cnic);
                        user.put("Password:",password);
                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "onSuccess:Driver Profile is created for  "+userID);
                            }
                        });
                        Intent intent = new Intent(driverregisterationactivity.this, drivermapactivity.class);
                        startActivity(intent);
                        Toast.makeText(driverregisterationactivity.this, "Welcome To boxpro Logistics Solutions you are registered successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(driverregisterationactivity.this, "Authentiaction Error", Toast.LENGTH_SHORT).show();

                        // ...
                    }

                }
            });
        }

    }
});



        }
}