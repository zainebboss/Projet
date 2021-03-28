/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.sparkAideCom;
import Entites.sparkAide;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.JOptionPane;
import Services.mysqlConnect;


public class FormateurController implements Initializable {

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
    
    ObservableList<sparkAide> listM;
    ObservableList<sparkAideCom> listmCom;
    Connection con =null ;
      
    PreparedStatement pst =null ;
    
     int index = -1;
     
     int selectedAide ;
     int selectedCom ;
    @FXML
    private AnchorPane imageV;
    @FXML
    private AnchorPane paneF;
    @FXML
    private void addCom() {
        
     con=mysqlConnect.Connectdb() ;
     String sql="insert into aidecom (commaintre,id_sujet) values(?,?)" ;
   
     
     try{
     
     
     pst =con.prepareStatement(sql);
     pst.setString(1,textF.getText());
     pst.setInt(2,this.selectedAide);
     pst.execute();
     
    JOptionPane.showMessageDialog(null,"aidecom add succes ");
     UpdateTable ();
   
 }catch(Exception e){
     JOptionPane.showMessageDialog(null,e);
 }
    }
    
    
      public void UpdateTable (){
        
        col_com.setCellValueFactory(new PropertyValueFactory<>("commaintre")  );
        
        
         listmCom = mysqlConnect.getDatasparkAideCom(this.selectedAide) ;
         
         if (listmCom.size()!=0){
             
         table_com.setItems(listmCom);
           
         }
         else {
                      table_com.setItems(null);

         } 
    }
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // UpdateTable ();
    }    

    
    
    
    @FXML
    private void affichage(ActionEvent event) {
        col_sujetF.setCellValueFactory(new PropertyValueFactory<>("sujet")  );
        col_problemeF.setCellValueFactory(new PropertyValueFactory<sparkAide,String>("probleme")  );
        col_mailF.setCellValueFactory(new PropertyValueFactory<sparkAide,String>("mail")  );
        
        listM = mysqlConnect.getDatasparkAide() ;
        table_aideF.setItems(listM);
    }

    @FXML
    private void suppCOM(ActionEvent event) {
          con = mysqlConnect.Connectdb();
    String sql = "delete from aidecom where id= ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, this.selectedCom);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Delete");
            UpdateTable() ;
           
  
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
    public void Edit (){   
        try {
           con = mysqlConnect.Connectdb();
         

            String sql = "update aidecom set commaintre= ? where id= ? ";
            pst= con.prepareStatement(sql);
            pst.setString(1,textF.getText() );
            pst.setInt(2, this.selectedCom);

            pst.execute();
            JOptionPane.showMessageDialog(null, "Update");

           UpdateTable ();
          
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }

    @FXML
    private void getSubject(MouseEvent event) {
       sparkAide com = table_aideF.getSelectionModel().getSelectedItem();
        this.selectedAide = com.getId();   
             UpdateTable ();
    
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
    private void pdf(MouseEvent event) {
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

            if (job.showPrintDialog(this.paneF.getScene().getWindow())) {
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


