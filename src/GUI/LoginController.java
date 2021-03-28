/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Formation;
import Entites.Inscription;
import Entites.Utilisateur;
import Entites.sparkAide;
import Entites.sparkAideCom;
import Services.SendMailJava;
import Services.ServiceUtilisateur;
import Services.mysqlConnect;
import Utils.Connexion;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.StageStyle;
import javafx.util.Callback;



/**
 * FXML Controller class
 *
 * @author Hp Omen
 */
public class LoginController implements Initializable {
    
    @FXML
    private TextField id;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
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
    private TextField search;
    @FXML
    private TextField search1;

     @FXML
     private AnchorPane acceuil;
     @FXML
     private AnchorPane AideCom;
     @FXML
     private AnchorPane paneInscription;
     @FXML
     private AnchorPane paneFormation;
     
     @FXML
     private AnchorPane page;
     @FXML
     private AnchorPane aide;
     
     @FXML
     private AnchorPane avis;
      @FXML
     private HBox box;
      @FXML
     private HBox hmenu;
     @FXML
     private Button connexion;
     @FXML
    private Button inscription;
     @FXML
    private Button inscrire;
     @FXML
    private Button con;
     @FXML
    private Button modifierprofil;
     
     @FXML
    private ComboBox <String>formation;
    @FXML
    private Button btn;
    @FXML
    private HBox hinit;
    @FXML
    private HBox hangry;
    @FXML
    private HBox hnothappy;
    @FXML
    private HBox hhappy;
    @FXML
    private VBox menuadmin;
    @FXML
    private VBox menuformateur;
    @FXML
    private VBox dashboard;
     @FXML
    private VBox apprenant;
    @FXML
    private VBox listeFormateur;
    @FXML
    private VBox listeAvis;
    @FXML
    private ComboBox <String>formateur;
     @FXML
    private PieChart statistique;
    @FXML
    private TableView<Utilisateur> tvapprenant;
    private TableColumn<Utilisateur, Integer> cid;
    @FXML
    private TableColumn<Utilisateur, String> colemail;
    @FXML
    private TableColumn<Utilisateur, String> colnom;
    @FXML
    private TableColumn<Utilisateur, String> colprenom;
    @FXML
    private TableColumn<Utilisateur, String> coltelephone;
    @FXML
    private TableColumn<Utilisateur, String> coladresse;
    @FXML
    private TableColumn<Utilisateur, String> coldate_naissance;
    @FXML
    private TableView<Utilisateur> tvformateur;
    @FXML
    private TableColumn<Utilisateur, Integer> cid1;
    @FXML
    private TableColumn<Utilisateur, String> colemail1;
    @FXML
    private TableColumn<Utilisateur, String> colpassword1;
    @FXML
    private TableColumn<Utilisateur, String> colnom1;
    @FXML
    private TableColumn<Utilisateur, String> colprenom1;
    @FXML
    private TableColumn<Utilisateur, String> coltelephone1;
    @FXML
    private TableColumn<Utilisateur, String> coladresse1;
    @FXML
    private TableColumn<Utilisateur, String> coldate_naissance1;
    @FXML
    private TableColumn<Utilisateur, String> colaction;
    @FXML
    private TableView tvavis;
    @FXML
    private TableColumn colemailavis;
    @FXML
    private TableColumn colnomavis;
    @FXML
    private TableColumn colprenomavis;
    @FXML
    private TableColumn colavis;
    
 
    private int selectUti;
    private int selectedFor ;
    private int selectedFormateur;
    
    ObservableList<ObservableList> liste ;
    ObservableList<Utilisateur> data = FXCollections.observableArrayList();
    ObservableList<Utilisateur> data1 = FXCollections.observableArrayList();
    ObservableList <String> ff = FXCollections.observableArrayList();
   
     
     
     private Connection cnx;
    private PreparedStatement pst;
    private ResultSet rs;
    @FXML
    private Button valider;
    
   
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UpdateTable();
        UpdateTablee();
        UpdateTableAvis();
        UpdateTableApprenant();
        UpdateTableFormateur();
        search();
        search1();
        UpdateTables() ;
         search_aide();
        
        //liste Java
        try {
            ObservableList<Inscription> liste = FXCollections.observableArrayList();
            cnx = Connexion.getInstance().getConnection();
            String requete = "SELECT i.*, f.* FROM inscription i, formation f WHERE i.formation_id = f.id AND f.titre='JAVA'";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                liste.add(new Inscription (rs.getInt("id"), rs.getInt("utilisateur_id"), rs.getInt("formation_id"), rs.getDate("date_inscrit")));
            }
            //liste SQL
            
            ObservableList<Inscription> liste1 = FXCollections.observableArrayList();
            cnx = Connexion.getInstance().getConnection();
            String requete1 = "SELECT i.*, f.* FROM inscription i, formation f WHERE i.formation_id = f.id AND f.titre='SQL'";
            PreparedStatement pst1 = cnx.prepareStatement(requete1);
            ResultSet rs1 = pst1.executeQuery();
            while (rs1.next()) {
                liste1.add(new Inscription (rs1.getInt("id"), rs1.getInt("utilisateur_id"), rs1.getInt("formation_id"), rs1.getDate("date_inscrit")));
            }
            //liste JAVASCRIPT
           
            ObservableList<Inscription> liste2 = FXCollections.observableArrayList();
            cnx = Connexion.getInstance().getConnection();
            String requete2 = "SELECT i.*, f.* FROM inscription i, formation f WHERE i.formation_id = f.id AND f.titre='JAVASCRIPT'";
            PreparedStatement pst2 = cnx.prepareStatement(requete2);
            ResultSet rs2 = pst2.executeQuery();
            while (rs2.next()) {
                liste2.add(new Inscription (rs2.getInt("id"), rs2.getInt("utilisateur_id"), rs2.getInt("formation_id"), rs2.getDate("date_inscrit")));
            }
            //liste PHP
            
            ObservableList<Inscription> liste3 = FXCollections.observableArrayList();
            cnx = Connexion.getInstance().getConnection();
            String requete3 = "SELECT i.*, f.* FROM inscription i, formation f WHERE i.formation_id = f.id AND f.titre='PHP'";
            PreparedStatement pst3 = cnx.prepareStatement(requete3);
            ResultSet rs3 = pst3.executeQuery();
            while (rs3.next()) {
                liste3.add(new Inscription (rs3.getInt("id"), rs3.getInt("utilisateur_id"), rs3.getInt("formation_id"), rs3.getDate("date_inscrit")));
            }
            
            //liste CodeName
            
            ObservableList<Inscription> liste4 = FXCollections.observableArrayList();
            cnx = Connexion.getInstance().getConnection();
            String requete4 = "SELECT i.*, f.* FROM inscription i, formation f WHERE i.formation_id = f.id AND f.titre='CodeName'";
            PreparedStatement pst4 = cnx.prepareStatement(requete4);
            ResultSet rs4 = pst4.executeQuery();
            while (rs4.next()) {
                liste4.add(new Inscription (rs4.getInt("id"), rs4.getInt("utilisateur_id"), rs4.getInt("formation_id"), rs4.getDate("date_inscrit")));
            }
            
            //liste Symfony
            
            ObservableList<Inscription> liste5 = FXCollections.observableArrayList();
            cnx = Connexion.getInstance().getConnection();
            String requete5 = "SELECT i.*, f.* FROM inscription i, formation f WHERE i.formation_id = f.id AND f.titre='Symfony'";
            PreparedStatement pst5 = cnx.prepareStatement(requete5);
            ResultSet rs5 = pst5.executeQuery();
            while (rs5.next()) {
                liste5.add(new Inscription (rs5.getInt("id"), rs5.getInt("utilisateur_id"), rs5.getInt("formation_id"), rs5.getDate("date_inscrit")));
            }
            
            //liste jquery
            
            ObservableList<Inscription> liste6 = FXCollections.observableArrayList();
            cnx = Connexion.getInstance().getConnection();
            String requete6 = "SELECT i.*, f.* FROM inscription i, formation f WHERE i.formation_id = f.id AND f.titre='Jquery'";
            PreparedStatement pst6 = cnx.prepareStatement(requete6);
            ResultSet rs6 = pst6.executeQuery();
            while (rs6.next()) {
                liste6.add(new Inscription (rs6.getInt("id"), rs6.getInt("utilisateur_id"), rs6.getInt("formation_id"), rs6.getDate("date_inscrit")));
            }
            ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
                new PieChart.Data("JAVA", liste.size()),
                    new PieChart.Data("JAVASCRIPT", liste2.size()),
                new PieChart.Data("PHP", liste3.size()),
                new PieChart.Data("SQL", liste1.size()),
                new PieChart.Data("Symfony", liste5.size()),
                 new PieChart.Data("JQUERY", liste6.size()),
                new PieChart.Data("CodeNameOne", liste4.size()));
                statistique.setData(pieData);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @FXML
    public void showPaneConnexion(){
       box.setVisible(true);
       id.setVisible(false);
        email.setVisible(true);
        password.setVisible(true);
        nom.setVisible(false);
        prenom.setVisible(false);
        telephone.setVisible(false);
        adresse.setVisible(false);
        date_naissance.setVisible(false);
        con.setVisible(true);
        inscrire.setVisible(false);
        modifierprofil.setVisible(false);
        paneInscription.setVisible(true);
        paneFormation.setVisible(false);
        acceuil.setVisible(false);
        hmenu.setVisible(false);
        menuadmin.setVisible(false);
        dashboard.setVisible(false);
        apprenant.setVisible(false);
        listeFormateur.setVisible(false);
        menuformateur.setVisible(false);
        listeAvis.setVisible(false);
        aide.setVisible(false);
        AideCom.setVisible(false);
        
    }
    @FXML
    public void showPaneInscription(){
        box.setVisible(true);
       id.setVisible(false);
        email.setVisible(true);
        password.setVisible(true);
        nom.setVisible(true);
        prenom.setVisible(true);
        telephone.setVisible(true);
        adresse.setVisible(true);
        date_naissance.setVisible(true);
        con.setVisible(false);
        modifierprofil.setVisible(false);
        inscrire.setVisible(true);
        paneInscription.setVisible(true);
        paneFormation.setVisible(false);
        acceuil.setVisible(false);
        hmenu.setVisible(false);
        menuadmin.setVisible(false);
        dashboard.setVisible(false);
        apprenant.setVisible(false);
        listeFormateur.setVisible(false);
         menuformateur.setVisible(false);
         listeAvis.setVisible(false);
         aide.setVisible(false);
         AideCom.setVisible(false);
        
    }
    @FXML
    public void showPaneFormation(){
        
        paneInscription.setVisible(false);
        paneFormation.setVisible(true);
        box.setVisible(false);
        acceuil.setVisible(false);
        hmenu.setVisible(false);
        menuadmin.setVisible(false);
        dashboard.setVisible(false);
        apprenant.setVisible(false);
        listeFormateur.setVisible(false);
         menuformateur.setVisible(false);
         listeAvis.setVisible(false);
         aide.setVisible(false);
         AideCom.setVisible(false);
    }
    @FXML
    public void showDashboard(){
        
        paneInscription.setVisible(false);
        paneFormation.setVisible(false);
        box.setVisible(false);
        acceuil.setVisible(false);
        hmenu.setVisible(false);
        menuadmin.setVisible(true);
        dashboard.setVisible(true);
        apprenant.setVisible(false);
        listeFormateur.setVisible(false);
         menuformateur.setVisible(false);
         listeAvis.setVisible(false);
         aide.setVisible(false);
         AideCom.setVisible(false);
    }
    @FXML
    public void showAide(){
        
        paneInscription.setVisible(false);
        paneFormation.setVisible(false);
        box.setVisible(false);
        acceuil.setVisible(false);
        hmenu.setVisible(true);
        menuadmin.setVisible(true);
        dashboard.setVisible(false);
        apprenant.setVisible(false);
        listeFormateur.setVisible(false);
         menuformateur.setVisible(false);
         listeAvis.setVisible(false);
         aide.setVisible(true);
         AideCom.setVisible(false);
    }
    @FXML
    public void showDashboardFormateur(){
        
        paneInscription.setVisible(false);
        paneFormation.setVisible(false);
        box.setVisible(false);
        acceuil.setVisible(false);
        hmenu.setVisible(false);
        menuadmin.setVisible(false);
        dashboard.setVisible(true);
        apprenant.setVisible(false);
        listeFormateur.setVisible(false);
         menuformateur.setVisible(true);
         listeAvis.setVisible(false);
         aide.setVisible(false);
         AideCom.setVisible(false);
    }
     @FXML
    public void showmenuaFormateur(){
        
        paneInscription.setVisible(false);
        paneFormation.setVisible(false);
        box.setVisible(false);
        acceuil.setVisible(false);
        hmenu.setVisible(false);
        menuadmin.setVisible(false);
        dashboard.setVisible(true);
        apprenant.setVisible(false);
        listeFormateur.setVisible(false);
         menuformateur.setVisible(true);
         listeAvis.setVisible(false);
         aide.setVisible(false);
         AideCom.setVisible(false);
    }
     @FXML
    public void showAideCom(){
        
        paneInscription.setVisible(false);
        paneFormation.setVisible(false);
        box.setVisible(false);
        acceuil.setVisible(false);
        hmenu.setVisible(false);
        menuadmin.setVisible(false);
        dashboard.setVisible(false);
        apprenant.setVisible(false);
        listeFormateur.setVisible(false);
         menuformateur.setVisible(true);
         listeAvis.setVisible(false);
         aide.setVisible(false);
         AideCom.setVisible(true);
    }
     @FXML
    public void showmenu(){
        
        paneInscription.setVisible(false);
        paneFormation.setVisible(false);
        box.setVisible(false);
         avis.setVisible(false);
         acceuil.setVisible(false);
         hmenu.setVisible(true);
         menuadmin.setVisible(false);
         dashboard.setVisible(false);
         apprenant.setVisible(false);
         listeFormateur.setVisible(false);
          menuformateur.setVisible(false);
          listeAvis.setVisible(false);
          aide.setVisible(false);
          AideCom.setVisible(false);
          
    }
    @FXML
    
    public void showinit(){
        
        paneInscription.setVisible(false);
        paneFormation.setVisible(false);
        box.setVisible(false);
         avis.setVisible(true);
         acceuil.setVisible(false);
         hmenu.setVisible(true);
         menuadmin.setVisible(false);
         dashboard.setVisible(false);
         apprenant.setVisible(false);
         listeFormateur.setVisible(false);
          menuformateur.setVisible(false);
          listeAvis.setVisible(false);
          aide.setVisible(false);
          AideCom.setVisible(false);
    }
    @FXML
    public void showprofil() throws SQLException{
         String req = "SELECT * FROM utilisateur WHERE email=?";
            String temail= email.getText();
             pst = cnx.prepareStatement(req);
            pst.setString(1, temail);
            rs = pst.executeQuery();
             while (rs.next()) {      
        id.setText(String.valueOf(rs.getInt("id")));
        email.setText(rs.getString("email"));
        password.setText(rs.getString("password"));
        nom.setText(rs.getString("nom"));
        prenom.setText(rs.getString("prenom"));
        telephone.setText(rs.getString("telephone"));
        adresse.setText(rs.getString("adresse"));
        date_naissance.getEditor().setText(String.valueOf(rs.getDate("date_naissance")));
             }
         box.setVisible(false);
       id.setVisible(false);
        email.setVisible(true);
        password.setVisible(true);
        nom.setVisible(true);
        prenom.setVisible(true);
        telephone.setVisible(true);
        adresse.setVisible(true);
        date_naissance.setVisible(true);
        con.setVisible(false);
        inscrire.setVisible(false);
        modifierprofil.setVisible(true);
        paneInscription.setVisible(true);
        paneFormation.setVisible(false);
        acceuil.setVisible(false);
        hmenu.setVisible(true);
        avis.setVisible(false);
        menuadmin.setVisible(false);
        apprenant.setVisible(false);
        listeFormateur.setVisible(false);
         menuformateur.setVisible(false);
         listeAvis.setVisible(false);
         aide.setVisible(false);
         AideCom.setVisible(false);
  
    }
         @FXML
    public void showHboxangry(){
        
        paneInscription.setVisible(false);
        paneFormation.setVisible(false);
        hangry.setVisible(true);
        hnothappy.setVisible(false);
        hhappy.setVisible(false);
        hinit.setVisible(false);
        acceuil.setVisible(false);
        hmenu.setVisible(false);
        menuadmin.setVisible(false);
        apprenant.setVisible(false);
        listeFormateur.setVisible(false);
         menuformateur.setVisible(false);
         listeAvis.setVisible(false);
         aide.setVisible(false);
         AideCom.setVisible(false);
        
    }
    @FXML
    public void showHboxnothappy(){
           
        paneInscription.setVisible(false);
        paneFormation.setVisible(false);
        hangry.setVisible(false);
        hnothappy.setVisible(true);
        hhappy.setVisible(false);
        hinit.setVisible(false);
        acceuil.setVisible(false);
        hmenu.setVisible(false);
        menuadmin.setVisible(false);
        apprenant.setVisible(false);
        listeFormateur.setVisible(false);
         menuformateur.setVisible(false);
         listeAvis.setVisible(false);
         aide.setVisible(false);
         AideCom.setVisible(false);
       
    }
    @FXML
    public void showHboxhappy(){
         
        paneInscription.setVisible(false);
        paneFormation.setVisible(false);
        hangry.setVisible(false);
        hnothappy.setVisible(false);
        hhappy.setVisible(true);
        hinit.setVisible(false);
        acceuil.setVisible(false);
        hmenu.setVisible(false);
        menuadmin.setVisible(false);
        apprenant.setVisible(false);
        listeFormateur.setVisible(false);
         menuformateur.setVisible(false);
         listeAvis.setVisible(false);
         aide.setVisible(false);
         AideCom.setVisible(false);
        
    }
    @FXML
    public void showlisteApprenant(){
        
        paneInscription.setVisible(false);
        paneFormation.setVisible(false);
        box.setVisible(false);
        acceuil.setVisible(false);
        hmenu.setVisible(false);
        menuadmin.setVisible(true);
        dashboard.setVisible(false);
        apprenant.setVisible(true);
        listeFormateur.setVisible(false);
         menuformateur.setVisible(false);
         listeAvis.setVisible(false);
         aide.setVisible(false);
         AideCom.setVisible(false);
    }
     @FXML
    public void showlisteAvis(){
        
        paneInscription.setVisible(false);
        paneFormation.setVisible(false);
        box.setVisible(false);
        acceuil.setVisible(false);
        hmenu.setVisible(false);
        menuadmin.setVisible(false);
        dashboard.setVisible(false);
        apprenant.setVisible(false);
        listeFormateur.setVisible(false);
         menuformateur.setVisible(true);
         listeAvis.setVisible(true);
         aide.setVisible(false);
         AideCom.setVisible(false);
    }
    @FXML
    public void showlisteApprenantFormateur(){
        
        paneInscription.setVisible(false);
        paneFormation.setVisible(false);
        box.setVisible(false);
        acceuil.setVisible(false);
        hmenu.setVisible(false);
        menuadmin.setVisible(false);
        dashboard.setVisible(false);
        apprenant.setVisible(true);
        listeFormateur.setVisible(false);
         menuformateur.setVisible(true);
         listeAvis.setVisible(false);
         aide.setVisible(false);
         AideCom.setVisible(false);
    }
     @FXML
    public void showlisteFormateur(){
        
        paneInscription.setVisible(false);
        paneFormation.setVisible(false);
        box.setVisible(false);
        acceuil.setVisible(false);
        hmenu.setVisible(false);
        menuadmin.setVisible(true);
        dashboard.setVisible(false);
        apprenant.setVisible(false);
        listeFormateur.setVisible(true);
         menuformateur.setVisible(false);
         listeAvis.setVisible(false);
         aide.setVisible(false);
         AideCom.setVisible(false);
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
                switch(rs.getString("role")){
                case "ROLE_APPRENANT" :
                    showmenu();
      ;break;
        
      case "ROLE_FORMATEUR" :
               showmenuaFormateur();
          ;break;
         case "ROLE_ADMIN" :
                    showDashboard();
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
  
       public void UpdateTable(){
        
        
        try {
            cnx = Connexion.getInstance().getConnection();
            String requete = "SELECT * FROM `formation` order by id desc";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
               
            ff.add(rs.getString("titre")); 
            
            }
       formation.setItems(ff);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
      
    
    
     @FXML
    private void SaveFormation() {

        try {
            cnx = Connexion.getInstance().getConnection();
            
          
            String req = "SELECT * FROM utilisateur WHERE email=?";
            String temail= email.getText();
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, temail);
            ResultSet rs = pst.executeQuery();
             while (rs.next()) {
            selectUti=rs.getInt("id");
             }
           String requete = "INSERT INTO inscription(utilisateur_id,formation_id,date_inscrit) VALUES (?,?,?)";
             pst= cnx.prepareStatement(requete);
            pst.setInt(1,selectUti);
            pst.setInt(2,selectedFor);
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
        
        String s = formation.getSelectionModel().getSelectedItem();
            try {
                 String req = "SELECT * FROM `formation` WHERE titre=?";
            pst = cnx.prepareStatement(req);
                pst.setString(1, s);
                rs = pst.executeQuery();
             while (rs.next()) {
            selectedFor=rs.getInt("id");
             }
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
    
   
    public void UpdateTablee(){
        
        
        try {
            cnx = Connexion.getInstance().getConnection();
            String requete = "SELECT * FROM utilisateur WHERE role='ROLE_FORMATEUR' order by id desc";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               
            formateur.getItems().add(rs.getString("nom")); 
            }
       
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
        
      @FXML
    private void SaveAvis() {

        try {
            cnx = Connexion.getInstance().getConnection();
            
          
            String req = "SELECT * FROM utilisateur WHERE email=?";
            String temail= email.getText();
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, temail);
            ResultSet rs = pst.executeQuery();
             while (rs.next()) {
            selectUti=rs.getInt("id");
             }
           String requete = "INSERT INTO avis(formateur_id,apprenant_id,note) VALUES (?,?,?)";
             pst= cnx.prepareStatement(requete);
            pst.setInt(1,selectedFormateur);
            pst.setInt(2,selectUti);
            if(hangry.isVisible()){
               pst.setInt(3, 1);
               pst.execute();
            JOptionPane.showMessageDialog(null, "Update");
            }else if(hnothappy.isVisible()){
                pst.setInt(3, 2); 
                pst.execute();
            JOptionPane.showMessageDialog(null, "Update");
            }else if(hhappy.isVisible()){
                pst.setInt(3, 3); 
                pst.execute();
            JOptionPane.showMessageDialog(null, "Update");
            }
          
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);
        }
    } 
    
    @FXML
    private void getFormateur(MouseEvent event) {
        
        String s = formateur.getSelectionModel().getSelectedItem();
            try {
                 String req = "SELECT * FROM `utilisateur` WHERE nom=?";
            pst = cnx.prepareStatement(req);
                pst.setString(1, s);
                rs = pst.executeQuery();
             while (rs.next()) {
            selectedFormateur=rs.getInt("id");
             }
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
    
    
    @FXML
    private void Update() {

        try {
            cnx = Connexion.getInstance().getConnection();
            String value1 = id.getText();
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
            UpdateTable ();
       

            JOptionPane.showMessageDialog(null, "modifier");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);
        }
    }
   public void UpdateTableApprenant(){
        
        data.clear();
        try {
            cnx = Connexion.getInstance().getConnection();
            String requete = "SELECT * FROM utilisateur WHERE role='ROLE_APPRENANT' order by id desc";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                data.add(new Utilisateur(rs.getInt("id"), rs.getString("email"), rs.getString("password"), rs.getString("nom"),rs.getString("prenom"),rs.getString("telephone"),rs.getString("adresse"),rs.getDate("date_naissance")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
        colemail.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("email"));
        colnom.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("nom"));
        colprenom.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("prenom"));
        coltelephone.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("telephone"));
        coladresse.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("adresse"));
        coldate_naissance.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("date_naissance"));
        tvapprenant.setItems(data);
    }
    
    void search(){ 
     
     
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        coltelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        tvapprenant.setItems(data);
             FilteredList<Utilisateur> filteredData = new FilteredList<>(data, b -> true);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(U -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (U.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } 
                if (U.getEmail().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } 
                if (U.getPrenom().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } 
                if (U.getTelephone().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } 
                else {
                    return false; // Does not match.
                }
            });
        });
        SortedList<Utilisateur> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvapprenant.comparatorProperty());
        tvapprenant.setItems(sortedData);    
    }
    public void UpdateTableFormateur(){
        
        data1.clear();
        try {
            cnx = Connexion.getInstance().getConnection();
            String requete = "SELECT * FROM utilisateur WHERE role='ROLE_FORMATEUR' order by id desc";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                data1.add(new Utilisateur(rs.getInt("id"), rs.getString("email"), rs.getString("password"), rs.getString("nom"),rs.getString("prenom"),rs.getString("telephone"),rs.getString("adresse"),rs.getDate("date_naissance")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
        cid1.setCellValueFactory(new PropertyValueFactory<>("id"));
        colemail1.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("email"));
        colpassword1.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("password"));
        colnom1.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("nom"));
        colprenom1.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("prenom"));
        coltelephone1.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("telephone"));
        coladresse1.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("adresse"));
        coldate_naissance1.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("date_naissance"));
         
         
         //add cell of button edit 
         Callback<TableColumn<Utilisateur, String>, TableCell<Utilisateur, String>> cellFoctory = (TableColumn<Utilisateur, String> param) -> {
            // make cell containing buttons
            final TableCell<Utilisateur, String> cell = new TableCell<Utilisateur, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        
                      
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            Utilisateur u = tvformateur.getSelectionModel().getSelectedItem();
                            ServiceUtilisateur su = new ServiceUtilisateur();
                            su.supprimer(u);
                            UpdateTableFormateur();
                            
                           

                          

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            Utilisateur u= tvformateur.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("add.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            AddController addController = loader.getController();
                            addController.setUpdate(true);
                            addController.setTextField(u.getId(), u.getEmail(), u.getPassword(), u.getNom(), u.getPrenom(), u.getTelephone(), u.getAdresse(), u.getDate_naissance());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            

                           

                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);


                    }
                }

            };

            return cell;
        };
         colaction.setCellFactory(cellFoctory);
         tvformateur.setItems(data1);
         
         
    }
         
      @FXML
    private void getAddView(MouseEvent event) {
        try {
             
                Parent root =FXMLLoader.load(getClass().getResource("Add.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
             
        } catch (IOException ex) {
            System.err.println(ex);
        }
        
    }   
        
        void search1(){ 
     
     
       cid1.setCellValueFactory(new PropertyValueFactory<>("id")); 
       colemail1.setCellValueFactory(new PropertyValueFactory<>("email"));
        colnom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colprenom1.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        coltelephone1.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        tvformateur.setItems(data1);
             FilteredList<Utilisateur> filteredData = new FilteredList<>(data1, b -> true);
        search1.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(U -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (U.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } 
                if (U.getEmail().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } 
                if (U.getPrenom().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } 
                if (U.getTelephone().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } 
                else {
                    return false; // Does not match.
                }
            });
        });
        SortedList<Utilisateur> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvformateur.comparatorProperty());
        tvformateur.setItems(sortedData);    
    }
         @FXML
    private void print(MouseEvent event) {
     Printer printer = Printer.getDefaultPrinter();

        // Print page layout object.
        // Set "LANDSCAPE" as the page orientation for the convenience for the test.
        //   If the output pdf has the text information, the output file is shown in a PORTRAIT mode.
        //   If not, it will be shown in a LANDSCAPE mode.
        PageLayout layout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.DEFAULT);

        // Create a printer job.
        PrinterJob  job = PrinterJob.createPrinterJob();

        if (job != null) {
            // Set the job name.
            job.getJobSettings().setJobName("SOLUTION");

            if (job.showPrintDialog(this.page.getScene().getWindow())) {
                // Print out the specified pane.
                job.printPage(layout, this.tvformateur);
                
            }
            else {
                System.out.println("Print canceled.");
            }

            // Finish the print job.
            job.endJob();
        }
    }

    




private void UpdateTableAvis() {
        tvavis.getColumns().clear();
          
          liste = FXCollections.observableArrayList();
          try{
            cnx = Connexion.getInstance().getConnection();
            //sql string ifademiz. 
            String req = "SELECT * FROM utilisateur WHERE email=?";
            String temail= email.getText();
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, temail);
            ResultSet rs1 = pst.executeQuery();
             while (rs1.next()) {
            selectUti=rs1.getInt("id");
             }
             
            String SQL = "SELECT DISTINCT u.email,u.nom,u.prenom,a.note FROM utilisateur u JOIN avis a ON u.id=a.apprenant_id ";//
           //pst.setInt(1, selectUti);
           
            ResultSet rs = cnx.createStatement().executeQuery(SQL);
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                final int j = i;                
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setStyle("-fx-pref-width:250px;");
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                        return new SimpleStringProperty(param.getValue().get(j).toString());                        
                    }                    
                });
                 colemail.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("email"));
               
                tvavis.getColumns().addAll(col); 
                System.out.println("Column ["+i+"] ");
            }

            
            while(rs.next()){
                
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    
                    row.add(rs.getString(i));
                }
                System.out.println(row);
                liste.add(row);
            }

            
            tvavis.setItems(liste);
          }catch(Exception e){
              e.printStackTrace();
              System.out.println(e);             
          }
    }
    ////nidhal
     @FXML
    private TableView<sparkAide> table_aide ;

    @FXML
    private TableColumn<sparkAide, String> col_sujet;

    @FXML
    private TableColumn<sparkAide, String> col_probleme;
    
    @FXML
    private TextField text_s;

    @FXML
    private TextField text_p;

    @FXML
    private TextField filterAide;
    ObservableList<sparkAide> listM;
    ObservableList<sparkAide> dataList;
 
    
 int index = -1;
 
    @FXML
    private WebView ViewWeb;
    @FXML
    private TextField text_mail;
    @FXML
    private TableColumn<sparkAide, String> col_mail;

    @FXML
    public void addaide (){
     cnx=mysqlConnect.Connectdb() ;
     String sql="insert into aide (sujet,probleme,mail)values(?,?,?)" ;
 try{
     pst =cnx.prepareStatement(sql);
     pst.setString(1,text_s.getText());
     pst.setString(2,text_p.getText());
     pst.setString(3,text_mail.getText());
     pst.execute();
    JOptionPane.showMessageDialog(null,"aide add succes ");
     UpdateTable ();
     search_aide();
 }catch(Exception e){
     JOptionPane.showMessageDialog(null,e);
 }
 
 }
 
    @FXML
    void getSelected (){
   
    index= table_aide.getSelectionModel().getSelectedIndex();
   if (index  <= -1){
        return ;
  }
     text_s.setText(col_sujet.getCellData(index).toString()) ;
     text_p.setText(col_probleme.getCellData(index).toString());
     text_mail.setText(col_mail.getCellData(index).toString());
}
    
    @FXML
    public void Edit (){   
        try {
           cnx = mysqlConnect.Connectdb();
            String value1 = text_s.getText();
            String value2 = text_p.getText();
            String value3 = text_mail.getText();
          
            String sql = "update aide set sujet= '"+value1+"',probleme= '"+value2+"',mail= '"+value3+"' where sujet= '"+value1+"' ";
            pst= cnx.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Update");

           UpdateTable ();
           search_aide();
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    @FXML
        public void Delete(){
    cnx = mysqlConnect.Connectdb();
    String sql = "delete from aide where sujet= ?";
        try {
            pst = cnx.prepareStatement(sql);
            pst.setString(1, text_s.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Delete");
            UpdateTable() ;
            search_aide();
  
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    
    }
    
    public void UpdateTables (){
        
        col_sujet.setCellValueFactory(new PropertyValueFactory<sparkAide,String>("sujet")  );
        col_probleme.setCellValueFactory(new PropertyValueFactory<sparkAide,String>("probleme")  );
        col_mail.setCellValueFactory(new PropertyValueFactory<sparkAide,String>("mail")  );
        
         listM = mysqlConnect.getDatasparkAide() ;
         table_aide.setItems(listM);
    }
 
     void search_aide() {          
        col_sujet.setCellValueFactory(new PropertyValueFactory<sparkAide,String>("sujet"));
        col_probleme.setCellValueFactory(new PropertyValueFactory<sparkAide,String>("probleme"));
    
        
        dataList = mysqlConnect.getDatasparkAide() ;  
        table_aide.setItems(dataList);
        FilteredList<sparkAide> filteredData = new FilteredList<>(dataList, b -> true);  
      filterAide.textProperty().addListener((observable, oldValue, newValue) -> {
      filteredData.setPredicate(personaide -> {
    if (newValue == null || newValue.isEmpty()) {
     return true;
    }    
    String lowerCaseFilter = newValue.toLowerCase();
    
    if (personaide.getSujet().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
     return true; // Filter matches sujet
    } else if (personaide.getProbleme().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches probleme
    }       
         else  
          return false; // Does not match.
   });
  });  
  SortedList<sparkAide> sortedData = new SortedList<>(filteredData);  
  sortedData.comparatorProperty().bind(table_aide.comparatorProperty());  
  table_aide.setItems(sortedData);      
    }
    
    

    

   

    @FXML
    private void handleButtonAction(javafx.event.ActionEvent event) throws IOException {
          System.out.println("You clicked me!");
        Parent homepage = FXMLLoader.load(getClass().getResource("formateur.fxml"));
        Scene homepageScene = new Scene(homepage);
       Stage appStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
       appStage.setScene(homepageScene);
       appStage.show();
    }



    @FXML
    private void goGoogle(javafx.scene.input.MouseEvent event) {
         final WebEngine web =ViewWeb.getEngine() ;
        String urlweb ="https://www.google.com/";
        web.load(urlweb);
    }

    @FXML
    private void goYoutube(javafx.scene.input.MouseEvent event) {
           final WebEngine webYouTube =ViewWeb.getEngine() ;
        String urlweb ="https://www.youtube.com/";
        webYouTube.load(urlweb);
    }
        @FXML
    private TableView<sparkAide> table_aideF;
  
    @FXML
    private TableColumn<sparkAide, String> col_sujetF;
    
    @FXML
    private TableColumn<sparkAide, String> col_mailF;
    
    @FXML
    private TableColumn<sparkAide, String> col_problemeF;
    
    @FXML
    private TableView<sparkAideCom> table_com;
    @FXML
    private TextArea textF;
    @FXML
    private TableColumn<sparkAideCom, String> col_com;
    
    ObservableList<sparkAide> listee;
    ObservableList<sparkAideCom> listmCom;
   
     
     int selectedAide ;
     int selectedCom ;
    @FXML
    private AnchorPane imageV;
   
    @FXML
    private void addCom() {
        
     cnx=mysqlConnect.Connectdb() ;
     String sql="insert into aidecom (commaintre,id_sujet) values(?,?)" ;
   
     
     try{
     
     
     pst =cnx.prepareStatement(sql);
     pst.setString(1,textF.getText());
     pst.setInt(2,this.selectedAide);
     pst.execute();
     
    JOptionPane.showMessageDialog(null,"aidecom add succes ");
     UpdateTablecom ();
   
 }catch(Exception e){
     JOptionPane.showMessageDialog(null,e);
 }
    }
    
    
      public void UpdateTablecom (){
        
        col_com.setCellValueFactory(new PropertyValueFactory<>("commaintre")  );
        
        
         listmCom = mysqlConnect.getDatasparkAideCom(this.selectedAide) ;
         
         if (listmCom.size()!=0){
             
         table_com.setItems(listmCom);
           
         }
         else {
                      table_com.setItems(null);

         } 
    }
      

    @FXML
    private void affichage() {
        col_sujetF.setCellValueFactory(new PropertyValueFactory<>("sujet")  );
        col_problemeF.setCellValueFactory(new PropertyValueFactory<sparkAide,String>("probleme")  );
        col_mailF.setCellValueFactory(new PropertyValueFactory<sparkAide,String>("mail")  );
        
        listee = mysqlConnect.getDatasparkAide() ;
        table_aideF.setItems(listee);
    }

    @FXML
    private void suppCOM(ActionEvent event) {
          cnx = mysqlConnect.Connectdb();
    String sql = "delete from aidecom where id= ?";
        try {
            pst = cnx.prepareStatement(sql);
            pst.setInt(1, this.selectedCom);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Delete");
            UpdateTablecom() ;
           
  
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        
    }

    @FXML
    private void getCom(MouseEvent event) {
        
     sparkAideCom com = table_com.getSelectionModel().getSelectedItem();
        this.selectedCom = com.getId();   
            
     textF.setText(com.getCommaintre()) ;
    
    }
     @FXML
    public void EditCom (){   
        try {
           cnx = mysqlConnect.Connectdb();
         

            String sql = "update aidecom set commaintre= ? where id= ? ";
            pst= cnx.prepareStatement(sql);
            pst.setString(1,textF.getText() );
            pst.setInt(2, this.selectedCom);

            pst.execute();
            JOptionPane.showMessageDialog(null, "Update");

           UpdateTablecom ();
          
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }

    @FXML
    private void getSubject(MouseEvent event) {
       sparkAide com = table_aideF.getSelectionModel().getSelectedItem();
        this.selectedAide = com.getId();   
             UpdateTablecom ();
    
    }

    @FXML
    private void goMail(MouseEvent event) throws IOException {
        
            System.out.println("You clicked me!");
        Parent homepage = FXMLLoader.load(getClass().getResource("Mailing.fxml"));
        Scene homepageScene = new Scene(homepage);
       Stage appStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
       appStage.setScene(homepageScene);
       appStage.show();
    }

  

    @FXML
    private void pdf() {
     Printer printer = Printer.getDefaultPrinter();

        // Print page layout object.
        // Set "LANDSCAPE" as the page orientation for the convenience for the test.
        //   If the output pdf has the text information, the output file is shown in a PORTRAIT mode.
        //   If not, it will be shown in a LANDSCAPE mode.
        PageLayout layout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.DEFAULT);

        // Create a printer job.
        PrinterJob  job = PrinterJob.createPrinterJob();

        if (job != null) {
            // Set the job name.
            job.getJobSettings().setJobName("SOLUTION");

            if (job.showPrintDialog(this.page.getScene().getWindow())) {
                // Print out the specified pane.
                job.printPage(layout, this.table_com);
                
            }
            else {
                System.out.println("Print canceled.");
            }

            // Finish the print job.
            job.endJob();
        }
    }


    

}
