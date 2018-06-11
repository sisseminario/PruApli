package com.example.elena.eden;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Contactos extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
