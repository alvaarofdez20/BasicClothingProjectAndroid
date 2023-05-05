package com.example.basicclothingproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AdaptadorCart extends RecyclerView.Adapter<AdaptadorCart.ViewHolder> {

    private Context context;
    private List<Products> productsList;
    private List<Products> productsListOriginal;

    public AdaptadorCart(Context context, List<Products> productsList) {
        this.context = context;
        this.productsList = productsList;
        productsListOriginal = new ArrayList<>();
        productsListOriginal.addAll(productsList);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
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
            btnCart = (Button) view.findViewById(R.id.btnRemove);

            btnCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, "URL", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //Toast.makeText(AdaptadorCart.this, "PRODUCTO AÑADIDO AL CARRITO", Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //Toast.makeText(ProductsActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> parametros = new HashMap<String, String>();
                            parametros.put("referencia", textViewReferencia.getText().toString());
                            return parametros;
                        }
                    };
                    //RequestQueue requestQueue = Volley.newRequestQueue(this);
                    //requestQueue.add(stringRequest);
                }
            });
        }
    }
}
