package com.example.melogiri.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.melogiri.controller.AppController;
import com.example.melogiri.model.Utente;
import com.example.melogiri.R;

public class UtenteActivity extends AppCompatActivity {

    private TextView textViewNome;
    private AppController appController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utente);

        textViewNome = findViewById(R.id.nomeUtente);
        appController = new AppController();

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("textView3")) {
            String nome = intent.getStringExtra("textView3");
            textViewNome.setText(nome);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close socket connection when activity is destroyed
    }
}
