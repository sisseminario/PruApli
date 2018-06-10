package com.example.elena.eden;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class Venta extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        BottomNavigationView bottomNav = findViewById(R.id.boton_vnave);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contra,new Venta_list());


    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selecfrag = null;
                    switch (item.getItemId()){
                        case R.id.busqueve_id:
                            selecfrag = new Venta_busca();
                            break;
                        case R.id.lista_id:
                            selecfrag = new Venta_list();
                            break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contra,selecfrag).commit();
                    return true;
                }
            };




}
