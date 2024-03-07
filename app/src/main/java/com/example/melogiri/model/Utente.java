package com.example.melogiri.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Utente implements Serializable
{
    private String nome;
    private String cognome;
    private Date data;
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

    public Utente(String idutente, String nome, String cognome, String data_di_nascita, String email, String password) {
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
    public Date getData()
    {
        return data;
    }
    public void setData(Date data)
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
}
