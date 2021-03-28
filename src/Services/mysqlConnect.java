/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import Entites.sparkAide;
import Entites.sparkAideCom;


/**
 *
 * @author nidha
 */
public class mysqlConnect {
    
Connection con=null;

public static Connection Connectdb(){
    try{
       // Class.forName("com.mysql.jdbc.Driver") ;
        Connection con =DriverManager.getConnection("jdbc:mysql://localhost/eformation","root","");
       // JOptionPane.showMessageDialog(null,"ConnectionEstablished");
        return con ;
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
        return null ;
    }
    
}

public static ObservableList<sparkAide> getDatasparkAide(){
    
    Connection con =Connectdb();
    ObservableList<sparkAide> list =FXCollections.observableArrayList();

    try{
        PreparedStatement ps=con.prepareStatement("select * from aide");
    ResultSet rs=ps.executeQuery();
    while (rs.next()){
        list.add(new sparkAide (rs.getInt("id"),rs.getString("sujet"),rs.getString("probleme"),rs.getString("mail") )); 
    }
    }catch(Exception e){
        
    }
    return list ;
}
public static ObservableList<sparkAideCom> getDatasparkAideCom(int id_sujet){
    
    Connection con =Connectdb();
    ObservableList<sparkAideCom> list =FXCollections.observableArrayList();

    try{
        PreparedStatement ps=con.prepareStatement("select * from aidecom where id_sujet=?  ");
           ps.setInt(1,id_sujet);

    ResultSet rs=ps.executeQuery();
    while (rs.next()){
        list.add(new sparkAideCom (rs.getInt("id"),rs.getInt("id_sujet"),rs.getString("commaintre")  )); 
    }
    }catch(Exception e){
        
    }
    return list ;
}
}

