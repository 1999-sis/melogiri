package com.example.melogiri.model;

import java.util.ArrayList;
import java.util.List;

public class Carrello {
    private List<Bevanda> bevande;

    public Carrello() {
        bevande = new ArrayList<>(); // Inizializza la lista bevande nel costruttore
    }

    public void aggiungiProdotto(Bevanda prodotto) {
        bevande.add(prodotto);
    }

    public void rimuoviProdotto(Bevanda prodotto) {
        bevande.remove(prodotto);
    }

    public boolean contieneProdotto(Bevanda prodotto) {
            return bevande.contains(prodotto);
        }

    public List<Bevanda> getProdotti() {
        return bevande;
    }
}
