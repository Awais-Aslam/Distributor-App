package com.spikotech.sndapp.distributorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp_Activity extends AppCompatActivity {

    TextView login_page;
    EditText register_email;
    EditText register_password;
    EditText confirm_password;
    Button registerButton;
    private WebappApi webappApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_);

        login_page = findViewById(R.id.textView_login);
        register_email = findViewById(R.id.register_email);
        register_password = findViewById(R.id.register_password);
        confirm_password = findViewById(R.id.confirm_password);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        login_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp_Activity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void registerUser() {
        String email = register_email.getText().toString();
        String password = register_password.getText().toString();
        String confirmPassword = confirm_password.getText().toString();
        if (email.isEmpty()) {
            Toast.makeText(this, "Email Can't be empty", Toast.LENGTH_SHORT).show();
        }
        if (password.isEmpty() && confirmPassword.isEmpty()) {
            Toast.makeText(this, "Password and Confirm can' empty ", Toast.LENGTH_SHORT).show();
        }
        if (!password.contentEquals(confirmPassword)) {
            Toast.makeText(this, "Password and Confirm Password don't match", Toast.LENGTH_SHORT).show();
        }
        if (password.contentEquals(confirmPassword) && !password.isEmpty() && !confirmPassword.isEmpty()) {
            Toast.makeText(this, "User Register Successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SignUp_Activity.this, LoginActivity.class);
            startActivity(intent);
            /*RegisterUser user = new RegisterUser(email, password, confirmPassword);

            Call<String> call = webappApi.createUser(user);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(SignUp_Activity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    }

                    Toast.makeText(SignUp_Activity.this, "User Register Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUp_Activity.this, LoginActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(SignUp_Activity.this, "Failure : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });*/
        }
    }
}