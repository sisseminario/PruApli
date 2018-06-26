package com.example.elena.eden;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Form_propiFragment extends Fragment {
    Spinner sp_estado;
    Spinner sp_ciudades;
    Spinner sp_tipopropi;
    String[] estados;
    String[] ciudades;
    String[] tipopropiedad;
    private boolean isFirstTime = true;

     public Form_propiFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registro_propiedad, container, false);
        sp_estado = (Spinner) view.findViewById(R.id.menu_estado);
        estados = getResources().getStringArray(R.array.lista_estado);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,estados);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_estado.setAdapter(adapter);
        sp_estado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (isFirstTime){
                    isFirstTime = false;
                }
                else {
                    Toast.makeText(getContext(),estados[position], Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_ciudades = (Spinner) view.findViewById(R.id.lista_ciu);
        ciudades = getResources().getStringArray(R.array.lista_ciudades);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,ciudades);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_ciudades.setAdapter(adapter1);
        sp_ciudades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (isFirstTime){
                    isFirstTime = false;
                }
                else {
                    Toast.makeText(getContext(),ciudades[position], Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_tipopropi = (Spinner) view.findViewById(R.id.lista_tipovivienda);
        tipopropiedad = getResources().getStringArray(R.array.lista_tipovivenda);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,tipopropiedad);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_tipopropi.setAdapter(adapter2);
        sp_tipopropi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (isFirstTime){
                    isFirstTime = false;
                }
                else {
                    Toast.makeText(getContext(),tipopropiedad[position], Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }

}
