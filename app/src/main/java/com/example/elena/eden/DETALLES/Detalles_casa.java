package com.example.elena.eden.DETALLES;

public class Detalles_casa {
    private String estado ;
    private String descripcion ;
    private String supterreno ;
    private String amurallado ;
    private String servicios_basicos ;
    private String otros ;
    private int   numero_banios;
    private int numero_habitaciones;
    private int nuemro_cocina;
    private int pisos;
    private String elevador;
    private String piscina ;
    private String garaje ;
    private String amoblado ;
    private String direccion ;
    private int precio;
    private int moneda;
    private String tipo_vivenda ;
    private String nombre_zona ;
    private String nombre_ciudad;
    private String nombre_dueno ;
    private String apellido_dueno ;
    private int telefono_dueno ;
    private int celular_dueno;
    private String email_dueno;

    public Detalles_casa(String estado, String descripcion, String supterreno, String amurallado, String servicios_basicos, String otros, int numero_banios, int numero_habitaciones, int nuemro_cocina, int pisos, String elevador, String piscina, String garaje, String amoblado, String direccion, int precio, int moneda, String tipo_vivenda, String nombre_zona, String nombre_ciudad, String nombre_dueno, String apellido_dueno, int telefono_dueno, int celular_dueno, String email_dueno) {
        this.estado = estado;
        this.descripcion = descripcion;
        this.supterreno = supterreno;
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
        this.direccion = direccion;
        this.precio = precio;
        this.moneda = moneda;
        this.tipo_vivenda = tipo_vivenda;
        this.nombre_zona = nombre_zona;
        this.nombre_ciudad = nombre_ciudad;
        this.nombre_dueno = nombre_dueno;
        this.apellido_dueno = apellido_dueno;
        this.telefono_dueno = telefono_dueno;
        this.celular_dueno = celular_dueno;
        this.email_dueno = email_dueno;
    }

    public String getEstado() {
        return  this.estado;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public String getSupterreno() {
        return this.supterreno;
    }

    public String getAmurallado() {
        return this.amurallado;
    }

    public String getServicios_basicos() {
        return this.servicios_basicos;
    }

    public String getOtros() {
        return this.otros;
    }

    public int getNumero_banios() {
        return this.numero_banios;
    }

    public int getNumero_habitaciones() {
        return this.numero_habitaciones;
    }

    public int getNuemro_cocina() {
        return this.nuemro_cocina;
    }

    public int getPisos() {
        return this.pisos;
    }

    public String getElevador() {
        return this.elevador;
    }

    public String getPiscina() {
        return this.piscina;
    }

    public String getGaraje() {
        return this.garaje;
    }

    public String getAmoblado() {
        return this.amoblado;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public int getPrecio() {
        return this.precio;
    }

    public int getMoneda() {
        return this.moneda;
    }

    public String getTipo_vivenda() {
        return this.tipo_vivenda;
    }

    public String getNombre_zona() {
        return this.nombre_zona;
    }

    public String getNombre_ciudad() {
        return this.nombre_ciudad;
    }

    public String getNombre_dueno() {
        return this.nombre_dueno;
    }

    public String getApellido_dueno() {
        return this.apellido_dueno;
    }

    public int getTelefono_dueno() {
        return this.telefono_dueno;
    }

    public int getCelular_dueno() {
        return this.celular_dueno;
    }

    public String getEmail_dueno() {
        return this.email_dueno;
    }
}
