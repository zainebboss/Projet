/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Catégorie;
import Entites.Cour;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class DetailController implements Initializable {

    @FXML
    private TextField tfTitre;
private Cour cour = new Cour();
    @FXML
    private TextField tfFicher;
    @FXML
    private TextField tfCategorie;
    @FXML
    private Button btn_back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
      public void initData(Cour recievedCour) {
        System.out.println("cour : " + recievedCour);
        if (recievedCour != null) {
            this.cour.setID(recievedCour.getID());
            this.cour.setFichier(recievedCour.getFichier());
             this.cour.setDescription_cat(recievedCour.getDescription_cat());
            this.cour.setTitre(recievedCour.getTitre());
            tfTitre.setText(recievedCour.getTitre());
                      tfFicher.setText(cour.getFichier());
                       tfCategorie.setText(cour.getDescription_cat());
        }

    }


//    @FXML
//    private void afficherAjouterCours(ActionEvent event)throws IOException {
//          Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        Parent root = FXMLLoader.load(getClass().getResource("CoursUI.fxml"));
//        Scene scene = new Scene(root);
//        primaryStage.setTitle("Ajouter Cour");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//        
//        
//    }
//
//    @FXML
//    private void afficherListerCours(ActionEvent event) throws IOException {
//        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        Parent root = FXMLLoader.load(getClass().getResource("ListCours.fxml"));
//        Scene scene = new Scene(root);
//        primaryStage.setTitle("Liste des cours");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//            
//    }
//
//    @FXML
//    private void afficherStatistiquesCours(ActionEvent event) {
//    }

    void initData(Catégorie catégorie) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
        if (event.getSource() == btn_back) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            // hnee badll
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/gui/ListCours_App.fxml")));
            stage.setScene(scene);
            stage.show();

        }
        
        
        
        
    }
    
}
