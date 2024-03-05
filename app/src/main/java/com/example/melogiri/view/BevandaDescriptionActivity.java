package com.example.melogiri.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.melogiri.controller.AppController;
import com.example.melogiri.model.Bevanda;
import com.example.melogiri.R;

public class BevandaDescriptionActivity extends AppCompatActivity {

    private AppController appController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bevanda_description);

        appController = new AppController();

        Intent intent = getIntent();
        if (intent != null) {
            String nomeBevanda = intent.getStringExtra("nomeBevanda");
            String descrizione = intent.getStringExtra("descrizione");

            TextView textViewNomeBevanda = findViewById(R.id.bevandaNome);
            textViewNomeBevanda.setText(nomeBevanda);

            TextView textViewDescrizione = findViewById(R.id.textView2);
            textViewDescrizione.setText(descrizione);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close socket connection when activity is destroyed
        //appController.closeSocketConnection();
    }
}
