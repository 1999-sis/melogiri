package com.example.melogiri.view;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.melogiri.R;
import com.example.melogiri.fragment.CarrelloFragment;
import com.example.melogiri.adapter.CarrelloAdapter;

public class CarrelloActivity extends AppCompatActivity implements CarrelloAdapter.OnCarrelloChangeListener {

    private TextView prezzoTotaleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carrello_activity);

        prezzoTotaleTextView = findViewById(R.id.prezzoTotale);

        if (savedInstanceState == null) {
            CarrelloFragment fragment = new CarrelloFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    @Override
    public void onPrezzoTotaleChanged(double nuovoPrezzoTotale) {
        runOnUiThread(() -> prezzoTotaleTextView.setText(String.format("%.2f euro", nuovoPrezzoTotale)));
    }
}
