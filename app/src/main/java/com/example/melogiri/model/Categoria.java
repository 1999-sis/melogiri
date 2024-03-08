package com.example.melogiri.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Categoria implements Parcelable {
    public static final Categoria SOFT_DRINK = new Categoria("drinkanalcolici");
    public static final Categoria ALCOLICI = new Categoria("Alcolici");
    public static final Categoria ANALCOLICI = new Categoria("Analcolici");
    public static final Categoria VINI = new Categoria("Vini");

    String categoria;

    public Categoria(String categoria) {
        this.categoria = categoria;
    }

    // Costruttore vuoto
    public Categoria() {
    }

    protected Categoria(Parcel in) {
        categoria = in.readString();
    }

    public static final Creator<Categoria> CREATOR = new Creator<Categoria>() {
        @Override
        public Categoria createFromParcel(Parcel in) {
            return new Categoria(in);
        }

        @Override
        public Categoria[] newArray(int size) {
            return new Categoria[size];
        }
    };

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString(){
        return getCategoria();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(categoria);
    }
}

