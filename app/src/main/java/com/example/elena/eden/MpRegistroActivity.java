package com.example.elena.eden;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MpRegistroActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    private Boolean isInadd = false;
    private TextView txt_view;
    private ArrayList<Marker> list_markers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapas);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        txt_view = (TextView)this.findViewById(R.id.txt);
        list_markers = new ArrayList<Marker>();

        FloatingActionButton removeBtn = (FloatingActionButton) findViewById(R.id.remove);
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(list_markers.size() > 0){
                    Marker aux = list_markers.get(list_markers.size()-1);
                    aux.remove();
                    list_markers.remove(list_markers.size()-1);
                }

            }
        });

        FloatingActionButton addBtn= (FloatingActionButton) findViewById(R.id.add );
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isInadd == true){
                    isInadd = false;
                }else {
                    isInadd = true;
                }
                upDateMsnText();
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

            }
        });
    }



    private void upDateMsnText() {
        if (isInadd == true){
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
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(potosi, 11));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(potosi, 14));
        mMap.setOnMapClickListener(this);

    }

    @Override
    public void onMapClick(LatLng latLng) {
        if(isInadd) {
            Marker m = mMap.addMarker(new MarkerOptions().position(latLng).title("No title"));
            list_markers.add(m);
        }

    }

}

