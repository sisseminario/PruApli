package com.example.elena.eden;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.elena.eden.DATA.DataApp;
import com.example.elena.eden.DATA.UserData;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpResponse;

public class Home_mapa extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {
    private GoogleMap mMap;
    private TextView txt_view;
    private Boolean isInadd = false;
    private Marker list_markers;
    private double sendlat;
    private double sendlng;
    private Context root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        root = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_mapa);

        txt_view = (TextView)this.findViewById(R.id.txt);
        FloatingActionButton storeBtn = (FloatingActionButton)this.findViewById(R.id.store);
        storeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestParams params = new RequestParams();
                LatLng coor = list_markers.getPosition();
                sendlat = coor.latitude;
                sendlng = coor.longitude;
                params.put("lat", sendlat);
                params.put("lng", sendlng);
                AsyncHttpClient client = new AsyncHttpClient();
                if (UserData.ID != null) {
                    client.patch(DataApp.REST_USER_POST + "/" + UserData.ID, params, new JsonHttpResponseHandler() {
                        @Override
                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Intent volver_inicio = new Intent(Home_mapa.this, Home.class);
                            root.startActivity(volver_inicio);
                            Toast.makeText(root, "latitud y longitud registrados con exito", Toast.LENGTH_LONG).show();
                        }

                    });

                }
            }
        });
        // list_markers = new ArrayList<Marker>();
        FloatingActionButton removeBtn = (FloatingActionButton) findViewById(R.id.remove);
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.clear();
            }
        });

        FloatingActionButton addBtn = (FloatingActionButton) findViewById(R.id.add);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isInadd == true) {
                    isInadd = false;
                }else {
                    isInadd = true;
                }
                upDateMsnText();
            }
        });


        SupportMapFragment mapFragment = (SupportMapFragment) this.getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    public void upDateMsnText () {
        if (isInadd == true) {
            txt_view.setText("Add Mark");
        }else {
            txt_view.setText("");
        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng potosi = new LatLng(-19.578297, -65.758633);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(potosi, 14));
        mMap.setOnMapClickListener(this);
    }
    public void setMark(LatLng latLng) {
        Marker m = mMap.addMarker(new MarkerOptions().position(latLng).title("No title"));

    }
    @Override
    public void onMapClick(LatLng latLng) {

        if (isInadd) {
            //Marker m = mMap.addMarker(new MarkerOptions().position(latLng).title("No title"));

            //list_markers.add(m);
            list_markers = mMap.addMarker(new MarkerOptions().position(latLng).title("No title"));
        }
    }
}

