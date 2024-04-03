package com.example.melogiri.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.melogiri.R;
import com.example.melogiri.model.Bevanda;

import java.util.List;

public class CarrelloAdapter extends RecyclerView.Adapter<CarrelloAdapter.ViewHolder> {

    private List<Bevanda> prodottiCarrello;

    public CarrelloAdapter(List<Bevanda> prodottiCarrello) {
        this.prodottiCarrello = prodottiCarrello;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_bevanda, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Bevanda prodotto = prodottiCarrello.get(position);
        holder.productNameTextView.setText(prodotto.getNome());
        holder.productDescriptionTextView.setText(prodotto.getDescrizione());
    }

    @Override
    public int getItemCount() {
        return prodottiCarrello.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView productNameTextView;
        TextView productDescriptionTextView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(R.id.nomeBevanda);
            productDescriptionTextView = itemView.findViewById(R.id.descrizione);
        }
    }

}
