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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;

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
    
    @Override
    public void ajouter(Utilisateur U){
        try {
         stat=con.prepareStatement("INSERT INTO utilisateur(email,password,role,nom,prenom,telephone,adresse,date_naissance,enable) VALUES (?,?,?,?,?,?,?,?,?)");
         
         //if(pregMatch(pattern, U.getEmail())){
         //   stat.setString(1, U.getEmail());};
         //if(pregMatch1(pattern1,U.getPassword())){    
           // stat.setString(2, U.getPassword());}
         stat.setString(1,U.getEmail());
         stat.setString(2,U.getPassword());  
         stat.setString(3,U.getRole());
         stat.setString(4,U.getNom());
         stat.setString(5, U.getPrenom());
         stat.setInt(6, U.getTelephone());
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
            stat.setInt(3,U.getTelephone());
            stat.setString(4, U.getAdresse());
            stat.setInt(5, U.getId());
            stat.executeUpdate();
            etat=true;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
            etat=false;
        }
        return etat;
        
    }
        public void modiferU(Utilisateur U, int id) {
        try {
            String requete = "UPDATE utilisateur SET nom=? WHERE id=?";
            PreparedStatement pst = con.prepareStatement(requete);
            stat.setString(1, U.getNom());
            stat.setInt(2, U.getId());
            pst.executeUpdate();
            System.out.println("modifié");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
      @Override  
      public boolean supprimer(Utilisateur u){
       boolean etat = false;
        try {
            String Req2 = "select id from utilisateur where nom='"+u.getNom()+"'and prenom= '"+u.getPrenom()+"'";
            Statement Ps2 = con.createStatement();
            ResultSet Rs =  Ps2.executeQuery(Req2);
            String requete = "DELETE FROM utilisateur WHERE id=?";
            PreparedStatement pst = con.prepareStatement(requete);
             while(Rs.next()){  
            pst.setInt(1,Rs.getInt(1));
            i= pst.executeUpdate();
             }
           if(i>0){
            System.out.println("Utilisateur supprimé");
            etat=true;
           }else{System.out.println("Not deleted");}
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
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
                u.setTelephone(rs.getInt("telephone"));
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
    