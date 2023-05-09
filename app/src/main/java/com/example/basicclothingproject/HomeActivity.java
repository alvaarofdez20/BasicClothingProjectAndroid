package com.example.basicclothingproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

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
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerViewRopa, recyclerViewCalzado;
    private List<Products> productsList;
    AdaptadorProducts adaptadorProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // RECYCLERVIEW ROPA
        recyclerViewRopa = (RecyclerView) findViewById(R.id.listaRopa);
        recyclerViewRopa.setHasFixedSize(true);
        recyclerViewRopa.setLayoutManager(new LinearLayoutManager(this));

        productsList = new ArrayList<>();

        mostrarRopa("http://10.0.0.30/basic_clothing/readRopa.php");

        // RECYCLERVIEW CALZADO
        recyclerViewCalzado = (RecyclerView) findViewById(R.id.listaCalzado);
        recyclerViewCalzado.setHasFixedSize(true);
        recyclerViewCalzado.setLayoutManager(new LinearLayoutManager(this));

        productsList = new ArrayList<>();

        mostrarCalzado("http://10.0.0.30/basic_clothing/readCalzado.php");

        BottomNavigationView bottomNavigationView = findViewById(R.id.navBar);
        bottomNavigationView.setSelectedItemId(R.id.button_home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch(item.getItemId()){
                case R.id.button_home:
                    return true;
                case R.id.button_product:
                    startActivity(new Intent(getApplicationContext(), ProductsActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.button_cart:
                    startActivity(new Intent(getApplicationContext(), CartActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
            }
            return false;
        });
    }

    public void mostrarRopa(String URL) {
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
                    adaptadorProducts = new AdaptadorProducts(getApplicationContext(), productsList);
                    recyclerViewRopa.setAdapter(adaptadorProducts);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HomeActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void mostrarCalzado(String URL) {
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
                    adaptadorProducts = new AdaptadorProducts(getApplicationContext(), productsList);
                    recyclerViewCalzado.setAdapter(adaptadorProducts);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HomeActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}