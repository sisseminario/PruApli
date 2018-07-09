package com.example.elena.eden;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class Venta extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        BottomNavigationView navigation = findViewById(R.id.main_nav_v);
        navigation.setOnNavigationItemSelectedListener(this);
        //loadFragment(new Venta_listaFragment());

    }
    private boolean loadFragment(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction().replace(R.id.main_ve,fragment).commit();


            return true;
        }
        return false;

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()){
            case R.id.busqueve_id:
                fragment =new Venta_buscaFragment();
                break;
            case R.id.mmapa_id:
                fragment =new Blank();
                break;
        }

        return loadFragment(fragment);
    }



}
