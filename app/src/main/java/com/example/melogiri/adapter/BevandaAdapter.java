package com.example.melogiri.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.melogiri.R;
import com.example.melogiri.controller.ControllerCarrello;
import com.example.melogiri.model.Bevanda;
import com.example.melogiri.recycleView.RecycleViewInterface;
import com.squareup.picasso.Picasso;
import java.util.List;
import java.util.Locale;

public class BevandaAdapter extends RecyclerView.Adapter<BevandaAdapter.ViewHolder> {

    private RecycleViewInterface recycleViewInterface;
    private List<Bevanda> productList;
    private ControllerCarrello controllerCarrello;
    private boolean isCartView;

    public BevandaAdapter(List<Bevanda> productList, ControllerCarrello controllerCarrello, RecycleViewInterface recycleViewInterface, boolean isCartView) {
        this.productList = productList;
        this.controllerCarrello = controllerCarrello != null ? controllerCarrello : ControllerCarrello.getInstance();
        this.recycleViewInterface = recycleViewInterface;
        this.isCartView = isCartView;
    }

    public void setBevande(List<Bevanda> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_bevanda, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Bevanda bevanda = productList.get(position);
        holder.productNameTextView.setText(bevanda.getNome());
        holder.productDescriptionTextView.setText(bevanda.getDescrizione());
        holder.livelloAlcolicoRatingBar.setRating((float) bevanda.getLivelloAlcolico());
        holder.prezzoTextView.setText(String.format(Locale.getDefault(), "%.2f euro", (double) bevanda.getPrezzo()));
        holder.categoriaView.setText(bevanda.getCategoria().getCategoria());

        String category = bevanda.getCategoria().getCategoria().toLowerCase();
        int imageResId;
        switch (category) {
            case "vini":
                imageResId = R.drawable.wine;
                break;
            case "alcolici":
                imageResId = R.drawable.drinkalcolici;
                break;
            case "analcolici":
                imageResId = R.drawable.drinkanalcolici;
                break;
            case "softdrink":
                imageResId = R.drawable.softdrink;
                break;
            default:
                imageResId = R.drawable.carddrink; // Un'immagine predefinita se la categoria non è riconosciuta
                break;
        }

        Picasso.get()
                .load(imageResId)
                .resize(600, 600)
                .centerCrop()
                .into(holder.productImage);

        if (isCartView) {
            holder.addToCartButton.setVisibility(View.GONE);
            holder.removeFromCartButton.setVisibility(View.VISIBLE);
        } else {
            holder.addToCartButton.setVisibility(View.VISIBLE);
            //holder.removeFromCartButton.setVisibility(View.GONE);
        }

        holder.addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controllerCarrello.aggiungiProdotto(bevanda);
                notifyDataSetChanged();
                Toast.makeText(v.getContext(), bevanda.getNome() + " aggiunto al carrello", Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (recycleViewInterface != null) {
                    int pos = holder.getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        recycleViewInterface.onClickItem(pos);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoriaView;
        TextView prezzoTextView;
        RatingBar livelloAlcolicoRatingBar;
        TextView productNameTextView;
        TextView productDescriptionTextView;
        ImageView productImage;
        Button addToCartButton;
        Button removeFromCartButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(R.id.nomeBevanda);
            productDescriptionTextView = itemView.findViewById(R.id.descrizione);
            productImage = itemView.findViewById(R.id.imageView2);
            addToCartButton = itemView.findViewById(R.id.buttonAggiungiAlCarrello);
            categoriaView = itemView.findViewById(R.id.categoria);
            prezzoTextView = itemView.findViewById(R.id.prezzo);
            livelloAlcolicoRatingBar = itemView.findViewById(R.id.ratingBar);
            removeFromCartButton = itemView.findViewById(R.id.buttonRimuoviDalCarrello);
        }
    }
}
