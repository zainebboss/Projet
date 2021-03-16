/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Hp Omen
 */
public class AvisController implements Initializable {

    @FXML
    private HBox hinit;
    @FXML
    private HBox hangry;
    @FXML
    private HBox hnothappy;
    @FXML
    private HBox hhappy;
    
    @FXML
    public void showHboxangry(){
        hangry.setVisible(true);
        hnothappy.setVisible(false);
        hhappy.setVisible(false);
        hinit.setVisible(false);
    }
    @FXML
    public void showHboxnothappy(){
        hangry.setVisible(false);
        hnothappy.setVisible(true);
        hhappy.setVisible(false);
        hinit.setVisible(false);
    }
    @FXML
    public void showHboxhappy(){
        hangry.setVisible(false);
        hnothappy.setVisible(false);
        hhappy.setVisible(true);
        hinit.setVisible(false);
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
