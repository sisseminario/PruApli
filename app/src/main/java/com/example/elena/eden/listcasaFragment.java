package com.example.elena.eden;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

public class listcasaFragment extends Fragment implements AdapterView.OnItemClickListener {
    /*private ArrayList<ItemMenuStructure> LISTDATA;*/

    private View ROOT;
    private OnLoadDataComplete event;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@NonNull ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        DataApp.LISTDATA =new ArrayList<ItemMenuStructure>();
        ROOT= inflater.inflate(R.layout.list_fragment, container, false);
        loadData();
        return ROOT;
    }
    public void setOnloadCompleteData (OnLoadDataComplete event) {
        this.event = event;
    }
    private void loadData() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(DataApp.REST_USER_POST, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray listData = response.getJSONArray("info");
                    for (int i = 0; i < listData.length(); i++) {
                        JSONObject obj = listData.getJSONObject(i);
                        String estado = obj.getString("estado");
                        int numero_banios = obj.getInt("numero_banios");
                        int numero_habitaciones = obj.getInt("numero_habitaciones");
                        int precio = obj.getInt("precio");
                        int moneda = obj.getInt("moneda");
                        double lat = obj.getDouble("lat");
                        double lng = obj.getDouble("lng");
                        String descripcion = obj.getString("descripcion");
                        String id = obj.getString("_id");
                        // String url = DataApp.HOST + (String)obj.getJSONArray("gallery").get(0);
                        JSONArray listGallery = obj.getJSONArray("gallery");
                        ArrayList<String> urllist =  new ArrayList<String>();
                        for (int j = 0; j < listGallery.length(); j ++) {
                            urllist.add(DataApp.HOST + listGallery.getString(j));
                        }

                        DataApp.LISTDATA.add(new ItemMenuStructure(estado, descripcion, "",
                                "", "", numero_banios, numero_habitaciones, 0,
                                0, "", "",
                                "", "", "", "", precio, moneda,
                                "", "",
                                "", lat, lng, "", "",
                                0, 0,
                                "", "", id, urllist));
                    }
                    LoadComponents();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void LoadComponents () {

        ListView list = (ListView) ROOT.findViewById(R.id.superlista);
        MenuBaseAdapter adapter = new MenuBaseAdapter(this.getActivity(), DataApp.LISTDATA);
        list.setAdapter(adapter);
        this.event.OnLodCompleteDataResult();
        list.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent detaild = new Intent(this.getActivity(), DetallesPost.class);
        detaild.putExtra("id", position);
        this.getActivity().startActivity(detaild);
    }
}
