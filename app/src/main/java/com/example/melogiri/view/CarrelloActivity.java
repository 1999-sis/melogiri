package com.example.melogiri.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.melogiri.controller.ControllerCarrello;
import com.example.melogiri.adapter.CarrelloAdapter;
import com.example.melogiri.R;
import com.example.melogiri.model.Bevanda;

import java.util.List;

public class CarrelloActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CarrelloAdapter carrelloAdapter;
    private ControllerCarrello controllerCarrello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carrello_activity);

        recyclerView = findViewById(R.id.recyclerViewCart);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        controllerCarrello = ControllerCarrello.getInstance();

        List<Bevanda> prodottiCarrello = controllerCarrello.getProdotti();
        carrelloAdapter = new CarrelloAdapter(prodottiCarrello);
        recyclerView.setAdapter(carrelloAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
