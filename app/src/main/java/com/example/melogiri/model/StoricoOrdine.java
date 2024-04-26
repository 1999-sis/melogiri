package com.example.melogiri.model;

public class StoricoOrdine
{
    private String nome;
    private String date;
    private int quantita;
    private int totalePrezzo;

    public StoricoOrdine(String nome, String date, int quantita, int totalePrezzo) {
        this.nome = nome;
        this.date = date;
        this.quantita = quantita;
        this.totalePrezzo = totalePrezzo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public int getTotalePrezzo() {
        return totalePrezzo;
    }

    public void setTotalePrezzo(int totalePrezzo) {
        this.totalePrezzo = totalePrezzo;
    }
}
