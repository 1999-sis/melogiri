package com.example.melogiri.util;

import com.example.melogiri.model.Bevanda;

import java.util.List;
/* Interfaccia che consente di attendere la chiamata dal server */
public interface BevandeCallback {
    void onBevandeLoaded(List<Bevanda> productList);
}
