package com.example.melogiri.util;

import com.example.melogiri.model.Bevanda;

import java.util.List;

public interface BevandeCallback {
    void onBevandeLoaded(List<Bevanda> productList);
}
