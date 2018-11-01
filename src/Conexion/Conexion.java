/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author killua
 */
public class Conexion {

    private Connection conn;

    public Conexion() {
        conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:/home/killua/NetBeansProjects/GestionTaxisCuba/taxis_cars";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
  

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void InsertDataDuenno(int id_duenno, String name_duenno, String phones_duenno,
            String mail_duenno) throws SQLException {
        String sql = "INSERT INTO own_cars_taxis(id_own_cars_taxis,"
                + "name_own_cars_taxis,"
                + "phones_own_cars_taxis,"
                + "mail_own_cars_taxis) VALUES(?,?,?,?)";
        if(id_duenno == -1){
            System.err.println("Problemas obteniendo Index");
        }else{
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id_duenno);
        pstmt.setString(2, name_duenno);
        pstmt.setString(3, phones_duenno);
        pstmt.setString(4, mail_duenno);
        pstmt.executeUpdate();
        }
        

    }
    public void InsertDataCars(int id_car_taxi, String marca_car_taxi, 
             String chapa_car_taxi,int service_type_car_taxi,
     int motor_type_car_taxi,ArrayList<File> Fotos,int condition_air,
     double precio, String descripcion, String horario) throws SQLException {
        
        
        String sql = "INSERT INTO car_taxi(id_car_taxi,"
                + "marca_car_taxi,"
                + "chapa_car_taxi,"
                + "service_type_car_taxi,"
                + "motor_type_car_taxi,"
                + "foto1_car_taxi,"
                + "foto2_car_taxi,"
                + "foto3_car_taxi,"
                + "condition_air,"
                + "precio,"
                + "desc,"
                + "horario)"
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        if(id_car_taxi == -1){
            System.err.println("Problemas obteniendo Index");
        }else{
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id_car_taxi);
        pstmt.setString(2, marca_car_taxi);
        pstmt.setString(3, chapa_car_taxi);
        pstmt.setInt(4, service_type_car_taxi);
        pstmt.setInt(5, motor_type_car_taxi);
        //Fotos 3
        pstmt.setInt(9, condition_air);
        pstmt.setDouble(10, precio);
        pstmt.setString(11, descripcion);
        pstmt.setString(12, horario);
        pstmt.executeUpdate();
        }
        

    }

    public int GetIndexDuenno() throws SQLException {
        String sql = "SELECT * FROM own_cars_taxis";
        int index=-1;
        if (conn.isClosed()) {
            System.out.println("Coneccion Cerrada");
        } else {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<Integer> enteros = new ArrayList();
            while (rs.next()) {
                enteros.add(rs.getInt("id_own_cars_taxis"));
            }
            if (enteros.size() == 0) {
                index= 0;
            } else {
                index = enteros.get(0);
            }
        }
        return index;

    }
    public ArrayList<Duenno> GetDuennos(int filtro) throws SQLException {
        String sql = "SELECT * FROM own_cars_taxis";
        ArrayList<Duenno> duennos = new ArrayList();
        if (conn.isClosed()) {
            System.out.println("Coneccion Cerrada");
        } else {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<Integer> enteros = new ArrayList();
            while (rs.next()) {
                int id = rs.getInt("id_own_cars_taxis");
                String nombre =  rs.getString("name_own_cars_taxis");
                 String telefonos =  rs.getString("phones_own_cars_taxis");
                  String mail =  rs.getString("mail_own_cars_taxis");
                  Duenno d = new Duenno(id, nombre, telefonos, mail);
                  duennos.add(d);
            }
           
        }
        return duennos;

    }
     public int GetIndexCars() throws SQLException {
        String sql = "SELECT * FROM car_taxi";
        int index=-1;
        if (conn.isClosed()) {
            System.out.println("Coneccion Cerrada");
        } else {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<Integer> enteros = new ArrayList();
            while (rs.next()) {
                enteros.add(rs.getInt("id_car_taxi"));
            }
            if (enteros.size() == 0) {
                index= 0;
            } else {
                index = enteros.get(0);
            }
        }
        return index;

    }
}
