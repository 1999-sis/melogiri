package com.example.melogiri.util;

import android.util.Log;

import com.example.melogiri.model.Bevanda;
import com.example.melogiri.model.Categoria;
import com.example.melogiri.model.Ordine;
import com.example.melogiri.model.Utente;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class SocketAPI {

    private String serverAddress;   // Server IP Address
    private int port;               // Server Port
    private static final String TAG = "SocketAPI";
    private Socket socket;
    public SocketAPI(String serverAddress, int port)
    {
        this.serverAddress = serverAddress;
        this.port = port;
    }
    public List<Bevanda> getBevande() throws IOException, JSONException
    {
        List<Bevanda> listaBevande = new ArrayList<>();
        String response = (createChannelSocket("3"));
        JSONArray jsonArray = new JSONArray(response);
        Log.d("NUMBER_BEVANDE_DATABASE", "Number of elements in JSONArray: " + jsonArray.length());
        for(int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            // Estrai i campi dall'oggetto JSON
            int idbevanda = jsonObject.getInt("idbevanda");
            String nome = jsonObject.getString("nome");
            String photo_url = jsonObject.getString("photo_url");
            int livello_alcolico = jsonObject.getInt("livello_alcolico");
            String descrizione = jsonObject.getString("descrizione");
            Categoria categoria = new Categoria();
            categoria.setCategoria(jsonObject.getString("categoria"));
            int prezzo = jsonObject.getInt("prezzo");
            Bevanda bevanda = new Bevanda(idbevanda, nome, photo_url, livello_alcolico,descrizione,categoria,prezzo);
            listaBevande.add(bevanda);
        }

        return listaBevande;
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
        utente.setData(jsonObject.getString("data_di_nascita"));
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

    public Ordine creaOrdine(Utente utente, List<Bevanda> carrello, OrdineCallback ordineCallback)
    {
        // Imposta l'ID utente statico per il test
        int idUtenteTest = 1;

        if (carrello == null || carrello.isEmpty()) {
            Log.e(TAG, "Il carrello è vuoto.");
            return null;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("4"); // Prefisso per l'azione di creazione dell'ordine
        sb.append(idUtenteTest); // Utilizza l'ID utente statico
        sb.append("!");
        double totale = 0.0;
        for (Bevanda bevanda : carrello) {
            sb.append("{").append(bevanda.getID()).append(",").append(bevanda.getQuantita()).append("},");
            totale += bevanda.getPrezzo() * bevanda.getQuantita();
        }

        // Rimuove l'ultima virgola se ci sono bevande nel carrello
        if (!carrello.isEmpty()) {
            sb.setLength(sb.length() - 1);
        }

        // Aggiunge il prezzo totale al messaggio
        sb.append("$").append(totale);

        String messaggioCompleto = sb.toString();
        Log.d(TAG, "Messaggio completo inviato al server: " + messaggioCompleto);

        // Qui avviene la comunicazione con il server utilizzando il socket
        String response = createChannelSocket(messaggioCompleto);
        if (response != null && response.equals("Ordine_OKKE")) {
            Log.d(TAG, "Risposta del server ricevuta: " + response);
            // Assumi che l'ordine sia stato creato con successo e restituisci un nuovo oggetto Ordine
            return new Ordine("confermato", totale, new Date(), utente, carrello);
        } else {
            // Log dell'errore o della risposta inattesa
            Log.e(TAG, "Errore nella creazione dell'ordine o risposta inattesa: " + response);
            return null;
        }
    }



    public String createChannelSocket(String message) {
        String response = "Errore nella richiesta";
        try {
            Log.d(TAG, "Tentativo di connessione al server: " + serverAddress + ":" + port);
            socket = new Socket(serverAddress, port);

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            InputStream inputStream = socket.getInputStream();
            Log.d(TAG, "Invio messaggio al server: " + message);
            writer.println(message);

            byte[] buffer = new byte[100000];
            int bytesRead = inputStream.read(buffer);

            if (bytesRead > 0) {
                response = new String(buffer, 0, bytesRead);
                Log.d(TAG, "Risposta ricevuta dal server: " + response);
            } else {
                Log.e(TAG, "Il server non ha inviato dati validi.");
            }
        } catch (UnknownHostException ex) {
            Log.e(TAG, "Indirizzo server non valido: " + serverAddress, ex);
        } catch (IOException ex) {
            Log.e(TAG, "Errore I/O durante la connessione al server: " + serverAddress, ex);
        } finally {
            if (socket != null && !socket.isClosed()) {
                try {
                    socket.close();
                } catch (IOException ex) {
                    Log.e(TAG, "Errore nella chiusura del socket", ex);
                }
            }
        }
        return response;
    }

}