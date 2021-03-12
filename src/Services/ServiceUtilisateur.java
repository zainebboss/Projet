/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.Utilisateur;
import IService.IService;
import Utils.Connexion;
import java.sql.Connection;
import java.sql.DriverManager;
import static java.sql.JDBCType.NULL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Hp Omen
 */
public class ServiceUtilisateur implements IService<Utilisateur> {
   
    private Connection con;
    private PreparedStatement stat;
    private ResultSet rs;
    int i=0;
    final String pattern = "^[A-Za-z0-9+_.-]+@(.+)$";
    final String pattern1 ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*])(?=\\S+$).{8,}$";

    public ServiceUtilisateur() {
        con=Connexion.getInstance().getConnection();
    }
    public static boolean pregMatch(String pattern, String content){
    return content.matches(pattern);}
      public static boolean pregMatch1(String pattern1, String content1){
    return content1.matches(pattern1);} 
      
      
      public boolean getByEmail(Utilisateur U){
          ArrayList<Utilisateur> list = new ArrayList<>();
      
        try {
            stat = con.prepareStatement("SELECT * FROM utilisateur WHERE email =?");
            stat.setString(1,U.getEmail());
            rs= stat.executeQuery();
            while(rs.next()){
               Utilisateur u = new Utilisateur();
                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setRole(rs.getString("role"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setTelephone(rs.getString("telephone"));
                 u.setAdresse(rs.getString("adresse"));
                 u.setDate_naissance(rs.getDate("date_naissance"));
                 u.setEnable(rs.getBoolean("enable"));
                list.add(u);  
            }
            rs.close();
            stat.close();
    
            if (list.size()!=0){
                JOptionPane.showMessageDialog(null, "l'utilisateur existe deja");
                return false;
            }
            else{
              
               ajouter(U) ;
               return true;
 
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
           
         return false;   
      }
    
    @Override
    public void ajouter(Utilisateur U)  {
        
        try {
         stat=con.prepareStatement("INSERT INTO utilisateur(email,password,role,nom,prenom,telephone,adresse,date_naissance,enable) VALUES (?,?,?,?,?,?,?,?,?)");
              //
         
//if(pregMatch(pattern, U.getEmail())){
         //   stat.setString(1, U.getEmail());};
         //if(pregMatch1(pattern1,U.getPassword())){    
           // stat.setString(2, U.getPassword());}
         stat.setString(1,U.getEmail());
         stat.setString(2,U.getPassword());  
         stat.setString(3,U.getRole());
         stat.setString(4,U.getNom());
         stat.setString(5, U.getPrenom());
         stat.setString(6, U.getTelephone());
         stat.setString(7, U.getAdresse());
         stat.setDate(8, U.getDate_naissance());
         stat.setBoolean(9, U.isEnable());
         stat.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
        @Override
        public boolean modifier(Utilisateur U){
            boolean etat=false;
        try {
            stat=con.prepareStatement("UPDATE utilisateur SET nom=?,prenom=?,telephone=?,adresse=? WHERE id=? ");
            stat.setString(1, U.getNom());
            stat.setString(2,U.getPrenom());
            stat.setString(3,U.getTelephone());
            stat.setString(4, U.getAdresse());
            stat.setInt(5, U.getId());
            stat.executeUpdate();
            etat=true;
        } catch (SQLException ex) {
            
            etat=false;
        }
        return etat;
        
    }
       @Override
        public boolean supprimer(Utilisateur U) {
            boolean etat=false;
        String req = "DELETE From utilisateur WHERE utilisateur.id=?";
        try {
 PreparedStatement pst2 = con.prepareStatement(req);
            pst2.setInt(1, U.getId());
            pst2.executeUpdate();
            System.out.println("livre supprimé du panier");
            etat = true;
        } catch (SQLException e) {
            e.printStackTrace();
            etat=false;
        }
        return etat;
    }
        
        

      @Override
       public ArrayList<Utilisateur> afficher(){
        ArrayList<Utilisateur> list = new ArrayList<>();
        try {
           stat = con.prepareStatement("SELECT * FROM utilisateur");
            rs= stat.executeQuery();
           
            while(rs.next()){
               Utilisateur u = new Utilisateur();
                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setRole(rs.getString("role"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setTelephone(rs.getString("telephone"));
                 u.setAdresse(rs.getString("adresse"));
                 u.setDate_naissance(rs.getDate("date_naissance"));
                 u.setEnable(rs.getBoolean("enable"));
                
                list.add(u);
            }
            rs.close();
            stat.close();
            
        } catch (Exception e) {
        }
        
        return list;
    }
        }
    