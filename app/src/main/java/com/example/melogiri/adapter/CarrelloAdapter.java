package com.example.melogiri.adapter;

import static java.lang.String.format;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.melogiri.R;
import com.example.melogiri.controller.ControllerCarrello;
import com.example.melogiri.model.Bevanda;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CarrelloAdapter extends RecyclerView.Adapter<CarrelloAdapter.ViewHolder> {

    private List<Bevanda> prodottiCarrello;
    private OnCarrelloChangeListener listener;
    private ControllerCarrello controllerCarrello = ControllerCarrello.getInstance();

    public CarrelloAdapter(List<Bevanda> prodottiCarrello, OnCarrelloChangeListener listener) {
        this.prodottiCarrello = new ArrayList<>(prodottiCarrello);
        this.listener = listener;
    }

    public interface OnCarrelloChangeListener {
        void onPrezzoTotaleChanged(double nuovoPrezzoTotale);
        void onProdottoRimosso(Bevanda prodotto);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_bevanda_carrello, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Bevanda prodotto = prodottiCarrello.get(position);
        holder.productNameTextView.setText(prodotto.getNome());
        holder.productDescriptionTextView.setText(prodotto.getDescrizione());
        holder.livelloAlcolicoRatingBar.setRating((float) prodotto.getLivelloAlcolico());
        holder.prezzoTextView.setText(String.format(Locale.ITALY, "%.2f euro", (double) prodotto.getPrezzo()));
        holder.categoriaView.setText(prodotto.getCategoria().getCategoria());
        holder.quantityTextView.setText(String.valueOf(prodotto.getQuantita()));

        Picasso.get()
                .load(R.drawable.carddrink)
                .resize(600, 600)
                .centerCrop()
                .into(holder.productImage);

        holder.buttonIncrement.setOnClickListener(v -> {
            int quantita = prodotto.getQuantita() + 1;
            prodotto.setQuantita(quantita);
            holder.quantityTextView.setText(String.valueOf(quantita));
            notifyItemChanged(position);
            listener.onPrezzoTotaleChanged(calcolaPrezzoTotale());
        });

        holder.buttonDecrement.setOnClickListener(v -> {
            int quantita = prodotto.getQuantita() - 1;
            if (quantita > 0) {
                prodotto.setQuantita(quantita);
                holder.quantityTextView.setText(String.valueOf(quantita));
                notifyItemChanged(position);
                listener.onPrezzoTotaleChanged(calcolaPrezzoTotale());
            }
        });

        holder.removeFromCartButton.setOnClickListener(v -> {
            Bevanda removedProduct = prodottiCarrello.remove(position);
            controllerCarrello.rimuoviProdotto(removedProduct);  // Update the controller
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, prodottiCarrello.size());
            listener.onProdottoRimosso(removedProduct);  // Notify the listener
            listener.onPrezzoTotaleChanged(calcolaPrezzoTotale());  // Update total price
        });
    }

    private double calcolaPrezzoTotale() {
        double totale = 0;
        for (Bevanda prodotto : prodottiCarrello) {
            totale += prodotto.getPrezzo() * prodotto.getQuantita();
        }
        return totale;
    }

    @Override
    public int getItemCount() {
        return prodottiCarrello.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView productNameTextView, productDescriptionTextView, categoriaView, prezzoTextView, quantityTextView;
        RatingBar livelloAlcolicoRatingBar;
        ImageView productImage;
        Button buttonIncrement, buttonDecrement, removeFromCartButton;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(R.id.nomeBevanda);
            productDescriptionTextView = itemView.findViewById(R.id.descrizione);
            categoriaView = itemView.findViewById(R.id.categoria);
            prezzoTextView = itemView.findViewById(R.id.prezzo);
            quantityTextView = itemView.findViewById(R.id.quantity);
            livelloAlcolicoRatingBar = itemView.findViewById(R.id.ratingBar);
            productImage = itemView.findViewById(R.id.imageView2);
            buttonIncrement = itemView.findViewById(R.id.buttonIncrement);
            buttonDecrement = itemView.findViewById(R.id.buttonDecrement);
            removeFromCartButton = itemView.findViewById(R.id.buttonRimuoviDalCarrello);
        }
    }
}
