package com.example.elena.eden;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements View.OnClickListener, OnMapReadyCallback ,GoogleMap.OnMapClickListener{

    private GoogleMap mMap;
    Button boton;
    Button boton2;
    Button boton3;
    ArrayList<LatLng> points;
    Polygon miPoligono ;
    ArrayList<Integer> tags;
    Marker marker;
    Context root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root = this;
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        boton = this.findViewById(R.id.button);
        boton2 = this.findViewById(R.id.button2);
        boton3 = this.findViewById(R.id.button3);
        boton.setOnClickListener(this);
        boton2.setOnClickListener(this);
        boton3.setOnClickListener(this);


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng potosi = new LatLng(-19.5722805, -65.7550063);
        mMap.addMarker(new MarkerOptions().position(potosi).title("Marker in Potosi"));
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(potosi, 8));
        mMap.setOnMapClickListener(this);
        UiSettings uiSetting = mMap.getUiSettings();

        //mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        uiSetting.setMyLocationButtonEnabled(true);
        uiSetting.setZoomControlsEnabled(true);
        uiSetting.setMyLocationButtonEnabled(true);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        // Add a marker in Sydney and move the camera
       // LatLng sydney = new LatLng(-34, 151);

        //mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        // Add a marker in Sydney and move the camera


        /*CameraPosition cameraPosition = CameraPosition.builder()
                .target(potosi)
                .zoom(15)
                .build();*/

       // mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        LatLng latLng = new LatLng(-19.5800938, -65.77409634);
        MarkerOptions markerOptions =
                new MarkerOptions()
                        .position(latLng)
                        .title("Mi casa")
                        .snippet("Em Si : Aqui vivo yo")
                        .draggable(true)
                        .flat(true)
                        .anchor(0.2f,1)
                        //.alpha(0.7f)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_flag_black_24dp));

        Marker marker1 = mMap.addMarker(markerOptions);
        MarkerOptions markerOptions1 =
                new MarkerOptions()
                        .position(latLng)
                        .title("Mi casa")
                        .snippet("Em Si : Aqui vivo yo")
                        .draggable(true)
                        .flat(true)
                        .anchor(0.5f,1)
                        .alpha(0.7f)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

        Marker marker2 = mMap.addMarker(markerOptions1);


        PolygonOptions rectOptions = new PolygonOptions()
                .add(new LatLng(-19.57796211, -65.773425321),
                        new LatLng(-19.57896211, -65.773425321),
                        new LatLng(-19.57996211, -65.77425321),
                        new LatLng(-19.57896211, -65.77425321),
                        new LatLng(-19.57796211, -65.77525321),
                        new LatLng(-19.57696211, -65.77525321))
                .strokeColor(Color.BLUE)
                .fillColor(Color.argb(50,50,50,50));


        Polygon polygon = mMap.addPolygon(rectOptions);

        //seton


    }

    @Override
    public void onMapClick(LatLng latLng) {


        //Toast.makeText(this, latLng.toString(), Toast.LENGTH_SHORT).show();
        /*MarkerOptions markerOptions =
                new MarkerOptions()
                        .position(latLng)
                        .title(latLng.toString())

                        .draggable(true)
                        .flat(true)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

        marker = mMap.addMarker(markerOptions);*/


        if (miPoligono == null){
            points = new ArrayList<>();
            PolygonOptions options =
                    new PolygonOptions().add(latLng)
                            .strokeColor(Color.RED)
                            .fillColor(Color.argb(50,50,50,50));
            miPoligono = mMap.addPolygon(options);
            points.add(latLng);
        }else {
            points.add(latLng);
            LatLng center = new LatLng(getPolygonCenterPoint(points).latitude,getPolygonCenterPoint(points).longitude);



            miPoligono.setPoints(points);
            if (marker != null) {
                marker.remove();
            }

            //marker = mMap.addMarker(new MarkerOptions().position(center).title("Polygon center"));
            marker = mMap.addMarker(new MarkerOptions().position(getPolygonCenterPoint((ArrayList) miPoligono.getPoints())).title("Polygon "));
            //mMap.animateCamera(CameraUpdateFactory.newLatLng(getPolygonCenterPoint(points)));
        }
    }

    @Override
    public void onClick(View v) {
        final int[] id = {v.getId()};
        switch(id[0]){
            //activar el trazo de poligono
            case R.id.button :  mMap.setOnMapClickListener(this);

                tags = new ArrayList<>();
                break;
            //limpiar tod el mapa
            case R.id.button2 : mMap.clear();if(tags != null){  tags.clear();miPoligono=null;}
                break;
            //enviar los puntos del mapa a otra Actividad
            case R.id.button3 :
                if (miPoligono != null && miPoligono.getPoints().size() >2) {
                    Toast.makeText(this,miPoligono.getPoints().toString(),Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(root, FormActivity.class);

                    intent.putParcelableArrayListExtra("points", points);
                    //intent.putExtra("doc",doc);
                    startActivity(intent);
                }else {
                    Toast.makeText(root, "El vecindario debe tener por lo menos 3 puntos de referencia", Toast.LENGTH_LONG).show();
                }


                break;
        }

        //Toast.makeText(this, "mmm", Toast.LENGTH_SHORT).show();
    }
    private LatLng getPolygonCenterPoint(ArrayList<LatLng> polygonPointsList){
        LatLng centerLatLng;
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for(int i = 0 ; i < polygonPointsList.size() ; i++)
        {
            builder.include(polygonPointsList.get(i));
        }
        LatLngBounds bounds = builder.build();
        centerLatLng =  bounds.getCenter();

        return centerLatLng;
    }
}
