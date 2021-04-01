/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;


public class Model {
    int id,duree,prix;
    String date,titre,description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }
    public int getDescription() {
        return duree;
    }

    public void setDescription(String description) {
        this.duree = duree;
    }

    public Model(int id, int prix, String date, String titre, int duree) {
        this.id = id;
        this.prix = prix;
        this.date = date;
        this.titre = titre;
        this.duree = duree;
    }

    public Model() {
    }

    @Override
    public String toString() {
        return "Model{" + "id=" + id + ", duree=" + duree + ", prix=" + prix + ", date=" + date + ", titre=" + titre + ", description=" + description + '}';
    }
    
    
}
