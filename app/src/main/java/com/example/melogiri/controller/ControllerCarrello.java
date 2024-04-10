package com.example.melogiri.controller;

import android.widget.Toast;

import com.example.melogiri.model.Bevanda;

import java.util.ArrayList;
import java.util.List;

public class ControllerCarrello {
    private static ControllerCarrello instance;
    private List<Bevanda> productList;

    public ControllerCarrello() {
        productList = new ArrayList<>();
    }

    public static synchronized ControllerCarrello getInstance() {
        if (instance == null) {
            instance = new ControllerCarrello();
        }
        return instance;
    }

    public void aggiungiProdotto(Bevanda bevanda)
    {
        productList.add(bevanda);
    }

    public void rimuoviProdotto(Bevanda bevanda) {
        productList.remove(bevanda);
    }

    public List<Bevanda> getProdotti() {
        return productList;
    }

    public double getPrezzoTotale() {
        double prezzoTotale = 0.0;

        for (Bevanda prodotto : productList) {
            prezzoTotale += prodotto.getPrezzo(); // Assumendo che Bevanda abbia un metodo getPrezzo() che restituisce il prezzo come double
        }
        return prezzoTotale;
    }
}
