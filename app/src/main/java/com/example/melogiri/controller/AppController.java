package com.example.melogiri.controller;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.melogiri.model.Utente;
import com.example.melogiri.util.SocketAPI;
import com.example.melogiri.view.HomePageActivity;
import com.example.melogiri.view.LoginActivity;

import org.json.JSONException;

import java.io.IOException;

public class AppController {
    private static final String TAG = "AppController";
    private SocketAPI socketAPI;

    public AppController() {
        socketAPI = new SocketAPI("209.38.244.243", 8080);
    }

    public void loginTask(LoginActivity activity, String email, String password) {

        // Creare e avviare un nuovo thread
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Effettua il login sul server
                    Utente utente = socketAPI.login(email, password);
                    Log.d("utente", utente.toString());

                    // Se l'email dell'utente non è null, le credenziali sono corrette
                    if (utente.getEmail() != null) {
                        Intent intent = new Intent(activity, HomePageActivity.class);
                        intent.putExtra("utente", utente);
                        activity.startActivity(intent);
                        activity.finish(); // Finish the LoginActivity to prevent going back to it
                    } else {
                        // Esegui il codice dell'interfaccia utente sul thread principale
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(activity, "Email o password non corretti.", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                } catch (final JSONException e) {
                    e.printStackTrace();
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(activity, "Errore nel formato dei dati: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (final IOException e) {
                    e.printStackTrace();
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(activity, "Errore di connessione: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

        thread.start();
    }
}