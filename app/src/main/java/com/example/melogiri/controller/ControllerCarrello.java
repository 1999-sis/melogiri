package com.example.melogiri.controller;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.example.melogiri.model.Bevanda;
import com.example.melogiri.model.Ordine;
import com.example.melogiri.model.Utente;
import com.example.melogiri.util.SocketAPI;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ControllerCarrello {

    private SocketAPI socketAPI;
    private static ControllerCarrello instance;
    private List<Bevanda> productList;

    public interface AcquistoCallback {
        void onSuccess(Ordine ordine);
        void onQuantitaZero();
        void onCarrelloVuoto();
    }

    private ControllerCarrello() {
        productList = new ArrayList<>();

        socketAPI = new SocketAPI("209.38.244.243", 8080);

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
        new Thread(() -> {
            Log.d(TAG, "Inizio del processo di finalizzazione dell'acquisto");
            boolean quantitaValida = verificaQuantita(productList);
            if (!quantitaValida) {
                Log.d(TAG, "Quantità non valida rilevata nei prodotti");
                runOnUiThread(context, () -> {
                    Toast.makeText(context, "La quantità dei prodotti non può essere 0", Toast.LENGTH_SHORT).show();
                    callback.onQuantitaZero();
                });
                return;
            }

            if (productList.isEmpty()) {
                runOnUiThread(context, () -> {
                    Toast.makeText(context, "Il carrello è vuoto", Toast.LENGTH_SHORT).show();
                    callback.onCarrelloVuoto();
                });
                return;
            }

            double prezzoTot = getPrezzoTotale();

            try {
                Ordine ordine = socketAPI.creaOrdine(utente, productList);
                if (ordine != null) {
                    ordine.setUtente(utente);  // Link the user with the order
                    runOnUiThread(context, () -> callback.onSuccess(ordine));
                } else {
                    runOnUiThread(context, () -> Toast.makeText(context, "Errore nella creazione dell'ordine", Toast.LENGTH_SHORT).show());
                }
            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(context, () -> Toast.makeText(context, "Errore di connessione al server", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }


    private boolean verificaQuantita(List<Bevanda> productList) {
        for (Bevanda prodotto : productList) {
            if (prodotto.getQuantita() == 0) {
                return false;
            }
        }
        return true;
    }

    private void runOnUiThread(Context context, Runnable action) {
        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(action);
        }
    }
}