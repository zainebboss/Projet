/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import java.sql.Date;
import java.sql.PreparedStatement;

/**
 *
 * @author Hp Omen
 */
public class Inscription {
    private int id;
    private int id_utilisateur;
    private int id_formation;
    private Date date_inscrit;
    

    public Inscription() {
    }

    public Inscription(int id, int id_utilisateur, int id_formation, Date date_inscrit) {
        this.id = id;
        this.id_utilisateur = id_utilisateur;
        this.id_formation = id_formation;
        this.date_inscrit = date_inscrit;
    }

    public int getId() {
        return id;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public int getId_formation() {
        return id_formation;
    }

    public Date getDate_inscrit() {
        return date_inscrit;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public void setId_formation(int id_formation) {
        this.id_formation = id_formation;
    }

    public void setDate_inscrit(Date date_inscrit) {
        this.date_inscrit = date_inscrit;
    }

    @Override
    public String toString() {
        return "Inscription{" + "id=" + id + ", id_utilisateur=" + id_utilisateur + ", id_formation=" + id_formation + ", date_inscrit=" + date_inscrit + '}';
    }
    
    
}
