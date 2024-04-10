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
import com.example.melogiri.adapter.BevandaAdapter;
import com.example.melogiri.controller.ControllerCarrello;
import com.example.melogiri.controller.ControllerHomePage;
import com.example.melogiri.model.Bevanda;
import com.example.melogiri.model.Categoria;
import com.example.melogiri.model.Utente;
import com.example.melogiri.recycleView.RecycleViewInterface;
import com.example.melogiri.util.BevandeCallback;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity implements RecycleViewInterface {
    private List<Bevanda> productList;
    private RecyclerView recyclerView;
    private BevandaAdapter adapter;
    private ControllerCarrello controllerCarrello;
    private boolean isCartView = false; // Flag to indicate if the adapter is being used in cart view

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
        ControllerHomePage controllerHomePage = new ControllerHomePage();

        Utente utente = new Utente();
        Intent intent = getIntent();

        utente = (Utente) intent.getSerializableExtra("utente");

        Log.d("Utente in HomePage: ", utente.toString());

        BottomNavigationView navigationView = findViewById(R.id.bottomNavigator);
        Utente finalUtente = utente;

        navigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.profile:
                    // Handle profile navigation
                    Intent profiloIntent = new Intent(HomePageActivity.this, ProfileActivity.class);
                    profiloIntent.putExtra("utente", finalUtente);
                    startActivity(profiloIntent);
                    break;
                case R.id.carrello:
                    // Open CarrelloActivity when "Carrello" is pressed
                    Intent carrelloIntent = new Intent(HomePageActivity.this, CarrelloActivity.class);
                    startActivity(carrelloIntent);
                    return true; // Return true to indicate that the item is selected
                case R.id.home:
                    // Handle home navigation
                    break;
            }
            return false; // Return false if the item is not selected
        });

        recyclerView = findViewById(R.id.rec_viewCard);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        controllerCarrello = ControllerCarrello.getInstance();

        controllerHomePage.getBevande(this, new BevandeCallback() {
            @Override
            public void onBevandeLoaded(List<Bevanda> productList) {
                adapter = new BevandaAdapter(productList, controllerCarrello, HomePageActivity.this, isCartView);
                recyclerView.setAdapter(adapter);

                Log.d("Product List", productList.toString());

                Button softDrink = findViewById(R.id.btn_softdrink);
                softDrink.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ArrayList<Bevanda> softDrinks = new ArrayList<>();

                        for (Bevanda bevanda : productList) {
                            String categoria = bevanda.getCategoria().toString();
                            Log.d("Categoria: ", categoria);
                            if (categoria.equalsIgnoreCase(new Categoria("softdrink").toString())) {
                                softDrinks.add(bevanda);
                            }
                        }
                        BevandaAdapter adapterSoft = new BevandaAdapter(softDrinks, controllerCarrello, HomePageActivity.this, isCartView);
                        recyclerView.setAdapter(adapterSoft);
                    }
                });

                Button vini = findViewById(R.id.btn_vini);
                vini.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ArrayList<Bevanda> vini = new ArrayList<>();

                        for (Bevanda bevanda : productList) {
                            String categoria = bevanda.getCategoria().toString();
                            Log.d("Categoria: ", categoria);
                            if (categoria.equalsIgnoreCase(new Categoria("vini").toString())) {
                                vini.add(bevanda);
                            }
                        }
                        BevandaAdapter adapterWine = new BevandaAdapter(vini, controllerCarrello, HomePageActivity.this, isCartView);
                        recyclerView.setAdapter(adapterWine);
                    }
                });

                Button Alcolici = findViewById(R.id.btn_alcolici);
                Alcolici.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ArrayList<Bevanda> alcolici = new ArrayList<>();

                        for (Bevanda bevanda : productList) {
                            String categoria = bevanda.getCategoria().toString();
                            Log.d("Categoria: ", categoria);
                            if (categoria.equalsIgnoreCase(new Categoria("drinkalcolici").toString())) {
                                alcolici.add(bevanda);
                            }
                        }
                        BevandaAdapter adapterAlcolici = new BevandaAdapter(alcolici, controllerCarrello, HomePageActivity.this, isCartView);
                        recyclerView.setAdapter(adapterAlcolici);
                    }
                });

                Button analcolici = findViewById(R.id.btn_analcolici);
                analcolici.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ArrayList<Bevanda> analcolici = new ArrayList<>();

                        for (Bevanda bevanda : productList) {
                            String categoria = bevanda.getCategoria().toString();
                            Log.d("Categoria: ", categoria);
                            if (categoria.equalsIgnoreCase(new Categoria("drinkanalcolici").toString())) {
                                analcolici.add(bevanda);
                            }
                        }
                        BevandaAdapter adapterAnalcolici = new BevandaAdapter(analcolici, controllerCarrello, HomePageActivity.this, isCartView);
                        recyclerView.setAdapter(adapterAnalcolici);
                    }
                });
            }
        });
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
