/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    public void InsertDataCars(int id_car_taxi,int id_own_cars_taxis, String marca_car_taxi, String chapa_car_taxi,int service_type_car_taxi,
    int motor_type_car_taxi,ArrayList<File> Fotos,int condition_air,double precio, String descripcion, String horario, int portada) throws SQLException, IOException {
        
        
        String sql = "INSERT INTO car_taxi(id_car_taxi,"
                + "marca_car_taxi,"
                + "id_own_cars_taxis,"
                + "chapa_car_taxi,"
                + "service_type_car_taxi,"
                + "motor_type_car_taxi,"
                + "foto1_car_taxi,"
                + "foto2_car_taxi,"
                + "foto3_car_taxi,"
                + "condition_air,"
                + "precio,"
                + "desc,"
                + "horario,"
                + "portada)"
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        if(id_car_taxi == -1){
            System.err.println("Problemas obteniendo Index");
        }else{
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id_car_taxi);
        pstmt.setInt(2, id_own_cars_taxis);
        pstmt.setString(3, marca_car_taxi);
        pstmt.setString(4, chapa_car_taxi);
        pstmt.setInt(5, service_type_car_taxi);
        pstmt.setInt(6, motor_type_car_taxi);
        //Fotos 3
        for(int i =0; i<Fotos.size(); i++){
          pstmt.setBytes(7+i, readFile(Fotos.get(i).getAbsolutePath()));
        }     
        pstmt.setInt(10, condition_air);
        pstmt.setDouble(11, precio);
        pstmt.setString(12, descripcion);
        pstmt.setString(13, horario);
         pstmt.setInt(14, portada);
        pstmt.executeUpdate(); 
        }
        

    }
      private byte[] readFile(String file) {
          ByteArrayOutputStream bos = null;
        try {
            File f = new File(file);
            FileInputStream fis = new FileInputStream(f);
            byte[] buffer = new byte[1024];
            bos = new ByteArrayOutputStream();
            for (int len; (len = fis.read(buffer)) != -1;) {
                bos.write(buffer, 0, len);
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e2) {
            System.err.println(e2.getMessage());
        }
        return bos != null ? bos.toByteArray() : null;
    }

    public int GetIndexDuenno() throws SQLException {
        String sql = "SELECT * FROM sqlite_sequence";
        int index=-1;
        if (conn.isClosed()) {
            System.out.println("Coneccion Cerrada");
        } else {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<Integer> enteros = new ArrayList();
            while (rs.next()) {
                if(rs.getString("name").equals("own_cars_taxis") ){
                  index = rs.getInt("seq");
                }
               
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
