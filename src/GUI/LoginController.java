/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Utilisateur;
import Services.ServiceUtilisateur;
import Utils.Connexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;



/**
 * FXML Controller class
 *
 * @author Hp Omen
 */
public class LoginController implements Initializable {
    
    
    @FXML
    private TextField emaill;
    @FXML
    private PasswordField passwordd;
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
     private TextField email;
    @FXML
     private PasswordField password;
     @FXML
     private AnchorPane paneConnexion;
     @FXML
     private AnchorPane paneInscription;
     
     @FXML
     private Button connexion;
     @FXML
    private Button inscription;
     @FXML
    private Button inscrire;
     @FXML
    private Button con;
     @FXML
    private ComboBox role;
     
     private Connection cnx;
    private PreparedStatement pst;
    private ResultSet rs;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        role.getItems().addAll("ROLE_APPRENANT","ROLE_FORMATEUR","ROLE_ADMIN");
    }   
    
    
    @FXML
    public void showPaneConnexion(){
        paneConnexion.setVisible(true);
        paneInscription.setVisible(false);
    }
    @FXML
    public void showPaneInscription(){
        paneConnexion.setVisible(false);
        paneInscription.setVisible(true);
    }
    
    @FXML
    public void Connection (ActionEvent event) throws Exception{
       cnx = Connexion.getInstance().getConnection();
       String sql = "select * from utilisateur where email=? and password=? and role=? ";
        try {
            pst =cnx.prepareStatement(sql);
            pst.setString(1,email.getText());
            pst.setString(2,password.getText());
             pst.setString(3,role.getValue().toString());
            rs=pst.executeQuery();
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Email ou Password correct");
                switch(role.getValue().toString()){
                case "ROLE_APPRENANT" :
                     try {
             con.getScene().getWindow().hide();
                    Parent root =FXMLLoader.load(getClass().getResource("InterfaceAdmin.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
             
        } catch (IOException ex) {
            System.err.println(ex);
        };break;
        
      case "ROLE_FORMATEUR" :
               try {
             con.getScene().getWindow().hide();
                Parent root =FXMLLoader.load(getClass().getResource("InterfaceAdmin.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
             
        } catch (IOException ex) {
            System.err.println(ex);
        };break;
         case "ROLE_ADMIN" :
                     try {
               

             con.getScene().getWindow().hide();
                Parent root =FXMLLoader.load(getClass().getResource("InterfaceAdmin.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();   
        } catch (IOException ex) {
            System.err.println(ex);
        };
                     System.out.println("");break;
                default:break ;        
            }
            }else{
                JOptionPane.showMessageDialog(null, "Email ou Password incorrect");
            }
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,e); 
        }
  
    }
        
    @FXML
        public void Save(ActionEvent event){
        
        System.err.println("Sauvegarde dans la base");
        String temail= emaill.getText();
        String tpassword= passwordd.getText();
        String tnom= nom.getText();
        String tprenom= prenom.getText();
        String ttelephone= telephone.getText();
        String tadresse= adresse.getText();
       java.util.Date date_naissance = Date.from(this.date_naissance.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date tdate_naissance = new java.sql.Date(date_naissance.getTime());
        Utilisateur u= new Utilisateur ();
        u.setEmail(temail);
        u.setPassword(tpassword);
        u.setRole("ROLE_APPRENANT");
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
            JOptionPane.showMessageDialog(null, "l'apprenant est déja existe");
        }
}
  
    }

