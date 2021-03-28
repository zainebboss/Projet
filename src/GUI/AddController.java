/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Utilisateur;
import Services.ServiceUtilisateur;
import Utils.Connexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Hp Omen
 */
public class AddController implements Initializable {

    @FXML
    private  TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField telephone;
    @FXML
    private TextField adresse;
    @FXML
    private DatePicker date_naissance;
    
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnvider;
    @FXML
    private Button btnclose;
    Connection cnx;
    ResultSet rd;
    PreparedStatement pst;
     private boolean update;
    int UtilisateurId;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
     @FXML
    private void vider() {
       
        email.setText(null);
        password.setText(null);
        nom.setText(null);
        prenom.setText(null);
        adresse.setText(null);
        telephone.setText(null);
        date_naissance.getEditor().setText(null);
        
    }
    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
        public void SaveFormateur(ActionEvent event){
            if(update==false){
        System.err.println("Sauvegarde dans la base");
        String temail= email.getText();
        String tpassword= password.getText();
        String tnom= nom.getText();
        String tprenom= prenom.getText();
        String ttelephone= telephone.getText();
        String tadresse= adresse.getText();
        java.util.Date date_naissance = Date.from(this.date_naissance.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date tdate_naissance = new java.sql.Date(date_naissance.getTime());
        Utilisateur u= new Utilisateur ();
        u.setEmail(temail);
        u.setPassword(tpassword);
        u.setRole("ROLE_FORMATEUR");
        u.setNom(tnom);
        u.setPrenom(tprenom);
        u.setTelephone(ttelephone);
        u.setAdresse(tadresse);
        u.setDate_naissance(tdate_naissance);
        u.setEnable(true);
        ServiceUtilisateur uc= new ServiceUtilisateur();
        boolean result = uc.getByEmail(u);
   
        if (result){
             JOptionPane.showMessageDialog(null, "votre inscription ajouter avec succes");
        }
        else{
            JOptionPane.showMessageDialog(null, "le formateur est déja existe");
        }}else{
                
                   try {
            cnx = Connexion.getInstance().getConnection();
            int value1 = UtilisateurId;
            String value2 = email.getText();
            String value3 = password.getText();
            String value4 = nom.getText();
            String value5=prenom.getText();
            String value6=telephone.getText();
            String value7=adresse.getText();
            String value8=date_naissance.getEditor().getText();

            String requete = "update utilisateur set email= '"+value2+"',password= '"+value3+"' ,nom= '"+value4+"',prenom= '"+value5+"' ,telephone= '"+value6+"' ,adresse= '"+value7+"',date_naissance= '"+value8+"' where id= '"+value1+"' ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.execute();
            
       

            JOptionPane.showMessageDialog(null, "Update");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);
        }
            }
}
        void setTextField(int id1, String email1, String password1, String nom1, String prenom1, String telephone1, String adresse1, Date date_naissance1) {

        UtilisateurId = id1;
        email.setText(email1);
        password.setText(password1);
        nom.setText(nom1);
        prenom.setText(prenom1);
        telephone.setText(telephone1);
        adresse.setText(adresse1);
        date_naissance.getEditor().setText(String.valueOf(date_naissance1));

    }

    void setUpdate(boolean b) {
        this.update = b;

    }
    
}
