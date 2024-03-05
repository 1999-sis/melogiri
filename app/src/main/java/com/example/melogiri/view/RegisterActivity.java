package com.example.melogiri.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.melogiri.R;
import com.example.melogiri.controller.AppController;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword, editTextNome, editTextCognome;
    private AppController appController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextEmail = findViewById(R.id.editTextTextEmailAddress);
        editTextPassword = findViewById(R.id.editTextTextPassword);
        editTextNome = findViewById(R.id.editTextTextPersonName);
        editTextCognome = findViewById(R.id.editTextTextPersonName2);

        appController = new AppController();

        // Call the register method when the register button is clicked
        findViewById(R.id.button).setOnClickListener(view -> register());
    }

    private void register() {
        String nome = editTextNome.getText().toString();
        String cognome = editTextCognome.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        // Call the register method of the app controller
        String response = appController.register(nome, cognome, email, password);

        // Handle the response as needed
    }
}
