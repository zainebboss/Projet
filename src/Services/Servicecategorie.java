/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.Catégorie;
import Utils.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author user
 */
public class Servicecategorie {
     private Connection con;
    private PreparedStatement stat;
    private ResultSet rs;
    int i=0;
int getselectedcat;

    public Servicecategorie() {
        con=Connexion.getInstance().getConnection();
    }
     
    
    public void ajouter(Catégorie C){
       try {
         stat=con.prepareStatement("INSERT INTO categorie(Description) VALUES (?)");
         stat.setString(1,C.getDescription());
         
        
        
         stat.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
     public void modifier(Catégorie C){
        try {
            stat=con.prepareStatement("UPDATE categorie SET Description=? WHERE id_catégorie=? ");
            stat.setString(1, C.getDescription());
            stat.setInt(2, C.getId_catégoerie());
            stat.execute();
        } catch (SQLException ex) {
           Logger.getLogger(ServiceCour.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
       public void modifer(Catégorie C, int Id_catégoerie) {
        try {
            String requete = "UPDATE categorie SET Description=? WHERE id_catégorie=?";
            PreparedStatement pst = con.prepareStatement(requete);
            stat.setString(1, C.getDescription());
            stat.setInt(2, C.getId_catégoerie());
            System.out.println(pst);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        
      public boolean supprimer(Catégorie C){
       boolean etat = false;
       try {
            String Req2 = "select id_catégorie from categorie where Description='"+C.getDescription()+"'";
            Statement Ps2 = con.createStatement();
            ResultSet Rs =  Ps2.executeQuery(Req2);
            String requete = "DELETE FROM categorie WHERE id_catégorie=?";
            PreparedStatement pst = con.prepareStatement(requete);
             while(Rs.next()){  
            pst.setInt(1,Rs.getInt(1));
            i= pst.executeUpdate();
             }
           if(i>0){
            System.out.println("categorie supprimé");
            etat=true;
           }else{System.out.println("Not deleted");}
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
            
        }
        return etat;
     }

    
 
 
public ArrayList<Catégorie> afficher(){
    
        ArrayList<Catégorie> list = new ArrayList<>();
        try {
           stat = con.prepareStatement("SELECT * FROM `categorie`");
           
           rs = stat.executeQuery();
            while(rs.next()){
               Catégorie C = new Catégorie();
               C.setId_catégoerie(rs.getInt("id_catégorie"));
                C.setDescription(rs.getString("Description"));
               
                list.add(C);
            }
            rs.close();
            stat.close();
            
        } catch (SQLException ex) {
        }
        
       
        return list;
    }
   
public ObservableList<String> afficher_Categorie(){
    ObservableList<String> list = FXCollections.observableArrayList();

        String requete = "SELECT * FROM `categorie`";
        try {
            PreparedStatement ps = con.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
              System.out.println( "q" + rs.getString("Description"));
                list.add( rs.getString("Description"));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    
      
    } 
public ArrayList<Catégorie> afficher(int id){
    
        ArrayList<Catégorie> list = new ArrayList<>();
        try {
            
           stat = con.prepareStatement("SELECT * FROM `categorie` where id_categorie?");
          
         stat.setInt(1,id);
           
           rs = stat.executeQuery();
            while(rs.next()){
               Catégorie C = new Catégorie();
               C.setId_catégoerie(rs.getInt("id_catégorie"));
                C.setDescription(rs.getString("Description"));
               
                list.add(C);
            }
            rs.close();
            stat.close();
            
        } catch (SQLException ex) {
        }
        
       
        return list;
        
        
        
        
        
    }  














    
}
