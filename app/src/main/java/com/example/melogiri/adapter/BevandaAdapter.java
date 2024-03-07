package com.example.melogiri.adapter;

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
import com.example.melogiri.recycleView.RecycleViewInterface;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BevandaAdapter extends RecyclerView.Adapter<BevandaAdapter.ViewHolder> {

    private RecycleViewInterface recycleViewInterface;
    private List<Bevanda> productList;
    private ControllerCarrello controllerCarrello;

    public BevandaAdapter(List<Bevanda> productList, ControllerCarrello controllerCarrello, RecycleViewInterface recycleViewInterface) {
        this.productList = productList;
        this.controllerCarrello = controllerCarrello;
        this.recycleViewInterface = recycleViewInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_bevanda, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Bevanda bevanda = productList.get(position);
        holder.productNameTextView.setText(bevanda.getNome());
        holder.productDescriptionTextView.setText(bevanda.getDescrizione());

        Picasso.get()
                .load(bevanda.getUrlPhoto())
                .resize(50, 50)
                .centerCrop()
                .into(holder.productImage);

        holder.addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controllerCarrello.aggiungiProdotto(bevanda);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView productNameTextView;
        TextView productDescriptionTextView;
        ImageView productImage;
        Button addToCartButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(R.id.nomeBevanda);
            productDescriptionTextView = itemView.findViewById(R.id.descrizione);
            productImage = itemView.findViewById(R.id.imageView2);
            addToCartButton = itemView.findViewById(R.id.button3); // Assumi che l'ID del pulsante sia "buttonAggiungiAlCarrello"

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
