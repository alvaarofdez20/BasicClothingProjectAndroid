package com.example.basicclothingproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class CartList extends AppCompatActivity {

    private TextView textViewReferencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        textViewReferencia = (TextView) findViewById(R.id.textViewReferencia);
        String txtRef = textViewReferencia.getText().toString();

        System.out.println(txtRef);
    }
}