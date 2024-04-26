package com.example.melogiri.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.melogiri.R;
import com.example.melogiri.adapter.BevandaAdapter;
import com.example.melogiri.adapter.OrdineAdapter;
import com.example.melogiri.controller.ControllerProfilo;
import com.example.melogiri.controller.StoricoOrdineCallBack;
import com.example.melogiri.model.StoricoOrdine;
import com.example.melogiri.model.Utente;
import com.example.melogiri.recycleView.RecycleViewInterface;

import java.util.List;

public class ProfileActivity extends AppCompatActivity implements RecycleViewInterface {
    private OrdineAdapter adapter;
    private RecyclerView recyclerView;
    private boolean isCartView = false; // Flag to indicate if the adapter is being used in cart view

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utente);

        Intent intent = getIntent();
        Utente utente = (Utente) intent.getSerializableExtra("utente");

        recyclerView = findViewById(R.id.ordiniUtente);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        TextView textViewNomeUtente = findViewById(R.id.nomeUtente);
        textViewNomeUtente.setText(utente.getNome());

        TextView textViewCognomeUtente = findViewById(R.id.cognomeUtente);
        textViewCognomeUtente.setText(utente.getCognome());

        TextView textViewDataNascita = findViewById(R.id.dataDiNascita);
        if (utente.getData() != null) {
            textViewDataNascita.setText(utente.getData().toString()); // Make sure data is formatted correctly
        }

        TextView textViewEmail = findViewById(R.id.Email);
        textViewEmail.setText(utente.getEmail());

        Button btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(ProfileActivity.this, LoginActivity.class);
                loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Clear the activity stack
                startActivity(loginIntent);
                finish(); // Ensure this activity is finished so the user can't return to it by pressing back
            }
        });

        ControllerProfilo controllerProfilo = ControllerProfilo.getInstance();
        controllerProfilo.getOrdini(this, utente.getId(), new StoricoOrdineCallBack()
        {
            @Override
            public void onSuccess(List<StoricoOrdine> storicoOrdineList)
            {
                //GESTIRE L'HOLDER
                adapter = new OrdineAdapter(storicoOrdineList, ProfileActivity.this, controllerProfilo, isCartView);
                recyclerView.setAdapter(adapter);



            }
        });

    }


    @Override
    public void onClickItem(int position) {

    }
}
