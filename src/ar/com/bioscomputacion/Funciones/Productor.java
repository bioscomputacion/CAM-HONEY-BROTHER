/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Funciones;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Caco
 */
public class Productor extends Persona {
    
    private int cod_productor;
    private int cod_persona; 
    private String fecha_venta_miel_1;
    private String fecha_venta_miel_2;
    private String fecha_venta_miel_3;
    private String nombre_fantasia;
    private String razon_social;
    private String condicion_iva;
    private String cuit;
    private String domicilio_fiscal;
    private String estado;

    //metodos constructores
    
    public Productor() {
    }

    public Productor(String fecha_venta_miel_1, String fecha_venta_miel_2, String fecha_venta_miel_3, String nombre_fantasia, String razon_social, String condicion_iva, String cuit, String domicilio_fiscal, String estado, String nombre, String documento, String pais, String estado_provincia, String localidad, String domicilio, String telefono, String correo) {
        super(nombre, documento, pais, estado_provincia, localidad, domicilio, telefono, correo);
        this.fecha_venta_miel_1 = fecha_venta_miel_1;
        this.fecha_venta_miel_2 = fecha_venta_miel_2;
        this.fecha_venta_miel_3 = fecha_venta_miel_3;
        this.nombre_fantasia = nombre_fantasia;
        this.razon_social = razon_social;
        this.condicion_iva = condicion_iva;
        this.cuit = cuit;
        this.domicilio_fiscal = domicilio_fiscal;
        this.estado = estado;
    }
    
    //getters y setters

    public int getCod_productor() {
        return cod_productor;
    }

    public int getCod_persona() {
        return cod_persona;
    }
    
    public String getFecha_venta_miel_1() {
        return fecha_venta_miel_1;
    }

    public void setFecha_venta_miel_1(String fecha_venta_miel_1) {
        this.fecha_venta_miel_1 = fecha_venta_miel_1;
    }

    public String getFecha_venta_miel_2() {
        return fecha_venta_miel_2;
    }

    public void setFecha_venta_miel_2(String fecha_venta_miel_2) {
        this.fecha_venta_miel_2 = fecha_venta_miel_2;
    }

    public String getFecha_venta_miel_3() {
        return fecha_venta_miel_3;
    }

    public void setFecha_venta_miel_3(String fecha_venta_miel_3) {
        this.fecha_venta_miel_3 = fecha_venta_miel_3;
    }

    public String getNombre_fantasia() {
        return nombre_fantasia;
    }

    public void setNombre_fantasia(String nombre_fantasia) {
        this.nombre_fantasia = nombre_fantasia;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public String getCondicion_iva() {
        return condicion_iva;
    }

    public void setCondicion_iva(String condicion_iva) {
        this.condicion_iva = condicion_iva;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getDomicilio_fiscal() {
        return domicilio_fiscal;
    }

    public void setDomicilio_fiscal(String domicilio_fiscal) {
        this.domicilio_fiscal = domicilio_fiscal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public boolean registrar(Productor productor){
        try {
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            PreparedStatement pst = cn.prepareStatement("INSERT INTO persona (nombre,documento,pais,estado_provincia,localidad,domicilio,telefono,correo) "
                    + "VALUES (?,?,?,?,?,?,?,?)");
            PreparedStatement pst2 = cn.prepareStatement("INSERT INTO productor (cod_persona,fecha_venta_miel_1,fecha_venta_miel_2,fecha_venta_miel_3,nombre_fantasia,razon_social,condicion_iva,cuit,domicilio_fiscal,estado) VALUES ((select cod_persona from persona order by cod_persona desc limit 1),"
                    + "?,?,?,?,?,?,?,?,?)");
            
            
            pst.setString(1, productor.getNombre());
            pst.setString(2, productor.getDocumento());
            pst.setString(3, productor.getPais());
            pst.setString(4, productor.getEstado_provincia());
            pst.setString(5, productor.getLocalidad());
            pst.setString(6, productor.getDomicilio());
            pst.setString(7, productor.getTelefono());
            pst.setString(8, productor.getCorreo());
            
            pst2.setString(1, productor.getFecha_venta_miel_1());
            pst2.setString(2, productor.getFecha_venta_miel_2());
            pst2.setString(3, productor.getFecha_venta_miel_3());
            pst2.setString(4, productor.getNombre_fantasia());
            pst2.setString(5, productor.getRazon_social());
            pst2.setString(6, productor.getCondicion_iva());
            pst2.setString(7, productor.getCuit());
            pst2.setString(8, productor.getDomicilio_fiscal());
            pst2.setString(9, productor.getEstado());
            
            int N = pst.executeUpdate();
            int N2 = pst2.executeUpdate();

            if (N != 0 || N2 != 0) {
                return true;
            } else {
                return false;
            }
            
            
        } catch (Exception e) {
        }
        return false;
    }
    
}
