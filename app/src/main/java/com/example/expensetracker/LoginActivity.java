package com.example.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private TextView goToRegisterScreen;
    EditText emailInput;
    EditText passwordInput;
    TextView loginButton;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ascunderea titlului din screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        //deschiderea screen-ului de register
        goToRegisterScreen = findViewById(R.id.goToRegisterScreen);
        goToRegisterScreen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openRegisterActivity();
            }
        });

        emailInput=findViewById(R.id.email_input);
        passwordInput=findViewById(R.id.password_input);
        loginButton=findViewById(R.id.loginButton);
        mAuth=FirebaseAuth.getInstance();

        loginButton.setOnClickListener(view -> {
            loginUser();
        });

    }

    private void loginUser() {
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

        if (TextUtils.isEmpty(email)){
            emailInput.setError("Email cannot be empty");
            emailInput.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            passwordInput.setError("Password cannot be empty");
            passwordInput.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "User logged in", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    }
                    else{
                        Toast.makeText(LoginActivity.this,"Registration error: "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void openRegisterActivity() {
        Intent intent= new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}