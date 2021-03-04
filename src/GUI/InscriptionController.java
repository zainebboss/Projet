/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Utilisateur;
import Services.ServiceUtilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.Date;
import java.time.ZoneId;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Dridi
 */
public class InscriptionController implements Initializable {
    

    private TextField email;
    private PasswordField password;
    private TextField nom;
    private TextField prenom;
    private TextField telephone;
    private TextField adresse;
    private DatePicker date_naissance;
    
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public void Save(ActionEvent event){
        
        System.err.println("Sauvegarde dans la base");
        String temail= email.getText();
        String tpassword= password.getText();
        String tnom= nom.getText();
        String tprenom= prenom.getText();
        Integer ttelephone= Integer.parseInt(telephone.getText());
        String tadresse= adresse.getText();
        java.util.Date date_naissance = Date.from(this.date_naissance.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date tdate_naissance = new java.sql.Date(date_naissance.getTime());
        Utilisateur u= new Utilisateur (0, temail, tpassword, "apprenant", tnom, tprenom, ttelephone, tadresse, tdate_naissance, true);
        ServiceUtilisateur uc= new ServiceUtilisateur();
        uc.ajouter(u);
}
   

      @FXML
    private void OnAdd(ActionEvent event) {
        try {
            
             java.util.Date date_naissance = Date.from(this.date_naissance.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date tdate_naissance = new java.sql.Date(date_naissance.getTime());

            System.out.println("=================Sauvegarde dans la base==================");
            String temail= email.getText();
        String tpassword= password.getText();
        String trole="apprenant";
        String tnom= nom.getText();
        String tprenom= prenom.getText();
        Integer ttelephone= Integer.parseInt(telephone.getText());
        String tadresse= adresse.getText();
            try {
                

                Utilisateur U = new Utilisateur(temail, tpassword, trole, tnom, tprenom, ttelephone, tadresse, tdate_naissance, true);
                ServiceUtilisateur uc= new ServiceUtilisateur();
               uc.ajouter(U);
                JOptionPane.showMessageDialog(null, "Ajouter");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "vueiller verfirier");
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load();
            LoginController pc2 = loader.getController();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }     

   

}
