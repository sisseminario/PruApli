package com.example.elena.eden;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elena.eden.DATA.DataApp;
import com.example.elena.eden.DETALLES.Detalles_casa;
import com.example.elena.eden.ItemMenu.LoaderImg;
import com.example.elena.eden.ItemMenu.OnLoadCompleImg;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;

import cz.msebera.android.httpclient.Header;

public class DetallesCasa extends AppCompatActivity {
    private static int ID;
    //view pager
    private DetallesCasa.SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;



    private  String idCasa;
    private static int position;
    protected TextView est,des, amu,ser_ba,otr,n_ba,n_co,
            nu_ha,pi,ele,pic,ga,amo,dire,prec, mon,t_vi,zo,ciu,n_du,a_du,
            tef_d,cel_d,ema_d;
    protected Context root;
    protected Detalles_casa DATA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_casa);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        root = this;
        idCasa = this.getIntent().getExtras().getString("id");
        position = DetallesCasa.this.getIntent().getExtras().getInt("position");
        LoadDesta();
        loadAsyncData();
        //ID= this.getIntent().getExtras().getInt("id");

       // Toast.makeText(this, idCasa, Toast.LENGTH_SHORT).show();
        //id= this.getIntent().getExtras().getString("id");

        //LLAMADAS//

        FloatingActionButton mDialButton = (FloatingActionButton) findViewById(R.id.llamada);
        final TextView mPhoneNoEt = (TextView) findViewById(R.id.textView90);


        mDialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNo = mPhoneNoEt.getText().toString();
                if(!TextUtils.isEmpty(phoneNo)) {
                    String dial = "tel:" + phoneNo;
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
                }else {
                    Toast.makeText(DetallesCasa.this, "Enter a phone number", Toast.LENGTH_SHORT).show();
                }

            }
        });
        //view pager
        mSectionsPagerAdapter = new DetallesCasa.SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.containerdet);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }


    private void loadAsyncData() {
        AsyncHttpClient client = new AsyncHttpClient();
        Log.i("RUTA DE LA CASA",DataApp.REST_USER_POST + "/" +idCasa);
        client.get(DataApp.REST_USER_POST + "/" +idCasa,
                new JsonHttpResponseHandler(){
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                        try {

                            ///String estado = response.getString("estado");
                            String descripcion = response.getString("descripcion");
                            String supterreno = response.getString("supterreno");
                            String amurallado = response.getString("amurallado");
                            String servicios_basicos = response.getString("servicios_basicos");
                            String otros = response.getString("otros");
                            int numero_banios = response.getInt("numero_banios");
                            int numero_habitaciones = response.getInt("numero_habitaciones");
                            //int nuemro_cocina = response.getInt("nuemro_cocina");
                            int pisos = response.getInt("pisos");
                            String elevador = response.getString("elevador");
                            String piscina = response.getString("piscina");
                            String garaje = response.getString("garaje");
                            String amoblado = response.getString("amoblado");
                            String direccion = response.getString("direccion");
                            int precio = response.getInt("precio");
                            int moneda = response.getInt("moneda");
                            String tipo_vivenda = response.getString("tipo_vivienda");
                            String nombre_zona = response.getString("nombre_zona");
                            String nombre_ciudad = response.getString("nombre_ciudad");
                            String nombre_dueno = response.getString("nombre_dueno");
                            String apellido_dueno = response.getString("apellido_dueno");
                            int telefono_dueno = response.getInt("telefono_dueno");
                            int celular_dueno = response.getInt("celular_dueno");
                            String email_dueno = response.getString("email_dueno");
                            DATA = new Detalles_casa("",descripcion, supterreno, amurallado, servicios_basicos, otros, numero_banios,
                                    numero_habitaciones, 1, pisos, elevador, piscina, garaje, amoblado, direccion, precio, moneda, tipo_vivenda, nombre_zona,
                                    nombre_ciudad, nombre_dueno, apellido_dueno, telefono_dueno, celular_dueno, email_dueno);

                            setInformation();
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
        this.n_ba.setText(String.valueOf(DATA.getNumero_banios()));
        this.n_co.setText(String.valueOf(DATA.getNumero_banios()));
        this.nu_ha.setText(String.valueOf(DATA.getNumero_habitaciones()));
        this.ele.setText(DATA.getElevador());
        this.pic.setText(DATA.getPiscina());
        this.ga.setText(DATA.getGaraje());
        this.amo.setText(DATA.getAmoblado());
        this.dire.setText(DATA.getDireccion());
        this.prec.setText(String.valueOf(DATA.getPrecio()));
        this.mon.setText(String.valueOf(DATA.getMoneda()));
        this.t_vi.setText(DATA.getTipo_vivenda());
        this.zo.setText(DATA.getNombre_zona());
        this.ciu.setText(DATA.getNombre_ciudad());
        this.n_du.setText(DATA.getNombre_dueno());
        this.a_du.setText(DATA.getApellido_dueno());
        this.tef_d.setText(String.valueOf(DATA.getTelefono_dueno()));
        this.cel_d.setText(String.valueOf(DATA.getCelular_dueno()));
        this.pi.setText(String.valueOf(DATA.getPisos()));
        this.ema_d.setText(DATA.getEmail_dueno());

    }

    private void LoadDesta() {
        this.est=(TextView)this.findViewById(R.id.est);
        this.des=(TextView)this.findViewById(R.id.detal);
        this.amu=(TextView)this.findViewById(R.id.amurrallado);
        this.ser_ba=(TextView)findViewById(R.id.servicios_basicos);
        this.otr=(TextView)this.findViewById(R.id.otros);
        this.n_ba=this.findViewById(R.id.numero_banios);
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
    public static class PlaceholderFragment extends Fragment implements OnLoadCompleImg {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";


        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static DetallesCasa.PlaceholderFragment newInstance(int sectionNumber) {

            DetallesCasa.PlaceholderFragment fragment = new DetallesCasa.PlaceholderFragment();

            Bundle args = new Bundle();
            args.putString("url", DataApp.LISTDATA.get(position).getUrlimg().get(sectionNumber));
            //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_detalles_post, container, false);
            ImageView img = (ImageView)rootView.findViewById(R.id.imagen);
            String url = this.getArguments().getString("url");
            LoaderImg loader = new LoaderImg();
            loader.setOnloadCompleteImg(img, 0, this);
            loader.execute(url);
            //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }

        @Override
        public void OnloadCompleteImgResult(ImageView img, int position, Bitmap imgsourceimg) {
            img.setImageBitmap(imgsourceimg);
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            //Log.i("SIZE : ",String.valueOf(info.size()));
            return DetallesCasa.PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.

            return DataApp.LISTDATA.get(position).getUrlimg().size();
        }
    }


}
