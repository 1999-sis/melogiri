package com.example.melogiri.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.melogiri.R;
import com.example.melogiri.controller.AppController;
import com.example.melogiri.controller.ControllerRegister;

import java.sql.Date;

public class RegisterActivity extends AppCompatActivity
{

    private EditText editTextEmail, editTextPassword, editTextNome, editTextCognome;
    private ControllerRegister controllerRegister = new ControllerRegister();;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextEmail = findViewById(R.id.editTextTextEmailAddress);
        editTextPassword = findViewById(R.id.editTextTextPassword);
        editTextNome = findViewById(R.id.editTextTextPersonName);
        editTextCognome = findViewById(R.id.editTextTextPersonName2);

        // Call the register method when the register button is clicked
        findViewById(R.id.button).setOnClickListener(view -> register());
    }

    private void register()
    {
        String nome = editTextNome.getText().toString();
        String cognome = editTextCognome.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        // Call the register method of the app controller
        controllerRegister.register(this, nome, cognome, "1999-01-01", email, password);


    }
}
