/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import static java.util.Collections.list;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author user
 */
public class Cour {
    private int ID;

  private  String titre;
  private  String Description_cat;
  
   private String fichier;
   private int formation_id;

 


    public Cour() {
    }

   
    
    
    
    
    public Cour(String titre, String fichier) {
        this.titre = titre;
        this.fichier = fichier;
    }
    public Cour(int ID, String titre, String fichier) {
        this.ID = ID;
        this.titre = titre;
        this.fichier = fichier;
    }

    public Cour(int ID, String titre, String Description_cat, String fichier) {
        this.ID = ID;
        this.titre = titre;
        this.Description_cat = Description_cat;
        this.fichier = fichier;
    }

    public Cour(String titre, String Description_cat, String fichier, int formation_id) {
        this.titre = titre;
        this.Description_cat = Description_cat;
        this.fichier = fichier;
        this.formation_id = formation_id;
    }

    public Cour(int ID, String titre, String Description_cat, String fichier, int formation_id) {
        this.ID = ID;
        this.titre = titre;
        this.Description_cat = Description_cat;
        this.fichier = fichier;
        this.formation_id = formation_id;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription_cat() {
        return Description_cat;
    }

    public void setDescription_cat(String Description_cat) {
        this.Description_cat = Description_cat;
    }

    public String getFichier() {
        return fichier;
    }

    public void setFichier(String fichier) {
        this.fichier = fichier;
    }

    public int getFormation_id() {
        return formation_id;
    }

    public void setFormation_id(int formation_id) {
        this.formation_id = formation_id;
    }

    @Override
    public String toString() {
        return "Cour{" + "ID=" + ID + ", titre=" + titre + ", Description_cat=" + Description_cat + ", fichier=" + fichier + ", formation_id=" + formation_id + '}';
    }
    

 

    
    
}
 //"select titre from cour where id_formation in(select id_formation from formation where id_formation = '"+f.getId_formation+"')"//