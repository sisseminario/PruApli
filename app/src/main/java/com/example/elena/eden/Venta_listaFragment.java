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
public class Venta_listaFragment extends Fragment {
    private View ROOT;
    private OnLoadDataComplete event;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DataApp.LISTDATA = new ArrayList<ItemMenuStructure>();
       // ROOT = inflater.inflate(R.layout.fragment_venta_lista, container, false);
        loadData();
        return ROOT;
    }

    public void setOnloadCompleteData(OnLoadDataComplete event) {
        this.event = event;
    }

    private void loadData() {
        AsyncHttpClient client = new AsyncHttpClient();
        ///aqui se pone para el servicio

        client.get(DataApp.REST_USER_POST, new JsonHttpResponseHandler() {

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
                        int numero_banios = obj.getInt("numero_banios");
                        int numero_habitaciones = obj.getInt("numero_habitaciones");
                        int nuemro_cocina = obj.getInt("nuemro_cocina");

                        int pisos = obj.getInt("pisos");
                        String elevador = obj.getString("elevador");
                        String piscina = obj.getString("piscina");
                        String garaje = obj.getString("garaje");
                        String amoblado = obj.getString("amoblado");
                        String ubicacion = obj.getString("ubicacion");
                        String direccion = obj.getString("direccion");
                        int precio = obj.getInt("precio");
                        int moneda = obj.getInt("moneda");
                        String tipo_vivenda = obj.getString("tipo_vivenda");
                        String nombre_zona = obj.getString("nombre_zona");
                        String nombre_ciudad = obj.getString("nombre_ciudad");
                        double lat = obj.getDouble("lat");
                        double lng = obj.getDouble("lng");
                        String nombre_dueno = obj.getString("nombre_dueno");
                        String apellido_dueno = obj.getString("apellido_dueno");
                        int telefono_dueno = obj.getInt("telefono_dueno");
                        int celular_dueno = obj.getInt("celular_dueno");
                        String supterrreno = obj.getString("supterrreno");
                        String email_dueno = obj.getString("email_dueno");
                        String id = obj.getString("_id");
                        String url = (String) obj.getJSONArray("gallery").get(0);
                        JSONArray listGallery = obj.getJSONArray("gallery");
                        ArrayList<String> urllist = new ArrayList<String>();
                        for (int j = 0; j < listGallery.length(); j++) {
                            urllist.add(DataApp.HOST + listGallery.getString(j));
                        }

                        DataApp.LISTDATA.add(new ItemMenuStructure(estado, descripcion,"",urllist, "","",
                                "",numero_banios,numero_habitaciones,0,0,"","",
                                "","","","",precio,moneda,"",""
                                ,"",lat,lng,"","",0,0,
                                "",id));
                    }
                    LoadComponents();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void LoadComponents() {

        //ListView list = (ListView) ROOT.findViewById(R.id.super_lista_anti);
        MenuBaseAdapter adapter = new MenuBaseAdapter(this.getActivity(), DataApp.LISTDATA);
       // list.setAdapter(adapter);
        this.event.OnLodCompleteDataResult();
    }
}
