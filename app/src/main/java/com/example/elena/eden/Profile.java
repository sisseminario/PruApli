package com.example.elena.eden;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.elena.eden.DATA.DataApp;
import com.example.elena.eden.DATA.UserData;
import com.example.elena.eden.ItemMenu.LoaderImg;
import com.example.elena.eden.ItemMenu.OnLoadCompleImg;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Profile extends AppCompatActivity implements OnLoadCompleImg {
    private ImageView IMG;
    private TextView TXT;
    private Profile root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        root = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (UserData.IMG != null) {
            IMG.setImageBitmap(UserData.IMG);
        }

        LoadComponents();
        LoadExternalData();

    }

    private void LoadExternalData() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://192.168.1.109:7777/api/vo1.0/propiedad" + "/" + UserData.ID, new  JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray data = response.getJSONArray("avatars");
                    String photo = data.getString(0);
                    UserData.PHOTOURL = photo;
                    if (UserData.IMG == null) {
                        LoaderImg img = new LoaderImg();
                        img.setOnloadCompleteImg(IMG,1, (OnLoadCompleImg) root);
                        img.execute(photo);
                    }

                    String nombre_dueno = response.getString("nombre_dueno");
                    UserData.NAME = nombre_dueno;
                    String estado = response.getString("estado");
                    String apellidos_dueno = response.getString("apellidos_dueno");
                    String telefono_dueno = response.getString("telefono_dueno");
                    String celular_dueno = response.getString("celular_dueno");
                    String email_dueno = response.getString("email_dueno");
                    String tipo_vivienda = response.getString("tipo_vivienda");
                    String direccion = response.getString("direccion");
                    String nombre_ciudad = response.getString("nombre_ciudad");
                    String precio = response.getString("precio");
                    String moneda = response.getString("moneda");
                    String descripcion = response.getString("descripcion");
                    String supterreno = response.getString("supterreno");
                    String amurallado = response.getString("amurallado");
                    String servicios_basicos = response.getString("servicios_basicos");
                    String numero_habitaciones = response.getString("numero_habitaciones");
                    String numero_banios = response.getString("numero_banios");
                    String nuemro_cocinas = response.getString("nuemro_cocinas");
                    String otros = response.getString("otros");
                    String pisos = response.getString("pisos");
                    String elevador = response.getString("elevador");
                    String piscina = response.getString("piscina");
                    String garaje = response.getString("garaje");
                    String amoblado = response.getString("amoblado");
                    String nombre_zona = response.getString("nombre_zona");

                    TXT.setText(" " + nombre_dueno + " " + estado + " " + apellidos_dueno + " " + telefono_dueno + " "
                            + celular_dueno + " " + email_dueno + " " + tipo_vivienda + " " + direccion + " " + nombre_ciudad
                            + " " + precio + " " + moneda + " " + descripcion + " " + supterreno + " " + amurallado + " "
                            + servicios_basicos + " " + numero_habitaciones + " " + numero_banios + " " + nuemro_cocinas + " "
                            + otros + " " + pisos + " " + elevador + " " + piscina + " " + garaje + " " + amoblado + " " + nombre_zona);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            });
    }

    private void LoadComponents() {
        IMG = (ImageView)this.findViewById(R.id.imageView);
        TXT = (TextView)this.findViewById(R.id.textView7);
    }

    @Override
    public void OnloadCompleteImgResult(ImageView img, int position, Bitmap imgsourceimg) {
        UserData.IMG = imgsourceimg;
        img.setImageBitmap(imgsourceimg);
    }
}
