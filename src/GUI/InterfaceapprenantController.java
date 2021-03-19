/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hp Omen
 */
public class InterfaceapprenantController implements Initializable {
    Connection cnx;
    
     @FXML
    private TableView<Utilisateur> tvapprenant;
    
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void UpdateTable (){
        
        data.clear();
        try {
            cnx = Connexion.getInstance().getConnection();
            String requete = "SELECT * FROM utilisateur order by id desc";
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
        tvapprenant.setItems(data);
    }
   
   
}
