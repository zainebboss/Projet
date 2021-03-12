/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import java.sql.Date;


/**
 *
 * @author Hp Omen
 */
public class Utilisateur {
    private int id;
    private String email;
    private String password;
    private String role;
    private String nom;
    private String prenom;
    private String telephone;
    private String adresse;
    private Date date_naissance;
    private boolean enable;

    public Utilisateur() {
    }

   
    public Utilisateur(int id, String email, String password, String role, String nom, String prenom, String telephone, String adresse, Date date_naissance, boolean enable) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.adresse = adresse;
         this.date_naissance = date_naissance;
        this.enable = enable;
    }

    public Utilisateur(int id, String email, String password, String nom, String prenom, String telephone, String adresse, Date date_naissance) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.adresse = adresse;
        this.date_naissance = date_naissance;
    }

   
    

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getAdresse() {
        return adresse;
    }
    public Date getDate_naissance() {
        return date_naissance;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        String crypte="";
        for (int i=0; i<password.length();i++)  {
            int c=password.charAt(i)^48; 
            crypte=crypte+(char)c;
             
        }
        
     this.password=crypte;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }
    

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", email=" + email + ", password=" + password + ", role=" + role + ", nom=" + nom + ", prenom=" + prenom + ", telephone=" + telephone + ", adresse=" + adresse + ", date_naissance=" + date_naissance + ", enable=" + enable + '}';
    }

    
    
    
    
    
}
