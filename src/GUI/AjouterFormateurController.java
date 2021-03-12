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

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.ResultSet;

import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Connection;
import java.sql.Date;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;


/**
 *
 * @author user
 */
public class AjouterFormateurController implements Initializable {
    
    Connection cnx;
    
    int index=-1;
    
    private Label label;
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
    private TextField search;
    
    @FXML
    private TableView<Utilisateur> tvformateur;
    
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
    private TableColumn<Utilisateur, Date> coldate_naissance;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;
    
    ObservableList<Utilisateur> data = FXCollections.observableArrayList();
    
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
        email.setText("");
        password.setText("");
        nom.setText("");
        prenom.setText("");
        telephone.setText("");
        adresse.setText("");
        
        if (result){
             JOptionPane.showMessageDialog(null, "votre inscription ajouter avec succes");
             UpdateTable ();
             
            
        }
        else{
            JOptionPane.showMessageDialog(null, "l'utilisateur existe deja");
        }
    
        
        
        
    }
        
         @FXML
    private void onUpdate(ActionEvent event) {

        try {
            cnx = Connexion.getInstance().getConnection();
            String value1 = id.getText();
            String value2 = email.getText();
            String value3 = password.getText();
            String value4 = nom.getText();
            String value5=prenom.getText();
            String value6=telephone.getText();
            String value7=adresse.getText();
            java.util.Date date_naissance = Date.from(this.date_naissance.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            java.sql.Date tdate_naissance = new java.sql.Date(date_naissance.getTime());
            String value8=telephone.getText();

            String requete = "update utilisateur set email= '" + value2 + "',password= '" + value3 + "',nom= '" + value4 +"',prenom='"+value5+ "',telephone= '" + value6 +"',adresse='"+value7+ "',date_naissance='"+value8+ "'where id='" + value1 + "'";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.execute();

            JOptionPane.showMessageDialog(null, "Update");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    @Override //  
    public void initialize(URL url, ResourceBundle rb) {
        
        UpdateTable ();
        search();
        
    }    
    
    
    
      @FXML
    private void getSelected(MouseEvent event) {
        index = tvformateur.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        
        email.setText(colemail.getCellData(index).toString());
        nom.setText(colnom.getCellData(index).toString());
        prenom.setText(colprenom.getCellData(index).toString());
        telephone.setText(coltelephone.getCellData(index).toString());
        adresse.setText(coladresse.getCellData(index).toString());
        
      
        
    
    }
   
    void search() { 
     
     
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        coltelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        tvformateur.setItems(data);
             FilteredList<Utilisateur> filteredData = new FilteredList<>(data, b -> true);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(f -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (f.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } 
                if (f.getEmail().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } 
                if (f.getPrenom().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } 
                if (f.getTelephone().toLowerCase().contains(lowerCaseFilter)) {
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
    private void supprimer(ActionEvent event) {
        Utilisateur u = tvformateur.getSelectionModel().getSelectedItem();
        ServiceUtilisateur su = new ServiceUtilisateur();
        su.supprimer(u);
        UpdateTable();
         
    }
    
//     @FXML
//    private void OnDelete(ActionEvent event) {
//        
//        
//        
//         cnx = Connexion.getInstance().getConnection();
//    
//        try {
//            
//            Utilisateur u = tvformateur.getSelectionModel().getSelectedItem();
//             
//            String requete = "delete from utilisateur where id =? ";
//            PreparedStatement pst = cnx.prepareStatement(requete);
//          
//            pst.setInt(1,u.getId());
//
//             pst.executeUpdate();
//             UpdateTable();
//            
//            JOptionPane.showMessageDialog(null, "Delete");
//           // UpdateTable();
//            tvformateur.getItems().removeAll(tvformateur.getSelectionModel().getSelectedCells());
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Table est vide");
//        }
//
//       
//    }
    public void UpdateTable (){
        
        data.clear();
        try {
            cnx = Connexion.getInstance().getConnection();
            String requete = "SELECT * FROM utilisateur WHERE role='ROLE_FORMATEUR' order by id desc";
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
        coldate_naissance.setCellValueFactory(new PropertyValueFactory<Utilisateur, Date>("date_naissance"));
        tvformateur.setItems(data);
    }
   
    
}
