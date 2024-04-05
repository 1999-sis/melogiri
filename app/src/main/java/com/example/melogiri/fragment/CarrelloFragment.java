package com.example.melogiri.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.melogiri.controller.ControllerCarrello;
import com.example.melogiri.adapter.CarrelloAdapter;
import com.example.melogiri.R;
import com.example.melogiri.model.Bevanda;
import java.util.List;

public class CarrelloFragment extends Fragment {

    private RecyclerView recyclerView;
    private CarrelloAdapter carrelloAdapter;
    private ControllerCarrello controllerCarrello;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carrello, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewCart);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        controllerCarrello = ControllerCarrello.getInstance();

        List<Bevanda> prodottiCarrello = controllerCarrello.getProdotti();
        if (prodottiCarrello != null && !prodottiCarrello.isEmpty()) {
            Log.d("CarrelloFragment", "Products in the cart: " + prodottiCarrello.size());
            // Assicurati che l'Activity corrente implementi l'interfaccia OnCarrelloChangeListener
            if (getActivity() instanceof CarrelloAdapter.OnCarrelloChangeListener) {
                carrelloAdapter = new CarrelloAdapter(prodottiCarrello, (CarrelloAdapter.OnCarrelloChangeListener) getActivity());
                recyclerView.setAdapter(carrelloAdapter);
            } else {
                Log.e("CarrelloFragment", "The activity does not implement OnCarrelloChangeListener");
            }
        } else {
            Log.e("CarrelloFragment", "No products in the cart");
            // Potresti voler mostrare un messaggio o un'immagine placeholder qui
        }

        return view;
    }
}
