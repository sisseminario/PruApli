package com.example.elena.eden;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

import static com.example.elena.eden.R.id.agua;

public class Registro_casa extends AppCompatActivity implements View.OnClickListener {

    Spinner sp_ciudades;
    Spinner sp_tipopropi;
    String[] ciudades;
    String[] tipopropiedad;
    HttpClient cliente;
    HttpPost post;
    List<NameValuePair> lista;
    private boolean isFirstTime = true;
    EditText nombre_dueno, apellidos_dueno, telefono_dueno, celular_dueno, email_dueno,
            direccion, precio_propiedad, nomeda_bol, desciption_propi, supterreno, numero_habitaciones,
            numero_banios, nuemro_cocina, otros_cuartos, numpisos, nombre_zona;

    Button camara;
    Button mapa;
    private Context root;
    Button guardar;
    RadioGroup radio, radio1, radio2, radio3, radio4, radio5;
    CheckBox agua, luz, gas, alcantarillado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        root = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_casa);

        //Botones
        camara = (Button) findViewById(R.id.boton_cam);
        camara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camara = new Intent(Registro_casa.this, Home_camara.class);
                startActivity(camara);
            }
        });

        mapa = (Button) findViewById(R.id.boton_Mapa);
        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapa = new Intent(Registro_casa.this, Home_mapa.class);
                startActivity(mapa);
            }
        });

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
        nombre_dueno = (EditText) this.findViewById(R.id.nombre_dueno);
        apellidos_dueno = (EditText) this.findViewById(R.id.apellidos_dueno);
        telefono_dueno = (EditText) this.findViewById(R.id.telefono_dueno);
        celular_dueno = (EditText) this.findViewById(R.id.celular_dueno);
        email_dueno = (EditText) this.findViewById(R.id.email_dueno);

        direccion = (EditText) this.findViewById(R.id.direccion);
        precio_propiedad = (EditText) this.findViewById(R.id.precio_propiedad);
        nomeda_bol = (EditText) this.findViewById(R.id.nomeda_bol);
        desciption_propi = (EditText) this.findViewById(R.id.desciption_propi);
        supterreno = (EditText) this.findViewById(R.id.supterreno);
        numero_habitaciones = (EditText) this.findViewById(R.id.numero_habitaciones);
        numero_banios = (EditText) this.findViewById(R.id.numero_banios);
        nuemro_cocina = (EditText) this.findViewById(R.id.nuemro_cocina);
        otros_cuartos = (EditText) this.findViewById(R.id.otros_cuartos);
        numpisos = (EditText) this.findViewById(R.id.numpisos);
        nombre_zona = (EditText) this.findViewById(R.id.nombre_zona);

        guardar = (Button)findViewById(R.id.guardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nombre_dueno.getText().toString().equals("")){
                    Toast.makeText(Registro_casa.this, "Porfabor Introdusca su Nombre", Toast.LENGTH_LONG).show();
                }else {
                    new Registro_casa.EnviarDatos(Registro_casa.this).execute();
                }
            }
        });


    }

    class EnviarDatos extends AsyncTask<String, String, String > {



        private Activity contexto;
        EnviarDatos(Activity context){
            this.contexto = context;
        }
        @Override
        protected String doInBackground(String... strings) {

            if(datos ()){
                contexto.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(contexto, "Datos enviados Existosamente", Toast.LENGTH_SHORT).show();

                        nombre_dueno.setText("");
                        apellidos_dueno.setText("");
                        telefono_dueno.setText("");
                        celular_dueno.setText("");
                        email_dueno.setText("");

                        direccion.setText("");
                        precio_propiedad.setText("");
                        nomeda_bol.setText("");
                        desciption_propi.setText("");
                        supterreno.setText("");
                        numero_habitaciones.setText("");
                        numero_banios.setText("");
                        nuemro_cocina.setText("");
                        otros_cuartos.setText("");
                        numpisos.setText("");
                        nombre_zona.setText("");

                    }
                });


            }
            else{
                contexto.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(contexto, "Su nombre es incorrecto", Toast.LENGTH_SHORT).show();

                    }
                });
            }

            return null;
        }
    }

    private boolean datos (){
        cliente = new DefaultHttpClient();
        post = new HttpPost("http://192.168.1.109:7777/api/vo1.0/propiedad");
        radio = (RadioGroup) this.findViewById(R.id.seleccionventa);
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

        radio1 = (RadioGroup) this.findViewById(R.id.radioGroup);
        String amurallado = "";
        if (radio1.getCheckedRadioButtonId() == R.id.amurrallado_si) {
            amurallado = "Amurallado";
        } else {
            amurallado = "";
        }

        agua = (CheckBox) this.findViewById(R.id.agua);
        String agua1 = "";
        if (agua.isChecked()) {
            agua1 = " agua, ";
        } else {
            agua1 = "";
        }
        luz = (CheckBox) this.findViewById(R.id.luz);
        String luz1 = "";
        if (luz.isChecked()) {
            luz1 = " luz, ";
        } else {
            luz1 = "";
        }
        gas = (CheckBox) this.findViewById(R.id.gas);
        String gas1 = "";
        if (gas.isChecked()) {
            gas1 = " gas, ";
        } else {
            gas1 = "";
        }
        alcantarillado = (CheckBox) this.findViewById(R.id.alcantarillado);
        String alcantarillado1 = "";
        if (alcantarillado.isChecked()) {
            alcantarillado1 = " alcantarillado";
        } else {
            alcantarillado1 = "";
        }

        radio2 = (RadioGroup) this.findViewById(R.id.selction_elv);
        String elevador = "";
        if (radio2.getCheckedRadioButtonId() == R.id.si_elev) {
            elevador = "elevador";
        } else {
            elevador = "";
        }

        radio3 = (RadioGroup) this.findViewById(R.id.selction_piscina);
        String piscina = "";
        if (radio3.getCheckedRadioButtonId() == R.id.si_piscina) {
            piscina = "piscina";
        } else {
            piscina = "";
        }

        radio4 = (RadioGroup) this.findViewById(R.id.selction_garaje);
        String garaje = "";
        if (radio4.getCheckedRadioButtonId() == R.id.si_garaje) {
            garaje = "garaje";
        } else {
            garaje = "";
        }

        radio5 = (RadioGroup) this.findViewById(R.id.selction_amoblado);
        String amoblado = "";
        if (radio5.getCheckedRadioButtonId() == R.id.si_amoblado) {
            amoblado = "amoblado";
        } else {
            amoblado = "";
        }

        lista = new ArrayList<NameValuePair>(11);
        lista.add(new BasicNameValuePair("estado",estado));
        lista.add(new BasicNameValuePair("nombre_dueno", nombre_dueno.getText().toString().trim()));
        lista.add(new BasicNameValuePair("apellido_dueno", apellidos_dueno.getText().toString().trim()));
        lista.add(new BasicNameValuePair("telefono_dueno", telefono_dueno.getText().toString().trim()));
        lista.add(new BasicNameValuePair("celular_dueno", celular_dueno.getText().toString().trim()));
        lista.add(new BasicNameValuePair("email_dueno", email_dueno.getText().toString().trim()));
        lista.add(new BasicNameValuePair("tipo_vivienda", sp_tipopropi.getSelectedItem().toString()));
        lista.add(new BasicNameValuePair("direccion", direccion.getText().toString().trim()));
        lista.add(new BasicNameValuePair("nombre_ciudad", sp_ciudades.getSelectedItem().toString()));
        lista.add(new BasicNameValuePair("precio", precio_propiedad.getText().toString().trim()));
        lista.add(new BasicNameValuePair("moneda", nomeda_bol.getText().toString().trim()));
        lista.add(new BasicNameValuePair("descripcion", desciption_propi.getText().toString().trim()));
        lista.add(new BasicNameValuePair("supterreno", supterreno.getText().toString().trim()));
        lista.add(new BasicNameValuePair("amurallado", amurallado));
        lista.add(new BasicNameValuePair("servicios_basicos", agua1 + luz1 + gas1 + alcantarillado1));
        lista.add(new BasicNameValuePair("numero_habitaciones", numero_habitaciones.getText().toString().trim()));
        lista.add(new BasicNameValuePair("numero_banios", numero_banios.getText().toString().trim()));
        lista.add(new BasicNameValuePair("nuemro_cocinas", nuemro_cocina.getText().toString().trim()));
        lista.add(new BasicNameValuePair("otros", otros_cuartos.getText().toString().trim()));
        lista.add(new BasicNameValuePair("pisos", numpisos.getText().toString().trim()));
        lista.add(new BasicNameValuePair("elevador", elevador));
        lista.add(new BasicNameValuePair("piscina", piscina));
        lista.add(new BasicNameValuePair("garaje", garaje));
        lista.add(new BasicNameValuePair("amoblado", amoblado));
        lista.add(new BasicNameValuePair("nombre_zona", nombre_zona.getText().toString().trim()));

        try{
            post.setEntity(new UrlEncodedFormEntity(lista));
            cliente.execute(post);
            return true;

        }catch (UnsupportedEncodingException e){

            e.printStackTrace();

        }catch (ClientProtocolException e){

            e.printStackTrace();

        }catch (IOException e){

            e.printStackTrace();
        }

        return false;
    }

    @Override
    public void onClick(View v) {


        AsyncHttpClient client = new AsyncHttpClient();

    }
}
