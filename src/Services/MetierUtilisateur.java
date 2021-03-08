/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Utils.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Hp Omen
 */
public class MetierUtilisateur {
    private Connection con;
    private PreparedStatement stat;

    public MetierUtilisateur() {
        con=Connexion.getInstance().getConnection();
    }
    public void encrypt(String password){
        String crypte="";
        for (int i=0; i<password.length();i++)  {
            int c=password.charAt(i)^48; 
            crypte=crypte+(char)c;
             
        }
        
        System.out.println(crypte);
       
    }
    public void decrypt(String password){
        String aCrypter="";
        for (int i=0; i<password.length();i++)  {
            int c=password.charAt(i)^48; 
            aCrypter=aCrypter+(char)c;
        }
        System.out.println(aCrypter);
    }
    
}
