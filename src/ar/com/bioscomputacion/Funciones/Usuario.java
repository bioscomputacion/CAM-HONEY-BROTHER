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
 * @author braya
 */
public class Usuario extends Persona {
    
    private int cod_usuario;
    private String login;
    private String password;
    private String tipo;
    private String estado;
    
    

    public Usuario() {
    }

    public Usuario(String login, String password, String tipo, String estado, String nombre, String documento, String pais, String estado_provincia, String localidad, String domicilio, String telefono, String correo) {
        super(nombre, documento, pais, estado_provincia, localidad, domicilio, telefono, correo);
        this.login = login;
        this.password = password;
        this.tipo= tipo;
        this.estado = estado;
    }

    public int getCod_usuario() {
        return cod_usuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuario{usuario=").append(login);
        sb.append(", contrase\u00f1a=").append(password);
        sb.append(", Persona=").append(super.toString());
        sb.append('}');
        return sb.toString();
    }
    
    
    public boolean registrar(Usuario usuario){
        try {
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            PreparedStatement pst = cn.prepareStatement("INSERT INTO persona (nombre,documento,domicilio,telefono,correo) "
                    + "VALUES (?,?,?,?,?)");
            PreparedStatement pst2 = cn.prepareStatement("INSERT INTO usuario (cod_persona,login,password,tipo,estado) VALUES ((select cod_persona from persona order by cod_persona desc limit 1),"
                    + "?,?,?,?)");
            
            
            pst.setString(1, usuario.getNombre());
            pst.setString(2, usuario.getDocumento());
            pst.setString(3, usuario.getDomicilio());
            pst.setString(4, usuario.getTelefono());
            pst.setString(5, usuario.getCorreo());
            
            pst2.setString(1, usuario.getLogin());
            pst2.setString(2, usuario.getPassword());
            pst2.setString(3, usuario.getTipo());
            pst2.setString(4, usuario.getEstado());
            
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
