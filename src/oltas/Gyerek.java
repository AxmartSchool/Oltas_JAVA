/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oltas;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.beans.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Axmart
 */
public class Gyerek {

    static ArrayList<Gyerek> Beolvasas() {
        
        String host = "jdbc:mysql://localhost:3306/oltas";  // Kell a Liberyhez a mysql connector
        String userName = "root";
        String password = "";
        ArrayList<Gyerek> output = new ArrayList<>();
        
        try {
            Connection conn = DriverManager.getConnection(host, userName, password);
            java.sql.Statement sta= conn.createStatement();
            ResultSet rs = sta.executeQuery("SELECT * FROM vizsga_gyerek");
            while (rs.next()) {                
                output.add(new Gyerek(rs.getInt("id"), rs.getString("nev"), rs.getString("apja"), rs.getString("anyja"),rs.getString("tajszam"), rs.getDate("szuletett")));
            }
            sta.close();
            conn.close();
            
            
        } catch (Exception e) {
            Logger.getLogger(Gyerek.class.getName()).log(Level.SEVERE, e.toString(),"Nem sikerult a betoltes");
        }
        
            return output;
        
    }

    static void Modositas(Gyerek modositando) {
        
        String host = "jdbc:mysql://localhost:3306/oltas";  // Kell a Liberyhez a mysql connector
        String userName = "root";
        String password = "";
        String sql = "";
        
        try {
            Connection conn = DriverManager.getConnection(host, userName, password);
            java.sql.Statement sta= conn.createStatement();
            sql = "update vizsga_gyerek set nev = '"+modositando.getNev()+"', apja = '"+modositando.getApja()+"', anyja = '"+modositando.getAnyja()+"' where id = "+modositando.getId();
            //Logger.getLogger(Gyerek.class.getName()).log(Level.SEVERE, sql);
            sta.executeUpdate(sql);
            sta.close();
            conn.close();
            
            
        } catch (Exception e) {
            Logger.getLogger(Gyerek.class.getName()).log(Level.SEVERE, e.toString(),"Nem sikerult a modositas");
        }
        
        
        
    }

    static void Rogzites(Gyerek ujGyerek) {
        
        String host = "jdbc:mysql://localhost:3306/oltas";  // Kell a Liberyhez a mysql connector
        String userName = "root";
        String password = "";
        String sql = "";
        
        try {
            Connection conn = DriverManager.getConnection(host, userName, password);
            java.sql.Statement sta= conn.createStatement();
            sql = "insert into vizsga_gyerek(nev,apja,anyja,tajszam,szuletett) values ('"+ujGyerek.getNev()+"', '"+ujGyerek.getApja()+"','"+ujGyerek.getAnyja()+"','"+ujGyerek.getTajszam()+"' ,'"+ujGyerek.getSzuletett()+"')" ;
            //Logger.getLogger(Gyerek.class.getName()).log(Level.SEVERE, sql);
            sta.executeUpdate(sql);
            sta.close();
            conn.close();
            
            
        } catch (Exception e) {
            Logger.getLogger(Oltas.class.getName()).log(Level.SEVERE, e.toString(),"Nem sikerult a betoltes");
        }
    }
    
    private int id ;
    private String nev;
    private String apja;
    private String anyja;
    private String tajszam;
    private Date szuletett;


    public Gyerek(int id, String nev, String apja, String anyja, String tajszam, Date szuletett) {
        this.id = id;
        this.nev = nev;
        this.apja = apja;
        this.anyja = anyja;
        this.tajszam = tajszam;
        this.szuletett = szuletett;
    }

    public int getId() {
        return id;
    }
    public String getNev() {
        return nev;
    }

    public String getApja() {
        return apja;
    }

    public String getAnyja() {
        return anyja;
    }

    public String getTajszam() {
        return tajszam;
    }

    public Date getSzuletett() {
        return szuletett;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public void setApja(String apja) {
        this.apja = apja;
    }

    public void setAnyja(String anyja) {
        this.anyja = anyja;
    }

    public void setTajszam(String tajszam) {
        this.tajszam = tajszam;
    }

    public void setSzuletett(Date szuletett) {
        this.szuletett = szuletett;
    }
    
    
}
