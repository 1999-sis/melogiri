package com.example.melogiri.controller;

import android.util.Log;
import android.widget.Toast;
import com.example.melogiri.util.SocketAPI;
import com.example.melogiri.view.RegisterActivity;


public class ControllerRegister
{
    SocketAPI socket = new SocketAPI("209.38.244.243",8080);
    public void register(RegisterActivity registerActivity, String nome, String cognome, String data, String email, String password)
    {
        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run() {
                try
                {
                    String response = socket.register(nome, cognome, data, email, password);
                    Log.d("REGISTER_UTENTE", response);
                    if(response.equalsIgnoreCase("Registration_Successful"))
                    {
                        Toast.makeText(registerActivity.getApplicationContext(), "Registration_Successful", Toast.LENGTH_SHORT).show();
                        //intent per ritornare al Login

                    }

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

    }
}
