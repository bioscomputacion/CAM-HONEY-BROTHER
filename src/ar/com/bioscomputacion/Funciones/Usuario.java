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
    
    private String usuario;
    private String contraseña;
    private String estado;
    private String acceso;
    
    

    public Usuario() {
    }

    public Usuario(String usuario, String contraseña, String estado, String acceso, String nombre, String direccion, String telefono, String email, String dni) {
        super(nombre, direccion, telefono, email, dni);
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.estado = estado;
        this.acceso = acceso;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getAcceso() {
        return acceso;
    }

    public void setAcceso(String acceso) {
        this.acceso = acceso;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuario{usuario=").append(usuario);
        sb.append(", contrase\u00f1a=").append(contraseña);
        sb.append(", Persona=").append(super.toString());
        sb.append('}');
        return sb.toString();
    }
    
    
    public boolean registrar(Usuario usuario){
        try {
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            PreparedStatement pst = cn.prepareStatement("INSERT INTO persona (nombre,num_documento,direccion,telefono,email) "
                    + "VALUES (?,?,?,?,?)");
            PreparedStatement pst2 = cn.prepareStatement("INSERT INTO usuario (cod_persona,login,password,tipo,estado) VALUES ((select cod_persona from persona order by cod_persona desc limit 1),"
                    + "?,?,?,?)");
            
            
            pst.setString(1, usuario.getNombre());
            pst.setString(2, usuario.getDni());
            pst.setString(3, usuario.getDireccion());
            pst.setString(4, usuario.getTelefono());
            pst.setString(5, usuario.getEmail());
            
            pst2.setString(1, usuario.getUsuario());
            pst2.setString(2, usuario.getContraseña());
            pst2.setString(3, usuario.getAcceso());
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
