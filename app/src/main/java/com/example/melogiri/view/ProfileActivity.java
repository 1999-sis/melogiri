package com.example.melogiri.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.melogiri.R;
import com.example.melogiri.controller.AppController;
import com.example.melogiri.controller.ControllerRegister;
import com.example.melogiri.model.Utente;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utente);

        Intent intent = getIntent();

        Utente utente = (Utente) intent.getSerializableExtra("utente");

        TextView textViewNomeUtente = findViewById(R.id.nomeUtente);
        textViewNomeUtente.setText(utente.getNome());

        TextView textViewCognomeUtente = findViewById(R.id.cognomeUtente);
        textViewCognomeUtente.setText(utente.getCognome());

        if(null!=utente.getData()){
            TextView textViewDataNascita = findViewById(R.id.dataDiNascita);
            textViewDataNascita.setText((CharSequence) utente.getData());
        }

        TextView textViewEmail = findViewById(R.id.Email);
        textViewEmail.setText(utente.getEmail());




    };
}
