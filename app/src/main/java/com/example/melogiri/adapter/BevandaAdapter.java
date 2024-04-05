package com.example.melogiri.adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.melogiri.R;
import com.example.melogiri.controller.ControllerCarrello;
import com.example.melogiri.model.Bevanda;
import com.example.melogiri.recycleView.RecycleViewInterface;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Locale;

public class BevandaAdapter extends RecyclerView.Adapter<BevandaAdapter.ViewHolder> {

    private RecycleViewInterface recycleViewInterface;
    private List<Bevanda> productList;
    private ControllerCarrello controllerCarrello;
    private boolean isCartView; // Flag to indicate if the adapter is being used in cart view

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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Bevanda bevanda = productList.get(position);
        holder.productNameTextView.setText(bevanda.getNome());
        holder.productDescriptionTextView.setText(bevanda.getDescrizione());
        holder.livelloAlcolicoRatingBar.setRating((float) bevanda.getLivelloAlcolico());
        holder.prezzoTextView.setText(String.valueOf(bevanda.getPrezzo()) + " euro");
        holder.categoriaView.setText((bevanda.getCategoria().getCategoria()));

        Picasso.get()
                .load(R.drawable.carddrink)
                .resize(600,600)
                .centerCrop()
                .into(holder.productImage);

        // Set visibility of buttons based on view type
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
            }
        });

        holder.buttonIncrement.setOnClickListener(v -> {
            if(bevanda.getQuantita() > 1) {
                Log.d("Increment", "Incrementing quantity");
                bevanda.setQuantita(bevanda.getQuantita() + 1);
                holder.quantityTextView.setText(String.valueOf(bevanda.getQuantita()));
            }
        });

        holder.buttonDecrement.setOnClickListener(v -> {
            if(bevanda.getQuantita() > 1){
                Log.d("Increment", "Decrementing quantity");
                bevanda.setQuantita((bevanda.getQuantita() - 1));
                holder.quantityTextView.setText(String.valueOf(bevanda.getQuantita()));
            }
        });

        /*
        holder.removeFromCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controllerCarrello.rimuoviProdotto(bevanda);
                notifyDataSetChanged();
            }
        });

         */
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView quantityTextView;
        Button buttonIncrement;
        Button buttonDecrement;
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
            categoriaView=itemView.findViewById(R.id.categoria);
            prezzoTextView=itemView.findViewById(R.id.prezzo);
            livelloAlcolicoRatingBar=itemView.findViewById(R.id.ratingBar);
            buttonIncrement=itemView.findViewById(R.id.buttonIncrement);
            buttonDecrement=itemView.findViewById(R.id.buttonDecrement);
            quantityTextView=itemView.findViewById(R.id.quantity);
            //removeFromCartButton = itemView.findViewById(R.id.removeLivello);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recycleViewInterface != null) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            recycleViewInterface.onClickItem(pos);
                        }
                    }
                }
            });
        }
    }
}
