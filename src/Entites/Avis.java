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
    private int formateur_id;
    private int apprenant_id;
    private int note;

    public Avis() {
    }

    public Avis(int id, int formateur_id, int apprenant_id, int note) {
        this.id = id;
        this.formateur_id = formateur_id;
        this.apprenant_id = apprenant_id;
        this.note = note;
    }
    

    public int getId() {
        return id;
    }

    public int Getformateur_id() {
        return formateur_id;
    }

    public int Getapprenant_id() {
        return apprenant_id;
    }

    public int getNote() {
        return note;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_formateur(int formateur_id) {
        this.formateur_id = apprenant_id;
    }

    public void setId_apprenant(int apprenant_id) {
        this.apprenant_id = apprenant_id;
    }

    public void setNote(int note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Avis{" + "id=" + id + ", id_formateur=" + apprenant_id + ", id_apprenant=" + apprenant_id + ", note=" + note + '}';
    }
    
    
}
