package com.example.melogiri.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.melogiri.R;
import com.example.melogiri.controller.AppController;
import com.example.melogiri.model.Utente;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private AppController appController;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.editTextTextEmailAddress2);
        editTextPassword = findViewById(R.id.editTextTextPassword2);
        appController = new AppController(); // Instantiate the AppController

        Button signInButton = findViewById(R.id.btn_signin);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        Button logInButton = findViewById(R.id.btn_login);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                Toast.makeText(com.example.melogiri.view.LoginActivity.this, editTextEmail.getText().toString(), Toast.LENGTH_SHORT).show();
                // Call the login task from the controller
                appController.loginTask(LoginActivity.this, email, password);
            }
        });
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}
