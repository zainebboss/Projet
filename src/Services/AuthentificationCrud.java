/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.Utilisateur;
import Utils.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Hp Omen
 */
public class AuthentificationCrud {
    Connection cnx;
    private PreparedStatement stat;
    private ResultSet rs;
     

    public AuthentificationCrud() {
         cnx = Connexion.getInstance().getConnection();
    }
    public boolean Login(Utilisateur U){
        boolean res=false;
        String sql = "select * from utilisateur where email=? and password=? and role=? ";
        try {
            stat =cnx.prepareStatement(sql);
            stat.setString(1,U.getEmail());
         stat.setString(2,U.getPassword());  
         stat.setString(3,U.getRole());
            rs=stat.executeQuery();
            res=true;
 
   }catch(SQLException E)
   {
       System.out.println(E.getMessage());
    }
        return res;
}
}

