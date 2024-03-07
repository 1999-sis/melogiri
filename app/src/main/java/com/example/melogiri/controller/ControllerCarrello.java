package com.example.melogiri.controller;

import android.util.Log;

import com.example.melogiri.model.Bevanda;
import com.example.melogiri.model.Carrello;

import java.util.List;

public class ControllerCarrello {
    private static ControllerCarrello instance;
    private Carrello carrello;

    private ControllerCarrello() {
        // Inizializza il carrello
        carrello = new Carrello();
    }

    public static synchronized ControllerCarrello getInstance() {
        if (instance == null) {
            instance = new ControllerCarrello();
        }
        return instance;
    }

    public void aggiungiProdotto(Bevanda prodotto) {
        carrello.aggiungiProdotto(prodotto);
        Log.d("ControllerCarrello", "Prodotto aggiunto al carrello: " + prodotto.getNome());
    }

    public void rimuoviProdotto(Bevanda prodotto) {
        carrello.rimuoviProdotto(prodotto);
        Log.d("ControllerCarrello", "Prodotto rimosso dal carrello: " + prodotto.getNome());
    }

    public boolean contieneProdotto(Bevanda prodotto) {
        return carrello.contieneProdotto(prodotto);
    }

    public List<Bevanda> getProdotti() {
        return carrello.getProdotti();
    }
}
