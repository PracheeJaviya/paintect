/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package oopproject;

/**
 *
 * @author Panth
 */
import java.sql.*;
import javax.swing.JOptionPane;

public class DBConnect {
    public static Connection connect(){
        Connection con = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/oopproject", "root","");
            
        } catch (Exception e){
            System.out.println("inter.DBConnect.connect()");
            JOptionPane.showConfirmDialog(null, e);
        }
        return con;
    }
    public static Connection connect(String connect1){
        Connection con = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(connect1, "root","");
            
            
        } catch (Exception e){
            System.out.println("inter.DBConnect.connect()");
            JOptionPane.showConfirmDialog(null, e);
        }
        return con;
    }
}
