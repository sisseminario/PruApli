package com.example.elena.eden.ItemMenu;

import android.graphics.Bitmap;

public class ItemMenuStructure {
    private String estado;
    private String descripcion;
    private String supterreno;
    private String servicios_basicos;
    private String descripcion_banio;
    private int pisos;
    private String elevador;
    private String garage;
    private int numparqueos;
    private String amoblado;
    private String ubicacion;
    private int precio;
    private String tipo_oferta;
    private String tipo_vivienda;
    private double latitud;
    private double longitud;
    private String nombre_dueno;
    private String apellido_dueno;
    private int celular_dueno;
    private int telefono_dueno;
    private String email_dueno;
    private String nombre_zona;
    private String nombre_ciudad;
    private String url;
    private Bitmap img;
    private String id;

    public ItemMenuStructure(String estado, String descripcion, String supterreno, String servicios_basicos, String descripcion_banio, int pisos, String elevador, String garage, int numparqueos, String amoblado, String ubicacion, int precio, String tipo_oferta, String tipo_vivienda, double latitud, double longitud, String nombre_dueno, String apellido_dueno, int celular_dueno, int telefono_dueno, String email_dueno, String nombre_zona, String nombre_ciudad, String urlimg, String id) {
        this.estado = estado;
        this.descripcion = descripcion;
        this.supterreno = supterreno;
        this.servicios_basicos = servicios_basicos;
        this.descripcion_banio = descripcion_banio;
        this.pisos = pisos;
        this.elevador = elevador;
        this.garage = garage;
        this.numparqueos = numparqueos;
        this.amoblado = amoblado;
        this.ubicacion = ubicacion;
        this.precio = precio;
        this.tipo_oferta = tipo_oferta;
        this.tipo_vivienda = tipo_vivienda;
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombre_dueno = nombre_dueno;
        this.apellido_dueno = apellido_dueno;
        this.celular_dueno = celular_dueno;
        this.telefono_dueno = telefono_dueno;
        this.email_dueno = email_dueno;
        this.nombre_zona = nombre_zona;
        this.nombre_ciudad = nombre_ciudad;
        this.url = urlimg;
        this.id = id;
    }

    public String getEstado() {
        return this.estado;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public String getSupterreno() {
        return this.supterreno;
    }

    public String getServicios_basicos() {
        return this.servicios_basicos;
    }

    public String getDescripcion_banio() {
        return this.descripcion_banio;
    }

    public int getPisos() {
        return this.pisos;
    }

    public String getElevador() {
        return this.elevador;
    }

    public String getGarage() {
        return this.garage;
    }

    public int getNumparqueos() {
        return this.numparqueos;
    }

    public String getAmoblado() {
        return this.amoblado;
    }

    public String getUbicacion() {
        return this.ubicacion;
    }

    public int getPrecio() {
        return this.precio;
    }

    public String getTipo_oferta() {
        return this.tipo_oferta;
    }

    public String getTipo_vivienda() {
        return this.tipo_vivienda;
    }

    public double getLatitud() {
        return this.latitud;
    }

    public double getLongitud() {
        return this.longitud;
    }

    public String getNombre_dueno() {
        return this.nombre_dueno;
    }

    public String getApellido_dueno() {
        return this.apellido_dueno;
    }

    public int getCelular_dueno() {
        return this.celular_dueno;
    }

    public int getTelefono_dueno() {
        return this.telefono_dueno;
    }

    public String getEmail_dueno() {
        return this.email_dueno;
    }

    public String getNombre_zona() {
        return this.nombre_zona;
    }

    public String getNombre_ciudad() {
        return this.nombre_ciudad;
    }

    public String getUrlimg() {
        return this.url;
    }

    public Bitmap getImg() {
        return this.img;
    }

    public String getId() {
        return this.id;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }


    public Bitmap getBitmap() {
        return this.img;
    }

}
