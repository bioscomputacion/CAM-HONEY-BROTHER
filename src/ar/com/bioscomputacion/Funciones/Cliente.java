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
public class Cliente extends Persona {

    private int cod_cliente;
    private int cod_persona; 
    private String razon_social;
    private String condicion_iva;
    private String cuit;
    private String domicilio_fiscal;
    private String estado;

    //metodos constructores
    
    public Cliente() {
    }

    public Cliente(String razon_social, String condicion_iva, String cuit, String domicilio_fiscal, String estado, String nombre, String documento, String pais, String estado_provincia, String localidad, String domicilio, String telefono, String correo) {
        super(nombre, documento, pais, estado_provincia, localidad, domicilio, telefono, correo);
        this.razon_social = razon_social;
        this.condicion_iva = condicion_iva;
        this.cuit = cuit;
        this.domicilio_fiscal = domicilio_fiscal;
        this.estado = estado;
    }
    
    //getters y setters

    public int getCod_cliente() {
        return cod_cliente;
    }

    public int getCod_persona() {
        return cod_persona;
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

    public boolean registrar(Cliente cliente){
        try {
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            PreparedStatement pst = cn.prepareStatement("INSERT INTO persona (nombre,documento,pais,estado_provincia,localidad,domicilio,telefono,correo) "
                    + "VALUES (?,?,?,?,?,?,?,?)");
            PreparedStatement pst2 = cn.prepareStatement("INSERT INTO cliente (cod_persona,razon_social,condicion_iva,cuit,domicilio_fiscal,estado) VALUES ((select cod_persona from persona order by cod_persona desc limit 1),"
                    + "?,?,?,?,?)");
            
            
            pst.setString(1, cliente.getNombre());
            pst.setString(2, cliente.getDocumento());
            pst.setString(3, cliente.getPais());
            pst.setString(4, cliente.getEstado_provincia());
            pst.setString(5, cliente.getLocalidad());
            pst.setString(6, cliente.getDomicilio());
            pst.setString(7, cliente.getTelefono());
            pst.setString(8, cliente.getCorreo());
            
            pst2.setString(1, cliente.getRazon_social());
            pst2.setString(2, cliente.getCondicion_iva());
            pst2.setString(3, cliente.getCuit());
            pst2.setString(4, cliente.getDomicilio_fiscal());
            pst2.setString(5, cliente.getEstado());
            
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
