/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

/**
 *
 * @author nidha
 */
public class sparkAide {
    
 int id ;
String sujet,probleme ;
String mail ;

    public sparkAide(int id ,String sujet, String probleme,String mail) {
        this.id=id ;
        this.sujet = sujet;
        this.probleme = probleme;
        this.mail=mail ;
    }

    public int getId() {
        return id;
    }

    public String getSujet() {
        return sujet;
    }

    public String getProbleme() {
        return probleme;
    }

    public String getMail() {
        return mail;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public void setProbleme(String probleme) {
        this.probleme = probleme;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "sparkAide{" + "id=" + id + ", sujet=" + sujet + ", probleme=" + probleme + ", mail=" + mail + '}';
    }
 

    
  

 
 
    
    
    
}

