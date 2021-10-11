/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Funciones;

/**
 *
 * @author braya
 */
public class Persona {
    
    private int cod_persona;
    private String nombre;
    private String documento;
    private String pais;
    private String estado_provincia;
    private String localidad;
    private String domicilio;
    private String telefono;
    private String correo;

    public Persona() {
    }

    public Persona(String nombre, String documento, String pais, String estado_provincia, String localidad, String domicilio, String telefono, String correo) {
        this.nombre = nombre;
        this.documento = documento;
        this.pais = pais;
        this.estado_provincia = estado_provincia;
        this.localidad = localidad;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.correo = correo;
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEstado_provincia() {
        return estado_provincia;
    }

    public void setEstado_provincia(String estado_provincia) {
        this.estado_provincia = estado_provincia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }


    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.documento = domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Persona{" + "nombre=" + nombre + ", documento=" + documento + ", pais=" + pais + ", estado_provincia=" + estado_provincia + ", localidad=" + localidad + ", domicilio " + domicilio + ", telefono" + telefono + ", correo" +  '}';
    }
    
}
