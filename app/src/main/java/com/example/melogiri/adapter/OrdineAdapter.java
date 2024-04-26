package com.example.melogiri.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.melogiri.R;
import com.example.melogiri.model.Ordine;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class OrdineAdapter extends RecyclerView.Adapter<OrdineAdapter.OrdineViewHolder> {

    private List<Ordine> ordini;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    public OrdineAdapter(List<Ordine> ordini) {
        this.ordini = ordini;
    }

    @NonNull
    @Override
    public OrdineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_ordine, parent, false);
        return new OrdineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdineViewHolder holder, int position) {
        Ordine ordine = ordini.get(position);
        holder.tvDataOrdine.setText(dateFormat.format(ordine.getData()));
        holder.tvQuantitaBevande.setText(String.format(Locale.getDefault(), "%d bevande", ordine.getBevandeAquistate().size()));
        holder.tvPrezzoTotale.setText(String.format(Locale.getDefault(), "€%.2f", ordine.getTotale()));
    }

    @Override
    public int getItemCount() {
        return ordini != null ? ordini.size() : 0;
    }

    static class OrdineViewHolder extends RecyclerView.ViewHolder {
        TextView tvDataOrdine;
        TextView tvQuantitaBevande;
        TextView tvPrezzoTotale;

        OrdineViewHolder(View itemView) {
            super(itemView);
            tvDataOrdine = itemView.findViewById(R.id.tvDataOrdine);
            tvQuantitaBevande = itemView.findViewById(R.id.tvQuantitaBevande);
            tvPrezzoTotale = itemView.findViewById(R.id.tvPrezzoTotale);
        }
    }
}
