package com.example.elena.eden;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class Registro_casa extends AppCompatActivity implements View.OnClickListener {

    Spinner sp_estado;
    Spinner sp_ciudades;
    Spinner sp_tipopropi;
    String[] estados;
    String[] ciudades;
    String[] tipopropiedad;
    private boolean isFirstTime = true;

    Button camara;
    Button mapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_casa);

        //Botones
        camara = (Button)findViewById(R.id.boton_cam);
        camara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camara = new Intent(Registro_casa.this, Home_camara.class);
                startActivity(camara);
            }
        });

        mapa = (Button)findViewById(R.id.boton_Mapa);
        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapa = new Intent(Registro_casa.this, Home_mapa.class);
                startActivity(mapa);
            }
        });

        ///

        sp_estado = (Spinner) findViewById(R.id.menu_estado);
        estados = getResources().getStringArray(R.array.lista_estado);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_spinner_item,estados);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_estado.setAdapter(adapter);
        sp_estado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (isFirstTime){
                    isFirstTime = false;
                }
                else {
                    Toast.makeText(getBaseContext(),estados[position], Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_ciudades = (Spinner) findViewById(R.id.lista_ciu);
        ciudades = getResources().getStringArray(R.array.lista_ciudades);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_spinner_item,ciudades);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_ciudades.setAdapter(adapter1);
        sp_ciudades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (isFirstTime){
                    isFirstTime = false;
                }
                else {
                    Toast.makeText(getBaseContext(),ciudades[position], Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_tipopropi = (Spinner) findViewById(R.id.lista_tipovivienda);
        tipopropiedad = getResources().getStringArray(R.array.lista_tipovivenda);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_spinner_item,tipopropiedad);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_tipopropi.setAdapter(adapter2);
        sp_tipopropi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (isFirstTime){
                    isFirstTime = false;
                }
                else {
                    Toast.makeText(getBaseContext(),tipopropiedad[position], Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
