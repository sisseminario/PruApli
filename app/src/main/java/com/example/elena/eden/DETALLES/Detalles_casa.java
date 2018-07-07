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
        return estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getSupterreno() {
        return supterreno;
    }

    public String getAmurallado() {
        return amurallado;
    }

    public String getServicios_basicos() {
        return servicios_basicos;
    }

    public String getOtros() {
        return otros;
    }

    public int getNumero_banios() {
        return numero_banios;
    }

    public int getNumero_habitaciones() {
        return numero_habitaciones;
    }

    public int getNuemro_cocina() {
        return nuemro_cocina;
    }

    public int getPisos() {
        return pisos;
    }

    public String getElevador() {
        return elevador;
    }

    public String getPiscina() {
        return piscina;
    }

    public String getGaraje() {
        return garaje;
    }

    public String getAmoblado() {
        return amoblado;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getPrecio() {
        return precio;
    }

    public int getMoneda() {
        return moneda;
    }

    public String getTipo_vivenda() {
        return tipo_vivenda;
    }

    public String getNombre_zona() {
        return nombre_zona;
    }

    public String getNombre_ciudad() {
        return nombre_ciudad;
    }

    public String getNombre_dueno() {
        return nombre_dueno;
    }

    public String getApellido_dueno() {
        return apellido_dueno;
    }

    public int getTelefono_dueno() {
        return telefono_dueno;
    }

    public int getCelular_dueno() {
        return celular_dueno;
    }

    public String getEmail_dueno() {
        return email_dueno;
    }
}
