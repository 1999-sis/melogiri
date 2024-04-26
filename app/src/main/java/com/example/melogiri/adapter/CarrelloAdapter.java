package com.example.melogiri.adapter;

import static java.lang.String.format;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_bevanda_carello2, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Bevanda prodotto = prodottiCarrello.get(position);
        holder.productNameTextView.setText(prodotto.getNome());
        holder.prezzoTextView.setText(format(Locale.ITALY, "%.2f euro", (double) prodotto.getPrezzo()));
        holder.quantityTextView.setText(format(Locale.ITALY, "%d", prodotto.getQuantita()));

        // Carica l'immagine in base alla categoria
        int imageResId = getImageResourceId(prodotto.getCategoria().getCategoria());
        Picasso.get()
                .load(imageResId)
                .resize(600, 600)
                .centerCrop()
                .into(holder.productImage);

        holder.buttonIncrement.setOnClickListener(v -> {
            int quantita = prodotto.getQuantita() + 1;
            prodotto.setQuantita(quantita);
            holder.quantityTextView.setText(format(Locale.ITALY, "%d", quantita));
            notifyItemChanged(position);
            listener.onPrezzoTotaleChanged(calcolaPrezzoTotale());
        });

        holder.buttonDecrement.setOnClickListener(v -> {
            int quantita = prodotto.getQuantita() - 1;
            if (quantita > 0) {
                prodotto.setQuantita(quantita);
                holder.quantityTextView.setText(format(Locale.ITALY, "%d", quantita));
                notifyItemChanged(position);
                listener.onPrezzoTotaleChanged(calcolaPrezzoTotale());
            }
        });

        holder.removeFromCartButton.setOnClickListener(v -> {
            Bevanda removedProduct = prodottiCarrello.remove(position);
            controllerCarrello.rimuoviProdotto(removedProduct);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, prodottiCarrello.size());
            listener.onProdottoRimosso(removedProduct);
            listener.onPrezzoTotaleChanged(calcolaPrezzoTotale());
        });
    }

    private double calcolaPrezzoTotale() {
        double totale = 0;
        for (Bevanda prodotto : prodottiCarrello) {
            totale += prodotto.getPrezzo() * prodotto.getQuantita();
        }
        return totale;
    }

    private int getImageResourceId(String category) {
        category = category.toLowerCase(Locale.ITALY); // Usare il locale italiano se i nomi delle categorie sono in italiano
        switch (category) {
            case "vini":
                return R.drawable.wine;
            case "alcolici":
                return R.drawable.drinkalcolici;
            case "analcolici":
                return R.drawable.drinkanalcolici;
            case "softdrink":
                return R.drawable.softdrink;
            default:
                return R.drawable.carddrink; // Un'immagine predefinita se la categoria non è riconosciuta
        }
    }

    @Override
    public int getItemCount() {
        return prodottiCarrello.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView productNameTextView, prezzoTextView, quantityTextView;
        ImageView productImage;
        Button buttonIncrement, buttonDecrement, removeFromCartButton;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(R.id.nomeBevanda);
            prezzoTextView = itemView.findViewById(R.id.prezzo);
            quantityTextView = itemView.findViewById(R.id.quantity);
            productImage = itemView.findViewById(R.id.imageView2);
            buttonIncrement = itemView.findViewById(R.id.buttonIncrement);
            buttonDecrement = itemView.findViewById(R.id.buttonDecrement);
            removeFromCartButton = itemView.findViewById(R.id.buttonRimuoviDalCarrello);
        }
    }
}
