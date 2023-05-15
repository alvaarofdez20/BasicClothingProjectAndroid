package com.example.basicclothingproject;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView textViewReferencia, textViewNombre, textViewTalla, textViewPrecio;
    private ImageView imageView;
    private Button btnRemove;
    private itemClickListener listener;

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);

        textViewReferencia = (TextView) itemView.findViewById(R.id.cartReferencia);
        textViewNombre = (TextView) itemView.findViewById(R.id.cartNombre);
        textViewTalla = (TextView) itemView.findViewById(R.id.cartTalla);
        textViewPrecio = (TextView) itemView.findViewById(R.id.cartPrecio);
        imageView = (ImageView) itemView.findViewById(R.id.cartImg);
    }

    public void setOnClickListener(itemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        listener.onClick(view, getAdapterPosition());
    }
}
