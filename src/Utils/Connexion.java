/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Hp Omen
 */
public class Connexion {
    
    String url = "jdbc:mysql://localhost:3306/eformation";
     String login = "root";
     String pwd = "";
    public  static Connexion db;
    public Connection con;
    private Connexion() {
         try {
             con=DriverManager.getConnection(url, login, pwd);
             System.out.println("connexion etablie");
         } catch (SQLException ex) {
             System.out.println(ex);
         }
    }
    
    public Connection  getConnection()
    {
    return con;
    }     
    public static Connexion getInstance()
    {if(db==null)
        db=new Connexion();
    return db;
    }     
     
     
     
     
}
