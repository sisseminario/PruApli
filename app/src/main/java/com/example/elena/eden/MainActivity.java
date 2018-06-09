package com.example.elena.eden;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    RelativeLayout ho,ve,alq,ant,ma,con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ho = findViewById(R.id.hoho);
        ve = findViewById(R.id.ven);
        alq = findViewById(R.id.alqi);
        ant = findViewById(R.id.anti);
        ma = findViewById(R.id.map);
        con= findViewById(R.id.cont);
        ho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                Intent intent =new Intent(MainActivity.this,Home.class);
                intent.addFlags(intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        ho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                Intent intent =new Intent(MainActivity.this,Home.class);
                intent.addFlags(intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        ve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                Intent intent =new Intent(MainActivity.this,Venta.class);
                intent.addFlags(intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);

            }
        });
        alq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                Intent intent =new Intent(MainActivity.this,Alquiler.class);
                intent.addFlags(intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        ant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                Intent intent =new Intent(MainActivity.this,Anticretico.class);
                intent.addFlags(intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                Intent intent =new Intent(MainActivity.this,Contactos.class);
                intent.addFlags(intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
    }

}
