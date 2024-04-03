package com.example.melogiri.adapter;

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
import com.example.melogiri.model.Bevanda;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CarrelloAdapter extends RecyclerView.Adapter<CarrelloAdapter.ViewHolder> {

    private List<Bevanda> prodottiCarrello;

    public CarrelloAdapter(List<Bevanda> prodottiCarrello) {
        this.prodottiCarrello = prodottiCarrello;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_bevanda_carrello, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Bevanda prodotto = prodottiCarrello.get(position);
        holder.productNameTextView.setText(prodotto.getNome());
        holder.productDescriptionTextView.setText(prodotto.getDescrizione());
        holder.livelloAlcolicoRatingBar.setRating((float) prodotto.getLivelloAlcolico());
        holder.prezzoTextView.setText(String.valueOf(prodotto.getPrezzo()) + " euro");
        holder.categoriaView.setText((prodotto.getCategoria().getCategoria()));

        Picasso.get()
                .load(R.drawable.carddrink)
                .resize(600,600)
                .centerCrop()
                .into(holder.productImage);
    }

    @Override
    public int getItemCount() {
        return prodottiCarrello.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView productNameTextView;
        TextView productDescriptionTextView;
        TextView categoriaView;
        TextView prezzoTextView;
        RatingBar livelloAlcolicoRatingBar;
        ImageView productImage;
        Button removeFromCartButton;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(R.id.nomeBevanda);
            productDescriptionTextView = itemView.findViewById(R.id.descrizione);
            categoriaView=itemView.findViewById(R.id.categoria);
            prezzoTextView=itemView.findViewById(R.id.prezzo);
            livelloAlcolicoRatingBar=itemView.findViewById(R.id.ratingBar);
            productImage = itemView.findViewById(R.id.imageView2);
            //removeFromCartButton = itemView.findViewById(R.id.removeCarrello);
        }
    }

}
