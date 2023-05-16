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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AdaptadorCart extends RecyclerView.Adapter<AdaptadorCart.ViewHolder>{

    private Context context;
    private List<Products> productsList;
    public AdaptadorCart(Context context, List<Products> productsList) {
        this.context = context;
        this.productsList = productsList;
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

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewReferencia, textViewNombre, textViewTalla, textViewPrecio;
        private final ImageView imageView;
        private final Button btnRemove;
        public ViewHolder(View view) {
            super(view);
            this.textViewNombre = (TextView) view.findViewById(R.id.textViewNombre);
            this.textViewReferencia = (TextView) view.findViewById(R.id.textViewReferencia);
            this.textViewPrecio = (TextView) view.findViewById(R.id.textViewPrecio);
            this.textViewTalla = (TextView) view.findViewById(R.id.textViewTalla);
            this.imageView = (ImageView) view.findViewById(R.id.foto);
            this.btnRemove = (Button) view.findViewById(R.id.btnRemove);
        }
    }

    public void eliminarProducto(int position){
        productsList.remove(position);
        notifyItemRemoved(position);
    }
}
