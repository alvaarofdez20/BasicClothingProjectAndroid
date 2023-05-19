package com.example.basicclothingproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {
        Products products = productsList.get(position);

        Glide.with(context).load(products.getImage()).into(viewHolder.imageView);

        viewHolder.textViewNombre.setText(products.getNombre());
        viewHolder.textViewReferencia.setText("Referencia: " + products.getReferencia());
        viewHolder.textViewTalla.setText("Talla: " + products.getTalla());
        viewHolder.textViewPrecio.setText("Precio: " + String.valueOf(products.getPrecio()));

        viewHolder.btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String referencia = productsList.get(position).getReferencia();

                agregarProducto(referencia);
            }
        });
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
        private final TextView textViewReferencia, textViewNombre, textViewTalla, textViewPrecio;
        private final ImageView imageView;
        private final Button btnCart;

        public ViewHolder(View view) {
            super(view);
            this.textViewNombre = (TextView) view.findViewById(R.id.textViewNombre);
            this.textViewReferencia = (TextView) view.findViewById(R.id.textViewReferencia);
            this.textViewPrecio = (TextView) view.findViewById(R.id.textViewPrecio);
            this.textViewTalla = (TextView) view.findViewById(R.id.textViewTalla);
            this.imageView = (ImageView) view.findViewById(R.id.foto);
            this.btnCart = (Button) view.findViewById(R.id.btnCart);
        }
    }

    private void agregarProducto(String referencia){
        String URL = "http://10.0.0.20/basic_clothing/insertCart.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "PRODUCTO AÑADIDO AL CARRITO", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "ERROR AL ELIMINAR", Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("referencia", referencia);
                parametros.put("dni", LoginActivity.dni);
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
