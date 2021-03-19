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
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Hp Omen
 */
public class InterfaceAdminController implements Initializable {
    
     Connection cnx;
     int index=-1;
    @FXML
    private TextField id;
    @FXML
    private  TextField email;
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
    private TableView<Utilisateur> tvformateur;
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
    private TableColumn<Utilisateur, Date> coldate_naissance1;
    ObservableList<Utilisateur> data1 = FXCollections.observableArrayList();
    
    @FXML
    private TableView<Utilisateur> tvapprenant;
    @FXML
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
    private Button btnajouter;
    @FXML
    private Button btnajouterformateur;
    @FXML
    private Button btnmodifierformateur;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;
    @FXML
     private VBox dashboard;
     @FXML
     private VBox apprenant;
     @FXML
     private VBox formateur;
     @FXML
     private VBox ajouterformateur;
   
    
    ObservableList<Utilisateur> data = FXCollections.observableArrayList();
    @FXML
    private TextField search;
    @FXML
    private TextField search1;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
            UpdateTable ();
            UpdateTable1 ();
            search();
            search1();
            
    } 
    
    
    @FXML
    public void showdashboard(){
        dashboard.setVisible(true);
        apprenant.setVisible(false);
        formateur.setVisible(false);
        ajouterformateur.setVisible(false);
        
        
    } 
    @FXML
    public void showapprenant(){
        dashboard.setVisible(false);
        apprenant.setVisible(true);
        formateur.setVisible(false);
        ajouterformateur.setVisible(false);
        
        
    } 
    
    @FXML
    public void showformateur(){
        dashboard.setVisible(false);
        apprenant.setVisible(false);
        formateur.setVisible(true);
        ajouterformateur.setVisible(false);
        
        
    } 
    @FXML
    public void showajouter(){
        dashboard.setVisible(false);
        apprenant.setVisible(false);
        formateur.setVisible(false);
        ajouterformateur.setVisible(true);
        btnmodifierformateur.setVisible(false);
        btnajouterformateur.setVisible(true);
        
        
        
    } 
    @FXML
    public void showmodifier(){
        dashboard.setVisible(false);
        apprenant.setVisible(false);
        formateur.setVisible(false);
        ajouterformateur.setVisible(true);
        btnmodifierformateur.setVisible(true);
        btnajouterformateur.setVisible(false);
        
        
        
    } 
    
    
   public void UpdateTable (){
        
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
   
    public void UpdateTable1 (){
        
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
       
        cid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colemail1.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("email"));
        colpassword1.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("password"));
        colnom1.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("nom"));
        colprenom1.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("prenom"));
        coltelephone1.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("telephone"));
        coladresse1.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("adresse"));
        coldate_naissance1.setCellValueFactory(new PropertyValueFactory<Utilisateur, Date>("date_naissance"));
        tvformateur.setItems(data1);
    }
   
   
   void search() { 
     
     
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
   
   void search1() { 
     
     
       cid.setCellValueFactory(new PropertyValueFactory<>("id")); 
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
        u.setRole("ROLE_FORMATEUR");
        u.setNom(tnom);
        u.setPrenom(tprenom);
        u.setTelephone(ttelephone);
        u.setAdresse(tadresse);
        u.setDate_naissance(tdate_naissance);
        u.setEnable(true);
        ServiceUtilisateur uc= new ServiceUtilisateur();
        boolean result = uc.getByEmail(u);
         UpdateTable1 ();
         search1();
         id.setText("");
        email.setText("");
        password.setText("");
        nom.setText("");
        prenom.setText("");
        telephone.setText("");
        adresse.setText("");
        if (result){
             JOptionPane.showMessageDialog(null, "votre inscription ajouter avec succes");
        }
        else{
            JOptionPane.showMessageDialog(null, "le formateur est déja existe");
        }
}
  
         @FXML
    private void getSelected(MouseEvent event) {
        index = tvformateur.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        
        id.setText(cid.getCellData(index).toString());
        email.setText(colemail1.getCellData(index).toString());
        password.setText(colpassword1.getCellData(index).toString());
        nom.setText(colnom1.getCellData(index).toString());
        prenom.setText(colprenom1.getCellData(index).toString());
        telephone.setText(coltelephone1.getCellData(index).toString());
        adresse.setText(coladresse1.getCellData(index).toString());
        date_naissance.getEditor().setText(coldate_naissance1.getCellData(index).toString());
        
      
        
    
    }
    
    
    @FXML
    private void onUpdate() {

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
            UpdateTable1 ();
            search1();
        id.setText("");
        email.setText("");
        password.setText("");
        nom.setText("");
        prenom.setText("");
        telephone.setText("");
        adresse.setText("");
        date_naissance.getEditor().setText("");

            JOptionPane.showMessageDialog(null, "Update");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);
        }
    }
    
    
   
     @FXML
    private void supprimer(ActionEvent event) {
        Utilisateur u = tvformateur.getSelectionModel().getSelectedItem();
        ServiceUtilisateur su = new ServiceUtilisateur();
        su.supprimer(u);
        UpdateTable1();
        search1();
         
    }
   
    }
    

