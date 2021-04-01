/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Catégorie;
import Entites.Cour;
import Entites.Formation;
import Entites.Inscription;
import Entites.Model;
import Entites.Session;
import Entites.Utilisateur;
import Entites.sparkAide;
import Entites.sparkAideCom;
import Services.ExcelMailAPI;
import Services.SendMailJava;
import Services.ServiceCour;
import Services.ServiceUtilisateur;
import Services.Servicecategorie;
import Services.mysqlConnect;
import Utils.Connexion;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.HostServices;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import org.controlsfx.control.Notifications;




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
     private AnchorPane profil;
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
    @FXML
    private Button btModifier7;
    @FXML
    private Button btValider7;
    
  
   
    
 
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
   boolean result;
   boolean resultformation;
    @FXML
    private Button btn1;
    @FXML
    private ImageView qr;
    @FXML
    private AnchorPane ajouterCour;
    @FXML
    private AnchorPane consulterCour;
    @FXML
    private AnchorPane ModifierCour;
    @FXML
    private TextField tfTitre1;
    @FXML
    private TextField tfFichier1;
    @FXML
    private Button btnstat;
    @FXML
    private PieChart pie;
    @FXML
    private AnchorPane statPane;
    @FXML
    private AnchorPane listeCourApp;
    @FXML
    private TableView<Catégorie> tvCategorie;
    @FXML
    private TableColumn<Catégorie, String> clDescription;
    @FXML
    private TextField tfDescription;
    @FXML
    private Label label_nom;
    @FXML
    private Label label_prenom;
    @FXML
    private Label label_adresse;
    @FXML
    private Label label_datenaissance;
    @FXML
    private Label label_email;
    @FXML
    private Label label_telephone;
    @FXML
    private AnchorPane PaneCategorie;
    Formation f=new Formation();
    @FXML
    private AnchorPane PaneAjouterFormation;
    @FXML
    private AnchorPane PaneChart;
    @FXML
    private BarChart<String,Number> chart;
    
    
   
    

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
         load();
         searchh();
          load5();
         search5();
         UpdateTablesFormation();
         try {
        // TODO
        stats();
    } catch (SQLException ex) {
        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
    }
         
          btValider7.setVisible(false);
        load7();
          combo_categorie.setItems(SV_Categorie.afficher_Categorie());
            combo_categorie.getSelectionModel().select(0);
        UpdateComboBox();
          ////
          
               int i,j,k,f,r,h,t;
        ServiceCour a = new ServiceCour();
       i=a.CountService("php");
       k=a.CountService("java");
       f=a.CountService("sql");
       r=a.CountService("html");
       h=a.CountService("mobile");
       t=a.CountService("javafx");
       
        ObservableList<PieChart.Data> pieChartData =
               FXCollections.observableArrayList(
                       new PieChart.Data("php",i),
                       new PieChart.Data("java",k),
                       new PieChart.Data("sql",f),
                       new PieChart.Data("html",r),
                        new PieChart.Data("mobile",h),
                         new PieChart.Data("javafx",t)
                       
               );
        pie.setData(pieChartData);
        
        
            
         
        
        
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
        profil.setVisible(false);
        ajouterCour.setVisible(false);
        consulterCour.setVisible(false);
        ModifierCour.setVisible(false);
        statPane.setVisible(false);
        listeCourApp.setVisible(false);
        PaneCategorie.setVisible(false);
        PaneAjouterFormation.setVisible(false);
        PaneChart.setVisible(false);
        
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
         profil.setVisible(false);
         ajouterCour.setVisible(false);
         consulterCour.setVisible(false);
         ModifierCour.setVisible(false);
          statPane.setVisible(false);
          listeCourApp.setVisible(false);
          PaneCategorie.setVisible(false);
          PaneAjouterFormation.setVisible(false);
          PaneChart.setVisible(false);
        
    }
    public void showPaneFormation(){
        
        if(result){
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
         profil.setVisible(false);
         ajouterCour.setVisible(false);
         consulterCour.setVisible(false);
         ModifierCour.setVisible(false);
          statPane.setVisible(false);
          listeCourApp.setVisible(false);
          PaneCategorie.setVisible(false);
          PaneAjouterFormation.setVisible(false);
          PaneChart.setVisible(false);
        }
        
    }
     @FXML
    public void showPaneAcceuil(){
        
        
        paneInscription.setVisible(false);
        paneFormation.setVisible(false);
        box.setVisible(false);
        acceuil.setVisible(true);
        hmenu.setVisible(false);
        menuadmin.setVisible(false);
        dashboard.setVisible(false);
        apprenant.setVisible(false);
        listeFormateur.setVisible(false);
         menuformateur.setVisible(false);
         listeAvis.setVisible(false);
         aide.setVisible(false);
         AideCom.setVisible(false);
         avis.setVisible(false);
         profil.setVisible(false);
         ajouterCour.setVisible(false);
         consulterCour.setVisible(false);
         ModifierCour.setVisible(false);
          statPane.setVisible(false);
          listeCourApp.setVisible(false);
          PaneCategorie.setVisible(false);
          PaneAjouterFormation.setVisible(false);
          PaneChart.setVisible(false);
        
        
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
         profil.setVisible(false);
         ajouterCour.setVisible(false);
         consulterCour.setVisible(false);
         ModifierCour.setVisible(false);
          statPane.setVisible(false);
          listeCourApp.setVisible(false);
          PaneCategorie.setVisible(false);
          PaneAjouterFormation.setVisible(false);
          PaneChart.setVisible(false);
    }
    @FXML
    public void showChart(){
        
        paneInscription.setVisible(false);
        paneFormation.setVisible(false);
        box.setVisible(false);
        acceuil.setVisible(false);
        hmenu.setVisible(false);
        menuadmin.setVisible(true);
        dashboard.setVisible(false);
        apprenant.setVisible(false);
        listeFormateur.setVisible(false);
         menuformateur.setVisible(false);
         listeAvis.setVisible(false);
         aide.setVisible(false);
         AideCom.setVisible(false);
         profil.setVisible(false);
         ajouterCour.setVisible(false);
         consulterCour.setVisible(false);
         ModifierCour.setVisible(false);
          statPane.setVisible(false);
          listeCourApp.setVisible(false);
          PaneCategorie.setVisible(false);
          PaneAjouterFormation.setVisible(false);
          PaneChart.setVisible(true);
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
         profil.setVisible(false);
         ajouterCour.setVisible(false);
         consulterCour.setVisible(false);
         ModifierCour.setVisible(false);
          statPane.setVisible(false);
          listeCourApp.setVisible(false);
          PaneCategorie.setVisible(false);
          PaneAjouterFormation.setVisible(false);
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
         profil.setVisible(false);
         ajouterCour.setVisible(false);
         consulterCour.setVisible(false);
         ModifierCour.setVisible(false);
          statPane.setVisible(false);
          listeCourApp.setVisible(false);
          PaneCategorie.setVisible(false);
          PaneAjouterFormation.setVisible(false);
          PaneChart.setVisible(false);
    }
    @FXML
    public void showajouterCour(){
        
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
         profil.setVisible(false);
         ajouterCour.setVisible(true);
         consulterCour.setVisible(false);
         ModifierCour.setVisible(false);
          statPane.setVisible(false);
          listeCourApp.setVisible(false);
          PaneCategorie.setVisible(false);
          PaneAjouterFormation.setVisible(false);
          PaneChart.setVisible(false);
    }
    @FXML
    public void showStatCour(){
        
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
         profil.setVisible(false);
         ajouterCour.setVisible(false);
         consulterCour.setVisible(false);
         ModifierCour.setVisible(false);
          statPane.setVisible(true);
          listeCourApp.setVisible(false);
          PaneCategorie.setVisible(false);
          PaneAjouterFormation.setVisible(false);
          PaneChart.setVisible(false);
    }
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
         profil.setVisible(false);
         ajouterCour.setVisible(false);
         consulterCour.setVisible(false);
         ModifierCour.setVisible(false);
         statPane.setVisible(false);
         listeCourApp.setVisible(false);
         PaneCategorie.setVisible(false);
         PaneAjouterFormation.setVisible(false);
         PaneChart.setVisible(false);
         
    }
    public void showAjouterCour(){
        
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
         AideCom.setVisible(false);
         profil.setVisible(false);
         ajouterCour.setVisible(true);
         consulterCour.setVisible(false);
         ModifierCour.setVisible(false);
         statPane.setVisible(false);
         listeCourApp.setVisible(false);
         PaneCategorie.setVisible(false);
         PaneAjouterFormation.setVisible(false);
         PaneChart.setVisible(false);
    }
    @FXML
    public void showAjouterCatégorie(){
        
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
         AideCom.setVisible(false);
         profil.setVisible(false);
         ajouterCour.setVisible(false);
         consulterCour.setVisible(false);
         ModifierCour.setVisible(false);
         statPane.setVisible(false);
         listeCourApp.setVisible(false);
         PaneCategorie.setVisible(true);
         PaneAjouterFormation.setVisible(false);
         PaneChart.setVisible(false);
    }
     @FXML
    public void showConsulterCour(){
        
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
         AideCom.setVisible(false);
         profil.setVisible(false);
         ajouterCour.setVisible(false);
         consulterCour.setVisible(true);
         ModifierCour.setVisible(false);
         statPane.setVisible(false);
         listeCourApp.setVisible(false);
         PaneCategorie.setVisible(false);
         PaneAjouterFormation.setVisible(false);
         PaneChart.setVisible(false);
    }
     @FXML
    public void showModifierCour(){
        
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
         AideCom.setVisible(false);
         profil.setVisible(false);
         ajouterCour.setVisible(false);
         consulterCour.setVisible(false);
         ModifierCour.setVisible(true);
         statPane.setVisible(false);
         listeCourApp.setVisible(false);
         PaneCategorie.setVisible(false);
         PaneAjouterFormation.setVisible(false);
         PaneChart.setVisible(false);
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
         profil.setVisible(false);
         ajouterCour.setVisible(false);
         consulterCour.setVisible(false);
         ModifierCour.setVisible(false);
         statPane.setVisible(false);
         listeCourApp.setVisible(false);
         PaneCategorie.setVisible(false);
         PaneAjouterFormation.setVisible(false);
         PaneChart.setVisible(false);
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
          profil.setVisible(false);
          ajouterCour.setVisible(false);
          consulterCour.setVisible(false);
          ModifierCour.setVisible(false);
          statPane.setVisible(false);
          listeCourApp.setVisible(false);
          PaneCategorie.setVisible(false);
          PaneAjouterFormation.setVisible(false);
          PaneChart.setVisible(false);
   
    }
      @FXML
    public void showAjouterFormation(){
        paneInscription.setVisible(false);
        paneFormation.setVisible(false);
        box.setVisible(false);
         avis.setVisible(false);
         acceuil.setVisible(false);
         hmenu.setVisible(false);
         menuadmin.setVisible(true);
         dashboard.setVisible(false);
         apprenant.setVisible(false);
         listeFormateur.setVisible(false);
          menuformateur.setVisible(false);
          listeAvis.setVisible(false);
          aide.setVisible(false);
          AideCom.setVisible(false);
          profil.setVisible(false);
          ajouterCour.setVisible(false);
          consulterCour.setVisible(false);
          ModifierCour.setVisible(false);
          statPane.setVisible(false);
          listeCourApp.setVisible(false);
          PaneCategorie.setVisible(false);
          PaneAjouterFormation.setVisible(true);
          PaneChart.setVisible(false);
   
    }
    @FXML
    public void showListeCourApp(){
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
          profil.setVisible(false);
          ajouterCour.setVisible(false);
          consulterCour.setVisible(false);
          ModifierCour.setVisible(false);
          statPane.setVisible(false);
          listeCourApp.setVisible(true);
          PaneCategorie.setVisible(false);
          PaneAjouterFormation.setVisible(false);
          PaneChart.setVisible(false);
   
    }
    @FXML
    public void showprofil(){
        updateProfil();
        paneInscription.setVisible(false);
        paneFormation.setVisible(false);
        box.setVisible(false);
         avis.setVisible(false);
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
          profil.setVisible(true);
          ajouterCour.setVisible(false);
          consulterCour.setVisible(false);
          ModifierCour.setVisible(false);
          statPane.setVisible(false);
          listeCourApp.setVisible(false);
          PaneCategorie.setVisible(false);
          PaneAjouterFormation.setVisible(false);
          PaneChart.setVisible(false);
           QRCode();
   
    }
    @FXML
    
    public void showinit(){
        
       paneInscription.setVisible(false);
        paneFormation.setVisible(false);
        box.setVisible(false);
         avis.setVisible(true);
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
          profil.setVisible(false);
          ajouterCour.setVisible(false);
          consulterCour.setVisible(false);
          ModifierCour.setVisible(false);
          statPane.setVisible(false);
          listeCourApp.setVisible(false);
          PaneCategorie.setVisible(false);
          PaneAjouterFormation.setVisible(false);
          PaneChart.setVisible(false);
    }
    
    @FXML
    public void showModifierprofil(){
        try {
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
            profil.setVisible(false);
            ajouterCour.setVisible(false);
            consulterCour.setVisible(false);
            ModifierCour.setVisible(false);
            statPane.setVisible(false);
            listeCourApp.setVisible(false);
            PaneCategorie.setVisible(false);
            PaneAjouterFormation.setVisible(false);
            PaneChart.setVisible(false);
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
  
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
         ajouterCour.setVisible(false);
         consulterCour.setVisible(false);
         ModifierCour.setVisible(false);
         statPane.setVisible(false);
         listeCourApp.setVisible(false);
         PaneCategorie.setVisible(false);
         PaneAjouterFormation.setVisible(false);
         PaneChart.setVisible(false);
        
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
         ajouterCour.setVisible(false);
         consulterCour.setVisible(false);
         ModifierCour.setVisible(false);
         statPane.setVisible(false);
         listeCourApp.setVisible(false);
         PaneCategorie.setVisible(false);
         PaneAjouterFormation.setVisible(false);
         PaneChart.setVisible(false);
       
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
         ajouterCour.setVisible(false);
         consulterCour.setVisible(false);
         ModifierCour.setVisible(false);
         statPane.setVisible(false);
         listeCourApp.setVisible(false);
         PaneCategorie.setVisible(false);
         PaneAjouterFormation.setVisible(false);
        
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
         ajouterCour.setVisible(false);
         consulterCour.setVisible(false);
         ModifierCour.setVisible(false);
         statPane.setVisible(false);
         listeCourApp.setVisible(false);
         PaneCategorie.setVisible(false);
         PaneAjouterFormation.setVisible(false);
         PaneChart.setVisible(false);
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
         ajouterCour.setVisible(false);
         consulterCour.setVisible(false);
         ModifierCour.setVisible(false);
         statPane.setVisible(false);
         listeCourApp.setVisible(false);
         PaneCategorie.setVisible(false);
         PaneAjouterFormation.setVisible(false);
         PaneChart.setVisible(false);
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
         ajouterCour.setVisible(false);
         consulterCour.setVisible(false);
         ModifierCour.setVisible(false);
         statPane.setVisible(false);
         listeCourApp.setVisible(false);
         PaneCategorie.setVisible(false);
         PaneAjouterFormation.setVisible(false);
         PaneChart.setVisible(false);
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
         ajouterCour.setVisible(false);
         consulterCour.setVisible(false);
         ModifierCour.setVisible(false);
         statPane.setVisible(false);
         listeCourApp.setVisible(false);
         PaneCategorie.setVisible(false);
         PaneAjouterFormation.setVisible(false);
         PaneChart.setVisible(false);
    }

    
    @FXML
    public void Connection (ActionEvent event) throws Exception{
        if(email.getText().isEmpty()&&password.getText().isEmpty()){
             JOptionPane.showMessageDialog(null, "SVP veuillez remplir les champs");
        }
        else{
               String crypte="";
        for (int i=0; i<password.getText().length();i++)  {
            int c=password.getText().charAt(i)^48; 
            crypte=crypte+(char)c;
        }
            cnx = Connexion.getInstance().getConnection();
       String sql = "select * from utilisateur where email=? and password=?";
        try {
            pst =cnx.prepareStatement(sql);
            pst.setString(1,email.getText());
            pst.setString(2,crypte);
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
       
    
  
    }
    final String pattern = "^[A-Za-z0-9+_.-]+@(.+)$";
    public static boolean pregMatch(String pattern, String content){
    return content.matches(pattern);}
    
    @FXML
        public void Save(ActionEvent event){
            
           if(email.getText().isEmpty()&&password.getText().isEmpty()&&nom.getText().isEmpty()&&prenom.getText().isEmpty()&&telephone.getText().isEmpty()&&adresse.getText().isEmpty()&&date_naissance.getEditor().getText().isEmpty()){
                 JOptionPane.showMessageDialog(null, "SVP veuillez remplir tous les champs correctement");
           }else if (pregMatch(pattern, email.getText())){
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
        result = uc.getByEmail(u);
        SendMailJava.sendMail(temail,"bienvenue","votre inscription est enregistrer"); 
         showPaneFormation();
         
        
         if (result){
             JOptionPane.showMessageDialog(null, "votre inscription ajouter avec succes");
            
        }
        else{
            JOptionPane.showMessageDialog(null, "l'apprenant est déja existe");
        }
        
        
            } else{
                JOptionPane.showMessageDialog(null, "email incorrect");
                 
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
        if(formation.getSelectionModel().getSelectedItem() == null){
            JOptionPane.showMessageDialog(null, "Chosisser votre  formation");
        }
        else{
            try {
            cnx = Connexion.getInstance().getConnection(); 
            getIdUtilisateur();
            getIdFormation();
             
           String requete = "INSERT INTO inscription(utilisateur_id,formation_id,date_inscrit) VALUES (?,?,?)";
             pst= cnx.prepareStatement(requete);
            pst.setInt(1,selectUti);
            pst.setInt(2,selectedFor);
           long millis=System.currentTimeMillis();
           java.sql.Date date=new java.sql.Date(millis);
            pst.setDate(3, date);
            resultformation=pst.execute();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);
        }
        }

        
    }
    private void getIdFormation() {
        
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
    private void getIdUtilisateur() {
        try {
        cnx = Connexion.getInstance().getConnection();
        String req = "SELECT * FROM utilisateur WHERE email=?";
            String temail= email.getText();
            PreparedStatement pst;
        
            pst = cnx.prepareStatement(req);
            pst.setString(1, temail);
            ResultSet rs = pst.executeQuery();
             while (rs.next()) {
            selectUti=rs.getInt("id");
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
        
        if(formateur.getSelectionModel().getSelectedItem() == null){
            JOptionPane.showMessageDialog(null, "Chosisser votre formateur");
        }
        else if((hangry.isVisible()||hnothappy.isVisible()||hhappy.isVisible())){
               try {
            cnx = Connexion.getInstance().getConnection();
            getIdUtilisateur();
            
           String requete = "INSERT INTO avis(formateur_id,apprenant_id,note) VALUES (?,?,?)";
             pst= cnx.prepareStatement(requete);
            pst.setInt(1,selectedFormateur);
            pst.setInt(2,selectUti);
            if(hangry.isVisible()){
               pst.setInt(3, 1);
               pst.execute();
            JOptionPane.showMessageDialog(null, "Merci votre choix est enregistré");
            }else if(hnothappy.isVisible()){
                pst.setInt(3, 2); 
                pst.execute();
            JOptionPane.showMessageDialog(null, "Merci votre choix est enregistré");
            }else if(hhappy.isVisible()){
                pst.setInt(3, 3); 
                pst.execute();
            JOptionPane.showMessageDialog(null, "Merci votre choix est enregistré");
            }
          
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);
        }
        }else{
            JOptionPane.showMessageDialog(null, "choisissez votre smile");
        }
        } 
    
    @FXML
    private void getFormateur() {
        
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
            updateProfil();
       

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
    @FXML
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
                                + "-fx-fill: #2b76a4;"
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
                    String req = "SELECT * FROM utilisateur WHERE email=?";
                    String temail= email.getText();
                    PreparedStatement pst = cnx.prepareStatement(req);
                    pst.setString(1, temail);
                    ResultSet rs1 = pst.executeQuery();
                     while (rs1.next()) {
                    selectUti=rs1.getInt("id");
                     }

                    String SQL = "SELECT DISTINCT u.email,u.nom,u.prenom,a.note FROM utilisateur u JOIN avis a ON u.id=a.apprenant_id WHERE formateur_id=? ";
                    PreparedStatement pst1 = cnx.prepareStatement(SQL);
                    pst1.setInt(1, selectUti);
                    ResultSet rs = pst1.executeQuery();

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

            @FXML
            private void envoimail() throws IOException {
                 ExcelMailAPI.excel();
            }
            @FXML
            private void déconnexion() throws IOException {
                 email.setText("");
                 password.setText("");
                 nom.setText("");
                 prenom.setText("");
                 date_naissance.getEditor().setText("");
                 telephone.setText("");
                 adresse.setText("");

            
                 
            }
            private void updateProfil() {
              
        try {
              String req = "SELECT * FROM utilisateur WHERE email=?";
            String temail= email.getText();
            pst = cnx.prepareStatement(req);
            
            pst.setString(1, temail);
            rs = pst.executeQuery();
            while (rs.next()) {
                label_email.setText(rs.getString("email"));
                label_nom.setText(rs.getString("nom"));
                label_prenom.setText(rs.getString("prenom"));
                label_telephone.setText(rs.getString("telephone"));
                label_adresse.setText(rs.getString("adresse"));
                label_datenaissance.setText(rs.getString("date_naissance"));
                 
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
                
            }
            
            private void QRCode(){
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            
            String data = email.getText();
            int width = 300;
            int height = 300;
            
            BufferedImage bufferedImage = null;
            try {
                BitMatrix byteMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, width, height);
                bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                bufferedImage.createGraphics();
                
                Graphics2D image = (Graphics2D) bufferedImage.getGraphics();
                image.setColor(Color.WHITE);
                image.fillRect(0, 0, width, height);
                image.setColor(Color.BLACK);
                
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        if (byteMatrix.get(i, j)) {
                            image.fillRect(i, j, 1, 1);
                        }
                    }
                }
                
                System.out.println("QR created successfully....");
                
            } catch (WriterException ex) {
                 //Todo
            }
            
            
            qr.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
        
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
     String sql="insert into aide (utilisateur_id,sujet,probleme,mail)values(?,?,?,?)" ;
 try{
     getIdUtilisateur();
     pst =cnx.prepareStatement(sql);
            pst.setInt(1,selectUti); 
     pst.setString(2,text_s.getText());
     pst.setString(3,text_p.getText());
     pst.setString(4,text_mail.getText());
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
    private void goMail(MouseEvent event) {
        
            System.out.println("You clicked me!");
        Parent homepage;
        try {
            homepage = FXMLLoader.load(getClass().getResource("Mailing.fxml"));
             Scene homepageScene = new Scene(homepage);
       Stage appStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
       appStage.setScene(homepageScene);
       appStage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       
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

     
//hiba
    
    
    
     @FXML
    private TextField tfTitre;
    @FXML
    private TextField tfFichier;
    @FXML
    private Button btAjouter;
    ServiceCour sc = new ServiceCour();
    @FXML
    private Button btnAnnuler;
    @FXML
    private ComboBox<String> form;
  
    ObservableList <String> f1 = FXCollections.observableArrayList();

    @FXML
    private Button browse;
    private int selectFormation;
    
    private File file;
    private PreparedStatement stat;
   
    @FXML
    private ComboBox<String> combo_categorie;
    Servicecategorie SV_Categorie = new Servicecategorie();    
    @FXML
    private Button btn_categorie;
    
    @FXML
    public void enregistrer(ActionEvent event) {
  
         
         
        if (!"".equals(tfTitre.getText()) && !"".equals(tfFichier.getText())) {
            Cour c = new Cour();
            getbyIdFormation();
            c.setTitre(tfTitre.getText());
            c.setFichier(tfFichier.getText());
            c.setFormation_id(selectFormation);
            c.setDescription_cat(combo_categorie.getSelectionModel().getSelectedItem());

            sc.ajouter(c);
                   Notifications notificationBuilder = Notifications.create()
                 
                                                     .title("cour ajouté")
                                                     .text("ajout avec succés")
                                            
                                                     
                                                     .hideAfter(javafx.util.Duration.seconds(2) )
                                                      .position(Pos.TOP_LEFT) ;
         notificationBuilder.show();
        
            tfTitre.clear();
            tfFichier.clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("Please Fill the Fields");
            alert.setContentText("*You Have Missed to fill some Fields");
            alert.showAndWait();
        }
    }
        private void getbyIdFormation() {
        
        String s = form.getSelectionModel().getSelectedItem();
            try {
                 String req = "SELECT * FROM formation WHERE titre=?";
            stat = cnx.prepareStatement(req);
                stat.setString(1, s);
                rs = stat.executeQuery();
             while (rs.next()) {
            selectFormation=rs.getInt("id");
             }
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        
        
        public void UpdateComboBox(){
        
        
        try {
            cnx = Connexion.getInstance().getConnection();
            String requete = "SELECT * FROM formation order by id desc";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
               
            f1.add(rs.getString("titre")); 
            
            }
       form.setItems(f1);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @FXML
    private void Browse(ActionEvent event) throws SQLException   {
         stat=cnx.prepareStatement("INSERT INTO cour(titre,fichier) VALUES (?,?)");
         FileChooser fc = new FileChooser();
         File selectedFile = fc.showOpenDialog(null);
         if(selectedFile != null){
             tfFichier.appendText(selectedFile.getAbsolutePath());
            // Image image =new Image(file.toURI().toString(),80 ,80, true,true);
             //tfFichier.setText(selectedFile.getName());
           // tfFichier.getText().add(selectedFile.getName());
             
         }else {
             System.out.print("FILE IS NOT VALID");
         }
        
     
         }
        
        
    
    
    
    
    

    @FXML
    public void annuler(ActionEvent event) {
         
          
    }

    
    

    
   
    private void handleClicks(ActionEvent event) throws IOException {
          if (event.getSource() == btn_categorie) {
            
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            // hnee badll
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/gui/CRUDCategorie.fxml")));
            stage.setScene(scene);
            stage.show();

        }
    }
     @FXML
    private Button btSupprimer;
    @FXML
    private Button btModifier;
    
    @FXML
    private TableView<Cour> tvCour;
    @FXML
    private TableColumn<Cour, String> clTitre;
    @FXML
    private TableColumn<Cour, String> clFichier;
    @FXML
    private TextField searchh;

    List<Cour> listCours;
    ServiceCour scc = new ServiceCour();
    ObservableList<Cour> data6;
    
    
    
    Cour Cour;

   
    @FXML
    private TableColumn<Cour, String> clCategorie;
    
    @FXML
    private void chercher(ActionEvent event) {
          ObservableList table = tvCour.getItems();
        
        searchh.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
	            if (oldValue != null && (newValue.length() < oldValue.length())) {
	                tvCour.setItems(table);
	            }
	            String value = newValue.toLowerCase();
	            ObservableList<Cour> subentries = FXCollections.observableArrayList();

	            long count = tvCour.getColumns().stream().count();
	            for (int i = 0; i < tvCour.getItems().size(); i++) {
	                for (int j = 0; j < count; j++) {
	                    String entry = "" + tvCour.getColumns().get(j).getCellData(i);
	                    //if (entry.toLowerCase().equals(value)
                                    
                            if (entry.toLowerCase().contains(CharSequence.class.cast(value))) 
                            {
	                        subentries.add(tvCour.getItems().get(i));
                                
	                        break;
                            }
	                }
	            }
	            tvCour.setItems(subentries);
	        });
	        load();
    }
       private void load() {
         tvCour.setVisible(true);
        listCours = scc.afficher();
        data6 = FXCollections.observableArrayList();
        if (!listCours.isEmpty()) {
            listCours.stream().forEach((j) -> {
                if (j != null) {
                            data6.add(new Cour(j.getID(), j.getTitre(), j.getDescription_cat(),j.getFichier()));
                    tvCour.setItems(data6);
                }
            });
        }
        clTitre.setCellValueFactory(new PropertyValueFactory<>("Titre"));
        clFichier.setCellValueFactory(new PropertyValueFactory<>("Fichier"));
        
         clCategorie.setCellValueFactory(new PropertyValueFactory<>("Description_cat"));
        
        
        
        
        
        
        
        
        
        
        

    }
       public void searchh() {
        searchh.setOnKeyReleased(e
                -> {
            if (searchh.getText().equals("") ) {

                try {
                    load();
                } catch (Exception ex) {
                }

            } else {

                try {
                    clTitre.setCellValueFactory(new PropertyValueFactory<>("Titre"));
        clFichier.setCellValueFactory(new PropertyValueFactory<>("Fichier"));
        
         clCategorie.setCellValueFactory(new PropertyValueFactory<>("Description_cat"));
                    tvCour.getItems().clear();

                    tvCour.setItems(sc.serach(searchh.getText()));

                } catch (Exception ex) {
                }
        

            }
        }
        );

    } 

    
       
    

    
private Cour cour = new Cour();
    ServiceCour sc1 = new ServiceCour();
    @FXML
    private void supprimer(ActionEvent event) {

        Cour c = tvCour.getSelectionModel().getSelectedItem();
        sc1.supprimer(c);
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            data5.clear();
            listCours.clear();
            load();
        } else {
            System.out.println("yessss");
        }
        

    }

    @FXML
    private void enregistrerr(ActionEvent event) throws IOException {

        this.cour.setFichier(tfFichier1.getText());
        this.cour.setTitre(tfTitre1.getText());
        System.out.println("updatedCour:" + this.cour);
        sc1.modifier(this.cour);
        tfTitre1.clear();
        tfFichier1.clear();

       


    }


    public void setCour(Cour cour) {
        this.cour = cour;
    }

    public void initData(Cour recievedCour) {
        System.out.println("cour : " + recievedCour);
        if (recievedCour != null) {
            this.cour.setID(recievedCour.getID());
            this.cour.setFichier(recievedCour.getFichier());
            this.cour.setTitre(recievedCour.getTitre());
            tfTitre1.setText(recievedCour.getTitre());
            
            tfFichier1.setText(recievedCour.getFichier());
        }
    }
    
    
    ObservableList<Cour> data5;
    @FXML
    private TableView<Cour> tvCour5;
    @FXML
    private TableColumn<Cour, String> clTitre5;
    @FXML
    private TableColumn<Cour, String> clFichier5;
    @FXML
    private TableColumn<Cour, String> clCategorie5;
    @FXML
    private TextField search5;
    
        List<Cour> listCours5;
    ServiceCour sc5= new ServiceCour();
    @FXML
    private void chercher5(ActionEvent event) {
          ObservableList table = tvCour5.getItems();
        
        search5.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
	            if (oldValue != null && (newValue.length() < oldValue.length())) {
	                tvCour5.setItems(table);
	            }
	            String value = newValue.toLowerCase();
	            ObservableList<Cour> subentries = FXCollections.observableArrayList();

	            long count = tvCour5.getColumns().stream().count();
	            for (int i = 0; i < tvCour5.getItems().size(); i++) {
	                for (int j = 0; j < count; j++) {
	                    String entry = "" + tvCour5.getColumns().get(j).getCellData(i);
	                    //if (entry.toLowerCase().equals(value)
                                    
                            if (entry.toLowerCase().contains(CharSequence.class.cast(value))) 
                            {
	                        subentries.add(tvCour5.getItems().get(i));
                                
	                        break;
                            }
	                }
	            }
	            tvCour5.setItems(subentries);
	        });
	        load();
    }
       private void load5() {
         tvCour5.setVisible(true);
        listCours5 = sc5.afficher();
        data5 = FXCollections.observableArrayList();
        if (!listCours5.isEmpty()) {
            listCours5.stream().forEach((j) -> {
                if (j != null) {
                            data5.add(new Cour(j.getID(), j.getTitre(), j.getDescription_cat(),j.getFichier()));
                    tvCour5.setItems(data5);
                }
            });
        }
        clTitre5.setCellValueFactory(new PropertyValueFactory<>("Titre"));
        clFichier5.setCellValueFactory(new PropertyValueFactory<>("Fichier"));
        
         clCategorie5.setCellValueFactory(new PropertyValueFactory<>("Description_cat"));
        
 

    }
          public void search5() {
        search5.setOnKeyReleased(e
                -> {
            if (search5.getText().equals("") ) {

                try {
                    load();
                } catch (Exception ex) {
                }

            } else {

                try {
                    clTitre5.setCellValueFactory(new PropertyValueFactory<>("Titre"));
        clFichier5.setCellValueFactory(new PropertyValueFactory<>("Fichier"));
        
         clCategorie5.setCellValueFactory(new PropertyValueFactory<>("Description_cat"));
                    tvCour5.getItems().clear();

                    tvCour5.setItems(sc.serach(search.getText()));

                } catch (Exception ex) {
                }
        

            }
        }
        );

    } 

    List<Catégorie> listCategorie;
    Servicecategorie sc7 = new Servicecategorie();
    ObservableList<Catégorie> data7;
    Catégorie categoriePourModif = null;
    
     public void load7() {
       // String query="SELECT categorie.id_catégorie,categorie.type,cour.titre,cour.fichier FROM categorie,cour WHERE categorie.cour=cour.titre";
        tvCategorie.setVisible(true);
        listCategorie = sc7.afficher();
        //tableau dans javafx
        data7 = FXCollections.observableArrayList(); //
        if (!listCategorie.isEmpty()) {//on teste si on a des dpnnes dans la bd pour remplir le tableau dans l'interface graphoquique 
            listCategorie.stream().forEach((j) -> {
                if (j != null) {
                    //on charge les donnes 
                    data7.add(new Catégorie(j.getId_catégoerie(), j.getDescription()));
                    tvCategorie.setItems(data7);
                }

            });
        }
        //on affecte la colonne au tablea 
        clDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
       
    }

    
    
    
    
    
    
    @FXML
    private void AjouterCategorie(ActionEvent event) {
         //on teste si les champs de texte ne sont pas vide
        if (!"".equals(tfDescription.getText())) {
            Catégorie c = new Catégorie();
            //on prend les donnes a partire de l'intergface
            c.setDescription(tfDescription.getText());
            
            sc7.ajouter(c);
            tfDescription.clear();
         
            data7.add(c);
            load();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("Please Fill the Fields");
            alert.setContentText("*You Have Missed to fill some Fields");
            alert.showAndWait();
        } 
           //Image img = new Image("/checks.jpg");
         
        
        
        
    }

    @FXML
    private void ModifierCategorie(ActionEvent event) {
           categoriePourModif = tvCategorie.getSelectionModel().getSelectedItem();
        tfDescription.setText(categoriePourModif.getDescription());
        btModifier7.setVisible(false);
        btValider7.setVisible(true);
        
    }

    @FXML
    private void ValiderModification(ActionEvent event) {
          if (!tfDescription.getText().equals(categoriePourModif.getDescription()))
                 {

            categoriePourModif.setDescription(tfDescription.getText());
            sc7.modifier(categoriePourModif);
            tfDescription.clear();
            
            load7();
        }
        btModifier7.setVisible(true);
        btValider7.setVisible(false);
        
        
        
        
    }
    
    
    

    @FXML
    private void SupprimerCategorie(ActionEvent event) {
        
          Catégorie c = tvCategorie.getSelectionModel().getSelectedItem();
        sc7.supprimer(c);
       
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            data7.clear();
            listCategorie.clear();
            load7();
        } else {
            System.out.println("yessss");
        }
    }
    //noura
    
    
    AnchorPane content;
    @FXML
    private Label label;
      @FXML
    private Label message;
       @FXML
    private TextField titre;
     @FXML
    private TextField prix;
     @FXML
    private TextArea description;
       @FXML
    private javafx.scene.control.TableView<Formation> table;


    @FXML
    private TableColumn<Formation, String> ttitre;

    @FXML
    private TableColumn<Formation, String> tdescription;

    @FXML
    private TableColumn<Formation, Integer> tprix;
    ResultSet rs8;
    @FXML
    private Button button;
    @FXML
    private Button modif;
    @FXML
    private Button supression;
    @FXML
    private void handlestat(ActionEvent event) throws IOException {
 Node node;
node = (Node)FXMLLoader.load(getClass().getResource("Calendar.fxml"));
content.getChildren().setAll(node);    }
    
    
    
void initiale() throws SQLException
{

   read();

}

         
    
 
 void read() throws SQLException
 {
 
  ResultSet rs8=f.listFormation();
     ObservableList<Formation> list5 = FXCollections.observableArrayList();
    while(rs8.next())
    {
    Formation x=new Formation();
   
        x.setDescription(rs8.getString("description"));
        x.setTitre(rs8.getString("titre"));
        x.setPrix(rs8.getInt("prix"));
        
        list5.add(x);

    }
 }
    @FXML
    private void handleButtonAction1(ActionEvent event) throws SQLException, IOException {
        System.out.println("Ajout!");
      add();
       
    }
       @FXML
    private void handleModifAction(ActionEvent event) throws SQLException, IOException {
         up();
    }
    
          @FXML
    private void handledeleteAction(ActionEvent event) throws SQLException, IOException {
      delete();
    }
    void add() throws IOException 
    {
                  
    Formation f= new Formation(titre.getText(), Integer.parseInt(prix.getText()), description.getText());
    f.createFormation();
          
  
          {
          message.setText("prix non numérique!");
          
          }
    
    }
      void up() throws SQLException, IOException
    {
                 try
  {
    Formation f= new Formation(titre.getText(), Integer.parseInt(prix.getText()), description.getText());
    f.setId(Integer.parseInt(label.getText()));
    f.updateFormation(f.getId());
          
  }              catch(Exception ex)
          {
          message.setText("titre existant ou prix non numérique!");
          
          }
    }
          void delete() throws SQLException
    {
        try{
    Formation f= new Formation();
    f.setId(Integer.parseInt(label.getText()));
    f.deleteFormation(f.getId());

        }
              catch(Exception ex)
          {
          message.setText("titre existant ou prix non numérique!");
          
          }
    
    }
     void plot()
    {
        Formation m=table.getSelectionModel().getSelectedItem();
     System.out.print(m.toString());
     titre.setText(String.valueOf(m.getTitre()));
     prix.setText(String.valueOf(m.getPrix()));
          description.setText(String.valueOf(m.getDescription()));
          label.setText(String.valueOf(m.getId()));

  
     
     
    }
      public void UpdateTablesFormation(){
          
            
        try {
            table.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> plot());
            
            
            ResultSet rs=f.listFormation();
            ObservableList<Formation> list = FXCollections.observableArrayList();
            while(rs.next())
            {
                Formation x=new Formation();
               
                x.setDescription(rs.getString("description"));
                x.setTitre(rs.getString("titre"));
                x.setPrix(rs.getInt("prix"));
                list.add(x);
                
            }
            
            ttitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
            tprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            tdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            
            
            table.setItems(list);
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    XYChart.Series<String,Number> series=new XYChart.Series<>();
  Session s=new Session();
    
void stats() throws SQLException    
{
    ResultSet rs=s.charts();
  
    while (rs.next())
    {
        System.out.println(rs.getString("formation")+" "+ rs.getInt("duree"));
      
    series.getData().add(new XYChart.Data<>(rs.getString("formation"), rs.getInt("duree")));
    System.out.println(series.getData().toString());
    }
    chart.getData().add(series);
}

    @FXML
    private void handlenextAction(ActionEvent event) {
    }

    @FXML
    private void refresh(ActionEvent event) {
    }


          
}
