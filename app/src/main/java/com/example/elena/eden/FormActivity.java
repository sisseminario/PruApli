package com.example.elena.eden;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.elena.eden.DATA.DataApp;
import com.example.elena.eden.DATA.UserData;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class FormActivity extends AppCompatActivity implements View.OnClickListener{
    Spinner sp_ciudades;
    String[] ciudades;
    private boolean isFirstTime = true;

    Button guardar;
    Intent intent;
    EditText departamento,nombre,lat,lng;
    ArrayList<LatLng> points;
    Context root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        root = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        sp_ciudades = (Spinner) findViewById(R.id.lista_ciu);
        ciudades = getResources().getStringArray(R.array.lista_ciudades);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, ciudades);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_ciudades.setAdapter(adapter1);

        intent = getIntent();
        points = new ArrayList<>();
        points = intent.getExtras().getParcelableArrayList("points");


        loadComponents();

    }

    private void loadComponents() {

        guardar = (Button)this.findViewById(R.id.guardar);
        guardar.setOnClickListener(this);
        sp_ciudades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (isFirstTime) {
                    isFirstTime = false;
                } else {
                    Toast.makeText(getBaseContext(), ciudades[position], Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //      departamento = this.findViewById(R.id.departamento);
        nombre = this.findViewById(R.id.nombre);

    }
    /*Metodo para obtener el centro de un poligono*/
    private LatLng getPolygonCenterPoint(ArrayList<LatLng> polygonPointsList){
        LatLng centerLatLng = null;
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for(int i = 0 ; i < polygonPointsList.size() ; i++)
        {
            builder.include(polygonPointsList.get(i));
        }
        LatLngBounds bounds = builder.build();
        centerLatLng =  bounds.getCenter();

        return centerLatLng;
    }
    @Override
    public void onClick(View v) {
        intent = getIntent();
        if (intent.getExtras() == null){
            Toast.makeText(this, "No se recibio las coordenadas", Toast.LENGTH_SHORT).show();
            this.finish();
            return;
        }

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        Double positions[] = getStringArrayFromLatLngArray(points);
        if (sp_ciudades.getSelectedItem().toString().length() != 0 && nombre.getText().length() != 0){
//            params.put("departamento", departamento.getText());
            params.put("departamento", sp_ciudades.getSelectedItem().toString());
            params.put("nombre", nombre.getText());
        }else{
            Toast.makeText(this, "Debe completar los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        params.put("zoom", 15);
        params.put("lat", getPolygonCenterPoint(points).latitude);
        params.put("lng", getPolygonCenterPoint(points).longitude);

        params.put("coordenadas", positions);
        /*if (positions.length < 1 ){
            Toast.makeText(this, getPolygonCenterPoint(points).toString(), Toast.LENGTH_LONG).show();
            //Log.i("latlng", "onClick: "+ getPolygonCenterPoint(points).toString());
            finish();
            return;
        }*/

        client.post(DataApp.REST_VECINDARIO_POST, params, new JsonHttpResponseHandler(){


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    String msn = response.getString("msn");
                    String doc = response.getString("doc");

                    if (msn != null) {
                        UserData.IDVECINDARIO = doc;

                        Toast.makeText(FormActivity.this, msn, Toast.LENGTH_SHORT).show();
                        //Intent intent = new Intent(root,VerVecindarioActivity.class);
                        // intent.putExtra("idVecindario",doc);
                        // startActivity(intent);
                        Intent intent = new Intent(root, VerVecindarioActivity.class);
                        root.startActivity(intent);

                    } else {
                        Toast.makeText(FormActivity.this, "Error al insertar el vecindario", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(FormActivity.this, "Error : " +throwable.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onRetry(int retryNo) {
                super.onRetry(retryNo);
                Toast.makeText(FormActivity.this, "Intento de reenvio nro : " + retryNo, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Double[] getStringArrayFromLatLngArray(ArrayList<LatLng> latLngArrayList) {
        int size = latLngArrayList.size();
        Double latLngs[] = new Double[size*2];
        latLngs[0] = latLngArrayList.get(0).latitude;
        latLngs[1] = latLngArrayList.get(0).longitude;

        for (int i = 2; i<size*2; i+=2){

            Double lat = latLngArrayList.get(i/2).latitude;
            Double lng = latLngArrayList.get(i/2).longitude;

            latLngs[i] = lat;
            latLngs[i+1] = lng;

        }
        return latLngs;
    }
}
