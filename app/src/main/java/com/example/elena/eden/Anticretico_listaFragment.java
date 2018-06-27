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
        client.get("http://192.168.43.22:7777/api/v1.0/anticretico", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                ////aqui sevicio
                try {
                    JSONArray listData = response.getJSONArray("info");
                    for (int i = 0; i < listData.length(); i++) {
                        JSONObject obj = listData.getJSONObject(i);
                        String street = obj.getString("street");
                        Integer price = obj.getInt("price");
                        double lat = obj.getDouble("lat");
                        double lon = obj.getDouble("lon");
                        String contact = obj.getString("contact");
                        String id = obj.getString("_id");
                        String url = (String)obj.getJSONArray("gallery").get(0);
                        DataApp.LISTDATA.add(new ItemMenuStructure(street, url, price, lat, lon, contact, "", "", id,""));
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