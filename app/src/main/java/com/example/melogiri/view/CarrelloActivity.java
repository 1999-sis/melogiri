package com.example.melogiri.view;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.melogiri.R;
import com.example.melogiri.adapter.CarrelloAdapter;
import com.example.melogiri.fragment.CarrelloFragment;
import com.example.melogiri.model.Bevanda;

public class CarrelloActivity extends AppCompatActivity implements CarrelloAdapter.OnCarrelloChangeListener {

    private TextView prezzoTotaleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carrello_activity);

        prezzoTotaleTextView = findViewById(R.id.prezzoTotale);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new CarrelloFragment())
                    .commit();
        }
    }

    @Override
    public void onPrezzoTotaleChanged(double nuovoPrezzoTotale) {
        runOnUiThread(() -> prezzoTotaleTextView.setText(String.format("%.2f euro", nuovoPrezzoTotale)));
    }

    @Override
    public void onProdottoRimosso(Bevanda prodotto) {
        runOnUiThread(() -> {
            Toast.makeText(this, prodotto.getNome() + " rimosso dal carrello", Toast.LENGTH_SHORT).show();
            // Potenzialmente aggiorna la UI qui se necessario
        });
    }
}
