package com.example.basicclothingproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ventanaRegister extends AppCompatActivity {

    EditText editTextEmail;
    EditText editTextPass;
    EditText editTextConfPass;
    Button btnRegister;
    TextView textViewRespuesta;


    private String email;
    private String pass;
    private String confPass;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_register);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPass = findViewById(R.id.editTextPassword);
        editTextConfPass = findViewById(R.id.editTextConfPassword);
        btnRegister = findViewById(R.id.btnRegister);
        textViewRespuesta = findViewById(R.id.textViewResp);
    }

}