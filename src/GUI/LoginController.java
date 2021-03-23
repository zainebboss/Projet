/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Formation;
import Entites.Utilisateur;
import Services.SendMailJava;
import Services.ServiceUtilisateur;
import Utils.Connexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;



/**
 * FXML Controller class
 *
 * @author Hp Omen
 */
public class LoginController implements Initializable {
    
    @FXML
    private TextField id;
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
     private AnchorPane paneFormation;
      @FXML
     private HBox box;
     
     @FXML
     private Button connexion;
     @FXML
    private Button inscription;
     @FXML
    private Button inscrire;
     @FXML
    private Button con;
     
     @FXML
    private ComboBox<Formation>  formation;
    @FXML
    private Button btn;
    private int selectUti;
    
   
    ObservableList<Utilisateur> data = FXCollections.observableArrayList();
    ObservableList<Formation> liste = FXCollections.observableArrayList();
     
     
     private Connection cnx;
    private PreparedStatement pst;
    private ResultSet rs;
    int selectedFor ;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UpdateTable ();
    }   
    
    
    @FXML
    public void showPaneConnexion(){
        paneConnexion.setVisible(true);
        paneInscription.setVisible(false);
        paneFormation.setVisible(false);
    }
    @FXML
    public void showPaneInscription(){
        paneConnexion.setVisible(false);
        paneInscription.setVisible(true);
        paneFormation.setVisible(false);
    }
    @FXML
    public void showPaneFormation(){
        paneConnexion.setVisible(false);
        paneInscription.setVisible(false);
        paneFormation.setVisible(true);
        box.setVisible(false);
    }
        
    
    
    @FXML
    public void Connection (ActionEvent event) throws Exception{
       cnx = Connexion.getInstance().getConnection();
       String sql = "select * from utilisateur where email=? and password=?";
        try {
            pst =cnx.prepareStatement(sql);
            pst.setString(1,email.getText());
            pst.setString(2,password.getText());
            
            rs=pst.executeQuery();
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Email ou Password correct");
                switch(rs.getString("role")){
                case "ROLE_APPRENANT" :
                     try {
             con.getScene().getWindow().hide();
                    Parent root =FXMLLoader.load(getClass().getResource("Avis.fxml"));
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
                Parent root =FXMLLoader.load(getClass().getResource("InterfaceFormateur.fxml"));
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
                     ;break;
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
        //SendMailJava.sendMail(temail,"bienvenue"," votre inscription est enregistrer");
        if (result){
             JOptionPane.showMessageDialog(null, "votre inscription ajouter avec succes");
        }
        else{
            JOptionPane.showMessageDialog(null, "l'apprenant est déja existe");
        }
}
  
       public void UpdateTable (){
        
        
        try {
            cnx = Connexion.getInstance().getConnection();
            String requete = "SELECT * FROM `formation` order by id desc";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
             ObservableList<Formation> liste = FXCollections.observableArrayList();
            while (rs.next()) {
               
            liste.add(new Formation(rs.getInt("id"),rs.getString("titre"))); 
            
            }
       formation.setItems(liste);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
      
    
    
     @FXML
    private void SaveFormation() {

        try {
            cnx = Connexion.getInstance().getConnection();
            
          
            String req = "SELECT * FROM utilisateur WHERE email=?";
            String temail= emaill.getText();
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, temail);
            ResultSet rs = pst.executeQuery();
             while (rs.next()) {
            selectUti=rs.getInt("id");
             }
           String requete = "INSERT INTO inscription(utilisateur_id,formation_id,date_inscrit) VALUES (?,?,?)";
             pst= cnx.prepareStatement(requete);
            pst.setInt(1,selectUti);
            pst.setInt(2, this.selectedFor);
           long millis=System.currentTimeMillis();
           java.sql.Date date=new java.sql.Date(millis);
            pst.setDate(3, date);
            pst.execute();
           

            JOptionPane.showMessageDialog(null, "Update");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);
        }
    }
     @FXML
    private void getFor(MouseEvent event) {
        
        Formation F = formation.getSelectionModel().getSelectedItem();
       
        if (formation.getValue()!=null){
             System.out.println(F.getTitre());
        this.selectedFor = F.getId(); 
            }
        
    
    }
    
}
