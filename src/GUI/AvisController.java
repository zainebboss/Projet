/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Utils.Connexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Hp Omen
 */
public class AvisController implements Initializable {

    @FXML
    private HBox hinit;
    @FXML
    private HBox hangry;
    @FXML
    private HBox hnothappy;
    @FXML
    private HBox hhappy;
    @FXML
    private ComboBox formateur;
    
    private Connection cnx;
    
    @FXML
    public void showHboxangry(){
        hangry.setVisible(true);
        hnothappy.setVisible(false);
        hhappy.setVisible(false);
        hinit.setVisible(false);
    }
    @FXML
    public void showHboxnothappy(){
        hangry.setVisible(false);
        hnothappy.setVisible(true);
        hhappy.setVisible(false);
        hinit.setVisible(false);
    }
    @FXML
    public void showHboxhappy(){
        hangry.setVisible(false);
        hnothappy.setVisible(false);
        hhappy.setVisible(true);
        hinit.setVisible(false);
    }
    public void UpdateTable (){
        
        
        try {
            cnx = Connexion.getInstance().getConnection();
            String requete = "SELECT * FROM utilisateur WHERE role='ROLE_FORMATEUR' order by id desc";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               
            formateur.getItems().addAll(rs.getString("nom")+" "+rs.getString("prenom")); 
            }
       
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UpdateTable ();
    }    
    
}
