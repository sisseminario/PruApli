package com.example.elena.eden;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.elena.eden.DATA.DataApp;
import com.example.elena.eden.ItemMenu.ItemMenuStructure;
import com.example.elena.eden.ItemMenu.MenuBaseAdapter;
import com.example.elena.eden.Utils.OnLoadDataComplete;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


/**
 * A simple {@link Fragment} subclass.
 */
public class Anticretico_listaFragment extends Fragment {
    private View ROOT;
    private OnLoadDataComplete event;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DataApp.LISTDATA = new ArrayList<ItemMenuStructure>();
        ROOT = inflater.inflate(R.layout.fragment_anticretico_lista, container, false);
        loadData();
        return ROOT;
    }
    public void setOnloadCompleteData (OnLoadDataComplete event) {
        this.event = event;
    }
    private void loadData() {
        AsyncHttpClient client = new AsyncHttpClient();
        ///aqui se pone para el servicio

        client.get("http://192.168.1.109:7777/api/v1.0/propiedad", new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                ////aqui sevicio
                try {
                    JSONArray listData = response.getJSONArray("info");
                    for (int i = 0; i < listData.length(); i++) {
                        JSONObject obj = listData.getJSONObject(i);

                        String vender_alqui_anticre = obj.getString("vender_alqui_anticre");
                        String estado = obj.getString("estado");
                        String descripcion = obj.getString("descripcion");
                        String amurallado = obj.getString("amurallado");
                        String servicios_basicos = obj.getString("servicios_basicos");
                        String otros = obj.getString("otros");
                        Integer numero_banios = obj.getInt("numero_banios");
                        Integer numero_habitaciones = obj.getInt("numero_habitaciones");
                        Integer nuemro_cocina = obj.getInt("nuemro_cocina");

                        Integer pisos = obj.getInt("pisos");
                        String elevador = obj.getString("elevador");
                        String piscina = obj.getString("piscina");
                        String garaje = obj.getString("garaje");
                        String amoblado = obj.getString("amoblado");
                        String ubicacion = obj.getString("ubicacion");
                        String direccion = obj.getString("direccion");
                        Integer precio = obj.getInt("precio");
                        Integer moneda = obj.getInt("moneda");
                        String tipo_vivenda = obj.getString("tipo_vivenda");
                        String nombre_zona = obj.getString("nombre_zona");
                        String nombre_ciudad = obj.getString("nombre_ciudad");
                        double lat = obj.getDouble("lat");
                        double lng = obj.getDouble("lng");
                        String nombre_dueno = obj.getString("nombre_dueno");
                        String apellido_dueno = obj.getString("apellido_dueno");
                        Integer telefono_dueno = obj.getInt("telefono_dueno");
                        Integer celular_dueno = obj.getInt("celular_dueno");
                        String supterrreno = obj.getString("supterrreno");
                        String email_dueno = obj.getString("email_dueno");
                        String id = obj.getString("_id");
                        String url = (String)obj.getJSONArray("gallery").get(0);JSONArray listGallery = obj.getJSONArray("gallery");
                        ArrayList<String> urllist =  new ArrayList<String>();
                        for (int j = 0; j < listGallery.length(); j ++) {
                            urllist.add(DataApp.HOST + listGallery.getString(j));
                        }

                        DataApp.LISTDATA.add(new ItemMenuStructure(vender_alqui_anticre, estado, descripcion, amurallado,
                                servicios_basicos, otros, numero_banios, numero_habitaciones, nuemro_cocina, pisos, elevador, piscina,
                                garaje, amoblado, ubicacion, direccion, precio, precio, moneda, tipo_vivenda, nombre_zona,
                                nombre_ciudad, lat, lng, nombre_dueno, apellido_dueno, telefono_dueno, celular_dueno,
                                supterrreno, email_dueno, id, urllist));
                    }
                    LoadComponents();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void LoadComponents () {

        ListView list = (ListView) ROOT.findViewById(R.id.super_lista_anti);
        MenuBaseAdapter adapter = new MenuBaseAdapter(this.getActivity(), DataApp.LISTDATA);
        list.setAdapter(adapter);
        this.event.OnLodCompleteDataResult();
    }
}