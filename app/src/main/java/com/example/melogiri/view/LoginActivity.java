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
import com.example.melogiri.controller.ControllerRegister;
import com.example.melogiri.model.Utente;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private AppController appController;
    private ControllerRegister register;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.editTextTextEmailAddress2);
        editTextPassword = findViewById(R.id.editTextTextPassword2);
        appController = new AppController(); // Instantiate the AppController
        register = new ControllerRegister();

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

                appController.loginTask(LoginActivity.this, email, password);
            }
        });

        findViewById(R.id.id_faccia).setOnClickListener(view ->  register.showBiometricPrompt(this));
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}
