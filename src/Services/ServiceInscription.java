/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.Inscription;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Hp Omen
 */
public class ServiceInscription {
    
    private Connection con;
    private PreparedStatement stat;
    private ResultSet rs;
    
     public void ajouter(Inscription I)  {
        
        try {
         stat=con.prepareStatement("INSERT INTO inscription(id_utilisateur,id_formation,date_inscrit) VALUES (?,?,?)");
         stat.setInt(1, I.getId_utilisateur());
         stat.setInt(2, I.getId_formation());
         stat.setDate(3, I.getDate_inscrit());
         stat.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
       public void afficher(){
        
        try {
           stat = con.prepareStatement("SELECT u.nom, u.prenom, f.titre, i.date_inscrit FROM utilisateur u INNER JOIN inscription i ON i.utilisateur_id = u.id INNER JOIN formation f ON i.formation_id = f.id;");
            rs= stat.executeQuery();
            while(rs.next()){
                
            }
            rs.close();
            stat.close();
            
        } catch (Exception e) {
        }
    }
    
}
