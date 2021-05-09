/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oltas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Axmart
 */
public class Oltas {
    
    private int id; 
    private int gyerekId;
    private String elnevezes;
    private int fizetendo;

    public Oltas(int id, int gyerekId, String elnevezes, int fizetendo) {
        this.id = id;
        this.gyerekId = gyerekId;
        this.elnevezes = elnevezes;
        this.fizetendo = fizetendo;
    }

    
    
    public int getId() {
        return id;
    }

    public int getGyerekId() {
        return gyerekId;
    }

    public String getElnevezes() {
        return elnevezes;
    }

    public int getFizetendo() {
        return fizetendo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGyerekId(int gyerekId) {
        this.gyerekId = gyerekId;
    }

    public void setElnevezes(String elnevezes) {
        this.elnevezes = elnevezes;
    }

    public void setFizetendo(int fizetendo) {
        this.fizetendo = fizetendo;
    }
    
    
        public static ArrayList<Oltas> Beolvasas(int gyerekId) {
        
        String host = "jdbc:mysql://localhost:3306/oltas";  // Kell a Liberyhez a mysql connector
        String userName = "root";
        String password = "";
        ArrayList<Oltas> output = new ArrayList<>();
        
        try {
            Connection conn = DriverManager.getConnection(host, userName, password);
            java.sql.Statement sta= conn.createStatement();
            ResultSet rs = sta.executeQuery("SELECT * FROM vizsga_oltas where gyerekID = "+gyerekId);
            while (rs.next()) {                
                output.add(new Oltas(rs.getInt("id"),rs.getInt("gyerekID"), rs.getString("elnevezes"), rs.getInt("fizetendo")));
            }
            sta.close();
            conn.close();
            
            
        } catch (Exception e) {
            Logger.getLogger(Oltas.class.getName()).log(Level.SEVERE, e.toString(),"Nem sikerult a betoltes");
        }
        
            return output;
        
    }
    
    
}
