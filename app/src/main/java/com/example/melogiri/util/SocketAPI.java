package com.example.melogiri.util;

import android.util.Log;

import com.example.melogiri.model.Utente;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketAPI {

    private String serverAddress;   // Server IP Address
    private int port;               // Server Port
    private static final String TAG = "SocketAPI";
    private Socket socket;
    public SocketAPI(String serverAddress, int port) {
        this.serverAddress = serverAddress;
        this.port = port;
    }

    public Utente login(String email, String password) throws JSONException, IOException {
        String message = "1" + email + "&$" + password;
        String response = createChannelSocket(message);
        Utente utente = new Utente();

        if(response.equalsIgnoreCase("user_notfound")){
            Log.d(TAG, "User not found.");
            return utente;
        }

        Log.d(TAG, "Received response from server: " + response);

        JSONTokener tokener = new JSONTokener(response);
        JSONObject jsonObject = new JSONObject(tokener);

        utente.setNome(jsonObject.getString("nome"));
        utente.setCognome(jsonObject.getString("cognome"));
        utente.setEmail(jsonObject.getString("email"));
        utente.setPassword(jsonObject.getString("password"));

        return utente;
    }

    public String register(String nome, String cognome, String data, String email, String password) throws IOException
    {
        /* Il Client manda la sequenza: nome_cognome&data?email!password */
        String message = "2" + nome + "_" + cognome + "&" + data+ "?" + email + "!" + password;
        String response = createChannelSocket(message);
        System.out.println(response);
        return response;
    }
    public String createChannelSocket(String message) throws IOException {
        String response = "Error in request";

        try
        {
            Log.d(TAG, "Connecting to server: " + serverAddress + ":" + port);
            socket = new Socket(serverAddress, port);

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            InputStream inputStream = socket.getInputStream();

            writer.println(message);

            byte[] buffer = new byte[100000];
            int bytesRead = inputStream.read(buffer);

            if (bytesRead > 0) {
                response = new String(buffer, 0, bytesRead);
            } else {
                Log.e(TAG, "The server did not send valid data.");
            }

        } catch (UnknownHostException ex) {
            Log.e(TAG, "Invalid server IP address: " + serverAddress);
            ex.printStackTrace();
        } catch (IOException ex) {
            Log.e(TAG, "I/O error while connecting to the server: " + serverAddress);
            ex.printStackTrace();
        }
        finally
        {
            socket.close();
        }

        return response;
    }

}

