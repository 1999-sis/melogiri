package com.example.melogiri.controller;

import android.util.Log;

import com.example.melogiri.model.Bevanda;
import com.example.melogiri.util.BevandeCallback;
import com.example.melogiri.util.SocketAPI;
import com.example.melogiri.view.HomePageActivity;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControllerHomePage
{
    List<Bevanda> listaBevande = new ArrayList<>();
    SocketAPI socketAPI = new SocketAPI("209.38.244.243", 8080);
    // Modifica il controller in modo che la chiamata API restituisca void
    public void getBevande(HomePageActivity activity, BevandeCallback callback) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Ottieni le bevande dalla SocketAPI
                    listaBevande = socketAPI.getBevande();

                    // Una volta ottenute le bevande, chiama il metodo di callback sull'UI thread
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            callback.onBevandeLoaded(listaBevande);
                        }
                    });

                    Log.d("BEVANDE LISTA", String.valueOf(listaBevande.size()));
                    Log.d("BEVANDE LISTA Verbose", listaBevande.toString());
                } catch (IOException | JSONException e) {
                    // Gestisci eventuali eccezioni
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }

}
