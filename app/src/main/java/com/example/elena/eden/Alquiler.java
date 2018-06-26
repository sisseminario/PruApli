package com.example.elena.eden;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class Alquiler extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alquiler);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        BottomNavigationView navigation = findViewById(R.id.main_nav_al);
        navigation.setOnNavigationItemSelectedListener(this);
        loadFragment(new Venta_listaFragment());
        

    }
    private boolean loadFragment(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction().replace(R.id.main_al,fragment).commit();


            return true;
        }
        return false;

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.busqueve_id:
                fragment =new Alquiler_buscaFragment();
                break;
            case R.id.lista_id:
                fragment =new Alquiler_listaFragment();
                break;
            case R.id.mmapa_id:
                fragment =new Alquiler_mapaFragment();
                break;
        }

        return loadFragment(fragment);
    }

}
