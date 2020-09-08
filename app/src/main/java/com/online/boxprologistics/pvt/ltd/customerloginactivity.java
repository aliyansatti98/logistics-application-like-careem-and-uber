package com.online.boxprologistics.pvt.ltd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class customerloginactivity extends AppCompatActivity {
    TextView create;
    EditText txtemail,txtpassword;
    Button loginbtn;
    CheckBox remeberme;
    private FirebaseAuth firebaseAuth;
    String email,password;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerloginactivity);
        remeberme = findViewById(R.id.customerremember);
        progressBar=findViewById(R.id.progressBar);
        SharedPreferences sharedPreferences = getSharedPreferences("Check Box",MODE_PRIVATE);
        String checkbox = sharedPreferences.getString("Remember","");
        email=sharedPreferences.getString("email","");
        password=sharedPreferences.getString("password","");

        if (checkbox.equals("True")){


                     //email=txtemail.getText().toString().trim();
                     //password=txtpassword.getText().toString().trim();
                    if(TextUtils.isEmpty(email)){
                        Toast.makeText(customerloginactivity.this, "Please Enter Email ", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(TextUtils.isEmpty(password)){
                        Toast.makeText(customerloginactivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(password.length()<6){
                        Toast.makeText(customerloginactivity.this, "Password should contain more then 6 characters", Toast.LENGTH_SHORT).show();
                    }
                    firebaseAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(customerloginactivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        Intent intent =new Intent(customerloginactivity.this,customermainactivity.class);
                                        startActivity(intent);
                                        finish();
                                    }

                                    else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(customerloginactivity.this, "Login Failed & User not availaible", Toast.LENGTH_SHORT).show();

                                    }// ...
                                }
                            });





        }
        else if (checkbox.equals("False")){
            Toast.makeText(this, "Sign in", Toast.LENGTH_SHORT).show();
        }
create = findViewById(R.id.customercreateaccount);
create.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(customerloginactivity.this,Customer_registeration.class);
        startActivity(intent);
    }
});
      firebaseAuth = FirebaseAuth.getInstance();
      txtemail = findViewById(R.id.customeremail);
      txtpassword=findViewById(R.id.customerpassword);
      loginbtn  = findViewById(R.id.customerlogin1);

      loginbtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              progressBar.setVisibility(View.VISIBLE);
              SharedPreferences sharedPreferences = getSharedPreferences("Check Box",MODE_PRIVATE);
              SharedPreferences.Editor editor = sharedPreferences.edit();
              editor.putString("email",email);
              editor.putString("password",password);

              editor.apply();
               email=txtemail.getText().toString().trim();
               password=txtpassword.getText().toString().trim();
              if(TextUtils.isEmpty(email)){
                  Toast.makeText(customerloginactivity.this, "Please Enter Email ", Toast.LENGTH_SHORT).show();
                  return;
              }
              if(TextUtils.isEmpty(password)){
                  Toast.makeText(customerloginactivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                  return;
              }
              if(password.length()<6){
                  Toast.makeText(customerloginactivity.this, "Password should contain more then 6 characters", Toast.LENGTH_SHORT).show();
              }
              firebaseAuth.signInWithEmailAndPassword(email, password)
                      .addOnCompleteListener(customerloginactivity.this, new OnCompleteListener<AuthResult>() {
                          @Override
                          public void onComplete(@NonNull Task<AuthResult> task) {
                              if (task.isSuccessful()) {
                                  progressBar.setVisibility(View.VISIBLE);
                                  Intent intent =new Intent(customerloginactivity.this,customermainactivity.class);
                                  startActivity(intent);
                              }

                              else {
                                  // If sign in fails, display a message to the user.
                                  Toast.makeText(customerloginactivity.this, "Login Failed & User not availaible", Toast.LENGTH_SHORT).show();

                              }// ...
                          }
                      });


          }
      });
      remeberme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
              if (compoundButton.isChecked()){

                  SharedPreferences sharedPreferences = getSharedPreferences("Check Box",MODE_PRIVATE);
                  SharedPreferences.Editor editor = sharedPreferences.edit();
                  editor.putString("email",email);
                  editor.putString("password",password);
                  editor.putString("Remember","true");

                  editor.apply();
                  Toast.makeText(customerloginactivity.this, "Checked", Toast.LENGTH_SHORT).show();

              }
              else if (!compoundButton.isChecked()){
                  SharedPreferences sharedPreferences = getSharedPreferences("Check Box",MODE_PRIVATE);
                  SharedPreferences.Editor editor = sharedPreferences.edit();
                  editor.putString("Remember","false");
                  editor.putString("email",email);
                  editor.putString("password",password);
                  editor.apply();
                  Toast.makeText(customerloginactivity.this, "UnChecked", Toast.LENGTH_SHORT).show();

              }
          }
      });

    }
}