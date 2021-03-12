/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Utils.Connexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Hp Omen
 */
public class Authentification {
     Connection cnx;

    public Authentification() {
         cnx = Connexion.getInstance().getConnection();
    }
       public String getRole(String L){
        String X="" ; 
        try{
        Statement PS = cnx.createStatement();
        String req = "select role from utilisateur where email='"+L+"'";
        ResultSet Rs = PS.executeQuery(req);
      
          while(Rs.next()){
            
               X=Rs.getString(1);
          }
      }catch(Exception E ){System.out.println(E.getMessage());}
              return X;  

    }
       public boolean Login(String L,String mdp){
        boolean res=true;
                
   try {
                     Statement Ps = cnx.createStatement();
            
         String req = "select * from utilisateur where email='"+L+"' and password='"+mdp+"'";
         ResultSet Rs = Ps.executeQuery(req);
         if(Rs.next()==false){
            
             System.out.println("Invalid E-mail ou Password");
             res=false;
         }
         
         
         
   }catch(SQLException E){System.out.println(E.getMessage());}
         
        return res ;
    }
     

   
        
}
