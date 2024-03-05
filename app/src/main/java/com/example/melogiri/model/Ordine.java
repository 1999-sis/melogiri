package com.example.melogiri.model;

import java.util.Date;

public class Ordine
{
    private String stato;
    private int totale;
    private Date data;
    private Utente utente;

    public Ordine(String stato, int totale, Date data, Utente utente)
    {
        this.stato = stato;
        this.totale = totale;
        this.data = data;
        this.utente = utente;
    }

    public String getStato()
    {
        return stato;
    }

    public void setStato(String stato)
    {
        this.stato = stato;
    }

    public int getTotale()
    {
        return totale;
    }

    public void setTotale(int totale)
    {
        this.totale = totale;
    }

    public Date getData()
    {
        return data;
    }

    public void setData(Date data)
    {
        this.data = data;
    }

    public Utente getUtente()
    {
        return utente;
    }

    public void setUtente(Utente utente)
    {
        this.utente = utente;
    }
}
