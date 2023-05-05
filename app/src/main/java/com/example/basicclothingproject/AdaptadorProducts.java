package com.example.basicclothingproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class AdaptadorProducts extends RecyclerView.Adapter<AdaptadorProducts.ViewHolder> {

    private Context context;
    private List<Products> productsList;
    private List<Products> productsListOriginal;

    public AdaptadorProducts(Context context, List<Products> productsList) {
        this.context = context;
        this.productsList = productsList;
        productsListOriginal = new ArrayList<>();
        productsListOriginal.addAll(productsList);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.products_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Products products = productsList.get(position);

        Glide.with(context).load(products.getImage()).into(viewHolder.imageView);

        viewHolder.textViewNombre.setText(products.getNombre());
        viewHolder.textViewReferencia.setText("Referencia: " + products.getReferencia());
        viewHolder.textViewTalla.setText("Talla: " + products.getTalla());
        viewHolder.textViewPrecio.setText("Precio: " + String.valueOf(products.getPrecio()));
    }

    public void filtrar(String nombre) {
        if (nombre.length() == 0) {
            productsList.clear();
            productsList.addAll(productsListOriginal);
        } else {
            List<Products> lista = productsList.stream()
                    .filter(i -> i.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                    .collect(Collectors.toList());
            productsList.clear();
            productsList.addAll(lista);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewReferencia, textViewNombre, textViewTalla, textViewPrecio;
        ImageView imageView;
        Button btnCart;

        public ViewHolder(View view) {
            super(view);
            textViewNombre = (TextView) view.findViewById(R.id.textViewNombre);
            textViewReferencia = (TextView) view.findViewById(R.id.textViewReferencia);
            textViewPrecio = (TextView) view.findViewById(R.id.textViewPrecio);
            textViewTalla = (TextView) view.findViewById(R.id.textViewTalla);
            imageView = (ImageView) view.findViewById(R.id.foto);
        }
    }
}
