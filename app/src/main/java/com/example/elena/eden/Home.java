package com.example.elena.eden;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class Home extends AppCompatActivity {


    Button insertar;
    Button eliminar;
    Button modificar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        insertar = (Button) findViewById(R.id.insertar);
        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent insertar = new Intent(Home.this,Registro_casa.class);
                startActivity(insertar);
            }
        });


    }
}
