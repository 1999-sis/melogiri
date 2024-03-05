package com.example.melogiri.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.melogiri.controller.AppController;
import com.example.melogiri.model.Bevanda;
import com.example.melogiri.model.Carrello;
import com.example.melogiri.model.CarrelloAdapter;
import com.example.melogiri.R;

import java.util.ArrayList;
import java.util.List;

public class CarrelloActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CarrelloAdapter carrelloAdapter;
    private AppController appController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carrello_activity);

        recyclerView = findViewById(R.id.recyclerViewCart);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        appController = new AppController();

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("carrello")) {
            List<Bevanda> prodottiCarrello = intent.getParcelableArrayListExtra("carrello");
            carrelloAdapter = new CarrelloAdapter(prodottiCarrello);
            recyclerView.setAdapter(carrelloAdapter);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close socket connection when activity is destroyed
    }
}
