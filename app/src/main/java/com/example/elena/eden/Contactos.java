package com.example.elena.eden;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Contactos extends AppCompatActivity  {
    EditText nom,corre,pass;
    Button resgis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        updateUI();
    }
    private void updateUI(){
        nom =(EditText) findViewById(R.id.id_nom);
        corre=(EditText) findViewById(R.id.id_corre);
        pass=(EditText) findViewById(R.id.id_pass);
        resgis=(Button) findViewById(R.id.id_regis);

    }
 }