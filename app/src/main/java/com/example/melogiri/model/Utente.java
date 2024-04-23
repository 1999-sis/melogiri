package com.example.melogiri.model;

import java.io.Serializable;
import java.util.List;

public class Utente implements Serializable
{
    private int idutente;
    private String nome;
    private String cognome;
    private String data;
    private String email;
    private String password;
    private List<Ordine> ordini;

    public Utente() {

    }

    @Override
    public String toString() {
        return "Utente{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", data=" + data +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", ordini=" + ordini +
                '}';
    }

    public Utente(int idutente, String nome, String cognome, String data, String email, String password) {
        this.idutente = idutente;
        this.nome = nome;
        this.cognome = cognome;
        this.data = data;
        this.email = email;
        this.password = password;
    }



    public String getNome()
    {
        return nome;
    }
    public void setNome(String nome)
    {
        this.nome = nome;
    }
    public String getCognome()
    {
        return cognome;
    }
    public void setCognome(String cognome)
    {
        this.cognome = cognome;
    }
    public String getData()
    {
        return data;
    }
    public void setData(String data)
    {
        this.data = data;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public List<Ordine> getOrdini()
    {
        return ordini;
    }
    public void setOrdini(List<Ordine> ordini)
    {
        this.ordini = ordini;
    }
    public int getId() { return idutente; }
    public void setId(int idutente) { this.idutente=idutente; }
}
