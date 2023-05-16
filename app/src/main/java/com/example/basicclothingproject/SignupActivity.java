package com.example.basicclothingproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class SignupActivity extends AppCompatActivity {

    private EditText signup_dni;
    private EditText signup_nombre;
    private EditText signup_apellidos;
    private EditText signup_email;
    private EditText signup_password;
    private EditText signup_conf;
    private EditText signup_tfno;
    private EditText signup_date;
    private Button signup_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signup_dni = findViewById(R.id.signup_dni);
        signup_nombre = findViewById(R.id.signup_nombre);
        signup_apellidos = findViewById(R.id.signup_apellidos);
        signup_email = findViewById(R.id.signup_email);
        signup_password = findViewById(R.id.signup_password);
        signup_conf = findViewById(R.id.signup_conf);
        signup_tfno = findViewById(R.id.signup_phone);
        signup_button = findViewById(R.id.signup_button);

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registro("http://10.0.0.20/basic_clothing/register.php");
            }
        });
    }

    private void registro(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.isEmpty()){
                    Toast.makeText(SignupActivity.this, "CLIENTE REGISTRADO", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SignupActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("dni", signup_dni.getText().toString());
                parametros.put("nombre", signup_nombre.getText().toString());
                parametros.put("apellidos", signup_apellidos.getText().toString());
                parametros.put("email", signup_email.getText().toString());
                parametros.put("password", signup_password.getText().toString());
                parametros.put("tfno", signup_tfno.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}