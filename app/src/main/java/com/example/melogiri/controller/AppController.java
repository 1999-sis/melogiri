package com.example.melogiri.controller;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.melogiri.model.SocketAPI;
import com.example.melogiri.model.Utente;
import com.example.melogiri.view.HomePageActivity;
import com.example.melogiri.view.LoginActivity;

import org.json.JSONException;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class AppController implements Runnable
{
    private OkHttpClient okHttpClient;
    private static final String TAG = "AppController";
    private SocketAPI socketAPI;

    public AppController()
    {
        socketAPI = new SocketAPI("209.38.244.243", 8080);
    }

    public void loginTask(LoginActivity activity, String email, String password)
    {
        // Definisci l'indirizzo del server e la porta
        String serverAddress = "209.38.244.243";
        int port = 8080; // Porta del server

        // Crea un nuovo oggetto SocketAPI
        SocketAPI socketAPI = new SocketAPI(serverAddress, port);

        // Creare e avviare un nuovo thread
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {
                    // Effettua il login sul server
                    Utente utente = socketAPI.login(email, password);
                    Log.d("utente",utente.toString());
                    if(utente.getEmail()!=null) {
                        Intent intent = new Intent(activity, HomePageActivity.class);
                        intent.putExtra("nome", utente.getNome());
                        intent.putExtra("cognome", utente.getCognome());
                        intent.putExtra("email", utente.getEmail());
                        activity.startActivity(intent);
                        activity.finish(); // Finish the LoginActivity to prevent going back to it using the back button
                    }else{
                        //Toast toast = Toast.makeText(activity,"Email/Password Errati",Toast.LENGTH_SHORT);
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }






    public String register(String nome, String cognome, String email, String password) {
        // Here you can implement the registration logic using the model classes
        // For demonstration, we'll just return a success message
        return "Registration successful";
    }


    @Override
    public void run()
    {

    }
}

