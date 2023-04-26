package com.example.basicclothingproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ventanaRegister extends AppCompatActivity {

    EditText editTextDNI;
    EditText editTextNonbre;
    EditText editTextApellidos;
    EditText editTextEmail;
    EditText editTextPassword;
    EditText editTextPasswordConf;
    EditText editTextTelefono;
    EditText editTextFecha;
    Button btnRegister;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_register);

        editTextDNI = findViewById(R.id.editTextDNI);
        editTextNonbre = findViewById(R.id.editTextNombre);
        editTextApellidos = findViewById(R.id.editTextApellidos);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextPasswordConf = findViewById(R.id.editTextPasswordConf);
        editTextTelefono = findViewById(R.id.editTextNumber);
        editTextFecha = findViewById(R.id.editTextDate);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarse("http://10.0.0.30/basic_clothing/register.php");
            }
        });
    }

    private void registrarse(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ventanaRegister.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("dni", editTextDNI.getText().toString());
                parametros.put("nombre", editTextNonbre.getText().toString());
                parametros.put("apellidos", editTextApellidos.getText().toString());
                parametros.put("email", editTextEmail.getText().toString());
                parametros.put("password", editTextPassword.getText().toString());
                parametros.put("tfno", editTextTelefono.getText().toString());
                parametros.put("fechaNacimiento", editTextFecha.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}