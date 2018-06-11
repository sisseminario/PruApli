package com.example.elena.eden;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import static com.example.elena.eden.R.id.main_anti;

public class Anticretico extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anticretico);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        BottomNavigationView navigation = findViewById(R.id.main_navanti);
        navigation.setOnNavigationItemSelectedListener(this);
        loadFragment(new Alquiler_listaFragment());

    }
    private boolean loadFragment(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction().replace(R.id.main_anti,fragment).commit();


            return true;
        }
        return false;

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.busqueve_id:
                fragment =new Anticretico_buscaFragment();
                break;
            case R.id.lista_id:
                fragment =new Anticretico_listaFragment();
                break;


        }

        return loadFragment(fragment);
    }
}

