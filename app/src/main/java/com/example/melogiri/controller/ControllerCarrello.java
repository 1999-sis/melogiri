package com.example.melogiri.controller;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.example.melogiri.model.Bevanda;
import com.example.melogiri.model.Ordine;
import com.example.melogiri.model.Utente;
import com.example.melogiri.util.OrdineCallback;
import com.example.melogiri.util.SocketAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ControllerCarrello {

    private SocketAPI socketAPI;
    private static ControllerCarrello instance;
    private List<Bevanda> productList;

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

    public void aggiungiProdotto(Bevanda bevanda)
    {
        if(!productList.contains(bevanda))
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

    public void finalizzaAcquisto(Utente utente, Context context, Activity activity, OrdineCallback ordineCallback)
    {
        final Ordine[] ordine = new Ordine[1];
        Log.d(TAG, "Inizio del processo di finalizzazione dell'acquisto");
        final boolean[][] quantitaValida = {{verificaQuantita(productList)}};
        if (!quantitaValida[0][0])
        {
            Log.d(TAG, "Quantità non valida rilevata nei prodotti");
            runOnUiThread(context, () -> {
                Toast.makeText(context, "La quantità dei prodotti non può essere 0", Toast.LENGTH_SHORT).show();

            });
            return;
        }

        if (productList.isEmpty()) {
            runOnUiThread(context, () -> {
                Toast.makeText(context, "Il carrello è vuoto", Toast.LENGTH_SHORT).show();
            });
            return;
        }

        new Thread(() ->
        {
            synchronized (this)
            {
                ordine[0] = socketAPI.creaOrdine(utente, productList);
                Log.d("ORDINE", ordine[0].toString());
                runOnUiThread(context,
                        ()->
                        {
                            Toast.makeText(context, "Ordine confermato", Toast.LENGTH_SHORT).show();

                            productList.clear();
                        });
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