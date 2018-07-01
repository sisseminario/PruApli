package com.example.elena.eden.ItemMenu;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class ItemMenuStructure {
    private String vender_alqui_anticre;
    private String estado;
    private String descripcion;
    private String supterrreno;
    private String amurallado;
    private String servicios_basicos;
    private String otros;
    private Integer numero_banios;
    private Integer numero_habitaciones;
    private Integer nuemro_cocina;
    private Integer pisos;
    private String elevador;
    private String piscina;
    private String garaje;
    private String amoblado;
    private String ubicacion;
    private String direccion;
    private Integer precio;
    private Integer moneda;
    private String tipo_vivenda;
    private String nombre_zona;
    private String nombre_ciudad;
    private double lat;
    private double lng;
    private String nombre_dueno;
    private String apellido_dueno;
    private Integer telefono_dueno;
    private Integer celular_dueno;
    private String email_dueno;
    private String id;
    private ArrayList<String> url;
    private ArrayList<Bitmap>  img;
    public ItemMenuStructure(String vender_alqui_anticre, String estado, String descripcion,
                             String amurallado, String servicios_basicos, String otros,
                             Integer numero_banios, Integer numero_habitaciones, Integer nuemro_cocina, Integer pisos,
                             String elevador, String piscina, String garaje, String amoblado,
                             String ubicacion, String direccion, Integer precio, Integer precio1,
                             Integer moneda, String tipo_vivenda, String nombre_zona,
                             String nombre_ciudad, double lat, double lng,
                             String nombre_dueno, String apellido_dueno, Integer telefono_dueno,
                             Integer celular_dueno, String supterrreno, String email_dueno,
                             String id, ArrayList<String> urlimg) {

        this.vender_alqui_anticre = vender_alqui_anticre;
        this.estado = estado;
        this.descripcion = descripcion;
        this.supterrreno = supterrreno;
        this.amurallado = amurallado;
        this.servicios_basicos = servicios_basicos;
        this.otros = otros;
        this.numero_banios = numero_banios;
        this.numero_habitaciones = numero_habitaciones;
        this.nuemro_cocina = nuemro_cocina;
        this.pisos = pisos;
        this.elevador = elevador;
        this.piscina = piscina;
        this.garaje = garaje;
        this.amoblado = amoblado;
        this.ubicacion = ubicacion;
        this.direccion = direccion;
        this.precio = precio;
        this.moneda = moneda;
        this.tipo_vivenda = tipo_vivenda;
        this.nombre_zona = nombre_zona;
        this.nombre_ciudad = nombre_ciudad;
        this.lat = lat;
        this.lng = lng;
        this.nombre_dueno = nombre_dueno;
        this.apellido_dueno = apellido_dueno;
        this.telefono_dueno = telefono_dueno;
        this.celular_dueno = celular_dueno;
        this.email_dueno = email_dueno;

        this.id = id;
        this.url = urlimg;
    }

    public void setImg(ArrayList<Bitmap> img) { this.img = img;}
    public void setPrecio (int p) {
        this.precio = precio;
    }
    public void setVender_alqui_anticre (String p) {this.vender_alqui_anticre = vender_alqui_anticre;}
    public ArrayList<Bitmap> getImg() {return this.img;}


    public String getVender_alqui_anticre() { return this.vender_alqui_anticre;}
    public ArrayList<Bitmap> getBitmap() {
        return this.img;
    }


    public ArrayList<String> getUrlimg() {
        return this.url;
    }
    public String getEstado (){ return this.estado;}
    public String getDescripcion (){ return this.descripcion;}
    public String getSupterrreno (){ return this.supterrreno;}
    public String getAmurallado (){ return this.amurallado;}
    public String getServicios_basicos(){ return this.servicios_basicos;}
    public String getOtros(){return this.otros;}
    public Integer getNumero_banios(){return this.numero_banios;}
    public Integer getNumero_habitaciones(){ return this.numero_habitaciones;}

    public Integer getNuemro_cocina(){ return this.nuemro_cocina;}
    public Integer getPisos(){ return this.pisos;}
    public String getElevador(){ return this.elevador;}
    public String getPiscina(){ return this.piscina;}
    public String getGaraje(){ return this.garaje;}
    public String getAmoblado() { return this.amoblado;}
    public String getUbicacion(){return this.ubicacion;}
    public String getDireccion(){ return this.direccion;}
    public Integer getPrecio(){ return this.precio;}
    public Integer getMonedamoneda(){ return this.moneda;}
    public String getTipo_vivenda(){ return this.tipo_vivenda;}
    public String getNombre_zona(){ return this.nombre_zona;}
    public String getNombre_ciudad(){ return this.nombre_ciudad;}
    public double getLat(){ return this.lat;}
    public double getLng(){ return this.lng;}
    public String getNombre_dueno(){ return this.nombre_dueno;}
    public String getApellido_dueno(){ return this.apellido_dueno;}
    public Integer getTelefono_dueno(){ return this.telefono_dueno;}
    public Integer getCelular_dueno(){ return this.celular_dueno;}
    public String getEmail_dueno(){ return this.email_dueno;}
    public String getId(){return this.id;}


}
