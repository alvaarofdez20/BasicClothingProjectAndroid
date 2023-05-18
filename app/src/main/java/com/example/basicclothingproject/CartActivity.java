package com.example.basicclothingproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartActivity extends AppCompatActivity implements AdaptadorCart.DeleteItem{

    private RecyclerView recyclerView;
    private List<Products> productsList = new ArrayList<>();
    private AdaptadorCart adaptadorCart;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // RECYCLERVIEW
        recyclerView = (RecyclerView) findViewById(R.id.listaProductosCarrito);

        adaptadorCart = new AdaptadorCart(getApplicationContext(), productsList);
        adaptadorCart.setOnDeleteItem(CartActivity.this);
        recyclerView.setAdapter(adaptadorCart);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productsList = new ArrayList<>();

        mostrarDatos("http://10.0.0.20/basic_clothing/readCart.php");

        BottomNavigationView bottomNavigationView = findViewById(R.id.navBar);
        bottomNavigationView.setSelectedItemId(R.id.button_cart);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch(item.getItemId()){
                case R.id.button_home:
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.button_product:
                    startActivity(new Intent(getApplicationContext(), ProductsActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.button_cart:
                    return true;
            }
            return false;
        });
    }

    public void mostrarDatos(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = (JSONObject) array.get(i);
                        productsList.add(new Products(
                                object.getString("referencia"),
                                object.getString("nombre"),
                                object.getString("tipo"),
                                object.getDouble("precio"),
                                object.getString("talla"),
                                object.getString("img")
                        ));
                    }
                    adaptadorCart = new AdaptadorCart(getApplicationContext(), productsList);
                    recyclerView.setAdapter(adaptadorCart);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CartActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void eliminarDatos(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(CartActivity.this, "PRODUCTO ELIMINADO", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CartActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("referencia", "1341123009_BEI_42");
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(String referencia) {
        eliminarDatos("http://10.0.0.20/basic_clothing/removeCart.php");
    }
}