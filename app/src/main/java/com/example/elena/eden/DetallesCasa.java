package com.example.elena.eden;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elena.eden.DATA.DataApp;
import com.example.elena.eden.DETALLES.Detalles_casa;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class DetallesCasa extends AppCompatActivity {
    private static int ID;


    private  String idCasa;
    protected TextView est,des, amu,ser_ba,otr,n_ba,n_co,
            nu_ha,pi,ele,pic,ga,amo,dire,prec, mon,t_vi,zo,ciu,n_du,a_du,
            tef_d,cel_d,ema_d;
    protected DetallesCasa root;
    protected Detalles_casa DATA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_casa);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LoadDesta();
        loadAsyncData();
        //ID= this.getIntent().getExtras().getInt("id");
        idCasa = this.getIntent().getExtras().getString("id");
        Toast.makeText(root, idCasa, Toast.LENGTH_SHORT).show();
        //id= this.getIntent().getExtras().getString("id");
    }


    private void loadAsyncData() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(DataApp.HOST +"/api/v1.0/home/" +idCasa,
                new JsonHttpResponseHandler(){
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                        try {

                            ///String estado = response.getString("estado");
                            String descripcion = response.getString("descripcion");
                            String supterreno = response.getString("supterreno");
                            String amurallado = response.getString("amurallado");
                            String servicios_basicos = response.getString("servicios_basicos");
                            String otros = response.getString("otros");
                            int numero_banios = response.getInt("  numero_banios");
                            int numero_habitaciones = response.getInt("numero_habitaciones");
                            int nuemro_cocina = response.getInt("nuemro_cocina");
                            int pisos = response.getInt("pisos");
                            String elevador = response.getString("elevador");
                            String piscina = response.getString("piscina");
                            String garaje = response.getString("garaje");
                            String amoblado = response.getString("amoblado");
                            String direccion = response.getString("direccion");
                            int precio = response.getInt("precio");
                            int moneda = response.getInt("moneda");
                            String tipo_vivenda = response.getString("tipo_vivenda");
                            String nombre_zona = response.getString("nombre_zona");
                            String nombre_ciudad = response.getString("nombre_ciudad");
                            String nombre_dueno = response.getString("nombre_dueno");
                            String apellido_dueno = response.getString("apellido_dueno");
                            int telefono_dueno = response.getInt("telefono_duen");
                            int celular_dueno = response.getInt("celular_dueno");
                            String email_dueno = response.getString("email_dueno");
                            DATA = new Detalles_casa("",descripcion, supterreno, amurallado, servicios_basicos, otros, numero_banios,
                                    numero_habitaciones, nuemro_cocina, pisos, elevador, piscina, garaje, amoblado, direccion, precio, moneda, tipo_vivenda, nombre_zona,
                                    nombre_ciudad, nombre_dueno, apellido_dueno, telefono_dueno, celular_dueno, email_dueno);

                            root.setInformation();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void setInformation() {
        this.est.setText(DATA.getEstado());
        this.des.setText(DATA.getDescripcion());
        this.amu.setText(DATA.getAmurallado());
        this.ser_ba.setText(DATA.getServicios_basicos());
        this.otr.setText(DATA.getOtros());
        this.n_ba.setText(DATA.getNumero_banios());
        this.n_co.setText(DATA.getNumero_banios());
        this.nu_ha.setText(DATA.getNumero_habitaciones());
        this.ele.setText(DATA.getElevador());
        this.pic.setText(DATA.getPiscina());
        this.ga.setText(DATA.getGaraje());
        this.amo.setText(DATA.getAmoblado());
        this.dire.setText(DATA.getDireccion());
        this.prec.setText(DATA.getPrecio());
        this.mon.setText(DATA.getMoneda());
        this.t_vi.setText(DATA.getTipo_vivenda());
        this.zo.setText(DATA.getNombre_zona());
        this.ciu.setText(DATA.getNombre_ciudad());
        this.n_du.setText(DATA.getNombre_dueno());
        this.a_du.setText(DATA.getApellido_dueno());
        this.tef_d.setText(DATA.getTelefono_dueno());
        this.cel_d.setText(DATA.getCelular_dueno());
        this.pi.setText(DATA.getPisos());
        this.ema_d.setText(DATA.getEmail_dueno());

    }

    private void LoadDesta() {
        this.est=(TextView)this.findViewById(R.id.est);
        this.des=(TextView)this.findViewById(R.id.detal);
        this.amu=(TextView)this.findViewById(R.id.amurrallado);
        this.ser_ba=(TextView)this.findViewById(R.id.servicios_b);
        this.otr=(TextView)this.findViewById(R.id.otros);
        this.n_ba=(TextView)this.findViewById(R.id.num_banios);
        this.n_co=(TextView)this.findViewById(R.id.nuemro_cocinas);
        this.nu_ha=(TextView)this.findViewById(R.id.numero_habitaciones);
        this.ele=(TextView)this.findViewById(R.id.elevador);
        this.pic=(TextView)this.findViewById(R.id.piss);
        this.ga=(TextView)this.findViewById(R.id.gar);
        this.amo=(TextView)this.findViewById(R.id.amo);
        this.dire=(TextView)this.findViewById(R.id.direc);
        this.prec=(TextView)this.findViewById(R.id.pre);
        this.mon=(TextView)this.findViewById(R.id.mone);
        this.t_vi=(TextView)this.findViewById(R.id.tipo_vi);
        this.zo=(TextView)this.findViewById(R.id.zon);
        this.ciu=(TextView)this.findViewById(R.id.ciud);
        this.n_du=(TextView)this.findViewById(R.id.du);
        this.a_du=(TextView)this.findViewById(R.id.due_a);
        this.tef_d=(TextView)this.findViewById(R.id.telef);
        this.cel_d=(TextView)this.findViewById(R.id.cel);
        this.pi=(TextView)this.findViewById(R.id.pisos);
        this.ema_d=(TextView)this.findViewById(R.id.emai_du);



    }


}
