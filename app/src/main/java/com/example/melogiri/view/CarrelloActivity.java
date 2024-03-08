package com.example.melogiri.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.melogiri.R;
import com.example.melogiri.fragment.CarrelloFragment;

public class CarrelloActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carrello_activity); // Change to the correct layout resource

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new CarrelloFragment())
                    .commit();
        }
    }
}
