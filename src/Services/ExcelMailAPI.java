/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Utils.Connexion;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

/**
 *
 * @author Hp Omen
 */


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import static java.lang.String.valueOf;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.swing.JOptionPane;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelMailAPI {

    public static void excel ()throws IOException {

        try {
        
            // *** for Database Connected ***//
            Connection cnx = null;
            Statement s = null;

            cnx = Connexion.getInstance().getConnection();
            
  

            s = cnx.createStatement();

            String sql = "SELECT * from utilisateur";

            ResultSet rec = s.executeQuery(sql);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            WritableWorkbook workbook = Workbook.createWorkbook(baos);

            // *** Create Font ***//
            WritableFont fontBlue = new WritableFont(WritableFont.TIMES, 10);
            fontBlue.setColour(Colour.BLUE);

            WritableFont fontRed = new WritableFont(WritableFont.TIMES, 10);
            fontRed.setColour(Colour.RED);

            // *** Sheet 1 ***//
            WritableSheet ws1 = workbook.createSheet("mySheet1", 0);

            // *** Header ***//
            WritableCellFormat cellFormat1 = new WritableCellFormat(fontRed);
            // cellFormat2.setBackground(Colour.ORANGE);
            cellFormat1.setAlignment(Alignment.CENTRE);
            cellFormat1.setVerticalAlignment(VerticalAlignment.CENTRE);
            cellFormat1.setBorder(Border.ALL, BorderLineStyle.THIN);

            // *** Data ***//
            WritableCellFormat cellFormat2 = new WritableCellFormat(fontBlue);
            // cellFormat2.setWrap(true);
            cellFormat2.setAlignment(jxl.format.Alignment.CENTRE);
            cellFormat2.setVerticalAlignment(VerticalAlignment.CENTRE);
            cellFormat2.setWrap(true);
            cellFormat2.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.HAIR, jxl.format.Colour.BLACK);

            ws1.mergeCells(0, 0, 5, 0);
            Label lable = new Label(0, 0, "This is your bill", cellFormat1);
            ws1.addCell(lable);

            // *** Header ***//
            ws1.setColumnView(0, 10); // Column ID
            ws1.addCell(new Label(0, 1, "id", cellFormat1));

            ws1.setColumnView(1, 15); // Column Email
            ws1.addCell(new Label(1, 1, "email", cellFormat1));

            ws1.setColumnView(2, 25); // Column Password
            ws1.addCell(new Label(2, 1, "password", cellFormat1));
            ws1.setColumnView(3, 25); // Column Role
            ws1.addCell(new Label(3, 1, "role", cellFormat1));

            ws1.setColumnView(4, 12); // Column Nom
            ws1.addCell(new Label(4, 1, "nom", cellFormat1));

            ws1.setColumnView(5, 25); // Prenom
            ws1.addCell(new Label(5, 1, "prenom", cellFormat1));
            
            ws1.setColumnView(6, 25); // telephone
            ws1.addCell(new Label(6, 1, "telephone", cellFormat1));
            
            ws1.setColumnView(7, 25); // adresse
            ws1.addCell(new Label(7, 1, "adresse", cellFormat1));
            
            ws1.setColumnView(8, 25); // date de naissance
            ws1.addCell(new Label(8, 1, "date_naissance", cellFormat1));
            
            ws1.setColumnView(9, 25); // enable
            ws1.addCell(new Label(9, 1, "enable", cellFormat1));

            

            int iRows = 2;
            while ((rec != null) && (rec.next())) {
              
              
                ws1.addCell(new Label(0, iRows, rec.getString("id"), cellFormat2));
                ws1.addCell(new Label(1, iRows, rec.getString("email"), cellFormat2));
                ws1.addCell(new Label(2, iRows, rec.getString("password"), cellFormat2));
                ws1.addCell(new Label(3, iRows, rec.getString("role"), cellFormat2));
                ws1.addCell(new Label(4, iRows, rec.getString("nom"), cellFormat2));
                ws1.addCell(new Label(5, iRows, rec.getString("prenom"), cellFormat2));
                ws1.addCell(new Label(6, iRows, rec.getString("telephone"), cellFormat2));
                ws1.addCell(new Label(7, iRows, rec.getString("adresse"), cellFormat2));
                ws1.addCell(new Label(8, iRows, rec.getString("date_naissance"), cellFormat2));
                ws1.addCell(new Label(9, iRows, rec.getString("enable"), cellFormat2));
               
                ++iRows;
            }

            workbook.write();
            workbook.close();

            System.out.println("Excel file created.");

            // Close
            try {
                if (cnx != null) {
                    s.close();
                    cnx.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            sendMail(baos);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void sendMail(ByteArrayOutputStream baos) throws AddressException, MessagingException {

        final String username = "sparkformation2021@gmail.com";
        final String password = "spark2021";

        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("protocol", "smtp");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("zaineb.boussaid@esprit.tn"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("zaineb.boussaid@esprit.tn"));
        message.setSubject("Testing Subject");
        message.setText("PFA");

        MimeBodyPart messageBodyPart = new MimeBodyPart();

        Multipart multipart = new MimeMultipart();

        messageBodyPart = new MimeBodyPart();

        String fileName = "utilisateur.xls";
        DataSource aAttachment = new ByteArrayDataSource(baos.toByteArray(), "application/octet-stream");
        messageBodyPart.setDataHandler(new DataHandler(aAttachment));
        messageBodyPart.setFileName(fileName);
        multipart.addBodyPart(messageBodyPart);

        message.setContent(multipart);

        System.out.println("Votre fichier excel est envoyé");
        JOptionPane.showMessageDialog(null, "Votre fichier excel est envoyé");

        Transport.send(message);

        System.out.println("Done");
    }

}

    

