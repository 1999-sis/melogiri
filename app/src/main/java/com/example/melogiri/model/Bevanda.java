package com.example.melogiri.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Bevanda implements Parcelable {
    private String nome;
    private String urlPhoto;
    private int livelloAlcolico;
    private int quantita;
    private String descrizione;
    private Categoria categoria;
    private int prezzo;

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public Bevanda(String nome, String urlPhoto, int livelloAlcolico, String descrizione,
                   Categoria categoria, int prezzo, int quantita) {
        this.nome = nome;
        this.urlPhoto = urlPhoto;
        this.livelloAlcolico = livelloAlcolico;
        this.descrizione = descrizione;
        this.categoria = categoria;
        this.prezzo = prezzo;
        this.quantita = quantita;
    }

    // Costruttore per le prove
    public Bevanda(String nome, String descrizione, String urlPhoto, Categoria categoria) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.urlPhoto = urlPhoto;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public int getLivelloAlcolico() {
        return livelloAlcolico;
    }

    public void setLivelloAlcolico(int livelloAlcolico) {
        this.livelloAlcolico = livelloAlcolico;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(int prezzo) {
        this.prezzo = prezzo;
    }

    // Implementazione dei metodi Parcelable
    public Bevanda(Parcel in) {
        nome = in.readString();
        urlPhoto = in.readString();
        livelloAlcolico = in.readInt();
        descrizione = in.readString();
        categoria = in.readParcelable(Categoria.class.getClassLoader());
        prezzo = in.readInt();
        quantita = in.readInt(); // Aggiungi il campo "quantita"
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeInt(livelloAlcolico);
        dest.writeString(descrizione);
        dest.writeParcelable(categoria, flags);
        dest.writeInt(prezzo);
        dest.writeInt(quantita); // Aggiungi il campo "quantita"
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Bevanda> CREATOR = new Creator<Bevanda>() {
        @Override
        public Bevanda createFromParcel(Parcel in) {
            return new Bevanda(in);
        }

        @Override
        public Bevanda[] newArray(int size) {
            return new Bevanda[size];
        }
    };

    @Override
    public String toString() {
        return "Bevanda{" +
                "nome='" + nome + '\'' +
                ", urlPhoto='" + urlPhoto + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", categoria=" + categoria +
                '}';
    }
}
