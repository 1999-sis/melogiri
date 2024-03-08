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

        // Check if products are retrieved correctly
        List<Bevanda> prodottiCarrello = controllerCarrello.getProdotti();
        if (prodottiCarrello != null) {
            Log.d("CarrelloFragment", "Products in the cart: " + prodottiCarrello.size());
            carrelloAdapter = new CarrelloAdapter(prodottiCarrello);
            recyclerView.setAdapter(carrelloAdapter);
        } else {
            Log.e("CarrelloFragment", "No products in the cart");
            // Log an error or handle the case where there are no products in the cart
            // You can add a placeholder message or handle the empty state here
        }

        return view;
    }
}
