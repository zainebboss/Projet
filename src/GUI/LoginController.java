/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.Authentification;
import Services.Session;
import Utils.Connexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.ResultSet;

/**
 * FXML Controller class
 *
 * @author Hp Omen
 */
public class LoginController implements Initializable {
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    private Label affichage;
    private PreparedStatement stat;
    private ResultSet rs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    
    @FXML
    public void login(ActionEvent actionEvent) {

      String temail=email.getText();
      String tpassword=password.getText();

        try {
            Connection cnx = Connexion.getInstance().getConnection();
            stat = cnx.prepareStatement("SELECT * FROM utilisateur WHERE email =? AND password = ?");
            stat.setString(1,temail);
            stat.setString(2,tpassword);
            rs =stat.executeQuery();
            if (rs.next()){
                affichage.setText("Connected");
            }else {

                affichage.setText("Not Connected");
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
}
