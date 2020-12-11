package com.example.android.studentonlineattendance;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edittextrollno,edittextpassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        edittextrollno=(EditText) findViewById(R.id.rollno1);
        edittextpassword=(EditText) findViewById(R.id.password1);
        findViewById(R.id.buttonsignup).setOnClickListener(this);
        /*for binding the layout editextview roll no the to variable Editext edittextrollno*/

        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.buttonsignup).setOnClickListener(this);
        findViewById(R.id.textlogin).setOnClickListener(this);

    }

    private void registerUser() {
        String rollno=edittextrollno.getText().toString().trim();
        String password=edittextpassword.getText().toString().trim();

        if(rollno.isEmpty()){
            edittextrollno.setError("roll no is required");
            edittextrollno.requestFocus();
            return;
        }

        if(password.isEmpty()){
            edittextpassword.setError("password is required");
            edittextpassword.requestFocus();
            return;
        }
        if(password.length()<6){
            edittextpassword.setError("min length for password is 6");
            return;
        }
        mAuth.createUserWithEmailAndPassword(rollno, password)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(SignUpActivity.this, ProfileActivity.class));

                        }
                        else {

                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        // ...
                    }
                });
   }
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.buttonsignup:
                registerUser();
                break;

            case R.id.textlogin:
                finish();
                startActivity(new Intent(this,MainActivity.class));
                break;
        }
    }


}
