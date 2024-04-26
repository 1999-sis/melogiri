package com.example.melogiri.controller;

import android.util.Log;

import com.example.melogiri.adapter.BevandaAdapter;
import com.example.melogiri.adapter.OrdineAdapter;
import com.example.melogiri.model.Bevanda;
import com.example.melogiri.model.StoricoOrdine;
import com.example.melogiri.util.OrdineCallback;
import com.example.melogiri.util.SocketAPI;
import com.example.melogiri.view.ProfileActivity;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class ControllerProfilo
{
    private SocketAPI socketAPI = new SocketAPI("209.38.244.243", 8080);
    private List<StoricoOrdine> ordiniUtente;
    private static ControllerProfilo instance;
    private OrdineAdapter adapter;

    public static synchronized ControllerProfilo getInstance() {
        if (instance == null) {
            instance = new ControllerProfilo();
        }
        return instance;
    }

    public void getOrdini(ProfileActivity profileActivity, int userID, StoricoOrdineCallBack storicoOrdineCallBack)
    {
        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run() {
                try {
                    // Ottieni le bevande dalla SocketAPI
                    ordiniUtente = socketAPI.getOrdini(userID);

                    // Una volta ottenute le bevande, chiama il metodo di callback sull'UI thread

                    profileActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            storicoOrdineCallBack.onSuccess(ordiniUtente);
                        }
                    });

                    Log.d("ORDINI LISTA", String.valueOf(ordiniUtente.size()));

                }
                catch (IOException | JSONException e)

                {
                    // Gestisci eventuali eccezioni
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }
}
