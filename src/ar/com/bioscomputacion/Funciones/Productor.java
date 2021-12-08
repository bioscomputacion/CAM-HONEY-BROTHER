/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Funciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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
    private int cantidad_colmenas;
    private String ubicacion_colmenas;
    private String floracion_miel;
    private String cura_miel;
    
    ConexionBD mysql = new ConexionBD();
    Connection cn = mysql.getConexionBD();


    //metodos constructores
    
    public Productor() {
    }

    public Productor(String fecha_venta_miel_1, String fecha_venta_miel_2, String fecha_venta_miel_3, String nombre_fantasia, String razon_social, String condicion_iva, String cuit, String domicilio_fiscal, String estado, Integer cantidad_colmenas, String ubicacion_colmenas, String floracion_miel, String cura_miel, String nombre, String documento, String pais, String estado_provincia, String localidad, String domicilio, String telefono, String correo) {
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
        this.cantidad_colmenas = cantidad_colmenas;
        this.ubicacion_colmenas = ubicacion_colmenas;
        this.floracion_miel = floracion_miel;
        this.cura_miel = cura_miel;
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

    public int getCantidad_colmenas() {
        return cantidad_colmenas;
    }

    public void setCantidad_colmenas(int cantidad_colmenas) {
        this.cantidad_colmenas = cantidad_colmenas;
    }

    public String getUbicacion_colmenas() {
        return ubicacion_colmenas;
    }

    public void setUbicacion_colmenas(String ubicacion_colmenas) {
        this.ubicacion_colmenas = ubicacion_colmenas;
    }

    public String getFloracion_miel() {
        return floracion_miel;
    }

    public void setFloracion_miel(String floracion_miel) {
        this.floracion_miel = floracion_miel;
    }

    public String getCura_miel() {
        return cura_miel;
    }

    public void setCura_miel(String cura_miel) {
        this.cura_miel = cura_miel;
    }
    
    
    
    public boolean registrar(Productor productor){
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("INSERT INTO persona (nombre,documento,pais,estado_provincia,localidad,domicilio,telefono,correo) "
                    + "VALUES (?,?,?,?,?,?,?,?)");
            PreparedStatement pst2 = cn.prepareStatement("INSERT INTO productor (cod_persona,fecha_venta_miel_1,fecha_venta_miel_2,fecha_venta_miel_3,nombre_fantasia,razon_social,condicion_iva,cuit,domicilio_fiscal,estado,cantidad_colmenas,ubicacion_colmenas,floracion_miel,cura_miel) VALUES ((select cod_persona from persona order by cod_persona desc limit 1),"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?)");
            
            
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
            pst2.setInt(10, productor.getCantidad_colmenas());
            pst2.setString(11, productor.getUbicacion_colmenas());
            pst2.setString(12, productor.getFloracion_miel());
            pst2.setString(13, productor.getCura_miel());
            
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

    public boolean eliminar(int codigoProductor) {

        try {

            PreparedStatement pst;
            pst = cn.prepareStatement("DELETE FROM persona WHERE cod_persona = (SELECT cod_persona FROM productor WHERE cod_productor ='"+ codigoProductor +"')");

            int N = pst.executeUpdate();

            if (N != 0) {
                
                ConexionBD.close(cn);
                ConexionBD.close(pst);
                return true;
                
            } else {
                
                ConexionBD.close(cn);
                ConexionBD.close(pst);
                return false;
                
            }
            
        } catch (SQLException ex) {
            
            ex.printStackTrace(System.out);
            
        }

        return false;
    }
    
    public boolean modificar(Productor productor) {

        try {

            PreparedStatement pst = cn.prepareStatement("UPDATE productor SET fecha_venta_miel1 = ?,fecha_venta_miel2 = ?,fecha_venta_miel3 = ?,nombre_fantasia = ?,razon_social = ?,"
                    + "condicion_iva = ?,cuit = ?,domicilio_fiscal = ?,estado = ?,cantidad_colmenas = ?,ubicacion_colmenas = ?,floracion_miel = ?,"
                    + "cura_miel = ? WHERE cod_productor = ?");
            
            PreparedStatement pst2 = cn.prepareStatement("UPDATE persona SET nombre = ?,documento = ?,"
                    + "pais = ?,estado_provincia = ?,localidad = ?,domicilio = ?,telefono = ?,correo = ? WHERE cod_persona = (SELECT cod_persona FROM productor WHERE cod_productor = ?)");

            pst.setString(1, productor.getFecha_venta_miel_1());
            pst.setString(2, productor.getFecha_venta_miel_2());
            pst.setString(3, productor.getFecha_venta_miel_3());
            pst.setString(4, productor.getNombre_fantasia());
            pst.setString(5, productor.getRazon_social());
            pst.setString(6, productor.getCondicion_iva());
            pst.setString(7, productor.getCuit());
            pst.setString(8, productor.getDomicilio_fiscal());
            pst.setString(9, productor.getEstado());
            pst.setInt(10, productor.getCantidad_colmenas());
            pst.setString(11, productor.getUbicacion_colmenas());
            pst.setString(12, productor.getFloracion_miel());
            pst.setString(13, productor.getCura_miel());
            pst.setInt(14, productor.getCod_productor());
            
            pst2.setString(1, productor.getNombre());
            pst2.setString(2, productor.getDocumento());
            pst2.setString(3, productor.getPais());
            pst2.setString(4, productor.getEstado_provincia());
            pst2.setString(5, productor.getLocalidad());
            pst2.setString(6, productor.getDomicilio());
            pst2.setString(7, productor.getTelefono());
            pst2.setString(7, productor.getCorreo());
            pst2.setInt(7, productor.getCod_productor());
            
            int N = pst.executeUpdate();
            
            int N2 = pst2.executeUpdate();

            if (N != 0 || N2 != 0) {
                
                ConexionBD.close(cn);
                ConexionBD.close(pst);
                ConexionBD.close(pst2);
                return true;
                
            } else {
                
                ConexionBD.close(cn);
                ConexionBD.close(pst);
                ConexionBD.close(pst2);
                return false;
                
            }
            
        } catch (SQLException ex) {
            
            ex.printStackTrace(System.out);
            
        }

        return false;
    }

    public DefaultTableModel listarProductores(String buscar) {

        DefaultTableModel modelo;

        String[] titulos = {"ID", "NOMBRE", "DOCUMENTO", "NACIONALIDAD", "PROVINCIA", "LOCALIDAD", "DOMICILIO", "TELEFONO", "CORREO", "FECHA VENTA MIEL 1", "FECHA VENTA MIEL 2", "FECHA VENTA MIEL 3", "NOMBRE FANTASIA", "RAZON SOCIAL", "CONDICION IVA", "CUIT", "DOMICIlIO FISCAL", "ESTADO", "CANTIDAD COLMENAS", "UBICACION COLMENAS", "FLORACION MIEL", "CURA MIEL"};

        String[] registros = new String[21];

        modelo = new DefaultTableModel(null, titulos) {
            
            @Override
            public boolean isCellEditable(int filas, int columnas) {
                if (columnas == 21) {
                    return true;
                } else {
                    return false;
                }

            }

        };
        
        try {
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT p.cod_productor, q.nombre, q.documento, q.pais, q.estado_provincia, q.localidad, q.domicilio, q.telefono, q.correo, p.fecha_venta_miel_1, p.fecha_venta_miel_2, p.fecha_venta_miel_3, p.nombre_fantasia, p.razon_social, p.condicion_iva, p.cuit, p.domicilio_fiscal, p.estado, p.cantidad_colmenas, p.ubicacion_colmenas, p.floracion_miel, p.cura_miel FROM productor p join persona q on p.cod_persona = q.cod_persona WHERE q.nombre LIKE '%" + buscar + "%' ORDER BY p.cod_productor ASC");

            System.out.println("pasa por acaaaaaaaaa");
            
            while (rs.next()) {
                
                registros[0] = rs.getString("cod_productor");
                registros[1] = rs.getString("nombre");
                registros[2] = rs.getString("documento");
                registros[3] = rs.getString("pais");
                registros[4] = rs.getString("estado_provincia");
                registros[5] = rs.getString("localidad");
                registros[6] = rs.getString("domicilio");
                registros[7] = rs.getString("telefono");
                registros[8] = rs.getString("correo");
                registros[9] = rs.getString("fecha_venta_miel_1");
                registros[10] = rs.getString("fecha_venta_miel_2");
                registros[11] = rs.getString("fecha_venta_miel_3");
                registros[12] = rs.getString("nombre_fantasia");
                registros[13] = rs.getString("razon_social");
                registros[14] = rs.getString("condicion_iva");
                registros[15] = rs.getString("cuit");
                registros[16] = rs.getString("domicilio_fiscal");
                registros[17] = rs.getString("estado");
                registros[18] = rs.getString("cantidad_colmenas");
                registros[19] = rs.getString("ubicacion_colmenas");
                registros[20] = rs.getString("floracion_miel");
                //registros[21] = rs.getString("cura_miel");

                modelo.addRow(registros);
                
            }
            
            System.out.println("pasa por aca tambien");
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
        }
        
        return modelo;
        
    }
    
    
}
