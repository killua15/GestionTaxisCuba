/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

/**
 *
 * @author killua
 */
public class Duenno {
    private int id_duenno;
    private String nombre_duenno;
    private String telefonos_duenno;
    private String mail_duenno ;
    
    public Duenno(int id_duenno, String nombre_duenno, String telefonos_duenno, String mail_duenno) {
        this.id_duenno = id_duenno;
        this.nombre_duenno = nombre_duenno;
        this.telefonos_duenno = telefonos_duenno;
        this.mail_duenno = mail_duenno;
    }

    public int getId_duenno() {
        return id_duenno;
    }

    public String getNombre_duenno() {
        return nombre_duenno;
    }

    public String getTelefonos_duenno() {
        return telefonos_duenno;
    }

    public String getMail_duenno() {
        return mail_duenno;
    }

    public void setId_duenno(int id_duenno) {
        this.id_duenno = id_duenno;
    }

    public void setNombre_duenno(String nombre_duenno) {
        this.nombre_duenno = nombre_duenno;
    }

    public void setTelefonos_duenno(String telefonos_duenno) {
        this.telefonos_duenno = telefonos_duenno;
    }

    public void setMail_duenno(String mail_duenno) {
        this.mail_duenno = mail_duenno;
    }
   
}
