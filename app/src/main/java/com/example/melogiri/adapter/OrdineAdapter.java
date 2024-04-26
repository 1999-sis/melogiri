package com.example.melogiri.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.melogiri.R;
import com.example.melogiri.controller.ControllerProfilo;
import com.example.melogiri.controller.StoricoOrdineCallBack;
import com.example.melogiri.model.StoricoOrdine;
import com.example.melogiri.recycleView.RecycleViewInterface;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class OrdineAdapter extends RecyclerView.Adapter<OrdineAdapter.OrdineViewHolder> {

    private List<StoricoOrdine> ordini;
    private String date;
    private RecycleViewInterface recycleViewInterface;
    private ControllerProfilo controllerProfilo;
    private boolean isCartView;

    public OrdineAdapter(List<StoricoOrdine> ordini, RecycleViewInterface recycleViewInterface, ControllerProfilo controllerProfilo, boolean isCartView)
    {
        this.ordini = ordini;
        this. recycleViewInterface = recycleViewInterface;
        this.controllerProfilo = controllerProfilo;
        this.isCartView = isCartView;
    }


    @NonNull
    @Override
    public OrdineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_ordine, parent, false);
        return new OrdineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdineViewHolder holder, int position) {
        StoricoOrdine ordine = ordini.get(position);

        holder.tvBevanda.setText(ordine.getNome());
        holder.tvDataOrdine.setText(ordine.getDate());
        holder.tvQuantitaBevande.setText(String.valueOf(ordine.getQuantita()));
        holder.tvPrezzoTotale.setText(String.valueOf(ordine.getTotalePrezzo()));
    }

    @Override
    public int getItemCount()
    {
        return ordini.size();
    }

    static class OrdineViewHolder extends RecyclerView.ViewHolder {
        TextView tvDataOrdine;
        TextView tvQuantitaBevande;
        TextView tvPrezzoTotale;
        TextView tvBevanda;

        OrdineViewHolder(View itemView)
        {
            super(itemView);
            tvBevanda = itemView.findViewById(R.id.valueBevanda);
            tvDataOrdine = itemView.findViewById(R.id.valueData);
            tvQuantitaBevande = itemView.findViewById(R.id.valueQuantita);
            tvPrezzoTotale = itemView.findViewById(R.id.valueTotale);
        }
    }
}
