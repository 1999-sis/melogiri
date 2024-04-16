package com.example.melogiri.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.melogiri.R;
import com.example.melogiri.adapter.CarrelloAdapter;
import com.example.melogiri.controller.ControllerCarrello;
import com.example.melogiri.fragment.CarrelloFragment;
import com.example.melogiri.model.Bevanda;
import com.example.melogiri.model.Ordine;
import com.example.melogiri.model.Utente;

import java.util.Date;

public class CarrelloActivity extends AppCompatActivity implements CarrelloAdapter.OnCarrelloChangeListener {

    private TextView prezzoTotaleTextView;
    private ControllerCarrello controllerCarrello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carrello_activity);

        Intent intent = getIntent();
        Utente utente = (Utente) intent.getSerializableExtra("utente");
        prezzoTotaleTextView = findViewById(R.id.prezzoTotale);

        // Usa l'istanza singleton del ControllerCarrello
        controllerCarrello = ControllerCarrello.getInstance();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new CarrelloFragment())
                    .commit();
        }

        Button buttonAcquista = findViewById(R.id.button3);
        buttonAcquista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Controlla direttamente la lista del singleton
                if (!controllerCarrello.getProdotti().isEmpty()) {
                    Date today = new Date();
                    double prezzoTot = controllerCarrello.getPrezzoTotale();
                    Ordine ordine = new Ordine("stato", prezzoTot, today, utente);
                    Log.d("CarrelloDebug", "Ordine: " + ordine.toString());
                    // Qui dovresti implementare la logica per finalizzare l'ordine
                } else {
                    Toast.makeText(CarrelloActivity.this, "Il carrello è vuoto", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onPrezzoTotaleChanged(double nuovoPrezzoTotale) {
        runOnUiThread(() -> prezzoTotaleTextView.setText(String.format("%.2f euro", nuovoPrezzoTotale)));
    }

    @Override
    public void onProdottoRimosso(Bevanda prodotto) {
        runOnUiThread(() -> {
            Toast.makeText(this, prodotto.getNome() + " rimosso dal carrello", Toast.LENGTH_SHORT).show();
            // Qui potrebbe essere necessario aggiornare l'adapter del RecyclerView
        });
    }
}
