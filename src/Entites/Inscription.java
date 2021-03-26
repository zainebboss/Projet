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
    private int utilisateur_id;
    private int formation_id;
    private Date date_inscrit;
    

    public Inscription() {
    }

    public Inscription(int id, int utilisateur_id, int formation_id, Date date_inscrit) {
        this.id = id;
        this.utilisateur_id = utilisateur_id;
        this.formation_id = formation_id;
        this.date_inscrit = date_inscrit;
    }

    public int getId() {
        return id;
    }

    public int getId_utilisateur() {
        return utilisateur_id;
    }

    public int getId_formation() {
        return formation_id;
    }

    public Date getDate_inscrit() {
        return date_inscrit;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_utilisateur(int utilisateur_id) {
        this.utilisateur_id = utilisateur_id;
    }

    public void setId_formation(int formation_id) {
        this.formation_id = formation_id;
    }

    public void setDate_inscrit(Date date_inscrit) {
        this.date_inscrit = date_inscrit;
    }

    @Override
    public String toString() {
        return "Inscription{" + "id=" + id + ", id_utilisateur=" + utilisateur_id + ", id_formation=" + formation_id + ", date_inscrit=" + date_inscrit + '}';
    }
    
    
}
