package com.example.melogiri.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.melogiri.R;
import com.example.melogiri.adapter.BevandaAdapter;
import com.example.melogiri.controller.ControllerCarrello;
import com.example.melogiri.controller.ControllerHomePage;
import com.example.melogiri.model.Bevanda;
import com.example.melogiri.model.Utente;
import com.example.melogiri.recycleView.RecycleViewInterface;
import com.example.melogiri.util.BevandeCallback;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class HomePageActivity extends AppCompatActivity implements RecycleViewInterface {

    private List<Bevanda> productList;
    private RecyclerView recyclerView;
    private BevandaAdapter adapter;
    private ControllerCarrello controllerCarrello;

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
        Utente utente;
        Intent intent = getIntent();
        utente = (Utente) intent.getSerializableExtra("utente");
        Log.d("Utente in HomePage: ", utente.toString());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigator);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.profile:
                        // Handle profile click
                        return true;
                    case R.id.carrello:
                        // Handle cart click
                        Intent cartIntent = new Intent(HomePageActivity.this, CarrelloActivity.class);
                        startActivity(cartIntent);
                        return true;
                    case R.id.home:
                        // Handle home click
                        return true;
                }
                return false;
            }
        });

        recyclerView = findViewById(R.id.rec_viewCard);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        controllerCarrello = ControllerCarrello.getInstance();

        controllerHomePage.getBevande(this, new BevandeCallback() {
            @Override
            public void onBevandeLoaded(List<Bevanda> productList) {
                adapter = new BevandaAdapter(productList, controllerCarrello, HomePageActivity.this);
                recyclerView.setAdapter(adapter);

                Log.d("Product List", productList.toString());
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
