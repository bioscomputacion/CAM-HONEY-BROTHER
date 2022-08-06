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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Caco
 */
public class Cliente extends Persona {

    private int cod_cliente;
    private int cod_persona; 
    private String nombre_fantasia;
    private String razon_social;
    private String condicion_iva;
    private String cuit;
    private String domicilio_fiscal;
    private String estado;
    private String categoria;

    //metodos constructores
    
    public Cliente() {
    }

    public Cliente(String nombre_fantasia, String razon_social, String condicion_iva, String cuit, String domicilio_fiscal, String estado, String categoria, String nombre, String documento, String pais, String estado_provincia, String localidad, String domicilio, String telefono, String correo) {
        super(nombre, documento, pais, estado_provincia, localidad, domicilio, telefono, correo);
        
        this.nombre_fantasia = nombre_fantasia;
        this.razon_social = razon_social;
        this.condicion_iva = condicion_iva;
        this.cuit = cuit;
        this.domicilio_fiscal = domicilio_fiscal;
        this.estado = estado;
        this.categoria = categoria;
        
    }
    
    //getters y setters

    public int getCod_cliente() {
        return cod_cliente;
    }

    public void setCod_cliente(int cod_cliente) {
        this.cod_cliente = cod_cliente;
    }

    public int getCod_persona() {
        return cod_persona;
    }

    public void setCod_persona(int cod_persona) {
        this.cod_persona = cod_persona;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }


    
    public boolean registrar(Cliente cliente){
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            PreparedStatement pst = cn.prepareStatement("INSERT INTO persona (nombre,documento,pais,estado_provincia,localidad,domicilio,telefono,correo) "
                    + "VALUES (?,?,?,?,?,?,?,?)");
            PreparedStatement pst2 = cn.prepareStatement("INSERT INTO cliente (cod_persona,nombre_fantasia,razon_social,condicion_iva,cuit,domicilio_fiscal,estado,categoria) VALUES ((select cod_persona from persona order by cod_persona desc limit 1),"
                    + "?,?,?,?,?,?,?)");
            
            
            pst.setString(1, cliente.getNombre());
            pst.setString(2, cliente.getDocumento());
            pst.setString(3, cliente.getPais());
            pst.setString(4, cliente.getEstado_provincia());
            pst.setString(5, cliente.getLocalidad());
            pst.setString(6, cliente.getDomicilio());
            pst.setString(7, cliente.getTelefono());
            pst.setString(8, cliente.getCorreo());
            
            pst2.setString(1, cliente.getNombre_fantasia());
            pst2.setString(2, cliente.getRazon_social());
            pst2.setString(3, cliente.getCondicion_iva());
            pst2.setString(4, cliente.getCuit());
            pst2.setString(5, cliente.getDomicilio_fiscal());
            pst2.setString(6, cliente.getEstado());
            pst2.setString(7, cliente.getCategoria());
            
            int N = pst.executeUpdate();
            int N2 = pst2.executeUpdate();

            ConexionBD.close(cn);
            ConexionBD.close(pst);
            ConexionBD.close(pst2);

            if (N != 0 || N2 != 0) {
                
                return true;
                
            } else {
                
                return false;
                
            }
            
        } catch (Exception e) {
        }
        return false;
        
    }

    public boolean eliminar(int codigoCliente) {

        try {

            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            PreparedStatement pst;
            pst = cn.prepareStatement("DELETE FROM persona WHERE cod_persona = (SELECT cod_persona FROM cliente WHERE cod_cliente ='"+ codigoCliente +"')");

            int N = pst.executeUpdate();
            
            ConexionBD.close(cn);
            ConexionBD.close(pst);

            if (N != 0) {
                
                return true;
                
            } else {
                
                return false;
                
            }
            
        } catch (SQLException ex) {
            
            ex.printStackTrace(System.out);
            
        }

        return false;
    }
    
    public boolean modificar(Cliente cliente, int codigoCliente) {

        try {

            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            PreparedStatement pst = cn.prepareStatement("UPDATE cliente SET nombre_fantasia = ?, razon_social = ?,"
                    + "condicion_iva = ?, cuit = ?, domicilio_fiscal = ?, estado = ?, categoria = ? WHERE cod_cliente = ?");
            
            PreparedStatement pst2 = cn.prepareStatement("UPDATE persona SET nombre = ?,documento = ?,"
                    + "pais = ?,estado_provincia = ?,localidad = ?,domicilio = ?,telefono = ?,correo = ? WHERE cod_persona = (SELECT cod_persona FROM cliente WHERE cod_cliente = ?)");

            pst.setString(1, cliente.getNombre_fantasia());
            pst.setString(2, cliente.getRazon_social());
            pst.setString(3, cliente.getCondicion_iva());
            pst.setString(4, cliente.getCuit());
            pst.setString(5, cliente.getDomicilio_fiscal());
            pst.setString(6, cliente.getEstado());
            pst.setString(7, cliente.getCategoria());
            pst.setInt(8, codigoCliente);
            
            pst2.setString(1, cliente.getNombre());
            pst2.setString(2, cliente.getDocumento());
            pst2.setString(3, cliente.getPais());
            pst2.setString(4, cliente.getEstado_provincia());
            pst2.setString(5, cliente.getLocalidad());
            pst2.setString(6, cliente.getDomicilio());
            pst2.setString(7, cliente.getTelefono());
            pst2.setString(8, cliente.getCorreo());
            pst2.setInt(9, codigoCliente);
            
            int N = pst.executeUpdate();
            
            int N2 = pst2.executeUpdate();

            ConexionBD.close(cn);
            ConexionBD.close(pst);
            ConexionBD.close(pst2);
            if (N != 0 || N2 != 0) {
                
                return true;
                
            } else {
                
                return false;
                
            }
            
        } catch (SQLException ex) {
            
            ex.printStackTrace(System.out);
            
        }

        return false;
    }

    public DefaultTableModel listarClientes(String buscar, String filtro) {

        DefaultTableModel modelo;

        String[] titulos = {"ID CLIENTE", "NOMBRE", "DOCUMENTO", "NACIONALIDAD", "PROVINCIA", "LOCALIDAD", "DOMICILIO", "TELEFONO", "CORREO", "NOMBRE FANTASIA","RAZON SOCIAL", "CONDICION IVA", "CUIT", "DOMICIlIO FISCAL", "ESTADO", "CATEGORIA"};

        String[] registros = new String[16];

        modelo = new DefaultTableModel(null, titulos) {
            
            /*@Override
            public boolean isCellEditable(int filas, int columnas) {
                if (columnas == 21) {
                    return true;
                } else {
                    return false;
                }

            }*/

        };
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = null;
            
            switch (filtro){
                
                case "TODOS":
                    
                    rs = st.executeQuery("SELECT c.cod_cliente, q.nombre, q.documento, q.pais, q.estado_provincia, q.localidad, q.domicilio, q.telefono, q.correo, c.nombre_fantasia, c.razon_social, c.condicion_iva, c.cuit, c.domicilio_fiscal, c.estado, c.categoria FROM cliente c join persona q on c.cod_persona = q.cod_persona WHERE c.cod_cliente <> '1' and q.nombre LIKE '%" + buscar + "%' ORDER BY c.cod_cliente ASC");
                    break;
                    
                case "STANDARDS":

                    rs = st.executeQuery("SELECT c.cod_cliente, q.nombre, q.documento, q.pais, q.estado_provincia, q.localidad, q.domicilio, q.telefono, q.correo, c.nombre_fantasia, c.razon_social, c.condicion_iva, c.cuit, c.domicilio_fiscal, c.estado, c.categoria FROM cliente c join persona q on c.cod_persona = q.cod_persona WHERE c.cod_cliente <> '1' and c.categoria = 'CLIENTE STANDARD'  and q.nombre LIKE '%" + buscar + "%' ORDER BY c.cod_cliente ASC");
                    break;
                    
                case "EXPORTADORES":

                    rs = st.executeQuery("SELECT c.cod_cliente, q.nombre, q.documento, q.pais, q.estado_provincia, q.localidad, q.domicilio, q.telefono, q.correo, c.nombre_fantasia, c.razon_social, c.condicion_iva, c.cuit, c.domicilio_fiscal, c.estado, c.categoria FROM cliente c join persona q on c.cod_persona = q.cod_persona WHERE c.cod_cliente <> '1' and c.categoria = 'EXPORTADOR INTERNO' and q.nombre LIKE '%" + buscar + "%' ORDER BY c.cod_cliente ASC");
                    break;
                    
            }
                    

            while (rs.next()) {
                
                registros[0] = rs.getString("cod_cliente");
                registros[1] = rs.getString("nombre");
                registros[2] = rs.getString("documento");
                registros[3] = rs.getString("pais");
                registros[4] = rs.getString("estado_provincia");
                registros[5] = rs.getString("localidad");
                registros[6] = rs.getString("domicilio");
                registros[7] = rs.getString("telefono");
                registros[8] = rs.getString("correo");
                registros[9] = rs.getString("nombre_fantasia");
                registros[10] = rs.getString("razon_social");
                registros[11] = rs.getString("condicion_iva");
                registros[12] = rs.getString("cuit");
                registros[13] = rs.getString("domicilio_fiscal");
                registros[14] = rs.getString("estado");
                registros[15] = rs.getString("categoria");

                modelo.addRow(registros);
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
        }
        
        return modelo;
        
    }
    
    public DefaultTableModel listarExportadoresInternos(String buscar) {

        DefaultTableModel modelo;

        String[] titulos = {"ID", "NOMBRE", "DOCUMENTO", "NACIONALIDAD", "PROVINCIA", "LOCALIDAD", "DOMICILIO", "TELEFONO", "CORREO", "NOMBRE FANTASIA","RAZON SOCIAL", "CONDICION IVA", "CUIT", "DOMICIlIO FISCAL", "ESTADO", "CATEGORIA"};

        String[] registros = new String[16];

        modelo = new DefaultTableModel(null, titulos) {
            
            /*@Override
            public boolean isCellEditable(int filas, int columnas) {
                if (columnas == 21) {
                    return true;
                } else {
                    return false;
                }

            }*/

        };
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT c.cod_cliente, q.nombre, q.documento, q.pais, q.estado_provincia, q.localidad, q.domicilio, q.telefono, q.correo, c.nombre_fantasia, c.razon_social, c.condicion_iva, c.cuit, c.domicilio_fiscal, c.estado, c.categoria FROM cliente c join persona q on c.cod_persona = q.cod_persona WHERE c.cod_cliente <> '1' and c.categoria = 'EXPORTADOR INTERNO' and q.nombre LIKE '%" + buscar + "%' ORDER BY c.cod_cliente ASC");

            while (rs.next()) {
                
                registros[0] = rs.getString("cod_cliente");
                registros[1] = rs.getString("nombre");
                registros[2] = rs.getString("documento");
                registros[3] = rs.getString("pais");
                registros[4] = rs.getString("estado_provincia");
                registros[5] = rs.getString("localidad");
                registros[6] = rs.getString("domicilio");
                registros[7] = rs.getString("telefono");
                registros[8] = rs.getString("correo");
                registros[9] = rs.getString("nombre_fantasia");
                registros[10] = rs.getString("razon_social");
                registros[11] = rs.getString("condicion_iva");
                registros[12] = rs.getString("cuit");
                registros[13] = rs.getString("domicilio_fiscal");
                registros[14] = rs.getString("estado");
                registros[15] = rs.getString("categoria");

                modelo.addRow(registros);
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
        }
        
        return modelo;
        
    }

}
