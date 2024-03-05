package com.example.melogiri.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.melogiri.R;
import com.example.melogiri.controller.AppController;
import com.example.melogiri.model.Bevanda;
import com.example.melogiri.model.BevandaAdapter;
import com.example.melogiri.view.CarrelloActivity;
import com.example.melogiri.model.Categoria;
import com.example.melogiri.model.Utente;
import com.example.melogiri.model.RecycleViewInterface;
import com.example.melogiri.view.UtenteActivity;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity implements RecycleViewInterface {
    private List<Bevanda> productList;
    private RecyclerView recyclerView;
    private BevandaAdapter adapter;
    private AppController appController;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuhompage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent = new Intent(this, UtenteActivity.class);

        Utente utente = new Utente();
        utente.setNome("Gianluca");

        intent.putExtra("textView3", utente.getNome());

        if (id == R.id.profile) {
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        appController = new AppController(); // Instantiate the AppController

        recyclerView = findViewById(R.id.rec_viewCard);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList = new ArrayList<>();
        productList.add(new Bevanda("Prodotto 1", "Descrizione prodotto 1", "Da inserire URL", new Categoria("Alcolici")));
        productList.add(new Bevanda("Prodotto 2", "Descrizione prodotto 2", "Da inserire URL", new Categoria("Soft Drink")));
        productList.add(new Bevanda("Prodotto 3", "Descrizione prodotto 3", "Da inserire URL", new Categoria("Analcolici")));
        productList.add(new Bevanda("Prodotto 4", "Descrizione prodotto 4", "Da inserire URL", new Categoria("Soft Drink")));
        productList.add(new Bevanda("Prodotto 5", "Descrizione prodotto 5", "Da inserire URL", new Categoria("Alcolici")));

        adapter = new BevandaAdapter(productList, this);
        recyclerView.setAdapter(adapter);

        Button showCartBtn = findViewById(R.id.btnShowCart);
        showCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, CarrelloActivity.class);
                startActivity(intent);
            }
        });

        Button softDrink = findViewById(R.id.btn_softdrink);
        softDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle soft drink button click
                // You can call methods from the controller to handle business logic
            }
        });

        // Add click listeners for other category buttons

        /*
        Button all = findViewById(R.id.allDrinksButton);
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle "All Drinks" button click
                // You can call methods from the controller to handle business logic
            }
        });

         */
    }

    @Override
    public void onClickItem(int position) {
        // Handle click on a beverage item
        // You can call methods from the controller to handle business logic
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
