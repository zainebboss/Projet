/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;



/**
 *
 * @author Hp Omen
 */
public class Avis {
    private int id;
    private int id_formateur;
    private int id_apprenant;
    private int note;

    public Avis() {
    }

    public int getId() {
        return id;
    }

    public int getId_formateur() {
        return id_formateur;
    }

    public int getId_apprenant() {
        return id_apprenant;
    }

    public int getNote() {
        return note;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_formateur(int id_formateur) {
        this.id_formateur = id_formateur;
    }

    public void setId_apprenant(int id_apprenant) {
        this.id_apprenant = id_apprenant;
    }

    public void setNote(int note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Avis{" + "id=" + id + ", id_formateur=" + id_formateur + ", id_apprenant=" + id_apprenant + ", note=" + note + '}';
    }
    
    
}
