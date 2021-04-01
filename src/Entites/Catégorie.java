/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

/**
 *
 * @author user
 */
public class Catégorie {
    int id_catégoerie;
    String Description;
    Cour cour;

    public Catégorie(int id_catégoerie, String Description) {
        this.id_catégoerie = id_catégoerie;
        this.Description = Description;
    }

    public Catégorie() {
       
    }

    public int getId_catégoerie() {
        return id_catégoerie;
    }

    public String getDescription() {
        return Description;
    }

    public void setId_catégoerie(int id_catégoerie) {
        this.id_catégoerie = id_catégoerie;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
    
    
     @Override
    public String toString() {
        return "Catégorie{" + "id_catégorie=" + id_catégoerie + ", Description=" + Description +  '}';
    }
}
