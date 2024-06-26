package com.example.melogiri.model;

import java.util.Date;
import java.util.List;

public class Ordine {
    private String stato;
    private double totale;
    private Date data;
    private Utente utente;
    private List<Bevanda> bevandeAquistate;
    private int userId;  // Direct reference to user ID for convenience

    // Constructor without bevandeAquistate
    public Ordine(String stato, double totale, Date data, Utente utente) {
        this.stato = stato;
        this.totale = totale;
        this.data = data;
        this.utente = utente;
        this.userId = utente.getId();  // Initialize user ID from Utente
    }

    // Constructor with bevandeAquistate
    public Ordine(String stato, double totale, Date data, Utente utente, List<Bevanda> bevandeAquistate) {
        this.stato = stato;
        this.totale = totale;
        this.data = data;
        this.utente = utente;
        this.bevandeAquistate = bevandeAquistate;
        this.userId = utente.getId();  // Initialize user ID from Utente
    }

    // Getter for user ID for direct access
    public int getUserId() {
        return userId;
    }

    // Existing getters and setters
    public List<Bevanda> getBevandeAquistate() {
        return bevandeAquistate;
    }

    public void setBevandeAquistate(List<Bevanda> bevandeAquistate) {
        this.bevandeAquistate = bevandeAquistate;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public double getTotale() {
        return totale;
    }

    public void setTotale(double totale) {
        this.totale = totale;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
        this.userId = utente.getId();  // Update user ID if Utente object is reset
    }

    @Override
    public String toString() {
        return "Ordine{" +
                ", stato='" + stato + '\'' +
                ", totale=" + totale +
                ", data=" + data +
                ", utente=" + utente +
                ", bevandeAquistate=" + bevandeAquistate +
                ", userId=" + userId +
                '}';
    }
}
