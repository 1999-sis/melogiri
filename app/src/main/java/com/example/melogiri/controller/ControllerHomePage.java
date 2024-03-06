package com.example.melogiri.controller;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.melogiri.adapter.BevandaAdapter;
import com.example.melogiri.model.Bevanda;
import com.example.melogiri.model.Utente;
import com.example.melogiri.util.BevandeCallback;
import com.example.melogiri.util.SocketAPI;
import com.example.melogiri.view.HomePageActivity;
import com.example.melogiri.view.LoginActivity;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControllerHomePage
{
    List<Bevanda> listaBevande = new ArrayList<>();
    SocketAPI socketAPI = new SocketAPI("209.38.244.243", 8080);
    public List<Bevanda> getBevande(HomePageActivity activity, BevandeCallback callback)
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {
                    listaBevande = socketAPI.getBevande();
                    // Una volta ottenuta la productList, chiama il metodo di callback
                    callback.onBevandeLoaded(listaBevande);
                    Log.d("BEVANDE LISTA", String.valueOf(listaBevande.size()));
                }
                catch (IOException | JSONException e)
                {
                    throw new RuntimeException(e);
                }
            }
        });

        thread.start();
        return listaBevande;
    }
}
