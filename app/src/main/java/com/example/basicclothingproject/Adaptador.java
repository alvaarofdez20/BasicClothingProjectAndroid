package com.example.basicclothingproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;


public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {

    private Context context;
    private List<Products> productsList;

    public Adaptador(Context context, List<Products> productsList){
        this.context = context;
        this.productsList = productsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.products_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position){
        Products products = productsList.get(position);

        //Glide.with(context).load(products.getImage()).into(viewHolder.imageView);

        viewHolder.textViewNombre.setText(products.getNombre());
        viewHolder.textViewReferencia.setText(products.getReferencia());
        viewHolder.textViewTalla.setText(products.getTalla());
        viewHolder.textViewPrecio.setText(String.valueOf(products.getPrecio()));
    }

    @Override
    public int getItemCount(){
        return productsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewReferencia, textViewNombre, textViewTalla, textViewPrecio;
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            textViewNombre = (TextView) view.findViewById(R.id.textViewNombre);
            textViewReferencia = (TextView) view.findViewById(R.id.textViewReferencia);
            textViewPrecio = (TextView) view.findViewById(R.id.textViewPrecio);
            textViewTalla = (TextView) view.findViewById(R.id.textViewTalla);
            //imageView = (ImageView) view.findViewById(R.id.foto);
        }
    }
}
