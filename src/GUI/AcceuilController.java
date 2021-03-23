/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Formation;
import Entites.Utilisateur;
import Utils.Connexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hp Omen
 */
public class AcceuilController implements Initializable {
 
    @FXML
    private ComboBox  formation;
    @FXML
    private Button btn;
    
    private Connection cnx;
    
    ObservableList<Utilisateur> data = FXCollections.observableArrayList();
    ObservableList<Formation> liste = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UpdateTable ();
    }    
    public void UpdateTable (){
        
        
        try {
            cnx = Connexion.getInstance().getConnection();
            String requete = "SELECT * FROM `formation` order by id desc";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               
            formation.getItems().add(rs.getString("titre")); 
            
            }
       
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        public void redirection () throws IOException{
         try {
             btn.getScene().getWindow().hide();
               Parent root =FXMLLoader.load(getClass().getResource("Login.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
             
        } catch (IOException ex) {
            System.err.println(ex);
        }
        }
    }
    
   

