package com.example.elena.eden;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elena.eden.DATA.DataApp;
import com.example.elena.eden.DATA.UserData;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;

public class Registro_casa extends AppCompatActivity implements View.OnClickListener {

    Spinner sp_ciudades;
    Spinner sp_tipopropi;
    String[] ciudades;
    String[] tipopropiedad;
    HttpClient cliente;
    HttpPost post;
    private boolean isFirstTime = true;

    Button camara;

    private Context root;
    Button guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        root = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_casa);

        //Botones

       /*camara = (Button) findViewById(R.id.boton_cam);
        camara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camara = new Intent(Registro_casa.this, Home_camara.class);
                startActivity(camara);
            }
        });
*/
        guardar = (Button) this.findViewById(R.id.guardar);
        guardar.setOnClickListener(this);

        ///


        sp_ciudades = (Spinner) findViewById(R.id.lista_ciu);
        ciudades = getResources().getStringArray(R.array.lista_ciudades);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, ciudades);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_ciudades.setAdapter(adapter1);
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
        sp_tipopropi = (Spinner) findViewById(R.id.lista_tipovivienda);
        tipopropiedad = getResources().getStringArray(R.array.lista_tipovivenda);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, tipopropiedad);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_tipopropi.setAdapter(adapter2);
        sp_tipopropi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (isFirstTime) {
                    isFirstTime = false;
                } else {
                    Toast.makeText(getBaseContext(), tipopropiedad[position], Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        TextView nombre_dueno = (TextView) this.findViewById(R.id.nombre_dueno);
        TextView apellidos_dueno = (TextView) this.findViewById(R.id.apellidos_dueno);
        TextView telefono_dueno = (TextView) this.findViewById(R.id.telefono_dueno);
        TextView celular_dueno = (TextView) this.findViewById(R.id.celular_dueno);
        TextView email_dueno = (TextView) this.findViewById(R.id.email_dueno);

        TextView direccion = (TextView) this.findViewById(R.id.direccion);
        TextView precio_propiedad = (TextView) this.findViewById(R.id.precio_propiedad);
        TextView nomeda_bol = (TextView) this.findViewById(R.id.nomeda_bol);
        TextView desciption_propi = (TextView) this.findViewById(R.id.desciption_propi);
        TextView supterreno = (TextView) this.findViewById(R.id.supterreno);
        TextView numero_habitaciones = (TextView) this.findViewById(R.id.numero_habitaciones);
        TextView numero_banios = (TextView) this.findViewById(R.id.numero_banios);
        TextView nuemro_cocina = (TextView) this.findViewById(R.id.nuemro_cocina);
        TextView otros_cuartos = (TextView) this.findViewById(R.id.otros_cuartos);
        TextView numpisos = (TextView) this.findViewById(R.id.numpisos);
        TextView nombre_zona = (TextView) this.findViewById(R.id.nombre_zona);


        RadioGroup radio = (RadioGroup) this.findViewById(R.id.seleccionventa);
        String estado = "";
        if (radio.getCheckedRadioButtonId() == R.id.vender) {
            estado = "vender";
        } else {
            if (radio.getCheckedRadioButtonId() == R.id.alquilar) {
                estado = "alquilar";
            } else {
                estado = "anticretico";
            }
        }

        RadioGroup radio1 = (RadioGroup) this.findViewById(R.id.radioGroup);
        String amurallado = "";
        if (radio1.getCheckedRadioButtonId() == R.id.amurrallado_si) {
            amurallado = "Amurallado";
        } else {
            amurallado = "";
        }

        CheckBox agua = (CheckBox) this.findViewById(R.id.agua);
        String agua1 = "";
        if (agua.isChecked()) {
            agua1 = " agua, ";
        } else {
            agua1 = "";
        }
        CheckBox luz = (CheckBox) this.findViewById(R.id.luz);
        String luz1 = "";
        if (luz.isChecked()) {
            luz1 = "luz";
        } else {
            luz1 = "";
        }
        CheckBox gas = (CheckBox) this.findViewById(R.id.gas);
        String gas1 = "";
        if (gas.isChecked()) {
            gas1 = "agua";
        } else {
            gas1 = "";
        }
        CheckBox alcantarillado = (CheckBox) this.findViewById(R.id.alcantarillado);
        String alcantarillado1 = "";
        if (alcantarillado.isChecked()) {
            alcantarillado1 = "alcantarillado1";
        } else {
            alcantarillado1 = "";
        }

        RadioGroup radio2 = (RadioGroup) this.findViewById(R.id.selction_elv);
        String elevador = "";
        if (radio2.getCheckedRadioButtonId() == R.id.si_elev) {
            elevador = "elevador";
        } else {
            elevador = "";
        }

        RadioGroup radio3 = (RadioGroup) this.findViewById(R.id.selction_piscina);
        String piscina = "";
        if (radio3.getCheckedRadioButtonId() == R.id.si_piscina) {
            piscina = "piscina";
        } else {
            piscina = "";
        }

        RadioGroup radio4 = (RadioGroup) this.findViewById(R.id.selction_garaje);
        String garaje = "";
        if (radio4.getCheckedRadioButtonId() == R.id.si_garaje) {
            garaje = "garaje";
        } else {
            garaje = "";
        }

        RadioGroup radio5 = (RadioGroup) this.findViewById(R.id.selction_amoblado);
        String amoblado = "";
        if (radio5.getCheckedRadioButtonId() == R.id.si_amoblado) {
            amoblado = "amoblado";
        } else {
            amoblado = "";
        }

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("estado", estado);
        params.put("nombre_dueno", nombre_dueno.getText());
        params.put("apellido_dueno", apellidos_dueno.getText());
        params.put("telefono_dueno", telefono_dueno.getText());
        params.put("celular_dueno", celular_dueno.getText());
        params.put("email_dueno", email_dueno.getText());
        params.put("tipo_vivienda", sp_tipopropi.getSelectedItem().toString());
        params.put("direccion", direccion.getText());
        params.put("nombre_ciudad", sp_ciudades.getSelectedItem().toString());
        params.put("precio", precio_propiedad.getText());
        params.put("moneda", nomeda_bol.getText());
        params.put("descripcion", desciption_propi.getText());
        params.put("supterreno", supterreno.getText());
        params.put("amurallado", amurallado);
        params.put("servicios_basicos", agua1 + luz1 + gas1 + alcantarillado1);
        params.put("numero_habitaciones", numero_habitaciones.getText());
        params.put("numero_banios", numero_banios.getText());
        params.put("nuemro_cocinas", nuemro_cocina.getText());
        params.put("otros", otros_cuartos.getText());
        params.put("pisos", numpisos.getText());
        params.put("elevador", elevador);
        params.put("piscina", piscina);
        params.put("garaje", garaje);
        params.put("amoblado", amoblado);
        params.put("nombre_zona", nombre_zona.getText());

        client.post(DataApp.REST_USER_POST, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    String msn = response.getString("msn");
                    String id = response.getString("id");
                    UserData.ID = id;
                    if (id != null) {
                        Intent camera = new Intent(root, Home_camara.class);
                        root.startActivity(camera);
                    } else {
                        Toast.makeText(root, "ERROR AL enviar los datos", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //AsyncHttpClient.log.w(LOG_TAG, "onSuccess(int, Header[], JSONObject) was not overriden, but callback was received");
            }
        });

    }
}