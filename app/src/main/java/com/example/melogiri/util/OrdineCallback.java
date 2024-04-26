package com.example.melogiri.util;

import com.example.melogiri.model.Ordine;

public interface OrdineCallback
{
    void onSuccess(Ordine ordine); // Metodo chiamato quando la creazione dell'ordine ha successo
    void onFailure(String errore); // Metodo chiamato quando la creazione dell'ordine fallisce
}
