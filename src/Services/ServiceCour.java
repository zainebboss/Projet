/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.Cour;
import Utils.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author user
 */
public class ServiceCour {
    
     private Connection con;
    private PreparedStatement stat;
    private ResultSet rs;
    int i=0;

    public ServiceCour() {
        con=Connexion.getInstance().getConnection();
    }
     
    
    public void ajouter(Cour C){
        try {
         stat=con.prepareStatement("INSERT INTO cours(titre,fichier,formation_id,Description_cat) VALUES (?,?,?,?)");
         stat.setString(1,C.getTitre());
         stat.setString(2,C.getFichier());
        stat.setInt(3,C.getFormation_id());
            stat.setString(4,C.getDescription_cat()); 
        
         stat.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
     public void modifier(Cour C){
        try {
            stat=con.prepareStatement("UPDATE cours SET titre=?, fichier=? WHERE id=? ");
            stat.setString(1, C.getTitre());
       
            stat.setString(2,C.getFichier());
            stat.setInt(3, C.getID());
            stat.execute();
        } catch (SQLException ex) {
           Logger.getLogger(ServiceCour.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
       public void modifer(Cour C, int id) {
        try {
            String requete = "UPDATE cours SET titre=?, fichier =?  WHERE id=?";
            PreparedStatement pst = con.prepareStatement(requete);
            stat.setString(1, C.getTitre());
            stat.setString(2, C.getFichier());
            stat.setInt(3, C.getID());
            System.out.println(pst);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        
      public boolean supprimer(Cour C){
       boolean etat = false;
       try {
            String Req2 = "select id from cours where titre='"+C.getTitre()+"'and fichier= '"+C.getFichier()+"'";
            Statement Ps2 = con.createStatement();
            ResultSet Rs =  Ps2.executeQuery(Req2);
            String requete = "DELETE FROM cours WHERE id=?";
            PreparedStatement pst = con.prepareStatement(requete);
             while(Rs.next()){  
            pst.setInt(1,Rs.getInt(1));
            i= pst.executeUpdate();
             }
           if(i>0){
            System.out.println("cour supprimé");
            etat=true;
           }else{System.out.println("Not deleted");}
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
            
        }
        return etat;
     }

   public ArrayList<Cour> afficher(){
    
        ArrayList<Cour> list = new ArrayList<>();
        try {
           stat = con.prepareStatement("SELECT * FROM cours");
           
           rs = stat.executeQuery();
            while(rs.next()){
               Cour C= new Cour();
               C.setID(rs.getInt("id"));
                C.setTitre(rs.getString("titre"));
                C.setFichier(rs.getString("fichier"));
                C.setDescription_cat(rs.getString("Description_cat"));
           
               
                list.add(C);
            }
            rs.close();
            stat.close();
            
        } catch (SQLException ex) {
        }
        
       
        return list;
    }
    
 
 
public ObservableList<String> afficher_Formation(){
    ObservableList<String> list = FXCollections.observableArrayList();

        String requete = "SELECT * FROM `formation`";
        try {
            PreparedStatement ps = con.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
              System.out.println( "q" + rs.getString("titre"));
                list.add( rs.getString("titre"));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    
      
    }
    
    /**
     *
     * @param Service
     * @return
     */
    public int CountService(String Service) {
        int i=0;
        try {
            PreparedStatement stat;
            String query = "select * from cours where titre='"+Service+"'";
            stat=con.prepareStatement(query);
            ResultSet rs = stat.executeQuery();
            while(rs.next()){
                i+=1;
            }
        }
         catch (SQLException ex) {
            System.out.println("Erreur " + ex.getMessage());
        }  
        return i;
    }
    
   public int return_id_formation_par_nom(String cas) {
        int i=0;
        try {
            PreparedStatement stat;
                        //String requete = "select * from formation where  Titre LIKE '%" + cas + "%'";
            String query = "select * from formation where  Titre LIKE '%" + cas + "%'";
            stat=con.prepareStatement(query);
            ResultSet rs = stat.executeQuery();
            while(rs.next()){
               i=rs.getInt("id");
            }
            System.out.println(i);
        }
         catch (SQLException ex) {
            System.out.println("Erreur " + ex.getMessage());
        }  
        return i;
    }
    public ObservableList<Cour> serach(String cas) throws SQLException {
                            ObservableList<Cour> list = FXCollections.observableArrayList();
int id=return_id_formation_par_nom(cas);
            String requete = "SELECT * FROM `cours` WHERE formation_id = " + id ;
       
            
            
            try {
            PreparedStatement ps = con.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
     Cour C= new Cour();
               C.setID(rs.getInt("id"));
                C.setTitre(rs.getString("titre"));
                C.setFichier(rs.getString("fichier"));
                C.setDescription_cat(rs.getString("Description_cat"));
            list.add(C);
                    
                    }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return list; 

    
    
    
    
    
    
    
    }
    
    // "select titre from cour where id_formation in(select id_formation from formation where id_formation = '"+f.getId_formation+"')"
    
}

