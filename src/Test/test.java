/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;
import Entites.Utilisateur;
import Services.MetierUtilisateur;
import Services.ServiceUtilisateur;
import java.sql.*;
import java.util.List;

/**
 *
 * @author Hp Omen
 */
public class test {

    /**
     * @param args the command line arguments
     */
static Statement ste;
static ResultSet result;
    public static void main(String[] args) {
        // TODO code application logic here
         ServiceUtilisateur ser = new ServiceUtilisateur();
         MetierUtilisateur met =new MetierUtilisateur();
        
        Utilisateur p1 = new Utilisateur();
        p1.setEmail("email");
        p1.setPassword("password");
        p1.setRole("role");
        p1.setNom("lkknbjnk");
        p1.setPrenom("bb");
        p1.setTelephone(12101345);
        p1.setAdresse("adresse");
        p1.setDate_naissance(Date.valueOf("1996-01-25"));
       p1.setEnable(true);
       Utilisateur p2 = new Utilisateur();
        p2.setEmail("zaineb@esprit.tn");
        p2.setPassword("password");
        p2.setRole("role");
        p2.setNom("zaineb");
        p2.setPrenom("boussaid");
        p2.setTelephone(12101345);
        p2.setAdresse("adresse");
        p2.setDate_naissance(Date.valueOf("1996-01-25"));
       p2.setEnable(true);
       
        
        
     
        //List<Utilisateur> list = ser.afficher();
         //  System.out.println(list);
       //
       //ser.ajouter(p1);
       //ser.modifier(p2);
       //ser.supprimer(p2);
       
       //ser.modifier(p1);
       met.encrypt("zaineb");
       met.decrypt("JQY^UR");
       
       
        }
    
    
}
