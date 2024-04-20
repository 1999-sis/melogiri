package com.example.melogiri.controller;

import android.content.Context;
import android.widget.Toast;
import com.example.melogiri.model.Bevanda;
import com.example.melogiri.model.Ordine;
import com.example.melogiri.model.Utente;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ControllerCarrello {
    private static ControllerCarrello instance;
    private List<Bevanda> productList;

    public interface AcquistoCallback {
        void onSuccess(Ordine ordine);
        void onQuantitaZero();
        void onCarrelloVuoto();
    }

    private ControllerCarrello() {
        productList = new ArrayList<>();
    }

    public static synchronized ControllerCarrello getInstance() {
        if (instance == null) {
            instance = new ControllerCarrello();
        }
        return instance;
    }

    public void aggiungiProdotto(Bevanda bevanda) {
        productList.add(bevanda);
    }

    public void rimuoviProdotto(Bevanda bevanda) {
        productList.remove(bevanda);
    }

    public List<Bevanda> getProdotti() {
        return new ArrayList<>(productList);
    }

    public double getPrezzoTotale() {
        double prezzoTotale = 0.0;
        for (Bevanda prodotto : productList) {
            prezzoTotale += prodotto.getPrezzo() * prodotto.getQuantita();
        }
        return prezzoTotale;
    }

    public void finalizzaAcquisto(Utente utente, Context context, AcquistoCallback callback) {
        boolean quantitaValida = !productList.isEmpty();

        for (Bevanda prodotto : productList) {
            if (prodotto.getQuantita() == 0) {
                quantitaValida = false;
                break;
            }
        }

        if (quantitaValida) {
            Date today = new Date();
            double prezzoTot = getPrezzoTotale();
            String statoDefault = "NUOVO";
            Ordine ordine = new Ordine(statoDefault, prezzoTot, today, utente);
            callback.onSuccess(ordine);
        } else if (!quantitaValida && !productList.isEmpty()) {
            Toast.makeText(context, "La quantità dei prodotti non può essere 0", Toast.LENGTH_SHORT).show();
            callback.onQuantitaZero();
        } else {
            Toast.makeText(context, "Il carrello è vuoto", Toast.LENGTH_SHORT).show();
            callback.onCarrelloVuoto();
        }
    }
}
