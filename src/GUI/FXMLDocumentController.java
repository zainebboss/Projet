/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.sparkAide;
import java.awt.event.ActionEvent;
import Services.mysqlConnect;
import Services.mysqlConnect;
import Services.mysqlConnect;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;



public class FXMLDocumentController implements Initializable {
    
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
    
//    @FXML
//    private Button go;
    
    
    @FXML
    private TextField filterAide;
 ObservableList<sparkAide> listM;
 ObservableList<sparkAide> dataList;
 
    
 int index = -1;
 Connection con =null ;
 
 ResultSet rs = null ;
 PreparedStatement pst =null ;
    @FXML
    private WebView ViewWeb;
    @FXML
    private TextField text_mail;
    @FXML
    private TableColumn<sparkAide, String> col_mail;
 
 
 
 
 


 @FXML
 public void addaide (){
     con=mysqlConnect.Connectdb() ;
     String sql="insert into aide (sujet,probleme,mail)values(?,?,?)" ;
 try{
     pst =con.prepareStatement(sql);
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
           con = mysqlConnect.Connectdb();
            String value1 = text_s.getText();
            String value2 = text_p.getText();
            String value3 = text_mail.getText();
          
            String sql = "update aide set sujet= '"+value1+"',probleme= '"+value2+"',mail= '"+value3+"' where sujet= '"+value1+"' ";
            pst= con.prepareStatement(sql);
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
    con = mysqlConnect.Connectdb();
    String sql = "delete from aide where sujet= ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, text_s.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Delete");
            UpdateTable() ;
            search_aide();
  
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    
    }
    
    public void UpdateTable (){
        
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
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
         
         UpdateTable() ;
         search_aide();
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
    

    
    

}
