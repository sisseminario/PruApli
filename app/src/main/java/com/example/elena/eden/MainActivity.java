package com.example.elena.eden;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    RelativeLayout reg, ofer,con,ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reg= findViewById(R.id.reg);
        ofer = findViewById(R.id.of);
        con= findViewById(R.id.cont);
        ma=findViewById(R.id.maapa);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,Home.class);
                intent.addFlags(intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        ofer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,CasasLista.class);
                intent.addFlags(intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);

            }
        });


        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,Contactos.class);
                intent.addFlags(intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        ma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,MapsActivity.class);
                intent.addFlags(intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        Animation traslacion = AnimationUtils.loadAnimation(this,R.anim.traslacion);
        traslacion.setFillAfter(true);
        traslacion.setRepeatMode(Animation.REVERSE);
        traslacion.setRepeatCount(5);

        Animation rotar = AnimationUtils.loadAnimation(this, R.anim.rotar);
        rotar.setFillAfter(true);
        rotar.setRepeatMode(Animation.REVERSE);
        rotar.setRepeatCount(5);

        this.con.startAnimation(traslacion);
        this.ma.startAnimation(rotar);
        this.ofer.startAnimation(traslacion);
        this.reg.startAnimation(rotar);

    }

}